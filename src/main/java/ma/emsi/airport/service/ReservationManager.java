package ma.emsi.airport.service;

import ma.emsi.airport.dao.entities.Client;
import ma.emsi.airport.dao.entities.Passager;
import ma.emsi.airport.dao.entities.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ReservationManager {
    public Reservation addReservation(Reservation reservation);
    public Page<Reservation> getAllReservation(int page, int taille);
    public Page<Reservation> searchReservation(String keyword, int page, int taille);
    public List<Reservation> getByKeyword(String keyword);
    public Reservation getReservationById(Integer id);
    public Reservation updateReservation(Reservation reservation);
    public boolean deleteReservation(Integer id);
    public void deleteAllReservation();
}
