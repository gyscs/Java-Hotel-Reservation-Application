package model;

import java.util.Date;

public class Reservation {
    private final Customer customer;
    private final IRoom room;
    private final Date checkInDate;
    private final Date checkOutDate;

    public Reservation(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        this.customer = customer;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public Date getCheckInDate() {
        return this.checkInDate;
    }
    public Date getCheckOutDate() {
        return this.checkOutDate;
    }

    @Override
    public String toString() {
        return "customer: " + customer + '\n' +
                " room: " + room + '\n' +
                " checkin date: " + checkInDate + '\n' +
                " checkout date: " + checkOutDate;
    }
    @Override
    public boolean equals(Object o) {
        if(o == this) return true;
        if(o == null) return false;
        if(this.getClass() != o.getClass()) return false;
        Reservation reservation = (Reservation) o;
        return (customer.equals(reservation.customer)) && (room.equals(reservation.room)) && (checkInDate.equals(reservation.checkInDate)) && (checkOutDate.equals(reservation.checkOutDate));
    }
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + (customer == null ? 0 : customer.hashCode());
        hash = 31 * hash + (room == null ? 0 : room.hashCode());
        hash = 31 * hash + (checkInDate == null ? 0 : checkInDate.hashCode());
        hash = 31 * hash + (checkOutDate == null ? 0 : checkOutDate.hashCode());
        return hash;
    }
}
