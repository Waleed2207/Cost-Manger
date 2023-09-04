package il.ac.shenkar.project.model;

import java.sql.Date;

/**
 * Cost class to represent cost object in database
 */
public class Cost {
    /**
     * id of the cost
     */
    private int id;
    /**
     * category id of the cost
     */
    private int categoryId;
    /**
     * category name of the cost
     */
    private String categoryName;
    /**
     * sum of the cost
     */
    private float sum;
    /**
     * currency of the cost
     */
    private String currency;
    /**
     * description of the cost
     */
    private String description;
    /**
     * date of the cost
     */
    private Date date;

    /**
     * constructor including id if the id is known
     * @param id
     * @param categoryId
     * @param categoryName
     * @param sum
     * @param currency
     * @param description
     * @param date
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
     * constructor if the id is unkown
     * @param categoryId
     * @param sum
     * @param currency
     * @param description
     * @param date
     */
    public Cost(int categoryId, float sum, String currency, String description, Date date) {
        setCategoryId(categoryId);
        setSum(sum);
        setCurrency(currency);
        setDescription(description);
        setDate(date);
    }

    /**
     * return the id of the cost
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * set the id of the cost
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * return the category id of the cost
     * @return
     */
    public int getCategoryId() {
        return categoryId;
    }

    /**
     * set the category id of the cost
     * @param categoryId
     */
    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * get category name of the cost
     * @return
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * set category name of the cost
     * @param categoryName
     */
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    /**
     * get sum of the cost
     * @return
     */
    public float getSum() {
        return sum;
    }

    /**
     * set sum of the cost
     * @param sum
     */
    public void setSum(float sum) {
        this.sum = sum;
    }

    /**
     * get description of the cost
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     * set description of the cost
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * get date of the cost
     * @return
     */
    public Date getDate() {
        return date;
    }

    /**
     * set date of the cost
     * @param date
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * get currency of the cost
     * @return
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * set currency of the cost
     * @param currency
     */
    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
