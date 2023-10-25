import java.text.NumberFormat;
import java.util.*;

public class StatementPrinter {

  public String print(Invoice invoice, HashMap<String, Play> plays) {
    invoice.calculateInvoice(plays);
    
    StringBuffer result = new StringBuffer(String.format("Statement for %s\n", invoice.customer));

    NumberFormat frmt = NumberFormat.getCurrencyInstance(Locale.US);
    
    for (PlayDetails play : invoice.invoiceDetails.playDetails) {
      
      result.append(String.format("  %s: %s (%s seats)\n", play.getPlayName(), frmt.format(play.getPricePaid()), play.getSeatsSold()));
    }
    result.append(String.format("Amount owed is %s\n", frmt.format(invoice.invoiceDetails.getTotalAmount())));
    result.append(String.format("You earned %s credits\n", invoice.invoiceDetails.getVolumeCredits()));
    return result.toString();
  }

} 
 