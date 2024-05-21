package ma.emsi.airport.service;

import lombok.AllArgsConstructor;
import ma.emsi.airport.dao.entities.Reservation;
import ma.emsi.airport.dao.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor

public class ReservationManagerService implements ReservationManager{
    @Autowired
    private ReservationRepository reservationRepository;
    @Override
    public Reservation addReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    public Page<Reservation> getAllReservation(int page, int taille) {
        return reservationRepository.findAll(Pageable.ofSize(page));
    }

    @Override
    public Page<Reservation> searchReservation(String keyword, int page, int taille) {
        return reservationRepository.findByidContains(keyword , PageRequest.of(page, taille));
    }

    @Override
    public List<Reservation> getByKeyword(String keyword) {
        return null;
    }

    @Override
    public Reservation getReservationById(Integer id) {
        return reservationRepository.findById(id).get();
    }

    @Override
    public Reservation updateReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    public boolean deleteReservation(Integer id) {
        try {
            reservationRepository.deleteById(id);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }
    @Override
    public void deleteAllReservation() {
        reservationRepository.deleteAll();
    }
}
