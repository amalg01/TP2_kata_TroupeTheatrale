import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // 1st example
        HashMap<String, Play> plays = new HashMap<>();
        plays.put("hamlet", new Play("Hamlet", Play.PlayType.TRAGEDY));
        plays.put("othello", new Play("Othello", Play.PlayType.TRAGEDY));

        Customer customer1 = new Customer("Amal", "CUST123", 300); // Create a customer
        Invoice invoice1 = new Invoice(customer1, List.of(
                new Performance("hamlet", 55),
                new Performance("othello", 40)));

        // 2nd example
        HashMap<String, Play> plays2 = new HashMap<>();
        plays2.put("as-like", new Play("As You Like It", Play.PlayType.COMEDY));
        plays2.put("othello", new Play("Othello", Play.PlayType.TRAGEDY));
        plays2.put("romeo", new Play("Romeo and Juliet", Play.PlayType.TRAGEDY));
        plays2.put("macbeth", new Play("Macbeth", Play.PlayType.TRAGEDY));

        Customer customer2 = new Customer("Siham", "CUST456", 150); // Create another customer
        Invoice invoice2 = new Invoice(customer2, List.of(
                new Performance("as-like", 35),
                new Performance("othello", 40),
                new Performance("romeo", 50),
                new Performance("macbeth", 30)));

        invoice1.calculateInvoice(plays);
        invoice2.calculateInvoice(plays2);

        invoice1.toText();
        invoice1.toHTML();

        invoice2.toText();
        invoice2.toHTML();
    }
}
