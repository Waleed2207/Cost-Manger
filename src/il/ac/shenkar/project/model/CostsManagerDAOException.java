package il.ac.shenkar.project.model;

    /**
     * Custom exception class for the project.
     */

public class CostsManagerDAOException extends Exception {

    /**
     * Constructs a new exception with the specified detail message.
     *
     * @param message The detail message (which is saved for later retrieval by the getMessage() method).
     */
    public CostsManagerDAOException(String message) {
        super(message);
    }

    /**
     * Constructs a new exception with the specified detail message and cause.
     *
     * @param message The detail message (which is saved for later retrieval by the getMessage() method).
     * @param cause   The cause (which is saved for later retrieval by the getCause() method). (A null value is permitted, and indicates that the cause is nonexistent or unknown.)
     */
    public CostsManagerDAOException(String message, Throwable cause) {
        super(message, cause);
    }
}
