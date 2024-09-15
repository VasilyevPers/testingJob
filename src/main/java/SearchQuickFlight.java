import lombok.Getter;

import java.util.*;

public class SearchQuickFlight {
    private final ParsingJsonFile parsingJsonFile;
    @Getter
    List<Ticket> quickTicketsList = new ArrayList<>();
    public SearchQuickFlight () {
        parsingJsonFile = new ParsingJsonFile();
        createQuickTicketList();
    }

    private Map<String, List<Ticket>> sortsTicketsByCompany () {
        Map<String, List<Ticket>> sortedTicketsByCompany = new HashMap<>();
        for (Ticket ticket : parsingJsonFile.getTicketList()) {
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
        for (Map.Entry<String, List<Ticket>> ticketsCompanyList : sortsTicketsByCompany().entrySet()) {
            if (ticketsCompanyList.getValue().size() == 1) {
                quickTicketsList.add(ticketsCompanyList.getValue().get(0));
            } else {
                Ticket minQuick = ticketsCompanyList.getValue().stream().sorted().toList().get(0);
                quickTicketsList.add(minQuick);
            }
        }
    }
}
