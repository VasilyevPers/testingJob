import java.util.List;
import java.util.Scanner;

public class Main {
    private static boolean workProgram = true;

    public static void main(String[] args) {
        System.out.println("Добро пожаловать в программу поиска билетов!");
        ParsingJsonFile parsingJsonFile = new ParsingJsonFile();

        while (workProgram) {
            System.out.println("Введите город вылета");
            String departureCity = new Scanner(System.in).nextLine();
            System.out.println("Введите город назначения");
            String destinationCity = new Scanner(System.in).nextLine();
            List<Ticket> listOfAllTicketsAlongTheRoute = SearchQuickFlight.searchForAllTicketsAlongTheRoute(parsingJsonFile.getTicketList(), departureCity, destinationCity);
            if (listOfAllTicketsAlongTheRoute.isEmpty()) {
                System.out.println("По указанному маршруту вылетов не найдено!");
            } else {
                calculateInformation(SearchQuickFlight.createQuickTicketList(listOfAllTicketsAlongTheRoute));}
        }
    }

    private static void calculateInformation (List<Ticket> quickTicketsList) {
        boolean calculateInformation = true;
        while (calculateInformation) {
            System.out.println("Выберите действие:" + "\n"
                    + "\t" + "1 - Поиск самых быстрых перелетов в каждой авиакомпании." + "\n"
                    + "\t" + "2 - Разница между средней ценой билета и медианой." + "\n"
                    + "\t" + "3 - Показать время полета по каждой авиакомпании." + "\n"
                    + "\t" + "4 - Изменить маршрут рейса." + "\n"
                    + "\t" + "5 - Выход из программы." + "\n");
            int operationCode = new Scanner(System.in).nextInt();
            switch (operationCode) {
                case 1 -> System.out.println(quickTicketsList + "\n");
                case 2 -> {
                    showsTicketPriseDifference(quickTicketsList);
                    System.out.println(" ");
                }
                case 3 ->{
                    for (Ticket ticket : quickTicketsList) {
                        SearchQuickFlight.calculateFlightTime(ticket);
                    }
                }
                case 4 -> calculateInformation = false;
                case 5 -> {
                    calculateInformation = false;
                    workProgram = false;
                    System.out.println("Хорошего дня!");
                }
                default -> System.out.println("Введена не верная команда!" + "\n");
            }
        }
    }
    
    private static void showsTicketPriseDifference (List<Ticket> quickTicketsList) {
        int ticketPriceDifference = CalculatingTheCostDifference.calculatesTicketPriceDifference(quickTicketsList);

        if (ticketPriceDifference < 0) {
            System.out.println("Средняя стоимость билетов меньше медианы на: " + Math.abs(ticketPriceDifference));
        } else if (ticketPriceDifference > 0) {
            System.out.println("Средняя стоимость билетов меньше медианы на: " + ticketPriceDifference);
        } else {
            System.out.println("Средняя стоимость билетов и медианы равна: " + ticketPriceDifference);
        }
    }
}