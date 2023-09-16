package il.ac.shenkar.project.model;
import java.sql.Date;
import java.util.List;

    /**
     * Model interface for interacting with the Costs Manager.
     */
public interface IModel {
    /**
     * Get a list of categories from the data access object (DAO).
     *
     * @return A list of categories.
     * @throws CostsManagerDAOException If there is an error retrieving the categories.
     */
    List<Category> getCategories() throws CostsManagerDAOException;

    /**
     * Add a new category to the data access object (DAO).
     *
     * @param name The name of the category to add.
     * @throws CostsManagerDAOException If there is an error adding the category.
     */
    void addCategory(String name) throws CostsManagerDAOException;

    /**
     * Get a list of costs within a specified date range from the data access object (DAO).
     *
     * @param start The start date of the range.
     * @param end   The end date of the range.
     * @return A list of costs within the specified date range.
     * @throws CostsManagerDAOException If there is an error retrieving the costs.
     */
    List<Cost> getCosts(Date start, Date end) throws CostsManagerDAOException;

    /**
     * Add a new cost to the data access object (DAO).
     *
     * @param sum          The cost amount.
     * @param currency     The currency of the cost.
     * @param categoryName The name of the category for the cost.
     * @param description  The description of the cost.
     * @param date         The date of the cost.
     * @throws CostsManagerDAOException If there is an error adding the cost.
     */
    void addCost(float sum, String currency, String categoryName, String description, Date date) throws CostsManagerDAOException;
}
