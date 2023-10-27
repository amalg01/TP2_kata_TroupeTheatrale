import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.*;

public class Invoice {

  public Customer customer;
  public List<Performance> performances;
  public InvoiceDetails invoiceDetails;

  public Invoice(Customer customer, List<Performance> performances) {
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

      // ajouter les détails de la pièce dans notre liste de PlayDetails
      this.invoiceDetails.playDetails.add(new PlayDetails(play.getName(), thisAmount, perf.audience));

      // add volume credits
      volumeCredits += Math.max(perf.audience - 30, 0);
      // add extra credit for every ten comedy attendees
      if (Play.PlayType.COMEDY.equals(play.getType()))
        volumeCredits += Math.floor(perf.audience / 5);

      totalAmount += thisAmount;
    }
    // Apply discount and deduct loyalty points if the customer has more than 150
    // points
    if (customer.getLoyaltyPoints() > 150) {
      totalAmount -= 15.0;
      customer.deductLoyaltyPoints(150);
    }
    this.invoiceDetails.setTotalAmount(totalAmount);
    this.invoiceDetails.setVolumeCredits(volumeCredits);
  }

  public String toText() {
    StringBuffer result = new StringBuffer(String.format("Statement for %s\n", this.customer.getName()));
    NumberFormat frmt = NumberFormat.getCurrencyInstance(Locale.US);

    for (PlayDetails play : this.invoiceDetails.playDetails) {

      result.append(String.format("  %s: %s (%s seats)\n", play.getPlayName(), frmt.format(play.getPricePaid()),
          play.getSeatsSold()));
    }
    result.append(String.format("Amount owed is %s\n", frmt.format(this.invoiceDetails.getTotalAmount())));
    result.append(String.format("You earned %s credits\n", this.invoiceDetails.getVolumeCredits()));

    try (BufferedWriter writer = new BufferedWriter(
        new FileWriter("./src/factures/facture_" + this.customer.getName() + ".txt"))) {
      writer.write(result.toString());
    } catch (IOException e) {
      System.err.println("Une erreur s'est produite lors de la création du fichier : " + e.getMessage());
    }

    return result.toString();
  }

  public String toHTML() {
    StringBuffer result = new StringBuffer();
    result.append("<html><head><title>Invoice</title></head><body>");

    result.append(
        String.format("<h1>Invoice</h1><ul><li><strong>Client :</strong> %s</li></ul>", this.customer.getName()));
    result.append("<table border=\"1\"><tr><th>Play</th><th>Seats sold</th><th>Price</th></tr>");

    NumberFormat frmt = NumberFormat.getCurrencyInstance(Locale.US);

    for (PlayDetails play : this.invoiceDetails.playDetails) {
      result.append(String.format("<tr><td>%s</td><td>%s</td><td>%s</td></tr>",
          play.getPlayName(), play.getSeatsSold(), frmt.format(play.getPricePaid())));
    }

    result.append(String.format("<tr><td colspan=\"2\"><strong>Total owed:</strong></td> <td>"
        + frmt.format(this.invoiceDetails.getTotalAmount()) + "</td></tr>"));
    result.append(String.format("<tr><td colspan=\"2\"><strong>Fidelity points earned: </strong></td><td>"
        + this.invoiceDetails.getVolumeCredits() + "</td></tr>"));
    result.append("</table>");
    result.append("<p>Pay within 30 days, and all will be well!</p>");
    // Terminez la construction de la chaîne HTML
    result.append("</body></html>");
    try (BufferedWriter writer = new BufferedWriter(
        new FileWriter("./src/factures/facture_" + this.customer.getName() + ".html"))) {
      writer.write(result.toString());
    } catch (IOException e) {
      System.err.println("Une erreur s'est produite lors de la création du fichier : " + e.getMessage());
    }

    return result.toString();
  }

}
