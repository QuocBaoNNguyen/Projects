import java.time.*;

class Ticket {
    Passenger passenger;
    Flight flight;
    LocalDateTime purchaseTime;
    LocalDateTime cancelDeadline;
    boolean purchasedWithSkyPoints;
    boolean cancelled;
    int row;
    char seat;


    Ticket(Passenger passenger, Flight flight, boolean purchasedWithSkyPoints, int row, char seat) {
        this.passenger = passenger;
        this.flight = flight;
        this.purchasedWithSkyPoints = purchasedWithSkyPoints;

        this.cancelled = false;
        this.purchaseTime = LocalDateTime.now();
        this.cancelDeadline = purchaseTime.plusDays(1);
        this.row = row;
        this.seat = seat;

    }

    boolean cancel() {
        if (this.cancelled || LocalDateTime.now().isAfter(this.cancelDeadline)) {
            return this.cancelled;
        }

        if (flight.ticketsBooked[row][seat - 'A'] == null){
            return true;
        }

        if (this.purchasedWithSkyPoints) {
            this.passenger.skyPoints += this.flight.costInSkyPoints();
        } else {
            this.passenger.cashBalance += this.flight.cost * 0.95;
        }

        flight.ticketsBooked[row][seat - 'A'] = null;

        this.cancelled = true;
        return this.cancelled;
    }

    /**
     * Return info string for ticket
     * <flightNo> (<departureAirport>/<arrivalAirport>) @<row><seat> <lastname>,
     * <firstname> <cancelled ? "[CANCELLED]" : "">
     */
    String getTicketInfo() {
        return flight.flightNo + " " + "(" + flight.departureAirport +
        "/" + flight.arrivalAirport + ") @" + (this.row + 1) + this.seat + " " +
        passenger.lastName + ", " + passenger.firstName + (cancelled ? " " + "[CANCELLED]" : ""); 
    }
}
