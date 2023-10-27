//import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.List;
// import java.util.Map;

import static org.approvaltests.Approvals.verify;
import static org.approvaltests.Approvals.verifyHtml;

public class StatementPrinterTests {

    @Test
    void exampleStatement() {

        HashMap<String, Play> plays = new HashMap<>();
        plays.put("hamlet",  new Play("Hamlet", Play.PlayType.TRAGEDY));
        plays.put("as-like",  new Play("As You Like It", Play.PlayType.COMEDY));
        plays.put("othello",  new Play("Othello", Play.PlayType.TRAGEDY));

        Customer customer = new Customer("BigCo", "CUST123");
        Invoice invoice = new Invoice(customer, List.of(
                new Performance("hamlet", 55),
                new Performance("as-like", 35), 
                new Performance("othello", 40)));

        invoice.calculateInvoice(plays);
        verify(invoice.toText());
    }

    @Test
    void exampleStatement2() {

        HashMap<String, Play> plays = new HashMap<>();
        plays.put("hamlet",  new Play("Hamlet", Play.PlayType.TRAGEDY));
        plays.put("as-like",  new Play("As You Like It", Play.PlayType.COMEDY));
        plays.put("othello",  new Play("Othello", Play.PlayType.TRAGEDY));

        Customer customer2 = new Customer("BigCo", "CUST123");
        Invoice invoice = new Invoice(customer2, List.of(
                new Performance("hamlet", 55),
                new Performance("as-like", 35), 
                new Performance("othello", 40)));

        invoice.calculateInvoice(plays); 
        var result2 = invoice.toHTML();
 
        verifyHtml(result2);  
        
    } 

} 


