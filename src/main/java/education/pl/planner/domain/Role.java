package education.pl.planner.domain;

import lombok.Getter;

@Getter
public enum Role {

    USER("USER"),
    ADMIN("ADMIN");

    public final String name;

    Role(String name) {
        this.name = name;
    }
}
