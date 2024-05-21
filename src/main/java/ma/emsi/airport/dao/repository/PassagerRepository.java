package ma.emsi.airport.dao.repository;

import jakarta.transaction.Transactional;
import ma.emsi.airport.dao.entities.Aeroport;
import ma.emsi.airport.dao.entities.Client;
import ma.emsi.airport.dao.entities.Passager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface PassagerRepository extends JpaRepository<Passager,Integer> {
    Page<Passager> findByidContains(String keyword, Pageable pageable);

}
