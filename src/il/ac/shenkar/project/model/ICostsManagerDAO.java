package il.ac.shenkar.project.model;
import java.sql.Date;
import java.util.List;

/**
 * Interface for the Costs Manager Data Access Object (DAO).
 */
public interface ICostsManagerDAO {
    /**
     * Add a new category to the database.
     *
     * @param category The category to add.
     * @throws CostsManagerDAOException If there is an error adding the category.
     */
    void addCategory(Category category) throws CostsManagerDAOException;

    /**
     * Get all categories in the database.
     *
     * @return A list of categories.
     * @throws CostsManagerDAOException If there is an error retrieving the categories.
     */
    List<Category> getCategories() throws CostsManagerDAOException;

    /**
     * Return a specific category in the database by name.
     *
     * @param name The name of the category to retrieve.
     * @return The retrieved category or null if not found.
     * @throws CostsManagerDAOException If there is an error retrieving the category.
     */
    Category getCategoryByName(String name) throws CostsManagerDAOException;

    /**
     * Add a new cost to the database.
     *
     * @param cost The cost to add.
     * @throws CostsManagerDAOException If there is an error adding the cost.
     */
    void addCost(Cost cost) throws CostsManagerDAOException;

    /**
     * Get all costs in the database from the start date to the end date.
     *
     * @param start The start date of the range.
     * @param end   The end date of the range.
     * @return A list of costs within the specified date range.
     * @throws CostsManagerDAOException If there is an error retrieving the costs.
     */
    List<Cost> getCosts(Date start, Date end) throws CostsManagerDAOException;
}
