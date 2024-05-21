package ma.emsi.airport.dao.repository;

import jakarta.transaction.Transactional;
import ma.emsi.airport.dao.entities.Aeroport;
import ma.emsi.airport.dao.entities.Client;
import ma.emsi.airport.dao.entities.Compagnie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface CompagnieRepository extends JpaRepository<Compagnie,Integer> {
    Page<Compagnie> findByidContains(String keyword, Pageable pageable);

}
