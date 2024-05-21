package ma.emsi.airport.service;

import lombok.AllArgsConstructor;
import ma.emsi.airport.dao.entities.Passager;
import ma.emsi.airport.dao.repository.PassagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class PassagerManagerservice implements PassagerManager{
    @Autowired
    private PassagerRepository passagerRepository;
    @Override
    public Passager addPassager(Passager passager) {
        return passagerRepository.save(passager);
    }

    @Override
    public Page<Passager> getAllPassager(int page, int taille) {
        return passagerRepository.findAll(Pageable.ofSize(page));
    }

    @Override
    public Page<Passager> searchPassager(String keyword, int page, int taille) {
        return passagerRepository.findByidContains(keyword , PageRequest.of(page, taille));
    }

    @Override
    public List<Passager> getByKeyword(String keyword) {
        return null;
    }

    @Override
    public Passager getPassagerById(Integer id) {
        return passagerRepository.findById(id).get();
    }

    @Override
    public Passager updatePassager(Passager passager) {
        return passagerRepository.save(passager);
    }

    @Override
    public boolean deletePassager(Integer id) {
        try {
            passagerRepository.deleteById(id);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }
    @Override
    public void deleteAllPassager() {
        passagerRepository.deleteAll();
    }

}
