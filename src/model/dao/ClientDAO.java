package model.dao;

import model.entities.Client;

import java.util.List;

public interface ClientDAO {
    public Client findClientById(Integer id);
    public List<Client> listClients();
    public void addClient(Client client);
    public void deleteClientById(Integer id);
    public void updateClientById(Integer id, Client updatedClient);
}
