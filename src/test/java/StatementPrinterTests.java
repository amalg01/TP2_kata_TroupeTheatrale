import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.approvaltests.Approvals.verify;
import static org.approvaltests.Approvals.verifyHtml;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue; 

public class StatementPrinterTests {

    @Test
    void exampleStatement() {

        Play hamlet = new Play("Hamlet", Play.PlayType.TRAGEDY);
        Play othello = new Play("Othello", Play.PlayType.TRAGEDY);
        Play asLike = new Play("As You Like It", Play.PlayType.COMEDY);

        Customer customer = new Customer("BigCo", "CUST123", 100);
        Invoice invoice = new Invoice(customer, List.of(
                new Performance(hamlet, 55),
                new Performance(asLike, 35), 
                new Performance(othello, 40)));

        invoice.calculateInvoice();
        verify(invoice.toText());
    }

    @Test
    void exampleStatement2() {

        Play hamlet = new Play("Hamlet", Play.PlayType.TRAGEDY);
        Play othello = new Play("Othello", Play.PlayType.TRAGEDY);
        Play asLike = new Play("As You Like It", Play.PlayType.COMEDY);

        Customer customer2 = new Customer("BigCo", "CUST123", 100);
        Invoice invoice = new Invoice(customer2, List.of(
                new Performance(hamlet, 55),
                new Performance(asLike, 35), 
                new Performance(othello, 40)));

        invoice.calculateInvoice(); 
        var result2 = invoice.toHTML();
 
        verifyHtml(result2);  
        
    } 
    // Tests unitaires
    @Test
    void testCalculateInvoiceWithReduction() {
        Customer customer = new Customer("BigCo", "CUST123", 200); // Plus de 150 points pour la réduction de fidélité
        Performance hamletPerformance = new Performance(new Play("Hamlet", Play.PlayType.TRAGEDY), 35);
        Invoice invoice = new Invoice(customer, List.of(hamletPerformance));
        invoice.calculateInvoice();
        assertTrue(invoice.reduction);
    }
    @Test
    void testAddReductionToHtmlToText() {
        Customer customer = new Customer("BigCo", "CUST123", 200); // Plus de 150 points pour la réduction de fidélité
        Performance hamletPerformance = new Performance(new Play("Hamlet", Play.PlayType.TRAGEDY), 35);
        Invoice invoice = new Invoice(customer, List.of(hamletPerformance));
        invoice.calculateInvoice();
        String htmlResult = invoice.toHTML();
        String textResult = invoice.toText();

        assertTrue(htmlResult.contains("** Reduction of 15 euros applied **"));
        assertTrue(textResult.contains("** Reduction of 15 euros applied **"));
    }
    @Test
    void testGetCustomerNumber() {
        Customer customer = new Customer("John Doe", "CUST123", 100);
        assertEquals("CUST123", customer.getCustomerNumber());
    }
    @Test
    void testDeductLoyaltyPointsInsufficientPoints() {
        Customer customer = new Customer("John Doe", "CUST123", 100);
        // Capture the output of System.err.println()
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setErr(new PrintStream(outputStream));
        
        customer.deductLoyaltyPoints(150); // Attempt to deduct more points than available

        // Reset System.err to the original PrintStream
        System.setErr(System.err);

        // Assert that the error message contains the expected text
        assertTrue(outputStream.toString().contains("Insufficient loyalty points to deduct."));
        assertEquals(100, customer.getLoyaltyPoints()); // Loyalty points should remain unchanged
    }
}