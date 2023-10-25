public class PlayDetails {
    private String playName;
    private double pricePaid;
    private int seatsSold;

    public PlayDetails(String playName, double pricePaid, int seatsSold) {
        this.playName = playName;
        this.pricePaid = pricePaid;
        this.seatsSold = seatsSold;
    }

    public String getPlayName() {
        return playName;
    }

    public double getPricePaid() {
        return pricePaid;
    }

    public int getSeatsSold() {
        return seatsSold;
    }
} 

