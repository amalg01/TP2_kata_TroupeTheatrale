import java.util.*;

public class Invoice {

  public String customer;
  public List<Performance> performances;
  public InvoiceDetails invoiceDetails;

  public Invoice(String customer, List<Performance> performances) {
    this.customer = customer;
    this.performances = performances;

    this.invoiceDetails = new InvoiceDetails(0.0, 0);
  }
}
