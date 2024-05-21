package ma.emsi.airport.service;

import lombok.AllArgsConstructor;
import ma.emsi.airport.dao.entities.Escale;
import ma.emsi.airport.dao.repository.EscaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class EscaleManagerService implements EscaleManager{
    @Autowired
    private EscaleRepository escaleRepository;
    @Override
    public Escale addEscale(Escale escale) {
        return escaleRepository.save(escale);
    }

    @Override
    public Page<Escale> getAllEscale(int page, int taille) {
        return escaleRepository.findAll(Pageable.ofSize(page));
    }

    @Override
    public Page<Escale> searchEscale(String keyword, int page, int taille) {
        return escaleRepository.findByidContains(keyword , PageRequest.of(page, taille));
    }

    @Override
    public List<Escale> getByKeyword(String keyword) {
        return null;
    }

    @Override
    public Escale getEscaleById(Integer id) {
        return escaleRepository.findById(id).get();
    }

    @Override
    public Escale updateEscale(Escale escale) {
        return escaleRepository.save(escale);
    }

    @Override
    public boolean deleteEscale(Integer id) {
        try {
            escaleRepository.deleteById(id);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }
    @Override
    public void deleteAllEscale() {
        escaleRepository.deleteAll();
    }
}
