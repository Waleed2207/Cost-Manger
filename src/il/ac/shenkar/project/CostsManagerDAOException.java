package il.ac.shenkar.project;

/**
 * exception class for the project
 */
public class CostsManagerDAOException extends Exception{
    /**
     * constructor with only message
     * @param message
     */
    public CostsManagerDAOException(String message) {
        super(message);
    }

    /**
     * constructor with message and cause
     * @param message
     * @param cause
     */
    public CostsManagerDAOException(String message, Throwable cause) {
        super(message, cause);
    }
}
