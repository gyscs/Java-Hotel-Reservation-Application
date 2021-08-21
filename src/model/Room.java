package model;


public class Room implements IRoom {
    private final String roomNumber;
    private final Double price;
    private final RoomType enumeration;

    public Room(String roomNumber, Double price, RoomType enumeration) {
        this.roomNumber = roomNumber;
        this.price = price;
        this.enumeration = enumeration;
    }

    @Override
    public String getRoomNumber() {
        return this.roomNumber;
    }

    @Override
    public Double getRoomPrice() {
        return this.price;
    }

    @Override
    public RoomType getRoomType() {
        return this.enumeration;
    }

    @Override
    public boolean isFree() {
        return this.price == 0.0;
    }

    @Override
    public String toString() {
        return "room number: " + this.roomNumber + " price: " + this.price + " room type: " + this.enumeration;
    }
    @Override
    public boolean equals(Object o) {
        if(o == this) return true;
        if(o == null) return false;
        if(this.getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return (roomNumber.equals(room.roomNumber)) && (price.equals(room.price)) && (enumeration.equals(room.enumeration));
    }
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + (int) Math.round(price);
        hash = 31 * hash + (roomNumber == null ? 0 : roomNumber.hashCode());
        hash = 31 * hash + (enumeration == RoomType.SINGLE ? 1 : 2);
        return hash;
    }
}
