import java.util.*;

public class StatementPrinter {

  public String print(Invoice invoice, HashMap<String, Play> plays) {
    invoice.calculateInvoice(plays);
    
    return invoice.toText();
  }

} 
  