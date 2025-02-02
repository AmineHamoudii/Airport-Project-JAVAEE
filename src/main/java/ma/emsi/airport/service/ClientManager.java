package ma.emsi.airport.service;

import ma.emsi.airport.dao.entities.Aeroport;
import ma.emsi.airport.dao.entities.Client;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClientManager {
    public Client addClient(Client client);
    public Page<Client> getAllClient(int page, int taille);
    public Page<Client> searchClient(String keyword, int page, int taille);
    public List<Client> getByKeyword(String keyword);
    public Client getClientById(Integer id);
    public Client updateClient(Client client);
    public boolean deleteClient(Integer id);
    public void deleteAllClient();
}
