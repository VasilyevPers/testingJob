import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;

public class SearchQuickFlight {

    public static List<Ticket> searchForAllTicketsAlongTheRoute (List<Ticket> allTicket, String departureCity, String destinationCity) {
        List<Ticket> sortedTicketsByCompany = new ArrayList<>();
        for (Ticket ticket : allTicket) {
            if (!ticket.getOriginName().equals(departureCity) || !ticket.getDestinationName().equals(destinationCity)) {
                continue;
            }
            sortedTicketsByCompany.add(ticket);
        }
        return sortedTicketsByCompany;
    }



    public static List<Ticket> createQuickTicketList (List<Ticket> listOfAllTicketsAlongTheRoute) {
        Map<String, List<Ticket>> sortedTicketsByCompany = new HashMap<>();
        List<Ticket> quickTicketsList = new ArrayList<>();
        for (Ticket ticket : listOfAllTicketsAlongTheRoute) {
            if (sortedTicketsByCompany.containsKey(ticket.getCarrier())) {
                sortedTicketsByCompany.get(ticket.getCarrier()).add(ticket);
            } else {
                List<Ticket> tickets = new ArrayList<>();
                tickets.add(ticket);
                sortedTicketsByCompany.put(ticket.getCarrier(), tickets);
            }

        }
        for(Map.Entry<String, List<Ticket>> companyTickets : sortedTicketsByCompany.entrySet()) {
            if (companyTickets.getValue().size() == 1) {
                quickTicketsList.add(companyTickets.getValue().get(0));
            } else {
                quickTicketsList.add(companyTickets.getValue().stream().sorted().toList().get(0));
            }
        }
        return quickTicketsList;
    }

    public static long flightTimeOfEpochSecond(Ticket ticket) {
        String start = ticket.getDepartureDate().toString() + "T" + ticket.getDepartureTime().toString();
        String finish = ticket.getArrivalDate().toString() + "T" + ticket.getArrivalTime().toString();

        LocalDateTime startFlight = LocalDateTime.parse(start);
        LocalDateTime finishFlight = LocalDateTime.parse(finish);

        return  finishFlight.toEpochSecond(ZoneOffset.of("Z")) - startFlight.toEpochSecond(ZoneOffset.of("Z"));
    }

    public static void calculateFlightTime (Ticket ticket) {
        long time = flightTimeOfEpochSecond(ticket);
        int hoursCount = (int) (time / 3600);
        time -= hoursCount * 3600L;
        int minutesCount = (int)time / 60;

        System.out.println("Время полета для авиаперевозчика " + ticket.getCarrier() +" составляет: " + "\n"
                + "\t" + hoursCount + " часов" +"\n"
                + "\t" + minutesCount + " минут");
    }
}
