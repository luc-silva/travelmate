package model.dao.implementations;

import model.dao.ClientDAO;
import model.entities.Client;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
                    connection.prepareStatement("SELECT * FROM Clients\n" +
                            "WHERE id = ?");
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

                return client;
            }
            return null;
        } catch (SQLException e){
            System.out.print(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Client> listClients() {
        PreparedStatement  preparedStatement = null;
        ResultSet resultSet = null;
        List<Client> clientList = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM Clients");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Client client = new Client();
                client.setId(resultSet.getInt("id"));
                client.setName(resultSet.getString("name"));
                client.setAge(resultSet.getInt("age"));

                clientList.add(client);
            }
            return  clientList;
        } catch (SQLException e){
            System.out.print(e.getMessage());
        } finally {
            try {
                if(preparedStatement != null){
                    preparedStatement.close();
                }
                if (resultSet != null){
                    resultSet.close();
                }
            } catch (SQLException e){
                System.out.print(e.getMessage());
            }
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
