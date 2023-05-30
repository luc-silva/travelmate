package model.dao.implementations;

import model.dao.RoomDAO;
import model.entities.Client;
import model.entities.Room;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
                                                    "INNER JOIN Clients " +
                                                    "ON Rooms.resident = Clients.id " +
                                                    "WHERE Rooms.id = ?");
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if(resultSet.next()){
               Room room = new Room();
               room.setDoor_number(resultSet.getInt("door_number"));
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
                statement.close();
                resultSet.close();
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
                preparedStatement.close();
                resultSet.close();
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
                preparedStatement.close();
                resultSet.close();
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
            preparedStatement = connection.prepareStatement("INSERT INTO Rooms (door_number, max_capability, resident)" +
                    "VALUES (?, ?, ?)");
            preparedStatement.setInt(1, room.getDoor_number());
            preparedStatement.setInt(2, room.getMax_capability());
            preparedStatement.setInt(3, room.getResident().getId());

            preparedStatement.execute();

        } catch (SQLException e){
            System.out.print(e.getMessage());
        } finally {
            try{
                preparedStatement.close();
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
                preparedStatement.close();
            } catch (SQLException e){
                System.out.print(e.getMessage());
            }
        }
    }

    @Override
    public void updateRoomById(Integer id, Room updatedRoom) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("UPDATE Rooms" +
                    "SET door_number = ?, max_capability = ?, resident = ? " +
                    "WHERE id = ?");
            preparedStatement.setInt(1, updatedRoom.getDoor_number());
            preparedStatement.setInt(2, updatedRoom.getMax_capability());
            preparedStatement.setInt(3, updatedRoom.getResident().getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e){
            System.out.print(e.getMessage());
        } finally {
            try{
                preparedStatement.close();
            } catch (SQLException e){
                System.out.print(e.getMessage());
            }
        }
    }
}
