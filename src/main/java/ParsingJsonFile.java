import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class ParsingJsonFile {
    private static final String DATA_FILE = "src/main/resources/tickets.json";
    private List<Ticket> ticketList;

    private void createTicketsList () throws IOException {
        ObjectMapper objectMapper = new ObjectMapper().findAndRegisterModules();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        File file = new File(DATA_FILE);
        JsonNode ticketsNode = objectMapper.readTree(file).get("tickets");
        TypeReference<List<Ticket>> typeReference = new TypeReference<>() {};
        ticketList = objectMapper.readValue(ticketsNode.traverse(), typeReference);
    }

    public List<Ticket> getTicketList() {
        try {
            createTicketsList();
        } catch (IOException e) {
            ticketList = new ArrayList<>();
            System.out.println("Не удалось создать список билетов");
        }
        return ticketList;
    }
}
