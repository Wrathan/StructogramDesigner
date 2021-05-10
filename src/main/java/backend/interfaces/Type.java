package backend.interfaces;

/**
 * Interface for structogram object types.
 */
public interface Type {

    /**
     * Overridden toString() method.
     * @return lowercase string representation of type
     */
    @Override
    String toString();

    /**
     * Transform the type to Java syntax.
     * @return transformed type
     */
    String getJava();

    /**
     * Transform the type to C++ syntax.
     * @return transformed type
     */
    String getCpp();
}
