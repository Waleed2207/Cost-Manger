package il.ac.shenkar.project.view;

import com.toedter.calendar.JDateChooser;
import il.ac.shenkar.project.model.Category;
import il.ac.shenkar.project.model.Cost;
import il.ac.shenkar.project.viewmodel.IViewModel;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import java.awt.event.ActionListener;

public class View implements IView {
    /**
     * viewmodel reference
     */
    private IViewModel vm;
    /**
     * main frame reference
     */
    private JFrame mainFrame;
    /**
     * reference to each panel
     */
    private JPanel mainPanel, titlePanel, costPanel, categoryPanel, reportPanel, reportWrapper;

    /**
     * labels in the costs panel
     */
    private JLabel costSumLabel, costCategoryLabel, costDescriptionLabel, costDateLabel, costCurrencyLabel;
    /**
     * text fields in the costs panel
     */
    private JTextField costSumTF, costDescriptionTF;
    /**
     * combobox in the costs panel
     */
    private JComboBox<String> costCategoryCB, costCurrencyCB;
    /**
     * date chooser in the costs panel
     */
    private JDateChooser costDateDC;
    /**
     * button in the costs panel
     */
    private JButton costSubmitBtn;

    /**
     * labels in the category panel
     */
    private JLabel categoryNameLabel;
    /**
     * text field in the category panel
     */
    private JTextField categoryNameTF;
    /**
     * button in the category panel
     */
    private JButton categorySubmitBtn;
    /**
     * text area in the category panel
     */
    private JTextArea categoryTA;

    /**
     * labels in the report panel
     */
    private JLabel reportFromLabel, reportToLabel;
    /**
     * date choosers in the report panel
     */
    private JDateChooser reportFromDC, reportToDC;
    /**
     * button in the report panel
     */
    private JButton reportSubmitBtn;
    /**
     * text area in the report panel
     */
    private JTextArea reportTA;
    /**
     * scroll pane in the report panel
     */
    private JScrollPane reportTASP;
    /**
     * Adjust this to your preferred width
     *  Adjust this to your preferred height
     */
    private int frameWidth = 800;
    private int frameHeight = 800;

