package backend.enums;

/**
 * Possible programming language.
 */
public enum Language {
    JAVA ("java"),
    CPP ("c++"),
    PYTHON ("python");

    private final String name;

    /**
     * Constructor for Language enum.
     * @param name programming language name
     */
    Language(String name) {
        this.name = name;
    }

    /**
     * Gets programming language name.
     * @return current name
     */
    public String getName() {
        return name;
    }
}
