package ifit.cluster.cassistant.domain;

public enum Status {
    NEW("new"),
    IN_PROGRESS("in progress"),
    ANSWERED("answered"),
    REMOVED("removed");

    private final String name;

    Status(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
