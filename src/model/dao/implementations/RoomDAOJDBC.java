package model.dao.implementations;

import model.dao.RoomDAO;
import model.entities.Room;

public class RoomDAOJDBC implements RoomDAO {

    @Override
    public Room findRoomById(Integer id) {
        return null;
    }

    @Override
    public Room findRoomByNumber(Integer door_number) {
        return null;
    }

    @Override
    public Room findRoomByResidentId(Integer id) {
        return null;
    }

    @Override
    public void addRoom(Room client) {

    }

    @Override
    public void deleteRoomById(Integer id) {

    }

    @Override
    public void updateRoomById(Integer id, Room updatedRoom) {

    }
}
