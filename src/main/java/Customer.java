public class Customer {
    private String name;
    private String customerNumber;
    private int loyaltyPoints;


    public Customer(String name, String customerNumber) {
        this.name = name;
        this.customerNumber = customerNumber;
        this.loyaltyPoints = 0;
    }

    public String getName() {
        return name;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public void addLoyaltyPoints(int points) {
        this.loyaltyPoints += points;
    }
}
