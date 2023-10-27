import java.util.List;

public class Main {
    public static void main(String[] args) {
        // 1st example
        Play hamlet = new Play("Hamlet", Play.PlayType.TRAGEDY);
        Play othello = new Play("othello", Play.PlayType.TRAGEDY);
        Play asLike = new Play("As You Like It", Play.PlayType.COMEDY);
        Play romeo = new Play("Romeo and Juliet", Play.PlayType.TRAGEDY);
        Play macBeth = new Play("Macbeth", Play.PlayType.TRAGEDY);

        Customer customer1 = new Customer("Amal", "CUST123", 300); // Create a customer
        Invoice invoice1 = new Invoice(customer1, List.of(
                new Performance(hamlet, 55),
                new Performance(othello, 40)));

        Customer customer2 = new Customer("Siham", "CUST456", 160); // Create another customer
        Invoice invoice2 = new Invoice(customer2, List.of(
                new Performance(asLike, 35),
                new Performance(othello, 40),
                new Performance(romeo, 50),
                new Performance(macBeth, 30)));

        invoice1.calculateInvoice();
        invoice2.calculateInvoice();

        invoice1.toText();
        invoice1.toHTML();

        invoice2.toText();
        invoice2.toHTML();
    }
}