package ma.emsi.airport.service;

import lombok.AllArgsConstructor;
import ma.emsi.airport.dao.entities.Compagnie;
import ma.emsi.airport.dao.repository.CompagnieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class CompagnieManagerService implements CompagnieManager{
    @Autowired
    private CompagnieRepository compagnieRepository;
    @Override
    public Compagnie addCompagnie(Compagnie compagnie) {
        return compagnieRepository.save(compagnie);
    }

    @Override
    public Page<Compagnie> getAllCompagnie(int page, int taille) {
        return compagnieRepository.findAll(Pageable.ofSize(page));
    }

    @Override
    public Page<Compagnie> searchCompagnie(String keyword, int page, int taille) {
        return compagnieRepository.findByidContains(keyword , PageRequest.of(page, taille));
    }

    @Override
    public List<Compagnie> getByKeyword(String keyword) {
        return null;
    }

    @Override
    public Compagnie getCompagnieById(Integer id) {
        return compagnieRepository.findById(id).get();
    }

    @Override
    public Compagnie updateCompagnie(Compagnie compagnie) {
        return compagnieRepository.save(compagnie);
    }

    @Override
    public boolean deleteCompagnie(Integer id) {
        try {
            compagnieRepository.deleteById(id);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }
    @Override
    public void deleteAllCompagnie() {
        compagnieRepository.deleteAll();
    }
}
