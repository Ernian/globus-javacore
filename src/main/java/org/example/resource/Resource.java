package org.example.resource;

import org.example.Logger;

public abstract class Resource implements AutoCloseable {
    protected final Logger logger;

    public Resource(Logger logger) {
        this.logger = logger;
    }
}
