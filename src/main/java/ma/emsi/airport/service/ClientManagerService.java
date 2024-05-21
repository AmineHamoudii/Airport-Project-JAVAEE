package ma.emsi.airport.service;

import lombok.AllArgsConstructor;
import ma.emsi.airport.dao.entities.Client;
import ma.emsi.airport.dao.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class ClientManagerService implements  ClientManager {
    @Autowired
    private ClientRepository clientRepository;
    @Override
    public Client addClient(Client client) {

        return clientRepository.save(client);
    }

    @Override
    public Page<Client> getAllClient(int page, int taille) {
        return clientRepository.findAll(Pageable.ofSize(page));
    }

    @Override
    public Page<Client> searchClient(String keyword, int page, int taille) {
        return clientRepository.findByidContains(keyword , PageRequest.of(page, taille));
    }

    @Override
    public List<Client> getByKeyword(String keyword) {
        return null;
    }

    @Override
    public Client getClientById(Integer id) {
        return clientRepository.findById(id).get();
    }

    @Override
    public Client updateClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public boolean deleteClient(Integer id) {
        try {
            clientRepository.deleteById(id);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }
    @Override
    public void deleteAllClient() {
        clientRepository.deleteAll();
    }
}
