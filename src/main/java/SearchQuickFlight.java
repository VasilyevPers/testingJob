import java.util.*;

public class SearchQuickFlight {
    private final ParsingJsonFile parsingJsonFile;
    private List<Ticket> quickTicketsList;
    private String departureCity;
    private String destinationCity;
    public SearchQuickFlight (String departureCity, String destinationCity) {
        this.departureCity = departureCity;
        this.destinationCity = destinationCity;
        parsingJsonFile = new ParsingJsonFile();
    }

    public Map<String, List<Ticket>> sortsTicketsByCompany () {
        Map<String, List<Ticket>> sortedTicketsByCompany = new HashMap<>();
        for (Ticket ticket : parsingJsonFile.getTicketList()) {
            if (!ticket.getOriginName().equals(departureCity) || !ticket.getDestinationName().equals(destinationCity)) {
                continue;
            }
            if (sortedTicketsByCompany.containsKey(ticket.getCarrier())) {
                sortedTicketsByCompany.get(ticket.getCarrier()).add(ticket);
            } else {
                List<Ticket> tickets = new ArrayList<>();
                tickets.add(ticket);
                sortedTicketsByCompany.put(ticket.getCarrier(), tickets);
            }
        }
        return sortedTicketsByCompany;
    }

    private void createQuickTicketList () {
        quickTicketsList = new ArrayList<>();
        for (Map.Entry<String, List<Ticket>> ticketsCompanyList : sortsTicketsByCompany().entrySet()) {
            if (ticketsCompanyList.getValue().size() == 1) {
                quickTicketsList.add(ticketsCompanyList.getValue().get(0));
            } else {
                Ticket minQuick = ticketsCompanyList.getValue().stream().sorted().toList().get(0);
                quickTicketsList.add(minQuick);
            }
        }
    }

    public void calculateFlightTime (Ticket ticket) {
        long time = new Ticket().calculatesTheFlightTime(ticket);
        int hoursCount = (int) (time / 3600);
        time -= hoursCount * 3600L;
        int minutesCount = (int)time / 60;

        System.out.println("Время полета для авиаперевозчика " + ticket.getCarrier() +" составляет: " + "\n"
                + "\t" + hoursCount + " часов" +"\n"
                + "\t" + minutesCount + " минут");
    }

    public List<Ticket> getQuickTicketsList() {
        createQuickTicketList();
        return quickTicketsList;
    }
}
