package backend.enums;

import backend.interfaces.Type;

/**
 * Possible types of a function.
 */
public enum FuncType implements Type {
    BOOLEAN("boolean", "bool"),
    CHAR("char", "char"),
    STRING("String", "std::string"),
    INT("int", "int"),
    DOUBLE("double", "double"),
    VOID("void", "void");

    private final String java;
    private final String cpp;

    /**
     * Constructor for FuncType enum.
     * @param java transformed type, according to Java syntax
     * @param cpp transformed type, according to C++ syntax
     */
    FuncType(final String java, final String cpp) {
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
