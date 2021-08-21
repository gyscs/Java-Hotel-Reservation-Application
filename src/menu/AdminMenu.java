package menu;

import api.AdminResource;
import model.Customer;
import model.IRoom;
import model.Room;
import model.RoomType;

import java.util.*;

public class AdminMenu {
    private final static String[] menu = {"1. See all Customers", "2. See all Rooms", "3. See all Reservations", "4. Add a Room", "5. Back to Main Menu"};
    private final static int exitNumber = 5;

    private AdminMenu() {}

    public static void main() {
        AdminMenu.showMenu();
        Scanner scanner = new Scanner(System.in);
        int userInput = scanner.nextInt();
        while(userInput != exitNumber) {
            switch (userInput) {
                case 1:
                    Collection<Customer> allCustomers = AdminResource.getAllCustomers();
                    Iterator<Customer> customerIterator = allCustomers.iterator();
                    while(customerIterator.hasNext()) {
                        System.out.println(customerIterator.next());
                    }
                    break;
                case 2:
                    Collection<IRoom> allRooms = AdminResource.getAllRooms();
                    Iterator<IRoom> iRoomIterator = allRooms.iterator();
                    while(iRoomIterator.hasNext()) {
                        System.out.println(iRoomIterator.next());
                    }
                    break;
                case 3:
                    AdminResource.displayAllReservations();
                    break;
                case 4:
                    List<IRoom> rooms = new LinkedList<IRoom>();
                    String exit = "n";
                    String userInput1 = "y";
                    while(!userInput1.equals(exit)) {
                        System.out.println("Please enter the room number");
                        String roomNumber = scanner.next();
                        System.out.println("Please enter the price");
                        Double price = scanner.nextDouble();
                        System.out.println("Please select the room type: 1. SINGLE, 2. DOUBLE");
                        int type = scanner.nextInt();
                        RoomType roomType = null;
                        if(type == 1) {
                            roomType = RoomType.SINGLE;
                        } else if(type == 2) {
                            roomType = RoomType.DOUBLE;
                        }
                        rooms.add(new Room(roomNumber, price, roomType));
                        System.out.println("Do you want to add another room? y/n");
                        userInput1 = scanner.next();
                    }
                    AdminResource.addRoom(rooms);
                    break;
                default:
                    System.out.println("Please enter a number between 1 and 5");
            }
            AdminMenu.showMenu();
            userInput = scanner.nextInt();
        }
    }
    public static void showMenu() {
        for(int i = 0; i < menu.length; i++) {
            System.out.println(menu[i]);
        }
    }
}
