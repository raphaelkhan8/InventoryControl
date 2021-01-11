package model;

public class Outsourced extends Part {
    private String companyName;

    // Constructor
    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        this.companyName = companyName;
        // Outsourced extends Part class so we need to call Part's setter methods to set the Part properties
        setId(id);
        setName(name);
        setPrice(price);
        setStock(stock);
        setMin(min);
        setMax(max);
    }

    /** Setter */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /** Getter */
    public String getCompanyName() {
        return this.companyName;
    }
}
