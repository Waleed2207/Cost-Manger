package il.ac.shenkar.project;
import il.ac.shenkar.project.model.IModel;
import il.ac.shenkar.project.model.Model;
import il.ac.shenkar.project.view.IView;
import il.ac.shenkar.project.view.View;
import il.ac.shenkar.project.viewmodel.IViewModel;
import il.ac.shenkar.project.viewmodel.ViewModel;


import javax.swing.*;

/**
 * main application class to run the main
 */
public class Main {
    /**
     * main function of the application
     * @param args
     * @throws ClassNotFoundException
     */
    public static void main(String[] args) throws ClassNotFoundException, CostsManagerDAOException {
        try{
            IModel model = new Model();
            IViewModel vm = new ViewModel();
            // run the view in the proper thread
            SwingUtilities.invokeLater(() -> {
                IView view = new View();
                view.start();
                // set the viewmodel in the view
                view.setViewModel(vm);
                // set the model and view in viewmodel
                vm.setModel(model);
                vm.setView(view);
                // sync the categories in the view with the categories in the database
                view.syncCategories();
            });
        }catch (CostsManagerDAOException | ClassNotFoundException e){
            e.printStackTrace();
        }


    }
}

