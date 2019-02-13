package ifit.cluster.cassistant.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

public enum Status {
    NEW("new"),
    IN_PROGRESS("in progress"),
    ANSWERED("answered"),
    REMOVED("removed");

    @JsonIgnore
    private final String name;

    Status(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
