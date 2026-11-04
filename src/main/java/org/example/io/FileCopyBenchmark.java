package org.example.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

public class FileCopyBenchmark {
    private final int BUFFER_SIZE;

    public FileCopyBenchmark() {
        this.BUFFER_SIZE = 8192;
    }

    public FileCopyBenchmark(int bufferKB) {
        this.BUFFER_SIZE = bufferKB * 1024;
    }

    public static void main() throws IOException {
        FileCopyBenchmark fileCopyBenchmark = new FileCopyBenchmark();
        String original = "src/main/java/org/example/io/original.bin";
        String copyWithoutBuffer = "src/main/java/org/example/io/copyWithoutBuffer.bin";
        String copyWithBuffer = "src/main/java/org/example/io/copyWithBuffer.bin";

        fileCopyBenchmark.generateTestFile(original,5);

        long timeWithoutBuffer = fileCopyBenchmark.makeMeasure(() ->
                fileCopyBenchmark.copyWithoutBuffer(original, copyWithoutBuffer)
        );

        long timeWithBuffer = fileCopyBenchmark.makeMeasure(() ->
                fileCopyBenchmark.copyWithBuffer(original, copyWithBuffer)
        );

        System.out.println("timeWithoutBuffer: " + timeWithoutBuffer / 1000_000 + " ms");
        System.out.println("timeWithBuffer: " + timeWithBuffer / 1000_000 + " ms");
        System.out.printf("timeWithoutBuffer/timeWithBuffer = %.1f", 1.0 * timeWithoutBuffer / timeWithBuffer);

        Files.deleteIfExists(Paths.get(original));
        Files.deleteIfExists(Paths.get(copyWithoutBuffer));
        Files.deleteIfExists(Paths.get(copyWithBuffer));
    }

    public void copyWithoutBuffer(String src, String dest) {
        try (var in = new FileInputStream(src);
             var out = new FileOutputStream(dest)) {

            int data;

            while ((data = in.read()) != -1) {
                out.write(data);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void copyWithBuffer(String src, String dest) {
        try (var in = new BufferedInputStream(new FileInputStream(src), BUFFER_SIZE);
             var out = new BufferedOutputStream(new FileOutputStream(dest), BUFFER_SIZE)) {

            int data;

            while ((data = in.read()) != -1) {
                out.write(data);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void generateTestFile(String path, int sizeMB) {
        Path source = Paths.get(path);
        byte[] chunk = new byte[1024 * 1024];
        Random random = new Random();
        random.nextBytes(chunk);

        try (OutputStream out = new FileOutputStream(source.toFile())) {
            for (int i = 0; i < sizeMB; i++) {
                out.write(chunk);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public long makeMeasure(Runnable task) {
        long start = System.nanoTime();
        task.run();

        return System.nanoTime() - start;
    }
}
