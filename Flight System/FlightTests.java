import tester.*;

import java.time.LocalDateTime;

class FlightTests {
    /**************************************************************************
     * 
     * Flight class tests
     * 
     *************************************************************************/

    boolean testCostInSkyPoints(Tester t) {
        Flight f = new Flight(
                "CA 987",
                "ZBAA",
                "KLAX",
                "B77W",
                1000.0,
                LocalDateTime.now(),
                LocalDateTime.now().plusHours(13),
                30, 10);

        return t.checkExpect(f.costInSkyPoints(), 100000.0);//gud
    }

    boolean testIsOccupied(Tester t) {
        Flight f = new Flight(
                "CA 987",
                "ZBAA",
                "KLAX",
                "B77W",
                1000.0,
                LocalDateTime.now(),
                LocalDateTime.now().plusHours(13),
                30, 10);
        Passenger p = new Passenger(1000, "Frodo", "Baggins", 1000, 10000.0);

        // set seat 4D to be occupied
        // because our system is zero-indexed, seat "4D" would be (3, 'D').
        f.ticketsBooked[3][3] = new Ticket(p, f, false, 3, 'D');

        return t.checkExpect(f.isOccupied(3, 'D'), true)//gud
                && t.checkExpect(f.isOccupied(4, 'A'), false);//gud
    }

    /**
     * Test that Flight.isOccupied returns `true` for all out of bound
     * seat indexes.
     */
    boolean testIsOccupiedOutOfBounds(Tester t) {
        Flight f = new Flight(
                "CA 987",
                "ZBAA",
                "KLAX",
                "B77W",
                1000.0,
                LocalDateTime.now(),
                LocalDateTime.now().plusHours(13),
                30, 10);
        for (int i = 0; i < f.ticketsBooked.length; i++){
        for (int r = 0; r < f.ticketsBooked[i].length; r++){
                f.ticketsBooked[i][r] = null;
            }
        }

        return t.checkExpect(f.isOccupied(100, 'A'), true)//gud
                && t.checkExpect(f.isOccupied(3, 'Z'), true)//gud
                && t.checkExpect(f.isOccupied(-2, 'a'), true)//gud
                && t.checkExpect(f.isOccupied(30, 'A'), true) // valid = 0-29 gud
                && t.checkExpect(f.isOccupied(20, 'K'), true); // valid = 'A'-'J' gud
    }

    /**
     * Flight.getNumBooked()
     */
    boolean testGetNumBooked(Tester t) {
         Flight f = new Flight(
                "CA 987",
                "ZBAA",
                "KLAX",
                "B77W",
                1000.0,
                LocalDateTime.now(),
                LocalDateTime.now().plusHours(13),
                30, 10);
        Flight g = new Flight(
                "CA 987",
                "ZBAA",
                "KLAX",
                "B77W",
                1000.0,
                LocalDateTime.now(),
                LocalDateTime.now().plusHours(13),
                30, 10);
        Flight h = new Flight(
                "CA 987",
                "ZBAA",
                "KLAX",
                "B77W",
                1000.0,
                LocalDateTime.now(),
                LocalDateTime.now().plusHours(13),
                30, 10);
        Passenger p = new Passenger(1000, "Frodo", "Baggins", 1000, 10000.0);

        for (int i = 0; i < f.ticketsBooked.length; i++){
            for (int r = 0; r < f.ticketsBooked[i].length; r++){
                f.ticketsBooked[i][r] = null;
            }
        }
        for (int i = 0; i < g.ticketsBooked.length; i++){
            for (int r = 0; r < g.ticketsBooked[i].length; r++){
                g.ticketsBooked[i][r] = null;
            }
        }
        for (int i = 0; i < h.ticketsBooked.length; i++){
            for (int r = 0; r < h.ticketsBooked[i].length; r++){
                h.ticketsBooked[i][r] = new Ticket(p, h, false, i, (char)('A' + r));
            }
        }
        
        f.ticketsBooked[3][3] = new Ticket(p, f, false, 3, 'D');
        return t.checkExpect(f.getNumBooked(), 1) && //gud
               t.checkExpect(g.getNumBooked(), 0) && //gud
               t.checkExpect(h.getNumBooked(), 300); //gud
    }

