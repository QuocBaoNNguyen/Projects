    class Passenger {
    int skyId;
    String firstName;
    String lastName;
    int skyPoints;
    double cashBalance;

    Passenger(int skyId, String firstName, String lastName,
            int skyPoints, double cashBalance) {
        this.skyId = skyId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.skyPoints = skyPoints;
        this.cashBalance = cashBalance;
    }

    Ticket book(Flight flight, int row, char seat) {
        if (flight.isOccupied(row, seat)) {
            System.out.println("Seat occupied!");
            return null;
        }
        if ((flight.ticketsBooked.length <= row) || (flight.ticketsBooked[row].length <= seat - 'A')){
            System.out.println("Out of seat range!");
            return null;
        }

    double skyPointsCost = flight.costInSkyPoints();
    boolean purchasedWithSkyPoints = false;

        if (this.skyPoints >= skyPointsCost){
            this.skyPoints -= skyPointsCost;
            purchasedWithSkyPoints = true;
            System.out.println("Purchased with SkyPoints! Remaining:" + this.skyPoints);
        }   else if (this.cashBalance > flight.cost) {
            this.cashBalance -= flight.cost;
            System.out.println("Purchased with CashBalance! Remaining:" + this.cashBalance);

        }   else {
            System.out.println("Not enough funds!");

            return null;
    }
    Ticket ticket = new Ticket(this, flight, purchasedWithSkyPoints, row, seat);
    flight.ticketsBooked[row][seat - 'A'] = ticket;

    return ticket;
}
}
