package il.ac.shenkar.project.model;

/**
 * Category class to represent category object in database
 */
public class Category {
    /**
     * id of the category
     */
    private int id;
    /**
     * name of the category
     */
    private String name;

    /**
     * constructor with two parameters if id is known
     * @param id
     * @param name
     */
    public Category(int id, String name) {
        setId(id);
        setName(name);
    }

    /**
     * constructor with one parameter if id is unknown
     * @param name
     */
    public Category( String name) {
        setName(name);
    }

    /**
     * return id of the category
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * set the id of the category
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * return name of the category
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * set the name of the category
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * string representation of the category
     * @return
     */
    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