    /**
     * constructor to initialize all elements
     */
    public View() {
        // initalize the frames and panels
        mainFrame = new JFrame("Costs Manager");
        mainPanel = new JPanel();
        titlePanel = new JPanel();
        costPanel = new JPanel();
        categoryPanel = new JPanel();
        reportPanel = new JPanel();
        reportWrapper = new JPanel();

        // initialize cost panel elements
        costSumLabel = new JLabel("sum:");
        costCategoryLabel = new JLabel("category:");
        costDescriptionLabel = new JLabel("Description:");
        costDateLabel = new JLabel("Date:");
        costCurrencyLabel = new JLabel("Currency:");
        costSumTF = new JTextField(10);
        costDescriptionTF = new JTextField(10);
        costCategoryCB = new JComboBox<String>();
        costCurrencyCB = new JComboBox<String>();
        costDateDC = new JDateChooser();
        costSubmitBtn = new JButton("Add Cost");

        // initialize category panel elements
        categoryNameLabel = new JLabel("Name:");
        categoryNameTF = new JTextField(10);
        categorySubmitBtn = new JButton("Add Category");
        categoryTA = new JTextArea();

        // initalize report panel elements
        reportFromLabel = new JLabel("From:");
        reportToLabel = new JLabel("To");
        reportFromDC = new JDateChooser();
        reportToDC = new JDateChooser();
        reportSubmitBtn = new JButton("Show Costs");
        reportTA = new JTextArea(10,1);
        reportTASP = new JScrollPane(reportTA);
    }
    /**
     * set the viewmodel reference
     * @param vm
     */
    @Override
    public void setViewModel(IViewModel vm) {
        this.vm = vm;

    }
    /**
     * start the interface
     */
    public void start() {
        ButtonsListener listener = new ButtonsListener();
        // setup the main frame and main panel
        BoxLayout boxlayout = new BoxLayout(mainPanel, BoxLayout.Y_AXIS);
        // Set the background color for mainPanel
        mainPanel.setBackground(Color.WHITE);
        // Set the background color for costPanel
        costPanel.setBackground(Color.LIGHT_GRAY);
        // Set the background color for categoryPanel
        categoryPanel.setBackground(Color.LIGHT_GRAY);
        // Set the background color for reportPanel
        reportPanel.setBackground(Color.LIGHT_GRAY);
        // Set the background color for titlePanel
        titlePanel.setBackground(Color.LIGHT_GRAY); // For example, you can choose any color you prefer
        // You can set background colors for other components as needed
        mainPanel.setLayout(boxlayout);

        // Assuming you have an ImageIcon object named 'icon' representing your desired icon
        JLabel titleLabel = getjLabel();

        titlePanel.add(titleLabel);
        // Set the preferred size of titlePanel to a height of 1 pixel
        titlePanel.setPreferredSize(new Dimension(titlePanel.getPreferredSize().width, 80));
        mainPanel.add(titlePanel);
        mainPanel.add(Box.createVerticalStrut(1));

        // setup cost panel and its elements
        costPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        costPanel.setPreferredSize(new Dimension(costPanel.getPreferredSize().width, 80)); // Set the height to 80 pixels

        TitledBorder titledBorder = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "New Cost");
        // Set the color of the title text
        titledBorder.setTitleColor(Color.decode("#142B4E")); // Set the title color using Color.decode
        // Set the custom TitledBorder as the border for costPanel
        costPanel.setBorder(titledBorder);
        costPanel.add(costSumLabel);
        costPanel.add(costSumTF);
        costPanel.add(costCurrencyLabel);
        costCurrencyCB.addItem("NIS");
        costCurrencyCB.addItem("USD");
        costCurrencyCB.addItem("EUR");
        costPanel.add(costCurrencyCB);
        costPanel.add(costCategoryLabel);
        costPanel.add(costCategoryCB);
        costCategoryCB.setPrototypeDisplayValue("Category Name");
        costPanel.add(costDescriptionLabel);
        costPanel.add(costDescriptionTF);
        costPanel.add(costDateLabel);
        costDateDC.setPreferredSize(new Dimension(130, costDateDC.getPreferredSize().height));

        costPanel.add(costDateDC);
        costPanel.add(costSubmitBtn);
        costSubmitBtn.addActionListener(listener);
        costPanel.add(Box.createHorizontalStrut(10));
        mainPanel.add(costPanel);
        // spacer
        mainPanel.add(Box.createVerticalStrut(1));

