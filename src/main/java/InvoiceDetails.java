public class InvoiceDetails {
    private double totalAmount;
    private int volumeCredits;
  
    public InvoiceDetails(double totalAmount, int volumeCredits) {
      this.totalAmount = totalAmount;
      this.volumeCredits = volumeCredits;
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
