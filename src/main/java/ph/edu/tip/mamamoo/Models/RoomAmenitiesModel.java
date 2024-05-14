package ph.edu.tip.mamamoo.Models;

public class RoomAmenitiesModel {
    public String roomAmenityName;
    public String roomAmenityDescription;
    public int roomAmenityCount;

    public RoomAmenitiesModel() {}

    public RoomAmenitiesModel(String roomAmenityName, String roomAmenityDescription, int roomAmenityCount) {
        this.roomAmenityName = roomAmenityName;
        this.roomAmenityDescription = roomAmenityDescription;
        this.roomAmenityCount = roomAmenityCount;
    }

    public String getRoomAmenityName() {
        return roomAmenityName;
    }
    public String getRoomAmenityDescription() {
        return roomAmenityDescription;
    }
    public int getRoomAmenityCount() {
        return roomAmenityCount;
    }

    public void setRoomAmenityName(String roomAmenityName) {
        this.roomAmenityName = roomAmenityName;
    }
    public void setRoomAmenityDescription(String roomAmenityDescription) {
        this.roomAmenityDescription = roomAmenityDescription;
    }
    public void setRoomAmenityCount(int roomAmenityCount) {
        this.roomAmenityCount = roomAmenityCount;
    }
}