        // setup category panel and its elements
        categoryPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        TitledBorder categoryPanelBorder = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "New Category");
        categoryPanelBorder.setTitleColor(Color.decode("#142B4E")); // Set the title color using Color.decode
        categoryPanel.setBorder(categoryPanelBorder);
        categoryPanel.add(categoryNameLabel);
        categoryPanel.add(categoryNameTF);
        categoryPanel.add(categorySubmitBtn);
        categorySubmitBtn.addActionListener(listener);
        mainPanel.add(categoryPanel);
        // spacer
        mainPanel.add(Box.createVerticalStrut(1));

        // setup report panel and its elements
        BoxLayout reportboxlayout = new BoxLayout(reportWrapper, BoxLayout.Y_AXIS);
        reportWrapper.setLayout(reportboxlayout);
        categoryPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        categoryPanel.setPreferredSize(new Dimension(categoryPanel.getPreferredSize().width, 70)); // Set the height to 400 pixels
        TitledBorder costsPanelBorder = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Costs Report");
        costsPanelBorder.setTitleColor(Color.decode("#142B4E")); // Set the title color using Color.decode
        reportPanel.setBorder(costsPanelBorder);
       // Set the preferred size with the desired width and the default height
        reportFromDC.setPreferredSize(new Dimension(130, reportFromDC.getPreferredSize().height));
        reportPanel.add(reportFromLabel);
        reportPanel.add(reportFromDC);
        reportToDC.setPreferredSize(new Dimension(130, reportToDC.getPreferredSize().height));
        reportPanel.add(reportToLabel);
        reportPanel.add(reportToDC);
        reportPanel.add(reportSubmitBtn);

        reportSubmitBtn.addActionListener(listener);
        reportWrapper.add(reportPanel);
        reportWrapper.add(reportTASP);
        mainPanel.add(reportWrapper);

        // change behavior of close button
        mainFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        // display the main frame
        mainFrame.add(mainPanel);
        mainFrame.pack();
        // Define the desired width and height


        mainFrame.setSize(frameWidth, frameHeight);
        // Center the frame on the screen (optional)
        mainFrame.setLocationRelativeTo(null);

        //handling button events

        mainFrame.setVisible(true);

    }

    /**
     * Create and return a JLabel with a custom title and an icon.
     *
     * @return A JLabel with a custom title and icon.
     */

    private static JLabel getjLabel() {
        // Load the icon from the specified image file path
        ImageIcon icon = new ImageIcon("./budget.png");

        // Create a JLabel with a custom HTML-formatted title
        JLabel titleLabel = new JLabel("<html><font color='#2C4F87'><b>Cost Manager</b></font></html>", SwingConstants.LEFT);

        // Set the font and style for the title label
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 35.0F));

        // Set the text color to blue for the title label
        titleLabel.setForeground(Color.BLUE);

        // Set the loaded icon for the titleLabel
        titleLabel.setIcon(icon);

        // Return the created and configured titleLabel
        return titleLabel;
    }


    /**
     * Helper function for setCosts.
     *
     * @param costs The list of costs to display.
     */
    private void updateReport(List<Cost> costs) {
        // Create a table header
        String table = "Date\tCategory\tCost\tCurrency\tDescription\n\n";

        // Populate the table with cost data
        for (Cost cost : costs) {
            table += cost.getDate() + "\t" +
                    cost.getCategoryName() + "\t" +
                    cost.getSum() + "\t" +
                    cost.getCurrency() + "\t" +
                    cost.getDescription() + "\n";
        }

        reportTA.setText(table);
    }

    /**
     * Sets the costs in the view.
     *
     * @param costs The list of costs to set.
     */
    @Override
    public void setCosts(List<Cost> costs) {
        // make sure to only execute in the dispatch thread
        if (SwingUtilities.isEventDispatchThread()) {
            updateReport(costs);
        } else {
            SwingUtilities.invokeLater(() -> {
                updateReport(costs);
            });
        }
    }

    /**
     * Helper function for setCategories.
     *
     * @param categories The list of categories to display.
     */
    private void updateCategories(List<Category> categories) {
        // update categories in combobox
        costCategoryCB.removeAllItems();
        StringBuffer sb = new StringBuffer();
        for (Category category : categories) {
            costCategoryCB.addItem(category.getName());
            sb.append(category.getName()).append("\n");
        }
        categoryTA.setText(sb.toString());
    }
    /**
     * Sets the categories in the view.
     *
     * @param categories The list of categories to set.
     */
    @Override
    public void setCategories(List<Category> categories) {
        // make sure to only execute in the dispatch thread
        if (SwingUtilities.isEventDispatchThread()) {
            updateCategories(categories);
        } else {
            SwingUtilities.invokeLater(() -> {
                updateCategories(categories);
            });
        }
    }
    /**
     * Displays a message to the user.
     *
     * @param msg     The message to display.
     * @param type    The type of message (e.g., information, warning).
     * @param msgType The message type (e.g., dialog, notification).
     */
    public void displayMSG(String msg , String type ,int msgType){
        // make sure to only execute in the dispatch thread
        if (SwingUtilities.isEventDispatchThread()) {
            JOptionPane.showMessageDialog(mainFrame, msg,
                    type, msgType);
        } else {
            SwingUtilities.invokeLater(() -> {
                JOptionPane.showMessageDialog(mainFrame, msg,
                        type, msgType);
            });
        }

    }
    /**
     * Syncs the categories in the view with the database.
     */
    @Override
    public void syncCategories() {
        vm.getCategories();
    }

    /**
     * ActionListener for handling button events
     */
    class ButtonsListener implements ActionListener {
        /**
         * Handles button clicks in the user interface.
         *
         * @param e The ActionEvent representing the button click event.
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            Object source = e.getSource();

            // Determine which button was clicked and invoke the corresponding action
            if (source == costSubmitBtn) {
                handleCostSubmitAction();
            } else if (source == categorySubmitBtn) {
                handleCategorySubmitAction();
            } else if (source == reportSubmitBtn) {
                handleReportSubmitAction();
            }
        }

        /**
         * Handles the action when the "Add Cost" button is clicked.
         */
        private void handleCostSubmitAction() {
            String categoryName = (String) costCategoryCB.getSelectedItem();
            String sumText = costSumTF.getText();
            String description = costDescriptionTF.getText();
            Float sum = 0F;

            // Check if a category is selected
            if (categoryName == null || categoryName.isEmpty()) {
                displayMSG("Must select a category.", "ERROR", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Check if the sum field is empty
            if (sumText.isEmpty()) {
                displayMSG("Sum cannot be empty.", "ERROR", JOptionPane.CANCEL_OPTION);
                return;
            }

            try {
                // Parse the sum as a float
                sum = Float.parseFloat(sumText);

                // Get the selected date and time from the calendar
                Calendar calendar = new GregorianCalendar();
                calendar.setTime(costDateDC.getDate());

                // Call the ViewModel to add the cost
                vm.addCost(sum, (String) costCurrencyCB.getSelectedItem(), categoryName, description, new Date(calendar.getTimeInMillis()));
            } catch (NumberFormatException err) {
                err.printStackTrace();
                displayMSG("Sum must be a float number.", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }

        /**
         * Handles the action when the "Add Category" button is clicked.
         */
        private void handleCategorySubmitAction() {
            String name = categoryNameTF.getText();

            // Check if the category name is empty
            if (name == null || name.isEmpty()) {
                displayMSG("Category name can't be empty.", "ERROR", JOptionPane.CANCEL_OPTION);
                return;
            }

            // Call the ViewModel to add the category
            vm.addCategory(name);
        }

        /**
         * Handles the action when the "Show Costs" button is clicked.
         */
        private void handleReportSubmitAction() {
            try {
                // Get the selected start date
                java.util.Date startDate = reportFromDC.getDate();
                // Get the selected end date
                java.util.Date endDate = reportToDC.getDate();

                // Check if either startDate or endDate is null
                if (startDate == null && endDate == null) {
                    throw new NullPointerException("Both start and end dates must be selected.");
                } else if (startDate == null) {
                    throw new NullPointerException("Start date must be selected.");
                } else if (endDate == null) {
                    throw new NullPointerException("End date must be selected.");
                }

                // Convert the dates to java.sql.Date if needed for your ViewModel
                java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());
                java.sql.Date sqlEndDate = new java.sql.Date(endDate.getTime());

                // Proceed with retrieving costs from the ViewModel
                vm.getCosts(sqlStartDate, sqlEndDate);
            } catch (NullPointerException e) {
                String errorMessage = e.getMessage();
                if (errorMessage != null && !errorMessage.isEmpty()) {
                    displayMSG(errorMessage, "ERROR", JOptionPane.CANCEL_OPTION);
                } else {
                    displayMSG("An unknown error occurred.", "ERROR", JOptionPane.CANCEL_OPTION);
                }
            }

        }
    }

}
