import java.text.NumberFormat;
import java.util.*;

public class StatementPrinter {

  public String print(Invoice invoice, HashMap<String, Play> plays) {
   
    double totalAmount = 0.0;
    int volumeCredits = 0;
    StringBuffer result = new StringBuffer(String.format("Statement for %s\n", invoice.customer));

    NumberFormat frmt = NumberFormat.getCurrencyInstance(Locale.US); 

    for (Performance perf : invoice.performances) {
      Play play = plays.get(perf.playID);
      double thisAmount = 0.0;

      switch (play.getType()) {
        case TRAGEDY:
          thisAmount = 400.0;
          if (perf.audience > 30) {
            thisAmount += 10.0 * (perf.audience - 30);
          }
          break;
        case COMEDY:
          thisAmount = 300.0;
          if (perf.audience > 20) {
            thisAmount += 100.0 + 5.0 * (perf.audience - 20);
          }
          thisAmount += 3.0 * perf.audience;
          break;
        default:
          throw new Error("unknown type: ${play.type}");
      }  

      // add volume credits
      volumeCredits += Math.max(perf.audience - 30, 0);
      // add extra credit for every ten comedy attendees
      if (Play.PlayType.COMEDY.equals(play.getType())) volumeCredits += Math.floor(perf.audience / 5);

      // print line for this order
      result.append(String.format("  %s: %s (%s seats)\n", play.getName(), frmt.format(thisAmount), perf.audience));
      totalAmount += thisAmount;
    } 
    result.append(String.format("Amount owed is %s\n", frmt.format(totalAmount)));
    result.append(String.format("You earned %s credits\n", volumeCredits));
    return result.toString();
  }

}
 