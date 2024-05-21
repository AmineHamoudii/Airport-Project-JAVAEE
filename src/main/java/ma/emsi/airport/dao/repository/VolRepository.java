package ma.emsi.airport.dao.repository;

import jakarta.transaction.Transactional;
import ma.emsi.airport.dao.entities.Client;
import ma.emsi.airport.dao.entities.Vol;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface VolRepository extends JpaRepository<Vol,Integer> {
    Page<Vol> findByidContains(String keyword, Pageable pageable);

}
