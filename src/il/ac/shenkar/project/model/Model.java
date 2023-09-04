package il.ac.shenkar.project.model;

import il.ac.shenkar.project.CostsManagerDAOException;

import java.sql.Date;
import java.util.List;

/**
 * Logic of the mvvm
 */
public class Model implements IModel {
    /**
     * DAO reference
     */
    private DerbyCostsManagerDAO costsManagerDB;

    /**
     * deafult constructor to initialize the DAO reference
     * @throws ClassNotFoundException
     */
    public Model() throws ClassNotFoundException, CostsManagerDAOException {
        costsManagerDB = new DerbyCostsManagerDAO();
    }

    /**
     * call get categories from DAO
     * @return
     * @throws CostsManagerDAOException
     */
    public List<Category> getCategories() throws CostsManagerDAOException {
        return costsManagerDB.getCategories();
    }
    /**
     * call add category from DAO
     * @param name
     * @throws CostsManagerDAOException
     */
    public void addCategory(String name) throws CostsManagerDAOException{
        Category newCate = new Category(name);
        costsManagerDB.addCategory(newCate);
    }
    /**
     * call get costs from DAO
     * @param start
     * @param end
     * @return
     * @throws CostsManagerDAOException
     */
    public List<Cost> getCosts(Date start , Date end) throws CostsManagerDAOException{
        return costsManagerDB.getCosts(start, end);
    }
    /**
     * call add cost from DAO
     * @param sum
     * @param currency
     * @param categoryName
     * @param description
     * @param date
     * @throws CostsManagerDAOException
     */
    public void addCost(float sum, String currency, String categoryName, String description, Date date) throws CostsManagerDAOException{
        Cost newCost = new Cost(costsManagerDB.getCategoryByName(categoryName).getId(),sum, currency, description,date);
        costsManagerDB.addCost(newCost);
    }
}
