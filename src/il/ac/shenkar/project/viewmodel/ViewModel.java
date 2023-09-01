package il.ac.shenkar.project.viewmodel;

import il.ac.shenkar.project.CostsManagerDAOException;
import il.ac.shenkar.project.dao.Category;
import il.ac.shenkar.project.dao.Cost;
import il.ac.shenkar.project.model.IModel;
import il.ac.shenkar.project.view.IView;

import javax.swing.*;
import java.sql.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * viewmodel implementation
 */
public class ViewModel implements IViewModel {
    /**
     * model reference
     */
    private IModel model;
    /**
     * view reference
     */
    private IView view;
    /**
     * executor service to run each call in a new thread
     */
    private ExecutorService service;

    /**
     * constructor to create a fixed thread pool
     */
    public ViewModel() {
        service = Executors.newFixedThreadPool(8);
    }

    /**
     * set the model
     * @param model
     */
    @Override
    public void setModel(IModel model) {
        this.model = model;
    }

    /**
     * set the view
     * @param view
     */
    @Override
    public void setView(IView view) {
        this.view = view;
    }

    /**
     * call add category in model
     * @param name
     */
    @Override
    public void addCategory(String name){
        // submit a thread to handel adding a category
        service.submit(() -> {
            try {
                model.addCategory(name);
                view.syncCategories();
                view.displayMSG("The category has been added successfully.","INFO", JOptionPane.INFORMATION_MESSAGE);
            } catch (CostsManagerDAOException e) {
                e.printStackTrace();
                view.displayMSG("Error adding category. make sure that the category doesn't already exist.","ERROR", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    /**
     * call add cost in model
     * @param sum
     * @param currency
     * @param categoryName
     * @param description
     * @param date
     */
    @Override
    public void addCost(float sum, String currency, String categoryName, String description, Date date) {
        // submit a thread to handel adding a cost
        service.submit(() -> {
            try {
                model.addCost(sum, currency, categoryName, description, date);
                view.displayMSG("The cost has been added successfully.","INFO", JOptionPane.INFORMATION_MESSAGE);
            } catch (CostsManagerDAOException e) {
                e.printStackTrace();
                view.displayMSG("Error adding cost. Try again!","ERROR", JOptionPane.ERROR_MESSAGE);

            }
        });
    }

    /**
     * call get costs in model
     * @param start
     * @param end
     */
    @Override
    public void getCosts(Date start, Date end) {
        // submit a thread to get all costs
        service.submit(() -> {
            try {
                List<Cost> list = model.getCosts(start, end);
                view.setCosts(list);
            } catch (CostsManagerDAOException e) {
                e.printStackTrace();
                view.displayMSG("Error getting costs. Try again!","ERROR", JOptionPane.ERROR_MESSAGE);

            }
        });
    }

    /**
     * call get categories in model
     */
    @Override
    public void getCategories() {
        // submit a thread to handel getting all categories
        service.submit(() -> {
            try {
                List<Category> list = model.getCategories();
                view.setCategories(list);
            } catch (CostsManagerDAOException e) {
                e.printStackTrace();
                view.displayMSG("Error getting categories. Try again!","ERROR", JOptionPane.ERROR_MESSAGE);

            }
        });
    }
}
