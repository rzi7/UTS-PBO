
public class Main {
    public static void main(String[] args) {
        TravelAgent agent = new TravelAgent();

        Trip paris = new Trip("Paris", 15000000, "1-MAY-2024", Trip.TripType.FLIGHT, 2);
        Trip newYork = new Trip("New York", 20000000, "15-MAY-2024", Trip.TripType.FLIGHT, 3);
        Trip london = new Trip("London", 18000000, "22-APR-2024", Trip.TripType.HOTEL, 10);
        Trip tokyo = new Trip("Tokyo", 8000000, "25-APR-2024", Trip.TripType.PACKAGE, 7);
        agent.addTrip(paris);
        agent.addTrip(newYork);
        agent.addTrip(london);
        agent.addTrip(tokyo);
        agent.showAvailableTrip();
        System.out.println("-----------------------------------------------------------------------------------------");

        Customer resti = new Customer("Resti", "Resti@gmail.com");
        Customer hanif = new Customer("Hanif", "Hanif@gmail.com");
        Customer aziz = new Customer("Aziz", "Aziz@gmail.com");
        Customer revan = new Customer("Revan", "Revan@gmail.com");

        agent.bookTrip(resti, paris);
        agent.bookTrip(hanif, paris);
        agent.bookTrip(revan, newYork);
        agent.bookTrip(aziz, paris);
        agent.bookTrip(aziz, newYork);
        agent.bookTrip(aziz, newYork);

        System.out.println("-----------------------------------------------------------------------------------------");
        agent.showAvailableTrip();
        System.out.println("-----------------------------------------------------------------------------------------");

        agent.cancelBooking(resti.getEmail(), paris);
        agent.cancelBooking(hanif.getEmail(), paris);
        agent.cancelBooking(aziz.getEmail(), paris);
        System.out.println("-----------------------------------------------------------------------------------------");
        agent.showAvailableTrip();
        System.out.println("-----------------------------------------------------------------------------------------");

        // OPSIONAL METHOD
        System.out.println("OUTPUT OPSIONAL");
        agent.getBookingsByCustomerEmail(resti.getEmail());
        agent.getBookingsByCustomerEmail(hanif.getEmail());
        agent.getBookingsByCustomerEmail(revan.getEmail());
        agent.getBookingsByCustomerEmail(aziz.getEmail());
        System.out.println("-----------------------------------------------------------------------------------------");
        agent.getAvailableTripsByType(Trip.TripType.FLIGHT);
        System.out.println("-----------------------------------------------------------------------------------------");
        agent.getAvailableTripsByDate("20-MAY-2024");
        agent.getAvailableTripsByDate("1-MAY-2024");

    }
}
