import java.time.*;

class Flight {

    String flightNo; /* Flight Number. E.g. "UAL 2247" */
    String departureAirport; /* ICAO code, e.g. "KSAN", "EDDF" */
    String arrivalAirport; /* ICAO code, e.g. "KSAN", "EDDF" */
    String aircraftType; /* ICAO code, e.g. "B738", "A388" */
    double cost; /* cost of the ticket. */
    LocalDateTime departureTime;
    LocalDateTime arrivalTime;
    int capacity; /* seating capacity */
    int numRows;
    int seatsPerRow;
    Ticket ticketsBooked[][];


    public Flight(String flightNo, String departureAirport,
            String arrivalAirport, String aircraftType,
            double cost, LocalDateTime departureTime,
            LocalDateTime arrivalTime, int numRows, int seatsPerRow) {
        this.flightNo = flightNo;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.aircraftType = aircraftType;
        this.cost = cost;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.numRows = numRows;
        this.seatsPerRow = seatsPerRow;
        this.capacity = numRows * seatsPerRow;
        this.ticketsBooked = new Ticket [this.numRows][this.seatsPerRow];

    }

    double costInSkyPoints() {
        return this.cost * 100;
    }

    boolean isOccupied(int row, char seat) {
        //only need for loop if LOOPING for a certain array value in an array

        //if for row/seat is IN of bounds
        if ((row < 0) || (ticketsBooked.length <= row) || (ticketsBooked[row].length <= seat - 'A') ){
                return true;
        }
        if ((ticketsBooked.length > row) && (ticketsBooked[row].length > seat - 'A')){
                return this.ticketsBooked[row][seat - 'A'] != null;
            }
        

        return false; 
    }

    int getNumBooked() {
        int totalTickets = this.capacity;//should be OUTSIDE the loop

        for (int i = 0; i < ticketsBooked.length; i++){
            for (int r = 0; r <ticketsBooked[i].length; r++){
                if (this.ticketsBooked[i][r] == null){
                    totalTickets--;
                }
            }
        }
        return totalTickets; 
    }

    boolean isFull() {
         for (int i = 0; i < ticketsBooked.length; i++){
            for (int r = 0; r < ticketsBooked[i].length; r++){
                if (this.ticketsBooked[i][r] == null){
                    return false;
                }
            }
        }
        return true; 
    }

    /**
     * Get Flight Plan Info String
     * "<flightNo>: <departureAirport>/<arrivalAirport> <aircraftType>
     * <getNumBooked()>/<capacity>"
     */
    String getFlightPlanInfo() {
        return this.flightNo + ": " + this.departureAirport + "/" + 
        this.arrivalAirport + " " + this.aircraftType + " " + 
        this.getNumBooked() + "/" + this.capacity; // TODO
    }
}
