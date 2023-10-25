import java.util.ArrayList;
import java.util.List;

public class InvoiceDetails {
    List<PlayDetails> playDetails;
    private double totalAmount;
    private int volumeCredits;
  
    public InvoiceDetails(double totalAmount, int volumeCredits) {
      this.totalAmount = totalAmount;
      this.volumeCredits = volumeCredits;
      this.playDetails = new ArrayList<>();
    }
  
    public double getTotalAmount() {
      return totalAmount;
    }
  
    public int getVolumeCredits() {
      return volumeCredits;
    }

    public void setTotalAmount(double totalAmount) {
      this.totalAmount= totalAmount;
    }
  
    public void setVolumeCredits(int volumeCredits) {
      this.volumeCredits= volumeCredits;
    }
}
