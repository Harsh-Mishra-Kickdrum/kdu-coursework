package question_2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
        * Main class to demonstrate the Flight Reservation System.
        */
public class FlightReservationDemo {
    private static final Logger logger = LoggerFactory.getLogger(FlightReservationDemo.class);

    public static void main(String[] args) {
        logger.info("Flight Reservation Demo started");

        TicketReservation flightSystem = new TicketReservation();

        // Booking flights
        flightSystem.bookFlight("Harsh", "Mishra", 23, "Male", "Economy", "123abc");
        flightSystem.bookFlight("Khushi", "Rani", 25, "Female", "Business", "DEF456");

        // Cancelling a flight
        String cancellationCode = "DEF456";
        boolean cancellationResult = flightSystem.cancelFlight(cancellationCode);
        logger.info("Cancellation Result: {}", cancellationResult);

        logger.info("Flight Reservation Demo completed");


    }

}
