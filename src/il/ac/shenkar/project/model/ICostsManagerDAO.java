package il.ac.shenkar.project.model;

import il.ac.shenkar.project.CostsManagerDAOException;

import java.sql.Date;
import java.util.List;

/**
 * interface of the costs manager DAO
 */
public interface ICostsManagerDAO {
    /**
     * add a new category to the database
     * @param category
     * @throws CostsManagerDAOException
     */
    public void addCategory(Category category) throws CostsManagerDAOException, CostsManagerDAOException;

    /**
     * get all categories in the database
     * @return
     * @throws CostsManagerDAOException
     */
    public List<Category> getCategories() throws CostsManagerDAOException;

    /**
     * return a specific category in the database by name
     * @param name
     * @return
     * @throws CostsManagerDAOException
     */
    public Category getCategoryByName(String name) throws CostsManagerDAOException;

    /**
     * add a new cost to the database
     * @param cost
     * @throws CostsManagerDAOException
     */
    public void addCost(Cost cost) throws CostsManagerDAOException;

    /**
     * get all costs in the database from start date till end date
     * @param start
     * @param end
     * @return
     * @throws CostsManagerDAOException
     */
    public List<Cost> getCosts(Date start, Date end) throws CostsManagerDAOException;
}
