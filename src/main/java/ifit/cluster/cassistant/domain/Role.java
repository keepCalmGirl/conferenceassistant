package ifit.cluster.cassistant.domain;

public enum Role {
    ADMIN("Administrator"),
    MODERATOR("Moderator"),
    SPEAKER("Speaker"),
    USER("User");

    private final String name;

    Role(String name) {
        this.name = name;
    }
}
