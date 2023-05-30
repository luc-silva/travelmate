package model.dao;

import model.entities.Room;

import java.util.List;

public interface RoomDAO {
    public Room findRoomById(Integer id);
    public Room findRoomByNumber(Integer door_number);
    public Room findRoomByResidentId(Integer id);
    public List<Room> listRooms();
    public List<Room> listAvailableRooms();
    public void addRoom(Room client);
    public void deleteRoomById(Integer id);
    public void updateRoomById(Integer id, Room updatedRoom);
}
