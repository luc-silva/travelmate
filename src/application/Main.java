package application;

import model.dao.ClientDAO;
import model.dao.DaoFactory;
import model.entities.Client;

public class Main {
    public static void main(String[] args) {
        ClientDAO clientDAO = DaoFactory.createClientDao();
        Client client = new Client();
        client.setAge(100);
        client.setName("Malajuicequias");
        clientDAO.addClient(client);

        Client foundClient = clientDAO.findClientById(1);
        foundClient.setName("not lucas");
        clientDAO.updateClientById(1, foundClient);
        System.out.print(foundClient);
    }
}