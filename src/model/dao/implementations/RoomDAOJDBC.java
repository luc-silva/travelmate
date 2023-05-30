package model.dao.implementations;

import model.dao.RoomDAO;
import model.entities.Client;
import model.entities.Room;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomDAOJDBC implements RoomDAO {
    Connection connection;
    public RoomDAOJDBC(Connection connection){
        this.connection = connection;
    }

    @Override
    public Room findRoomById(Integer id) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement =
                    connection.prepareStatement("SELECT Rooms.*, Clients.name, Clients.age " +
                                                    "From Rooms " +
                                                    "LEFT JOIN Clients " +
                                                    "ON Rooms.resident = Clients.id " +
                                                    "WHERE Rooms.id = ?");
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if(resultSet.next()){
               Room room = new Room();
               room.setDoor_number(resultSet.getInt("door_number"));
               room.setMax_capability(resultSet.getInt("max_capability"));
               room.setId(resultSet.getInt("id"));

                Client client = new Client();
                client.setName(resultSet.getString("name"));
                client.setAge(resultSet.getInt("age"));
                client.setId(resultSet.getInt("resident"));

               room.setResident(client);

               return room;
            }
        } catch (SQLException e){
            System.out.print(e.getMessage());
        } finally {
            try {
                if(statement != null){
                    statement.close();
                }
                if(resultSet != null){
                    resultSet.close();
                }
            }catch (SQLException e ){
                System.out.print(e.getMessage());
            }
        }
        return null;
    }

    @Override
    public Room findRoomByNumber(Integer door_number) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement =
                    connection.prepareStatement("SELECT Clients.name, Clients.age, Rooms.* FROM Rooms " +
                                                "INNER JOIN Clients " +
                                                "ON Rooms.resident = Clients.id " +
                                                "WHERE door_number = ?");
            preparedStatement.setInt(1, door_number);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                Room room = new Room();
                room.setDoor_number(door_number);
                room.setMax_capability(resultSet.getInt("max_capability"));
                room.setId(resultSet.getInt("id"));

                Client client = new Client();
                //passar para dentro de um método privado
                client.setAge(resultSet.getInt("age"));
                client.setName(resultSet.getString("name"));
                client.setId(resultSet.getInt("resident"));

                room.setResident(client);
                return room;
            }
        } catch (SQLException e){
            System.out.print(e.getMessage());
        } finally {
            try {
                if(preparedStatement != null){
                    preparedStatement.close();
                }
                if(resultSet != null){
                    resultSet.close();
                }
            } catch (SQLException e){
                System.out.print(e.getMessage());
            }
        }
        return null;
    }

    @Override
    public Room findRoomByResidentId(Integer id) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement =
                    connection.prepareStatement("SELECT Rooms.*, Clients.name, Clients.age FROM Rooms " +
                                                "INNER JOIN Clients " +
                                                "WHERE Rooms.resident = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                Room room = new Room();
                room.setDoor_number(resultSet.getInt("door_number"));
                room.setMax_capability(resultSet.getInt("max_capability"));
                room.setId(resultSet.getInt("id"));

                Client client = new Client();
                client.setAge(resultSet.getInt("age"));
                client.setName(resultSet.getString("name"));
                client.setId(resultSet.getInt("resident"));

                room.setResident(client);
                return room;
            }


        } catch (SQLException e){
            System.out.print(e.getMessage());
        } finally {
            try{
                if(preparedStatement != null){
                    preparedStatement.close();
                }
                if(resultSet != null){
                    resultSet.close();
                }
            } catch (SQLException e){
                System.out.print(e.getMessage());
            }
        }
        return null;
    }

    @Override
    public List<Room> listRooms() {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Room> rooms = new ArrayList<>();
        try {
            preparedStatement =
                    connection.prepareStatement("SELECT Rooms.*, Clients.name as residentName, Clients.age as residentAge\n" +
                            "FROM Rooms\n" +
                            "LEFT JOIN Clients\n" +
                            "ON Rooms.resident = Clients.id");
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                Room room = new Room();
                room.setId(resultSet.getInt("id"));
                room.setMax_capability(resultSet.getInt("max_capability"));
                room.setDoor_number(resultSet.getInt("door_number"));

                Client client = new Client();
                client.setName(resultSet.getString("residentName"));
                client.setId(resultSet.getInt("resident"));
                client.setAge(resultSet.getInt("residentAge"));

                room.setResident(client);

                rooms.add(room);

            }
            return rooms;
        }catch (SQLException e){
            System.out.print(e.getMessage());
        } finally {
            try{
                if(preparedStatement != null){
                    preparedStatement.close();
                }
                if(resultSet != null){
                    resultSet.close();
                }
            } catch (SQLException e){
                System.out.print(e.getMessage());
            }
        }

        return null;
    }

    @Override
    public List<Room> listAvailableRooms() {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Room> rooms = new ArrayList<>();
        try {
            preparedStatement =
                    connection.prepareStatement("SELECT * FROM Rooms\n" +
                            "WHERE resident is null;");
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                Room room = new Room();
                room.setId(resultSet.getInt("id"));
                room.setMax_capability(resultSet.getInt("max_capability"));
                room.setDoor_number(resultSet.getInt("door_number"));
                room.setResident(null);

                rooms.add(room);

            }
            return rooms;
        }catch (SQLException e){
            System.out.print(e.getMessage());
        } finally {
            try{
                if(preparedStatement != null){
                    preparedStatement.close();
                }
                if(resultSet != null){
                    resultSet.close();
                }
            } catch (SQLException e){
                System.out.print(e.getMessage());
            }
        }

        return null;
    }

    @Override
    public void addRoom(Room room) {
        PreparedStatement preparedStatement = null;

        try{
            preparedStatement = connection.prepareStatement("INSERT INTO Rooms (door_number, max_capability) " +
                    "VALUES (?, ?)");
            preparedStatement.setInt(1, room.getDoor_number());
            preparedStatement.setInt(2, room.getMax_capability());

            preparedStatement.executeUpdate();

        } catch (SQLException e){
            System.out.print(e.getMessage());
        } finally {
            try{
                if(preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e){
                System.out.print(e.getMessage());
            }
        }
    }

    @Override
    public void deleteRoomById(Integer id) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("DELETE FROM Rooms " +
                    "WHERE id = ?" );

            preparedStatement.execute();
        } catch (SQLException e){
            System.out.print(e.getMessage());
        } finally {
            try{
                if(preparedStatement != null){
                    preparedStatement.close();
                }
            } catch (SQLException e){
                System.out.print(e.getMessage());
            }
        }
    }

    @Override
    public void updateRoomById(Integer id, Room updatedRoom) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("UPDATE Rooms " +
                    "SET door_number = ?, max_capability = ?, resident = ? " +
                    "WHERE id = ?");
            preparedStatement.setInt(1, updatedRoom.getDoor_number());
            preparedStatement.setInt(2, updatedRoom.getMax_capability());
            if(updatedRoom.getResident() == null){
                preparedStatement.setNull(3, Types.INTEGER);
            } else {
                preparedStatement.setInt(3, updatedRoom.getResident().getId());
            }
            preparedStatement.setInt(4, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e){
            System.out.print(e.getMessage());
        } finally {
            try{
                if(preparedStatement != null){
                    preparedStatement.close();
                }
            } catch (SQLException e){
                System.out.print(e.getMessage());
            }
        }
    }
}
