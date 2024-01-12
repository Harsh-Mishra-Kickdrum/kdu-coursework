package question_2;
import java.util.*;

/**
 * TicketReservation class handles the logic for booking and canceling flights.
 */

public class TicketReservation {
    private static final int CONFIRMED_LIST_LIMIT = 10;
    private static final int WAITING_LIST_LIMIT = 3;

    private List<Passenger> confirmedList = new ArrayList<>();
    private Deque<Passenger> waitingList = new ArrayDeque<>();

    /**
     * Simulates booking a flight for a passenger.
     *
     * @param firstName          Passenger's first name
     * @param lastName           Passenger's last name
     * @param age                Passenger's age
     * @param gender             Passenger's gender
     * @param travelClass        Travel class (e.g., "Economy" or "Business")
     * @param confirmationNumber Confirmation number for the booking
     * @return True if booking is successful, false otherwise
     */
    public boolean bookFlight(String firstName, String lastName, int age, String gender,
                              String travelClass, String confirmationNumber) {

        //checking the condition whether ticket can be confirmed or not
        if (confirmedList.size() == CONFIRMED_LIST_LIMIT && waitingList.size() == WAITING_LIST_LIMIT)
            return false;

        Passenger newPassenger = new Passenger(firstName, lastName, age, gender, travelClass, confirmationNumber);

        if (confirmedList.size() == CONFIRMED_LIST_LIMIT) {
            waitingList.add(newPassenger);
            return true;
        }

        confirmedList.add(newPassenger);
        return true;
    }

    /**
     * Simulates canceling a flight for a passenger.
     *
     * @param confirmationNumber Confirmation number of the passenger to be canceled
     * @return True if cancellation is successful, false otherwise
     */

    public boolean cancelFlight(String confirmationNumber) {
        Iterator<Passenger> confirmedIterator = confirmedList.iterator();
        boolean checkRemoved = removePassenger(confirmedIterator, confirmationNumber);

        if (checkRemoved) {
            if (!waitingList.isEmpty()) {
                Passenger nextPassenger = waitingList.poll();
                confirmedList.add(nextPassenger);
            }
            return true;
        } else {
            Iterator<Passenger> waitingIterator = waitingList.iterator();
            return removePassenger(waitingIterator, confirmationNumber);
        }
    }

    /**
     * Removes a passenger from the specified list based on the confirmation number.
     *
     * @param iterator           Iterator for the list
     * @param confirmationNumber Confirmation number of the passenger to be removed
     * @return True if the passenger is successfully removed, false otherwise
     */
    private boolean removePassenger(Iterator<Passenger> iterator, String confirmationNumber) {
        while (iterator.hasNext()) {
            Passenger passenger = iterator.next();

            if (passenger.getConfirmationNumber().equals(confirmationNumber)) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }
}