    /**
     * Flight.isFull()
     */
    boolean testIsFull(Tester t) {
        Flight f = new Flight(
                "CA 987",
                "ZBAA",
                "KLAX",
                "B77W",
                1000.0,
                LocalDateTime.now(),
                LocalDateTime.now().plusHours(13),
                30, 10);
        Flight g = new Flight(
                "CA 987",
                "ZBAA",
                "KLAX",
                "B77W",
                1000.0,
                LocalDateTime.now(),
                LocalDateTime.now().plusHours(13),
                30, 10);
        Passenger p = new Passenger(1000, "Frodo", "Baggins", 1000, 10000.0);
        for (int i = 0; i < f.ticketsBooked.length; i++){
            for (int r = 0; r < f.ticketsBooked[i].length; r++){
                f.ticketsBooked[i][r] = new Ticket(p, f, false, i, (char)('A' + r));
            }
        }
        for (int i = 0; i < g.ticketsBooked.length; i++){
            for (int r = 0; r < g.ticketsBooked[i].length; r++){
                g.ticketsBooked[i][r] = new Ticket(p, g, false, i, (char)('A' + r));
            }
        }
        g.ticketsBooked[3][3] = null;//makes flight g NOT FULL
        return t.checkExpect(f.isFull(), true)&& //gud
               t.checkExpect(g.isFull(), false); //gud
    }

    /**
     * Flight.getFlightPlanInfo()
     */
    boolean testGetFlightPlanInfo(Tester t) {
        Flight f = new Flight(
                "CA 987",
                "ZBAA",
                "KLAX",
                "B77W",
                1000.0,
                LocalDateTime.now(),
                LocalDateTime.now().plusHours(13),
                30, 10);
        Passenger p = new Passenger(1000, "Frodo", "Baggins", 1000, 10000.0);
        f.ticketsBooked[3][3] = new Ticket(p, f, false, 3, 'D');

        
        return t.checkExpect(f.getFlightPlanInfo(), "CA 987: ZBAA/KLAX B77W 1/300"); //gud
    }

    /**************************************************************************
     * 
     * Passenger class tests
     * 
     **************************************************************************/

    /**
     * Passenger.book(Flight flight, int row, char seat)
     * when the seat in the parameters is occupied.
     */
    boolean testBookWhenOccupied(Tester t) {
        Flight f = new Flight(
                "CA 987",
                "ZBAA",
                "KLAX",
                "B77W",
                1000.0,
                LocalDateTime.now(),
                LocalDateTime.now().plusHours(13),
                30, 10);
        Passenger p = new Passenger(1000, "Frodo", "Baggins", 1000, 10000.0);
        f.ticketsBooked[3][3] = new Ticket(p, f, false, 3, 'D');
        return t.checkExpect(p.book(f, 3, 'D'), null)&& //gud
               t.checkExpect(p.book(f, 100, 'D'), null)&& //gud
               t.checkExpect(p.book(f, 3, 'Z'), null); //gud
    }

    /**
     * Passenger.book(Flight flight, int row, char seat)
     * when the booking is successful.
     */
    boolean testBookSuccess(Tester t) {
        Flight f = new Flight(
                "CA 987",
                "ZBAA",
                "KLAX",
                "B77W",
                1000.0,
                LocalDateTime.now(),
                LocalDateTime.now().plusHours(13),
                30, 10);
        Passenger p1 = new Passenger(1000, "Frodo", "Baggins", 1000, 10000.0);
        Passenger p2 = new Passenger(1000, "Frodo", "Baggins", 100000, 10000.0);
        Passenger p3 = new Passenger(1000, "Frodo", "Baggins", 10, 10.0);
        Passenger p4 = new Passenger(1000, "Frodo", "Baggins", 100000, 10000.0);

        for (int i = 0; i < f.ticketsBooked.length; i++){
            for (int r = 0; r < f.ticketsBooked[i].length; r++){
                f.ticketsBooked[i][r] = null;
            }
        }

        Ticket t1 = p1.book(f, 3, 'D');
        p2.book(f, 4, 'D');
        return t.checkExpect(p1.cashBalance, 9000.0) && //gud
               t.checkExpect(p2.skyPoints, 0) && //gud
               t.checkExpect(f.ticketsBooked[3]['D' - 'A'], t1) && //gud
               t.checkExpect(p3.book(f, 5, 'D'), null) && //tests broke passenger
               t.checkExpect(p2.book(f, 1000, 'Z'), null)&& // tests out of bounds
               t.checkExpect(p4.book(f, 3, 'D'), null); //tests booking alr booked seat
    }

