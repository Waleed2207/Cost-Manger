package il.ac.shenkar.project.model;

import java.sql.Date;

/**
 * Cost class to represent cost object in database
 */
public class Cost {
    /**
     * The ID of the cost.
     */
    private int id;

    /**
     * The category ID of the cost.
     */
    private int categoryId;

    /**
     * The category name of the cost.
     */
    private String categoryName;

    /**
     * The sum of the cost.
     */
    private float sum;

    /**
     * The currency of the cost.
     */
    private String currency;

    /**
     * The description of the cost.
     */
    private String description;

    /**
     * The date of the cost.
     */
    private Date date;

    /**
     * Constructs a Cost object with a known ID.
     *
     * @param id           The ID of the cost.
     * @param categoryId   The category ID of the cost.
     * @param categoryName The category name of the cost.
     * @param sum          The sum of the cost.
     * @param currency     The currency of the cost.
     * @param description  The description of the cost.
     * @param date         The date of the cost.
     */
    public Cost(int id, int categoryId, String categoryName, float sum, String currency, String description, Date date) {
        setId(id);
        setCategoryId(categoryId);
        setCategoryName(categoryName);
        setSum(sum);
        setCurrency(currency);
        setDescription(description);
        setDate(date);
    }

    /**
     * Constructs a Cost object with an unknown ID.
     *
     * @param categoryId  The category ID of the cost.
     * @param sum         The sum of the cost.
     * @param currency    The currency of the cost.
     * @param description The description of the cost.
     * @param date        The date of the cost.
     */
    public Cost(int categoryId, float sum, String currency, String description, Date date) {
        setCategoryId(categoryId);
        setSum(sum);
        setCurrency(currency);
        setDescription(description);
        setDate(date);
    }

    /**
     * Gets the ID of the cost.
     *
     * @return The ID of the cost.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the cost.
     *
     * @param id The ID of the cost.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the category ID of the cost.
     *
     * @return The category ID of the cost.
     */
    public int getCategoryId() {
        return categoryId;
    }

    /**
     * Sets the category ID of the cost.
     *
     * @param categoryId The category ID of the cost.
     */
    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * Gets the category name of the cost.
     *
     * @return The category name of the cost.
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * Sets the category name of the cost.
     *
     * @param categoryName The category name of the cost.
     */
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    /**
     * Gets the sum of the cost.
     *
     * @return The sum of the cost.
     */
    public float getSum() {
        return sum;
    }

    /**
     * Sets the sum of the cost.
     *
     * @param sum The sum of the cost.
     */
    public void setSum(float sum) {
        this.sum = sum;
    }

    /**
     * Gets the description of the cost.
     *
     * @return The description of the cost.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the cost.
     *
     * @param description The description of the cost.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the date of the cost.
     *
     * @return The date of the cost.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Sets the date of the cost.
     *
     * @param date The date of the cost.
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Gets the currency of the cost.
     *
     * @return The currency of the cost.
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * Sets the currency of the cost.
     *
     * @param currency The currency of the cost.
     */
    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
