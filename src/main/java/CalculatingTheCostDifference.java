import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class CalculatingTheCostDifference {
    private SearchQuickFlight quickFlight;
    private List<Ticket> ticketList;
    private int ticketPriceDifference;

    public CalculatingTheCostDifference (String departureCity, String destinationCity) {
        quickFlight = new SearchQuickFlight(departureCity, destinationCity);
        createTicketList();
    }

    private void createTicketList () {
        for (Map.Entry<String, List<Ticket>> sortedTicketsByCompany : quickFlight.sortsTicketsByCompany().entrySet()) {
            ticketList = new ArrayList<>();
            ticketList.addAll(sortedTicketsByCompany.getValue());
        }
    }

    private int calculatesTheAverageTicketPrice () {
        long totalTicketPrice = 0;
        for (Ticket ticket : ticketList) {
            totalTicketPrice += ticket.getPrice();
        }
        return (int) totalTicketPrice/ticketList.size();
    }

    private int calculatesTheMedianTicketPrice () {
        ticketList.sort(Comparator.comparing(Ticket::getPrice));
        int middleOfTheList = ticketList.size()/2;
        if (middleOfTheList*2 == ticketList.size()) {
            return (ticketList.get(middleOfTheList -1).getPrice()
                    + ticketList.get(middleOfTheList).getPrice())
                    / 2;
        }
        return ticketList.get(middleOfTheList).getPrice();
    }

    private void calculatesTicketPriceDifference () {
        ticketPriceDifference = calculatesTheAverageTicketPrice() - calculatesTheMedianTicketPrice();
    }

    public int getTicketPriceDifference() {
        calculatesTicketPriceDifference();
        return ticketPriceDifference;
    }
}