    /**************************************************************************
     * 
     * Ticket class tests
     * 
     **************************************************************************/

    /*
     * Ticket.getTicketInfo()
     */
    boolean testGetTicketInfo(Tester t) {
        Flight f = new Flight(
                "CA 987",
                "ZBAA",
                "KLAX",
                "B77W",
                1000.0,
                LocalDateTime.now(),
                LocalDateTime.now().plusHours(13),
                30, 10);
        Passenger p = new Passenger(1000, "Frodo", "Baggins", 1000, 10000.0);
        Ticket t1 =  new Ticket(p, f, false, 3, 'D');
        Ticket t2 =  new Ticket(p, f, false, 4, 'D');

        for (int i = 0; i < f.ticketsBooked.length; i++){
            for (int r = 0; r < f.ticketsBooked[i].length; r++){
                f.ticketsBooked[i][r] = null;
            }
        }
        f.ticketsBooked[3][3] = new Ticket(p, f, false, 3, 'D');
        f.ticketsBooked[4][3] = new Ticket(p, f, false, 4, 'D');
        t2.cancelled = true;

        return t.checkExpect(t1.getTicketInfo(), "CA 987 (ZBAA/KLAX) @4D Baggins, Frodo")&& //gud
               t.checkExpect(t2.getTicketInfo(), "CA 987 (ZBAA/KLAX) @5D Baggins, Frodo [CANCELLED]"); // gud
    }
    /**
     * Ticket.cancel()
     */
    boolean testCancel(Tester t) {

        Flight f = new Flight(
                "CA 987",
                "ZBAA",
                "KLAX",
                "B77W",
                1000.0,
                LocalDateTime.now(),
                LocalDateTime.now().plusHours(13),
                30, 10);
        Passenger p = new Passenger(1000, "Frodo", "Baggins", 1000, 10000.0);
        Passenger p2 = new Passenger(1000, "Frodo", "Baggins", 1000, 10000.0);


        for (int i = 0; i < f.ticketsBooked.length; i++){
            for (int r = 0; r < f.ticketsBooked[i].length; r++){
                f.ticketsBooked[i][r] = null;
            }
        }
        Ticket t1 =  new Ticket(p, f, false, 3, 'D');
        Ticket t2 =  new Ticket(p, f, false, 4, 'D');
        Ticket t3 =  new Ticket(p, f, false, 5, 'D');
        Ticket t4 =  new Ticket(p, f, false, 4, 'D');
        Ticket t5 =  new Ticket(p2, f, false, 6, 'D');




        f.ticketsBooked[3][3] = t1;
        f.ticketsBooked[6][3] = t5;


        boolean is_t1Cancelled = t1.cancel();

        t3.cancelled = true;

        t5.cancel();

        t4.cancelDeadline = LocalDateTime.now().minusHours(1000);
        
        return t.checkExpect(f.ticketsBooked[3][3], null) && //tests if it makes the said ticket null
               t.checkExpect(t1.cancel(), true) && // tests if it returns proper boolean after successful cancel
               t.checkExpect(t2.cancel(), true) && // tests cancelling on a null ticket
               t.checkExpect(t3.cancel(), true) && // tests cancelling on an already cancelled Ticket
               t.checkExpect(t4.cancel(), false); // tests if a flight isn't cancelled yet but past cancelDeadline
               
    }
}
