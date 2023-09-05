package il.ac.shenkar.project.model;

import il.ac.shenkar.project.CostsManagerDAOException;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Database access object implemented for Derby Database.
 */

public class DerbyCostsManagerDAO implements ICostsManagerDAO {
 //   private final String connectionString = "jdbc:derby://localhost:1527/CostManager;create=true";
    /**
     *  JDBC connection string for Derby database
     */
    private final String connectionString = "jdbc:derby:Costs-ManagerDB;create=true";
        private static final Logger logger = Logger.getLogger(DerbyCostsManagerDAO.class.getName());


    /**
     * Constructor for DerbyCostsManagerDAO.
     * Initializes the database driver and creates the necessary schema and tables if they don't exist.
     */
    public DerbyCostsManagerDAO() {

        loadDriverClassName();
        createSchemaAndTables();
    }
    /**
     * Load the Derby database driver class.
     */
    private void loadDriverClassName(){
        try {
            String driver = "org.apache.derby.jdbc.EmbeddedDriver";
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            logger.log(Level.SEVERE, "Failed to load database driver", e);
            System.exit(0);
        }
    }
    /**
     * Create the necessary schema and tables if they don't exist.
     */
    private void createSchemaAndTables() {
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;

        try {
            connection = DriverManager.getConnection(connectionString);
            statement = connection.createStatement();

            // Check if 'categories' table exists
            DatabaseMetaData dbm = connection.getMetaData();
            rs = dbm.getTables(null, null, "CATEGORIES", null);
            if (!rs.next()) {
                // Table does not exist, create it
                // Table does not exist, create it
                                statement.execute("""
                        CREATE TABLE categories (
                        id INTEGER GENERATED ALWAYS AS IDENTITY,
                        name VARCHAR(255),
                        PRIMARY KEY (id)
                        )
                """);
                // Insert data into the categories table
                statement.executeUpdate("INSERT INTO categories (name) VALUES ('Food')");
                statement.executeUpdate("INSERT INTO categories (name) VALUES ('School')");
            }

            // Close previous ResultSet
            rs.close();

            // Check if 'costs' table exists
            rs = dbm.getTables(null, null, "COSTS", null);
            if (!rs.next()) {
                // Table does not exist, create it
                statement.execute("CREATE TABLE costs (" +
                        "id INTEGER GENERATED ALWAYS AS IDENTITY, " +
                        "category_id INT, " +
                        "total FLOAT, " +
                        "currency VARCHAR(10), " +
                        "description VARCHAR(255), " +
                        "date DATE, " +
                        "PRIMARY KEY (id), " +
                        "FOREIGN KEY (category_id) REFERENCES categories(id))");
                // Insert data into the costs table
                // execute update
                statement.executeUpdate("INSERT INTO costs (category_id, total, currency, description, date) " +
                        "VALUES (1, 100.00, 'USD', 'Expense 1', '2023-09-01')");
                statement.executeUpdate("INSERT INTO costs (category_id, total, currency, description, date) " +
                        "VALUES (2, 50.00, 'EUR', 'Expense 2', '2023-09-02')");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources
            try {
                if (rs != null) rs.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Adds a new category to the database.
     *
     * @param category The category to add.
     * @throws CostsManagerDAOException If there is an error adding the category.
     */
    @Override
    public void addCategory(Category category) throws CostsManagerDAOException {
        try(Connection connection = DriverManager.getConnection(connectionString);

            PreparedStatement statement = connection.prepareStatement("INSERT INTO categories (name) VALUES(?)")){
            if(getCategoryByName(category.getName()) != null) {
                throw new CostsManagerDAOException("Category already exists");
            }
            statement.setString(1, category.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new CostsManagerDAOException("Error connecting to database", e);
        }
    }
    /**
     * Retrieves a list of all categories from the database.
     *
     * @return A list of categories.
     * @throws CostsManagerDAOException If there is an error retrieving the categories.
     */
    @Override
    public List<Category> getCategories() throws CostsManagerDAOException {
        List<Category> list = new LinkedList<>();
        try(Connection connection = DriverManager.getConnection(connectionString);
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM categories ORDER BY id");
            ResultSet rs = statement.executeQuery()){
            while(rs.next()){
                list.add(new Category(rs.getInt("id"), rs.getString("name")));
            }
        } catch (SQLException e) {
            throw new CostsManagerDAOException("Error connecting to database", e);
        }
        return list;
    }
    /**
     * Retrieves a category by its name from the database.
     *
     * @param name The name of the category to retrieve.
     * @return The retrieved category or null if not found.
     * @throws CostsManagerDAOException If there is an error retrieving the category.
     */
    @Override
    public Category getCategoryByName(String name) throws CostsManagerDAOException {
        Category category = null;
        ResultSet rs = null;
        try(Connection connection = DriverManager.getConnection(connectionString);
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM categories WHERE name = ?")){
            statement.setString(1, name);
            rs = statement.executeQuery();
            if(rs.next()) {
                category = new Category(rs.getInt("id"), rs.getString("name"));
            }
        } catch (SQLException e) {
            throw new CostsManagerDAOException("Error connecting to database", e);
        } finally {
            if(rs!=null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return category;
    }
    /**
     * Adds a new cost to the database.
     *
     * @param cost The cost to add.
     * @throws CostsManagerDAOException If there is an error adding the cost.
     */
    @Override
    public void addCost(Cost cost) throws CostsManagerDAOException {
        try(Connection connection = DriverManager.getConnection(connectionString);
            PreparedStatement statement = connection.prepareStatement("INSERT INTO costs (category_id, total, currency, description, date) VALUES(?, ?, ?, ?, ?)")){
            statement.setInt(1, cost.getCategoryId());
            statement.setFloat(2, cost.getSum());
            statement.setString(3, cost.getCurrency());
            statement.setString(4, cost.getDescription());
            statement.setDate(5, cost.getDate());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new CostsManagerDAOException("Error connecting to database", e);
        }
    }
    /**
     * Retrieves a list of costs within a specified date range from the database.
     *
     * @param start The start date of the range.
     * @param end   The end date of the range.
     * @return A list of costs within the specified date range.
     * @throws CostsManagerDAOException If there is an error retrieving the costs.
     */
    @Override
    public List<Cost> getCosts(Date start, Date end) throws CostsManagerDAOException {
        ResultSet rs = null;
        String query = "SELECT costs.*, categories.name as category_name FROM costs JOIN categories ON costs.category_id = categories.id WHERE costs.date >= ? AND costs.date <= ?";
        List<Cost> list = new LinkedList<>();
        try(Connection connection = DriverManager.getConnection(connectionString);
            PreparedStatement statement = connection.prepareStatement(query)){
            statement.setDate(1, start);
            statement.setDate(2, end);
            rs = statement.executeQuery();
            while(rs.next()){
                list.add(new Cost(rs.getInt("id"), rs.getInt("category_id"), rs.getString("category_name"), rs.getFloat("total"), rs.getString("currency"), rs.getString("description"), rs.getDate("date")));
            }
        } catch (SQLException e) {
            throw new CostsManagerDAOException("Error connecting to database", e);
        }
        return list;
    }
}
