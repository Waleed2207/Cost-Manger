package il.ac.shenkar.project.viewmodel;
import il.ac.shenkar.project.CostsManagerDAOException;
import il.ac.shenkar.project.model.Category;
import il.ac.shenkar.project.model.Cost;
import il.ac.shenkar.project.model.IModel;
import il.ac.shenkar.project.view.IView;
import javax.swing.*;
import java.sql.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ViewModel implementation
 */
public class ViewModel implements IViewModel {
    private IModel model; // Model reference
    private IView view;   // View reference
    private ExecutorService service; // Executor service to run tasks in separate threads

    /**
     * Constructor to create a fixed thread pool
     */
    public ViewModel() {
        service = Executors.newFixedThreadPool(8);
    }

    /**
     * Set the model.
     *
     * @param model The model to set.
     */
    @Override
    public void setModel(IModel model) {
        this.model = model;
    }

    /**
     * Set the view.
     *
     * @param view The view to set.
     */
    @Override
    public void setView(IView view) {
        this.view = view;
    }

    /**
     * Call the model to add a category.
     *
     * @param name The name of the category to add.
     */
    @Override
    public void addCategory(String name) {
        // Submit a thread to handle adding a category
        service.submit(() -> {
            try {
                model.addCategory(name);
                view.syncCategories();
                view.displayMSG("The category has been added successfully.", "INFO", JOptionPane.INFORMATION_MESSAGE);
            } catch (CostsManagerDAOException e) {
                e.printStackTrace();
                view.displayMSG("Error adding category. Make sure that the category doesn't already exist.", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    /**
     * Call the model to add a cost.
     *
     * @param sum          The cost sum to add.
     * @param currency     The currency of the cost.
     * @param categoryName The name of the category for the cost.
     * @param description  The description of the cost.
     * @param date         The date of the cost.
     */
    @Override
    public void addCost(float sum, String currency, String categoryName, String description, Date date) {
        // Submit a thread to handle adding a cost
        service.submit(() -> {
            try {
                model.addCost(sum, currency, categoryName, description, date);
                // Use SwingUtilities.invokeLater to update the UI on the EDT
                SwingUtilities.invokeLater(() -> {
                    view.displayMSG("The cost has been added successfully.", "INFO", JOptionPane.INFORMATION_MESSAGE);
                });
            } catch (CostsManagerDAOException e) {
                e.printStackTrace();
                // Use SwingUtilities.invokeLater to update the UI on the EDT
                SwingUtilities.invokeLater(() -> {
                    view.displayMSG("Error adding cost. Try again!", "ERROR", JOptionPane.ERROR_MESSAGE);
                });
            }
        });

    }

    /**
     * Call the model to get costs within a specified date range.
     *
     * @param start The start date of the range.
     * @param end   The end date of the range.
     */
    @Override
    public void getCosts(Date start, Date end) {
        // Submit a thread to get all costs
        service.submit(() -> {
            try {
                List<Cost> list = model.getCosts(start, end);
                // Use SwingUtilities.invokeLater to update the UI on the EDT
                SwingUtilities.invokeLater(() -> {
                    view.setCosts(list);
                });
            } catch (CostsManagerDAOException e) {
                e.printStackTrace();

                // Use SwingUtilities.invokeLater to display an error message in the UI on the EDT
                SwingUtilities.invokeLater(() -> {
                    view.displayMSG("Error getting costs. Try again!", "ERROR", JOptionPane.ERROR_MESSAGE);
                });
            }
        });

    }

    /**
     * Call the model to get categories.
     */
    @Override
    public void getCategories() {
        // Submit a thread to handle getting all categories
        service.submit(() -> {
            try {
                List<Category> list = model.getCategories();
                // Use SwingUtilities.invokeLater to update the UI on the EDT
                SwingUtilities.invokeLater(() -> {
                    view.setCategories(list);
                });

            } catch (CostsManagerDAOException e) {
                e.printStackTrace();

                // Use SwingUtilities.invokeLater to display an error message in the UI on the EDT
                SwingUtilities.invokeLater(() -> {
                    view.displayMSG("Error getting categories. Try again!", "ERROR", JOptionPane.ERROR_MESSAGE);
                });
            }
        });

    }
}
