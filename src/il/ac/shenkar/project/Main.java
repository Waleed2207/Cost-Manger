package il.ac.shenkar.project;
import il.ac.shenkar.project.model.CostsManagerDAOException;
import il.ac.shenkar.project.model.IModel;
import il.ac.shenkar.project.model.Model;
import il.ac.shenkar.project.view.IView;
import il.ac.shenkar.project.view.View;
import il.ac.shenkar.project.viewmodel.IViewModel;
import il.ac.shenkar.project.viewmodel.ViewModel;
import javax.swing.*;


    /**
     * Main application class to run the main
     */
public class Main {
    /**
     * Main function of the application.
     *
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        try {
            IModel model = new Model();
            IViewModel vm = new ViewModel();
            // Run the view in the proper thread
            SwingUtilities.invokeLater(() -> {
                IView view = new View();
                view.start();
                // Set the ViewModel in the view
                view.setViewModel(vm);
                // Set the model and view in ViewModel
                vm.setModel(model);
                vm.setView(view);
                // Sync the categories in the view with the categories in the database
                view.syncCategories();
            });
        } catch (CostsManagerDAOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}

