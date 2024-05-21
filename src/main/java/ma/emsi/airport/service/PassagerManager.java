package ma.emsi.airport.service;

import ma.emsi.airport.dao.entities.Client;
import ma.emsi.airport.dao.entities.Escale;
import ma.emsi.airport.dao.entities.Passager;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface PassagerManager {
    public Passager addPassager(Passager passager);
    public Page<Passager> getAllPassager(int page, int taille);
    public Page<Passager> searchPassager(String keyword, int page, int taille);
    public List<Passager> getByKeyword(String keyword);
    public Passager getPassagerById(Integer id);
    public Passager updatePassager(Passager passager);
    public boolean deletePassager(Integer id);
    void deleteAllPassager();
}
