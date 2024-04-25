import java.text.SimpleDateFormat;
import java.util.Date;

public class Trip {
    private String destination;
    private int price;
    private Date departureDate;
    public enum TripType {
        FLIGHT,
        HOTEL,
        PACKAGE
    }
    private TripType tripType;
    private int qty;

    public Trip(String destination, int price, String departureDate, TripType tripType, int qty) {
        this.destination = destination;
        this.price = price;
        this.tripType = tripType;
        this.qty = qty;

        try {
            this.departureDate = new SimpleDateFormat("dd-MMM-yyyy").parse(departureDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getDestination() {
        return destination;
    }

    public int getPrice() {
        return price;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public TripType getTripType() {
        return tripType;
    }

    public int getQty() {
        return qty;
    }

    public void decreaseQty() {
        qty--;
    }

    public void increaseQty() {
        qty++;
    }
}