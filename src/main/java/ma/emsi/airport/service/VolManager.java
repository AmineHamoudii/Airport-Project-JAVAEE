package ma.emsi.airport.service;

import ma.emsi.airport.dao.entities.Client;
import ma.emsi.airport.dao.entities.Escale;
import ma.emsi.airport.dao.entities.Vol;
import org.springframework.data.domain.Page;

import java.util.List;

public interface VolManager {
    public Vol addVol(Vol vol);
    public Page<Vol> getAllVol(int page, int taille);
    public Page<Vol> searchVol(String keyword, int page, int taille);
    public List<Vol> getByKeyword(String keyword);
    public Vol getVolById(Integer id);
    public Vol updateVol(Vol vol);
    public boolean deleteVol(Integer id);

    void deleteAllVol();
}
