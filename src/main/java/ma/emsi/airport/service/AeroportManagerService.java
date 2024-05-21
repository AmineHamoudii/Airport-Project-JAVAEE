package ma.emsi.airport.service;

import lombok.AllArgsConstructor;
import ma.emsi.airport.dao.entities.Aeroport;
import ma.emsi.airport.dao.repository.AeroportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class AeroportManagerService implements AeroportManager {
    @Autowired
    private AeroportRepository aeroportRepository;

    @Override
    public Aeroport addAeroport(Aeroport aeroport) {
        return aeroportRepository.save(aeroport);
    }

    @Override
    public Page<Aeroport> getAllAeroport(int page, int taille) {
        return aeroportRepository.findAll(Pageable.ofSize(page));
    }

    @Override
    public Page<Aeroport> searchAeroport(String keyword, int page, int taille) {
        return aeroportRepository.findByidContains(keyword , PageRequest.of(page, taille));
    }

    @Override
    public List<Aeroport> getByKeyword(String keyword) {
        return null;
    }

    @Override
    public Aeroport getAeroportById(Integer id) {
        return aeroportRepository.findById(id).get();
    }

    @Override
    public Aeroport updateAeroport(Aeroport aeroport) {
        return aeroportRepository.save(aeroport);
    }

    @Override
    public boolean deleteAeroport(Integer id) {
        try {
            aeroportRepository.deleteById(id);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }
    @Override
    public void deleteAllAeroport() {
        aeroportRepository.deleteAll();
    }
}
