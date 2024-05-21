package ma.emsi.airport.service;

import ma.emsi.airport.dao.entities.Client;
import ma.emsi.airport.dao.entities.Compagnie;
import ma.emsi.airport.dao.entities.Escale;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface EscaleManager {
    public Escale addEscale(Escale escale);
    public Page<Escale> getAllEscale(int page, int taille);
    public Page<Escale> searchEscale(String keyword, int page, int taille);
    public List<Escale> getByKeyword(String keyword);
    public Escale getEscaleById(Integer id);
    public Escale updateEscale(Escale escale);
    public boolean deleteEscale(Integer id);
    void deleteAllEscale();
}
