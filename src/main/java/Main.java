import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        SearchQuickFlight quickFlight = new SearchQuickFlight();
        System.out.println("Добро пожаловать в программу поиска билетов!");
        boolean workProgram = true;

        while (workProgram) {
            System.out.println("Выберите действие:" + "\n"
                                + "\t" + "1 - Поиск самых быстрых перелетов в каждой компании." + "\n"
                                + "\t" + "2 - Разница между средней ценой билета и медианой." + "\n"
                                + "\t" + "3 - Выход из программы." + "\n");
            int operationCode = new Scanner(System.in).nextInt();
            switch (operationCode) {
                case 1 -> System.out.println(quickFlight.getQuickTicketsList() + "\n");
                case 2 -> {
                    showsTicketPriseDifference();
                    System.out.println(" ");
                }
                case 3 -> {
                    workProgram = false;
                    System.out.println("Хорошего дня!");
                }
                default -> System.out.println("Введена не верная команда!" + "\n");
            }
        }
    }
    
    private static void showsTicketPriseDifference () {
        CalculatingTheCostDifference costDifference = new CalculatingTheCostDifference();
        int ticketPriceDifference = costDifference.getTicketPriceDifference();

        if (ticketPriceDifference < 0) {
            System.out.println("Средняя стоимость билетов меньше медианы на: " + Math.abs(ticketPriceDifference));
        } else if (ticketPriceDifference > 0) {
            System.out.println("Средняя стоимость билетов меньше медианы на: " + ticketPriceDifference);
        } else {
            System.out.println("Средняя стоимость билетов и медиана равны" + ticketPriceDifference);
        }
    }
}