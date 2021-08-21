package menu;

import api.HotelResource;
import model.IRoom;
import model.Reservation;

import java.util.*;

public class MainMenu {
    private static String[] menu = {"1. Find and reserve a room", "2. See my reservations", "3. Create an account", "4. Admin", "5. Exit"};
    private static int exitNumber = 5;

    private MainMenu() {};

    public static void main() {
        Scanner scanner = new Scanner(System.in);
        int userInput = 0;
        String email = null;
        while(userInput != exitNumber) {
            MainMenu.showMenu();
            try {
                userInput = scanner.nextInt();
            } catch (InputMismatchException exception) {
                System.out.println("Please enter a number between 1 and 5");
            }
            switch (userInput) {
                case 1:
                    System.out.println("Do you have an account? y/n");
                    String userInput1 = scanner.next();
                    if(userInput1.equals("y")){
                        System.out.println("Please enter your email");
                        email = scanner.next();
                        if(HotelResource.getCustomer(email) != null) {
                            System.out.println("Please enter your checkin date: DD/MM/YYYY");
                            String date = scanner.next();
                            Date checkInDate = MainMenu.setDate(date);
                            System.out.println("Please enter your checkout date: DD/MM/YYYY");
                            date = scanner.next();
                            Date checkOutDate = MainMenu.setDate(date);
                            Collection<IRoom> rooms = HotelResource.findARoom(checkInDate, checkOutDate);
                            Iterator<IRoom> iRoomIterator = rooms.iterator();
                            while (iRoomIterator.hasNext()) {
                                System.out.println(iRoomIterator.next());
                            }
                            System.out.println("Please enter a room number");
                            String roomNumber = scanner.next();
                            Reservation reservation = HotelResource.bookARoom(email, HotelResource.getRoom(roomNumber), checkInDate, checkOutDate);
                            System.out.println(reservation);
                        } else {
                            System.out.println("Cannot find this customer, please create an account");
                        }
                    } else {
                        System.out.println("Please create an account");
                    }
                    break;
                case 2:
                    System.out.println("Please enter your email");
                    email = scanner.next();
                    Collection<Reservation> customerReservations = HotelResource.getCustomersReservations(email);
                    Iterator<Reservation> reservationIterator = customerReservations.iterator();
                    while(reservationIterator.hasNext()) {
                        System.out.println(reservationIterator.next());
                    }
                    break;
                case 3:
                    System.out.println("Please enter your email");
                    email = scanner.next();
                    System.out.println("Please enter your first name");
                    String firstName = scanner.next();
                    System.out.println("Please enter your last name");
                    String lastName = scanner.next();
                    HotelResource.createACustomer(email, firstName, lastName);
                    System.out.println("Account created!");
                    break;
                case 4:
                    AdminMenu.main();
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Please enter a number between 1 and 5");
            }
        }
    }
    public static void showMenu() {
        for(int i = 0; i < menu.length; i++) {
            System.out.println(menu[i]);
        }
    }
    public static Date setDate(String input) {
        String[] inputs = input.split("/");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Integer.parseInt(inputs[2]), Integer.parseInt(inputs[1]) - 1, Integer.parseInt(inputs[0]));
        Date date = calendar.getTime();
        return date;
    }
}
