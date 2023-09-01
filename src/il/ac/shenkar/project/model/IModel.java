package il.ac.shenkar.project.model;

import il.ac.shenkar.project.CostsManagerDAOException;
import il.ac.shenkar.project.dao.Category;
import il.ac.shenkar.project.dao.Cost;

import java.sql.Date;
import java.util.List;

/**
 * Model interface
 */
public interface IModel {
    /**
     * call get categories from DAO
     * @return
     * @throws CostsManagerDAOException
     */
    public List<Category> getCategories() throws CostsManagerDAOException;

    /**
     * call add category from DAO
     * @param name
     * @throws CostsManagerDAOException
     */
    public void addCategory(String name) throws CostsManagerDAOException;

    /**
     * call get costs from DAO
     * @param start
     * @param end
     * @return
     * @throws CostsManagerDAOException
     */
    public List<Cost> getCosts(Date start, Date end) throws CostsManagerDAOException;

    /**
     * call add cost from DAO
     * @param sum
     * @param currency
     * @param categoryName
     * @param description
     * @param date
     * @throws CostsManagerDAOException
     */
    public void addCost(float sum, String currency, String categoryName, String description, Date date) throws CostsManagerDAOException;


}
