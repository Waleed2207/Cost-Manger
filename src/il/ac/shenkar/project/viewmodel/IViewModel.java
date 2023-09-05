package il.ac.shenkar.project.viewmodel;

import il.ac.shenkar.project.model.IModel;
import il.ac.shenkar.project.view.IView;

import java.sql.Date;

/**
 * ViewModel interface to facilitate communication between the view and model.
 */
public interface IViewModel {
    /**
     * Sets the model for the ViewModel.
     *
     * @param model The model to set.
     */
    void setModel(IModel model);

    /**
     * Sets the view for the ViewModel.
     *
     * @param view The view to set.
     */
    void setView(IView view);

    /**
     * Calls the method to add a category in the model.
     *
     * @param name The name of the category to add.
     */
    void addCategory(String name);

    /**
     * Calls the method to add a cost in the model.
     *
     * @param sum          The cost sum to add.
     * @param currency     The currency of the cost.
     * @param categoryName The name of the category for the cost.
     * @param description  The description of the cost.
     * @param date         The date of the cost.
     */
    void addCost(float sum, String currency, String categoryName, String description, Date date);

    /**
     * Calls the method to get costs from the model within a specified date range.
     *
     * @param start The start date of the range.
     * @param end   The end date of the range.
     */
    void getCosts(Date start, Date end);

    /**
     * Calls the method to get categories from the model.
     */
    void getCategories();
}
