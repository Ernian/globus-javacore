package org.example;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    private final Path path;
    private final DateTimeFormatter formatter;


    public Logger(String uri) {
        path = Paths.get(uri);
        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        if (!Files.exists(path)) {
            throw new RuntimeException("File %s does not exist".formatted(uri));
        }
    }

    public void info(String message) {
        String timestamp = LocalDateTime.now().format(formatter);
        String line = "[" + timestamp + "] INFO " + message + "\n";

        try {
            Files.writeString(
                    path,
                    line,
                    StandardCharsets.UTF_8,
                    StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void error(Exception exception) {
        String timestamp = LocalDateTime.now().format(formatter);
        String line = "[" + timestamp + "] ERROR " + exception.getMessage() + "\n";

        try {
            Files.writeString(
                    path,
                    line,
                    StandardCharsets.UTF_8,
                    StandardOpenOption.APPEND);
            Throwable[] suppressed = exception.getSuppressed();
            for (var ex : suppressed) {
                String suppressedLine = "[" + timestamp + "] SUPPRESSED ERROR " + ex.getMessage() + "\n";
                Files.writeString(
                        path,
                        suppressedLine,
                        StandardCharsets.UTF_8,
                        StandardOpenOption.APPEND);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}