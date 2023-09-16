package il.ac.shenkar.project.model;
import java.sql.Date;
import java.util.List;

/**
 * The Model class represents the logic of the MVVM architecture.
 */
public class Model implements IModel {
    /**
     * Reference to the Data Access Object (DAO).
     */
    private DerbyCostsManagerDAO costsManagerDB;

    /**
     * Default constructor to initialize the DAO reference.
     *
     * @throws ClassNotFoundException If the database driver class is not found.
     * @throws CostsManagerDAOException If there is an error with the DAO.
     */
    public Model() throws ClassNotFoundException, CostsManagerDAOException {
        costsManagerDB = new DerbyCostsManagerDAO();
    }

    /**
     * Get a list of categories from the DAO.
     *
     * @return A list of categories.
     * @throws CostsManagerDAOException If there is an error retrieving the categories.
     */
    public List<Category> getCategories() throws CostsManagerDAOException {
        return costsManagerDB.getCategories();
    }

    /**
     * Add a new category to the DAO.
     *
     * @param name The name of the category to add.
     * @throws CostsManagerDAOException If there is an error adding the category.
     */
    public void addCategory(String name) throws CostsManagerDAOException {
        Category newCategory = new Category(name);
        costsManagerDB.addCategory(newCategory);
    }

    /**
     * Get a list of costs within a specified date range from the DAO.
     *
     * @param start The start date of the range.
     * @param end   The end date of the range.
     * @return A list of costs within the specified date range.
     * @throws CostsManagerDAOException If there is an error retrieving the costs.
     */
    public List<Cost> getCosts(Date start, Date end) throws CostsManagerDAOException {
        return costsManagerDB.getCosts(start, end);
    }

    /**
     * Add a new cost to the DAO.
     *
     * @param sum          The cost amount.
     * @param currency     The currency of the cost.
     * @param categoryName The name of the category for the cost.
     * @param description  The description of the cost.
     * @param date         The date of the cost.
     * @throws CostsManagerDAOException If there is an error adding the cost.
     */
    public void addCost(float sum, String currency, String categoryName, String description, Date date) throws CostsManagerDAOException {
        Category category = costsManagerDB.getCategoryByName(categoryName);
        if (category != null) {
            Cost newCost = new Cost(category.getId(), sum, currency, description, date);
            costsManagerDB.addCost(newCost);
        } else {
            throw new CostsManagerDAOException("Category not found");
        }
    }
}
