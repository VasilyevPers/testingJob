import java.util.Comparator;
import java.util.List;

public class CalculatingTheCostDifference {

    private static int calculatesTheAverageTicketPrice (List<Ticket> quickTicketList) {
        long totalTicketPrice = 0;
        for (Ticket ticket : quickTicketList) {
            totalTicketPrice += ticket.getPrice();
        }
        return (int) totalTicketPrice/quickTicketList.size();
    }

    private static int calculatesTheMedianTicketPrice (List<Ticket> quickTicketList) {
        quickTicketList.sort(Comparator.comparing(Ticket::getPrice));
        int middleOfTheList = quickTicketList.size()/2;
        if (middleOfTheList*2 == quickTicketList.size()) {
            return (quickTicketList.get(middleOfTheList -1).getPrice()
                    + quickTicketList.get(middleOfTheList).getPrice())
                    / 2;
        }
        return quickTicketList.get(middleOfTheList).getPrice();
    }

    public static int calculatesTicketPriceDifference (List<Ticket> quickTicketList) {
        return calculatesTheAverageTicketPrice(quickTicketList)
                - calculatesTheMedianTicketPrice(quickTicketList);
    }

}
