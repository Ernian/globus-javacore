package org.example.resource;

import org.example.Logger;

public class Session extends Resource {
    public Session(Logger logger) {
        super(logger);
    }

    @Override
    public void close() throws Exception {
        logger.info("Session closed");
        throw new RuntimeException("Exception from Session");
    }
}