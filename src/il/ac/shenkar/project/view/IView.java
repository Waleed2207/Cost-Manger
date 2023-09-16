package il.ac.shenkar.project.view;
import il.ac.shenkar.project.model.Category;
import il.ac.shenkar.project.model.Cost;
import il.ac.shenkar.project.viewmodel.IViewModel;
import java.util.List;

    /**
     * The View interface defines methods for the user interface to interact with the application.
     */
public interface IView {
    /**
     * Sets the ViewModel reference for the view.
     *
     * @param vm The ViewModel instance to set.
     */
    void setViewModel(IViewModel vm);

    /**
     * Starts the user interface.
     */
    void start();

    /**
     * Sets the list of costs in the view.
     *
     * @param costs The list of costs to set.
     */
    void setCosts(List<Cost> costs);

    /**
     * Sets the list of categories in the view.
     *
     * @param categories The list of categories to set.
     */
    void setCategories(List<Category> categories);

    /**
     * Synchronizes the categories in the view with the database.
     */
    void syncCategories();

    /**
     * Displays a message to the user.
     *
     * @param msg      The message to display.
     * @param type     The type of message (e.g., information, warning).
     * @param msgType  The message type (e.g., dialog, notification).
     */
    void displayMSG(String msg, String type, int msgType);
}
