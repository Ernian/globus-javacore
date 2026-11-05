package org.example;

import org.example.resource.Connection;
import org.example.resource.File;
import org.example.resource.Session;

public class App {
    public static void main(String[] args) {
        Logger logger = new Logger("src/main/java/org/example/resource/app.log");

        try (Connection connection = new Connection(logger);
             File file = new File(logger);
             Session session = new Session(logger)) {
            throw new RuntimeException("Main exception");
        } catch (Exception e) {
            logger.error(e);
        }
    }
}
