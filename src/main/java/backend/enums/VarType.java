package backend.enums;

import backend.interfaces.Type;

/**
 * Possible types of a variable.
 */
public enum VarType implements Type {
    BOOLEAN("boolean", "bool"),
    CHAR("char", "char"),
    STRING("String", "std::string"),
    INT("int", "int"),
    DOUBLE("double", "double");

    private final String java;
    private final String cpp;

    /**
     * Constructor for VarType enum.
     * @param java transformed type, according to Java syntax
     * @param cpp transformed type, according to C++ syntax
     */
    VarType(final String java, final String cpp) {
        this.java = java;
        this.cpp = cpp;
    }

    @Override
    public String toString() {
        return name().toLowerCase();
    }

    public String getJava() {
        return java;
    }

    public String getCpp() {
        return cpp;
    }
}
