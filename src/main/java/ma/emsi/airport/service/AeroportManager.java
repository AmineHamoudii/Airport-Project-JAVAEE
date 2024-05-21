package ma.emsi.airport.service;

import ma.emsi.airport.dao.entities.Aeroport;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AeroportManager {
    public Aeroport addAeroport(Aeroport aeroport);
    public Page<Aeroport> getAllAeroport(int page, int taille);
    public Page<Aeroport> searchAeroport(String keyword, int page, int taille);
    public List<Aeroport> getByKeyword(String keyword);
    public Aeroport getAeroportById(Integer id);
    public Aeroport updateAeroport(Aeroport aeroport);
    public boolean deleteAeroport(Integer id);
    public void deleteAllAeroport();
}
