import java.text.NumberFormat;
import java.util.*;

public class Invoice {

  public String customer;
  public List<Performance> performances;
  public InvoiceDetails invoiceDetails;

  public Invoice(String customer, List<Performance> performances) {
    this.customer = customer;
    this.performances = performances;

    // on instancie receipt pour ne pas avoir l'erreur NullPointerException
    this.invoiceDetails = new InvoiceDetails(0.0, 0);

  }

  public void calculateInvoice(HashMap<String, Play> plays) {

    double totalAmount = 0.0;
    int volumeCredits = 0;

    for (Performance perf : this.performances) {
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
      this.invoiceDetails.playDetails.add(new PlayDetails(play.getName(), thisAmount, perf.audience)); // ajouter les
                                                                                                       // détails de la
                                                                                                       // pice dans
                                                                                                       // notre liste de
                                                                                                       // PlayDetails

      // add volume credits
      volumeCredits += Math.max(perf.audience - 30, 0);
      // add extra credit for every ten comedy attendees
      if (Play.PlayType.COMEDY.equals(play.getType()))
        volumeCredits += Math.floor(perf.audience / 5);

      totalAmount += thisAmount;
    }
    this.invoiceDetails.setTotalAmount(totalAmount);
    this.invoiceDetails.setVolumeCredits(volumeCredits);
  }

  public String toText() {
    StringBuffer result = new StringBuffer(String.format("Statement for %s\n", this.customer));

    NumberFormat frmt = NumberFormat.getCurrencyInstance(Locale.US);

    for (PlayDetails play : this.invoiceDetails.playDetails) {

      result.append(String.format("  %s: %s (%s seats)\n", play.getPlayName(), frmt.format(play.getPricePaid()),
      play.getSeatsSold()));
    }
    result.append(String.format("Amount owed is %s\n", frmt.format(this.invoiceDetails.getTotalAmount())));
    result.append(String.format("You earned %s credits\n", this.invoiceDetails.getVolumeCredits()));
    return result.toString();
  }
}
