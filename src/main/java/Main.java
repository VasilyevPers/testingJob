import java.util.Scanner;

public class Main {
    private static boolean workProgram = true;
    private static String departureCity;
    private static String destinationCity;

    public static void main(String[] args) {
        System.out.println("Добро пожаловать в программу поиска билетов!");

        while (workProgram) {
            System.out.println("Введите город вылета");
            departureCity = new Scanner(System.in).nextLine();
            System.out.println("Введите город назначения");
            destinationCity = new Scanner(System.in).nextLine();
            SearchQuickFlight quickFlight = new SearchQuickFlight(departureCity, destinationCity);
            if (quickFlight.sortsTicketsByCompany().isEmpty()) {
                System.out.println("По указанному маршруту вылетов не найдено!");
            } else {calculateInformation(quickFlight);}
        }
    }

    private static void calculateInformation (SearchQuickFlight quickFlight) {
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
                case 1 -> System.out.println(quickFlight.getQuickTicketsList() + "\n");
                case 2 -> {
                    showsTicketPriseDifference();
                    System.out.println(" ");
                }
                case 3 ->{
                    for (Ticket ticket : quickFlight.getQuickTicketsList()) {
                        quickFlight.calculateFlightTime(ticket);
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
    
    private static void showsTicketPriseDifference () {
        CalculatingTheCostDifference costDifference = new CalculatingTheCostDifference(departureCity, destinationCity);
        int ticketPriceDifference = costDifference.getTicketPriceDifference();

        if (ticketPriceDifference < 0) {
            System.out.println("Средняя стоимость билетов меньше медианы на: " + Math.abs(ticketPriceDifference));
        } else if (ticketPriceDifference > 0) {
            System.out.println("Средняя стоимость билетов меньше медианы на: " + ticketPriceDifference);
        } else {
            System.out.println("Средняя стоимость билетов и медианы равна: " + ticketPriceDifference);
        }
    }
}