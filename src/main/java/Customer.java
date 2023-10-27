public class Customer {
    private String name;
    private String customerNumber;
    private int loyaltyPoints;

    public Customer(String name, String customerNumber, int loyaltyPoints) {
        this.name = name;
        this.customerNumber = customerNumber;
        this.loyaltyPoints = loyaltyPoints;
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
    
    public void deductLoyaltyPoints(int points) {
        if (points <= loyaltyPoints) {
            loyaltyPoints -= points;
        } else {
            // Handle the case where the customer doesn't have enough points to deduct
            // You can throw an exception, display a message, or handle it as needed.
            System.err.println("Insufficient loyalty points to deduct.");
        }
    }
}
