import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class TravelAgent {
    private ArrayList<Trip> trips;
    private HashMap<Customer, Trip> bookings;

    public TravelAgent() {
        trips = new ArrayList<>();
        bookings = new HashMap<>();
    }

    public void addTrip(Trip trip) {
        trips.add(trip);
    }

    public void showAvailableTrip() {
        System.out.println("Terdapat " + countAvailableTrips() + " trip yang masih tersedia :");
        for (Trip trip : trips) {
            if (trip.getQty() > 0) {
                SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
                System.out.println("Destinasi : " + trip.getDestination() + " -- Keberangkatan : " + sdf.format(trip.getDepartureDate()) + " -- Harga: " + trip.getPrice() + " -- Qty : " + trip.getQty() + " -- Jenis : " + trip.getTripType());
            }
        }
    }

    private int countAvailableTrips() {
        int count = 0;
        for (Trip trip : trips) {
            if (trip.getQty() > 0) {
                count++;
            }
        }
        return count;
    }

    public void bookTrip(Customer customer, Trip trip) {
        boolean isAvailable = false;
        for(Customer c : bookings.keySet()) {
            for (Trip t : bookings.values()) {
                if (c.equals(customer) && t.equals(trip)) {
                    isAvailable = true;
                    break;
                }
            }
        }

        if (isAvailable) {
            System.out.println("Pemesanan gagal, pengguna telah memiliki pesanan pada trip yang sama dengan id " + customer.getId());
            return;
        } else {
            if (trip.getQty() <= 0) {
                System.out.println("Pemesanan gagal, perjalanan ke " + trip.getDestination() + " telah habis terjual.");
                return;
            } else {
                trip.decreaseQty();
                String bookingID = generateBookingID(trip);
                customer.setId(bookingID);
                bookings.put(customer, trip);
                System.out.println("Pemesanan berhasil dilakukan dengan booking id " + bookingID);
            }
        }
    }

    private String generateBookingID(Trip trip) {
        return "00" + trip.getDestination() +trip.getTripType().toString()+ trip.getQty();
    }

    public void cancelBooking(String email, Trip trip) {
        boolean isAvailable = false;
        for (Customer c : bookings.keySet()) {
            for (Trip t : bookings.values()) {
                if (c.getEmail().equals(email) && t.equals(trip)) {
                    trip.increaseQty();
                    bookings.remove(c);
                    System.out.println("Pesanan dengan id booking " + generateBookingID(trip) + " berhasil dibatalkan");
                    isAvailable = true;
                    return;
                }
            }
        }
        if (!isAvailable) {
            System.out.println("Pesanan tidak ditemukan.");
        }        
    }

    // OPSIONAL METHOD

    public void getBookingsByCustomerEmail(String email) {
        boolean isAvailable = true;
        for (Customer c : bookings.keySet()) {
            if (c.getEmail().equals(email)) {
                System.out.println("Pemesanan untuk email " + email + " :");
                isAvailable = true;
                Trip trip = bookings.get(c);
                System.out.println("Booking ID : " + c.getId() + " -- Destinasi : " + trip.getDestination());
                break;
            } else {
                isAvailable = false;
            }
        }

        if (!isAvailable) {
            System.out.println("Tidak ditemukan pesanan untuk email " + email);
            return;
        }
    }

    public void getAvailableTripsByType(Trip.TripType tripType) {
        int count = 0;
        System.out.println("Ditemukan trip untuk jenis " + tripType.toString());
        for (Trip trip : trips) {
            if (trip.getTripType() == tripType && trip.getQty() > 0) {
                count++;
                SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
                System.out.println("Destinasi : " + trip.getDestination() + " -- Keberangkatan : " + sdf.format(trip.getDepartureDate()) + " -- Harga: " + trip.getPrice() + " -- Qty : " + trip.getQty() + " -- Jenis : " + trip.getTripType());
            }
        }
        if (count == 0) {
            System.out.println("Tidak ditemukan trip untuk jenis " + tripType.toString());
        }
    }

    public void getAvailableTripsByDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        try {
            Date searchDate = sdf.parse(date);
            int count = 0;
            System.out.println("Ditemukan trip untuk keberangkatan tanggal " + sdf.format(searchDate));
            for (Trip trip : trips) {
                if (trip.getDepartureDate().equals(searchDate) && trip.getQty() > 0) {
                    count++;
                    SimpleDateFormat sdf2 = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
                    System.out.println("Destinasi : " + trip.getDestination() +
                            " -- Keberangkatan : " + sdf2.format(trip.getDepartureDate()) +
                            " -- Harga: " + trip.getPrice() +
                            " -- Qty : " + trip.getQty() +
                            " -- Jenis : " + trip.getTripType());
                }
            }
            if (count == 0) {
                System.out.println("Tidak ditemukan trip untuk keberangkatan tanggal " + sdf.format(searchDate));
            }
        } catch (ParseException e) {
            System.out.println("Format tanggal salah, gunakan format dd-MMM-yyyy, contoh: 01-MAY-2024");
        }
    }
}
