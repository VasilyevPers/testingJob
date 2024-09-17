import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Setter
@Getter
@NoArgsConstructor
public class Ticket implements Comparable<Ticket>{
    private String origin;
    @JsonSetter("origin_name")
    private String originName;
    private String destination;
    @JsonSetter("destination_name")
    private String destinationName;
    @JsonSetter("departure_date")
    @JsonFormat(pattern = "dd.MM.yy")
    private LocalDate departureDate;
    @JsonSetter("departure_time")
    @JsonFormat(pattern = "H:mm")
    private LocalTime departureTime;
    @JsonSetter("arrival_date")
    @JsonFormat(pattern = "dd.MM.yy")
    private LocalDate arrivalDate;
    @JsonSetter("arrival_time")
    @JsonFormat(pattern = "H:mm")
    private LocalTime arrivalTime;
    private String carrier;
    private int stops;
    private int price;

    @Override
    public String toString() {
        return "Ticket: " + "\n" +
                "\t" + "origin: " + origin + "\n" +
                "\t" + "originName: " + originName + "\n" +
                "\t" + "destination: " + destination + "\n" +
                "\t" + "destinationName: " + destinationName + "\n" +
                "\t" + "departureDate: " + departureDate + "\n" +
                "\t" + "departureTime: " + departureTime + "\n" +
                "\t" + "arrivalDate: " + arrivalDate + "\n" +
                "\t" + "arrivalTime: " + arrivalTime + "\n" +
                "\t" + "carrier: " + carrier + "\n" +
                "\t" + "stops: " + stops + "\n" +
                "\t" + "price: " + price +  "\n" ;
    }

    @Override
    public int compareTo(Ticket t) {
        return (int)(SearchQuickFlight.flightTimeOfEpochSecond(this)
                - SearchQuickFlight.flightTimeOfEpochSecond(t));
    }
}
