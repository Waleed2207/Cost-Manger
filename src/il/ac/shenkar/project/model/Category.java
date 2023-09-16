package il.ac.shenkar.project.model;

    /**
     * Category class to represent a category object in the database.
     */
public class Category {
    /**
     * The ID of the category.
     */
    private int id;

    /**
     * The name of the category.
     */
    private String name;

    /**
     * Constructs a Category object with a known ID.
     *
     * @param id   The ID of the category.
     * @param name The name of the category.
     */
    public Category(int id, String name) {
        setId(id);
        setName(name);
    }

    /**
     * Constructs a Category object with an unknown ID.
     *
     * @param name The name of the category.
     */
    public Category(String name) {
        setName(name);
    }

    /**
     * Gets the ID of the category.
     *
     * @return The ID of the category.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the category.
     *
     * @param id The ID of the category.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the name of the category.
     *
     * @return The name of the category.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the category.
     *
     * @param name The name of the category.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns a string representation of the category.
     *
     * @return A string representation of the category.
     */
    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
