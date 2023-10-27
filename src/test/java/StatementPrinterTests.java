import org.junit.jupiter.api.Test;
import java.util.List;

import static org.approvaltests.Approvals.verify;
import static org.approvaltests.Approvals.verifyHtml; 

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

}