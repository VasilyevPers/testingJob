import lombok.Getter;

import java.util.Comparator;
import java.util.List;

public class CalculatingTheCostDifference {
    private final ParsingJsonFile parsingJsonFile;
    @Getter
    private int ticketPriceDifference;

    public CalculatingTheCostDifference () {
        parsingJsonFile = new ParsingJsonFile();
        calculatesTicketPriceDifference();
    }

    private int calculatesTheAverageTicketPrice () {
        long totalTicketPrice = 0;
        for (Ticket ticket : parsingJsonFile.getTicketList()) {
            totalTicketPrice += ticket.getPrice();
        }
        return (int) totalTicketPrice/parsingJsonFile.getTicketList().size();
    }

    private int calculatesTheMedianTicketPrice () {
        List<Ticket> allTicketsList = parsingJsonFile.getTicketList();
        allTicketsList.sort(Comparator.comparing(Ticket::getPrice));
        int middleOfTheList = allTicketsList.size()/2;
        if (middleOfTheList*2 == allTicketsList.size()) {
            return (allTicketsList.get(middleOfTheList -1).getPrice()
                    + allTicketsList.get(middleOfTheList).getPrice())
                    / 2;
        }
        return allTicketsList.get(middleOfTheList).getPrice();
    }

    private void calculatesTicketPriceDifference () {
        ticketPriceDifference = calculatesTheAverageTicketPrice() - calculatesTheMedianTicketPrice();
    }
}
