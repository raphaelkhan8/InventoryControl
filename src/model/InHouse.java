package model;

// InHouse inherits from the Part class
public class InHouse extends Part {
    private int machineId;
    // Constructor
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId) {
        this.machineId = machineId;
        // InHouse extends Part class so we need to call Part's setter methods to set the Part properties
        setId(id);
        setName(name);
        setPrice(price);
        setStock(stock);
        setMin(min);
        setMax(max);
    }

    /// Setter ///
    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }

    /// Getter ///
    public int getMachineId() {
        return this.machineId;
    }
}
