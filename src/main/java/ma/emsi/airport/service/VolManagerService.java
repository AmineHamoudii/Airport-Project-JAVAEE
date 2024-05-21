package ma.emsi.airport.service;

import lombok.AllArgsConstructor;
import ma.emsi.airport.dao.entities.Vol;
import ma.emsi.airport.dao.repository.VolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class VolManagerService implements VolManager{
    @Autowired
    private VolRepository volRepository;
    @Override
    public Vol addVol(Vol vol) {
        return volRepository.save(vol);
    }

    @Override
    public Page<Vol> getAllVol(int page, int taille) {
        return volRepository.findAll(Pageable.ofSize(page));
    }

    @Override
    public Page<Vol> searchVol(String keyword, int page, int taille) {
        return volRepository.findByidContains(keyword , PageRequest.of(page, taille));
    }

    @Override
    public List<Vol> getByKeyword(String keyword) {
        return null;
    }

    @Override
    public Vol getVolById(Integer id) {
        return volRepository.findById(id).get();
    }

    @Override
    public Vol updateVol(Vol vol) {
        return volRepository.save(vol);
    }

    @Override
    public boolean deleteVol(Integer id) {
        try {
            volRepository.deleteById(id);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }
    @Override
    public void deleteAllVol() {
        volRepository.deleteAll();
    }
}
