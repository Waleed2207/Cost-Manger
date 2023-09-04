package il.ac.shenkar.project.view;

import il.ac.shenkar.project.model.Category;
import il.ac.shenkar.project.model.Cost;
import il.ac.shenkar.project.viewmodel.IViewModel;

import java.util.List;

/**
 * View interface to allow main and viewmodel to interact with the view.
 */
public interface IView {
    /**
     * Sets the viewmodel reference.
     *
     * @param vm The ViewModel instance to set.
     */
    void setViewModel(IViewModel vm);

    /**
     * Starts the interface.
     */
    void start();

    /**
     * Sets the costs in the view.
     *
     * @param costs The list of costs to set.
     */
    void setCosts(List<Cost> costs);

    /**
     * Sets the categories in the view.
     *
     * @param categories The list of categories to set.
     */
    void setCategories(List<Category> categories);

    /**
     * Syncs the categories in the view with the database.
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
