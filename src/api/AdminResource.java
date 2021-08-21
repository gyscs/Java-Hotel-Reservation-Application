package api;

import model.Customer;
import model.IRoom;
import model.Room;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class AdminResource {
    private AdminResource() {};

    public static Customer getCustomer(String email) {
        return CustomerService.getCustomer(email);
    }
    public static void addRoom(List<IRoom> rooms) {
        Iterator<IRoom> roomIterator = rooms.iterator();
        while(roomIterator.hasNext()) {
            ReservationService.addRoom(roomIterator.next());
        }
    }
    public static Collection<IRoom> getAllRooms() {
        return ReservationService.getAllRooms();
    }
    public static Collection<Customer> getAllCustomers() {
        return CustomerService.getAllCustomers();
    }
    public static void displayAllReservations() {
        ReservationService.printAllReservation();
    }
}
