package org.example.resource;

import org.example.Logger;

public class Connection extends Resource {
    public Connection(Logger logger) {
        super(logger);
    }

    @Override
    public void close() throws Exception {
        logger.info("Connection closed");
    }
}