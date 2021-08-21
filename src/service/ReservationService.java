package service;

import model.Customer;
import model.IRoom;
import model.Reservation;

import java.util.*;

public class ReservationService {
    private final static Map<String, IRoom> rooms = new HashMap<String, IRoom>();
    private final static HashMap<Customer, LinkedList<Reservation>> reservations = new HashMap<Customer, LinkedList<Reservation>>();
    private final static HashMap<IRoom, LinkedList<Reservation>> roomReservations = new HashMap<IRoom, LinkedList<Reservation>>();
    private static ReservationService reservationService = null;

    private ReservationService() {};
    public static ReservationService getInstance() {
        if(reservationService == null) {
            reservationService = new ReservationService();
        }
        return reservationService;
    }

    public static void addRoom(IRoom room) {
        rooms.put(room.getRoomNumber(), room);
    }
    public static IRoom getARoom(String roomId) {
        return rooms.get(roomId);
    }
    public static Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        LinkedList<Reservation> roomReservation = roomReservations.getOrDefault(room, new LinkedList<Reservation>());
        if(!isAvailable(roomReservation, checkInDate, checkOutDate)) {
            System.out.println("This room has been reserved");
            return null;
        }
        Reservation reservation = new Reservation(customer, room, checkInDate, checkOutDate);
        LinkedList<Reservation> customerReservation = reservations.getOrDefault(customer, new LinkedList<Reservation>());
        customerReservation.add(reservation);
        reservations.put(customer, customerReservation);
        roomReservation.add(reservation);
        roomReservations.put(room, roomReservation);
        return reservation;
    }
    public static Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate) {
        LinkedList<IRoom> availableRooms = new LinkedList<IRoom>();
        Collection<IRoom> allRooms = ReservationService.getAllRooms();
        for(IRoom room: allRooms) {
            if(!roomReservations.containsKey(room)) {
                availableRooms.add(room);
            } else {
                LinkedList<Reservation> roomReservation = roomReservations.get(room);
                if(isAvailable(roomReservation, checkInDate, checkOutDate)) {
                    availableRooms.add(room);
                }
            }
        }
        return availableRooms;
    }
    public static boolean isAvailable(LinkedList<Reservation> roomReservation, Date checkInDate, Date checkOutDate) {
        boolean available = true;
        for(Reservation reservation: roomReservation) {
            Date start = reservation.getCheckInDate();
            Date end = reservation.getCheckOutDate();
            if((checkInDate.after(start) && checkInDate.before(end)) || (checkOutDate.after(start) && checkOutDate.before(end))) {
                available = false;
            }
        }
        return available;
    }
    public static Collection<IRoom> getAllRooms() {
        return rooms.values();
    }
    public static Collection<Reservation> getCustomersReservation(Customer customer) {
        return reservations.get(customer);
    }
    public static void printAllReservation() {
        Collection<LinkedList<Reservation>> allReservations = reservations.values();
        Iterator<LinkedList<Reservation>> allReservationsIterator = allReservations.iterator();
        while(allReservationsIterator.hasNext()){
            LinkedList<Reservation> currReservations = allReservationsIterator.next();
            Iterator<Reservation> reservationIterator = currReservations.iterator();
            while (reservationIterator.hasNext()) {
                System.out.println(reservationIterator.next());
            }
        }
    }
}
