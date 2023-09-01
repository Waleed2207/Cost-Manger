package il.ac.shenkar.project.view;

import il.ac.shenkar.project.dao.Category;
import il.ac.shenkar.project.dao.Cost;
import il.ac.shenkar.project.viewmodel.IViewModel;

import java.util.List;

/**
 * View interface to allow main and viewmodel to interact with the view
 */
public interface IView {
    /**
     * set the viewmodel reference
     * @param vm
     */
    public void setViewModel(IViewModel vm);

    /**
     * start the interface
     */
    public void start();

    /**
     * set the costs in the view
     * @param costs
     */
    public void setCosts(List<Cost> costs);

    /**
     * set the categories in the view
     * @param categories
     */
    public void setCategories(List<Category> categories);

    /**
     * sync the categories in the view with the database
     */
    public void syncCategories();

    /**
     * display a message to the user
     * @param msg
     * @param type
     * @param msgType
     */
    public void displayMSG(String msg , String type ,int msgType);
}



