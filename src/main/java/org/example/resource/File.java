package org.example.resource;

import org.example.Logger;

public class File extends Resource {
    public File(Logger logger) {
        super(logger);
    }

    @Override
    public void close() throws Exception {
        logger.info("File closed");
        throw new RuntimeException("Exception from File");
    }
}