package ma.emsi.airport.service;

import ma.emsi.airport.dao.entities.Aeroport;
import ma.emsi.airport.dao.entities.Client;
import ma.emsi.airport.dao.entities.Compagnie;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface CompagnieManager {
    public Compagnie addCompagnie(Compagnie compagnie);
    public Page<Compagnie> getAllCompagnie(int page, int taille);
    public Page<Compagnie> searchCompagnie(String keyword, int page, int taille);
    public List<Compagnie> getByKeyword(String keyword);
    public Compagnie getCompagnieById(Integer id);
    public Compagnie updateCompagnie(Compagnie compagnie);
    public boolean deleteCompagnie(Integer id);
    void deleteAllCompagnie();
}
