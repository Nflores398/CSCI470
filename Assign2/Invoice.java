public class Invoice {
    private String partNumber;
    private String partDescpt;
    private int quantity;
    private double itemPrice;

    Invoice(String partNumber, String partDescpt, int quantity, double itemPrice) {
        setPartNumber(partNumber);
        setItemPrice(itemPrice);
        setQuantity(quantity);
        setPartDescpt(partDescpt);
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public void setPartDescpt(String partDescpt) {
        this.partDescpt = partDescpt;
    }

    public String getPartDescpt() {
        return partDescpt;
    }

    public void setQuantity(int quantity) {
        if (quantity <= 0) {
            this.quantity = 0;
        } else {
            this.quantity = quantity;
        }
    }

    public int getQuantity() {
        return quantity;
    }

    public void setItemPrice(double itemPrice) {
        if (itemPrice <= 0.0) {
            this.itemPrice = 0.0;
        } else {
            this.itemPrice = itemPrice;
        }
    }

    public Double getItemPrice() {
        return itemPrice;
    }

}