package model.dao.implementations;

import model.dao.ClientDAO;
import model.entities.Client;

import java.io.IOException;
import java.sql.*;

public class ClientDAOJDBC implements ClientDAO {
    Connection connection;

    public ClientDAOJDBC(Connection connection){
        this.connection = connection;
    }

    @Override
    public Client findClientById(Integer id) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement =
                    connection.prepareStatement("SELECT Clients.*, Rooms.door_number "
                            + "FROM Clients INNER JOIN Rooms "
                            + "ON Clients.id = Rooms.resident "
                            + "WHERE Clients.id = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            Client client = new Client();
            if(resultSet.next()){
                Integer clientId = resultSet.getInt("id");
                String clientName = resultSet.getString("name");
                Integer clientAge = resultSet.getInt("age");

                client.setId(clientId);
                client.setName(clientName);
                client.setAge(clientAge);

                System.out.println("Passou aqui");
                return client;
            }
            return null;
        } catch (SQLException e){
            System.out.print(e.getMessage());
        }
        return null;
    }

    @Override
    public void addClient(Client client) {
        PreparedStatement statement = null;
        try{
            statement =
                    connection.prepareStatement("INSERT INTO Clients (name, age)"
                            + " VALUES (? , ?)");
            statement.setString(1, client.getName());
            statement.setInt(2, client.getAge());
            statement.execute();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        } finally {
            try {
                statement.close();
            } catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    public void deleteClientById(Integer id) {
        PreparedStatement statement = null;
        try{
            statement = connection.prepareStatement("DELETE FROM Clients "
                                                    + "WHERE Clients.id = ?");
            statement.setInt(1, id);
        } catch (SQLException e){
            System.out.print(e.getMessage());
        }
    }

    @Override
    public void updateClientById(Integer id, Client updatedClient) {
        PreparedStatement statement;
        try{
            statement =
                    connection.prepareStatement("UPDATE Clients "
                                                    + "SET name = ?, age = ? "
                                                    + "WHERE Clients.id = ?");

            statement.setString(1, updatedClient.getName());
            statement.setInt(2, updatedClient.getAge());
            statement.setInt(3, id);

            statement.executeUpdate();
        } catch (SQLException e){
            System.out.print(e.getMessage());
        }
    }
}
