package il.ac.shenkar.project.viewmodel;

import il.ac.shenkar.project.model.IModel;
import il.ac.shenkar.project.view.IView;

import java.sql.Date;

/**
 * viewmodel interface to help view communicate with model
 */
public interface IViewModel {
    /**
     * set the model
     * @param model
     */
    public void setModel(IModel model);

    /**
     * set the view
     * @param view
     */
    public void setView(IView view);

    /**
     * call add category in model
     * @param name
     */
    public void addCategory(String name);

    /**
     * call add cost in model
     * @param sum
     * @param currency
     * @param categoryName
     * @param description
     * @param date
     */
    public void addCost(float sum, String currency, String categoryName, String description, Date date);

    /**
     * call get costs in model
     * @param start
     * @param end
     */
    public void getCosts(Date start, Date end);

    /**
     * call get categories in model
     */
    public void getCategories();
}
