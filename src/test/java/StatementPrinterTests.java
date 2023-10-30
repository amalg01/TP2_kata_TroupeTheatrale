import org.junit.jupiter.api.Test;
import java.util.List;

import static org.approvaltests.Approvals.verify;
import static org.approvaltests.Approvals.verifyHtml;
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
}