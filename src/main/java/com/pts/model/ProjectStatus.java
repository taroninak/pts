package com.pts.model;

/**
 * Created by taronpetrosyan on 8/15/16.
 */
public enum ProjectStatus {
    NEW("NEW"),
    ACTIVE("ACTIVE"),
    ONGOING("ONGOING"),
    CLOSED("CLOSED");

    private String type;

    ProjectStatus(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return this.type;
    }

}
