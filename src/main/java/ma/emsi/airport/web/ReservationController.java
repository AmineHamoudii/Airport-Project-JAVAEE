package ma.emsi.airport.web;

import ma.emsi.airport.dao.entities.Client;
import ma.emsi.airport.dao.entities.Reservation;
import ma.emsi.airport.service.ClientManager;
import ma.emsi.airport.service.ReservationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ReservationController {
    @Autowired
    ReservationManager reservationManager;

    @GetMapping("/ajouterReservation")
    public String ajouterReservationForm(Model model) {
        model.addAttribute("reservation", new Reservation());
        return "ajouterReservation";
    }

    @PostMapping("/ajouteReservation")
    public String ajouteReservation(@ModelAttribute("reservation") Reservation reservation,
                                BindingResult bindingResult,
                                Model model) {
        if (bindingResult.hasErrors()) {
            return "ajouterReservation";
        }
        reservationManager.addReservation(reservation);
        return "redirect:/listReservation";
    }

    @GetMapping("/listReservation")
    public String listReservation(Model model,
                             @RequestParam(name = "page", defaultValue = "0") int page,
                             @RequestParam(name = "taille", defaultValue = "6") int taille,
                             @RequestParam(name = "search", defaultValue = "") String keyword) {
        Page<Reservation> reservations = reservationManager.searchReservation(keyword, page, taille);
        model.addAttribute("listReservation", reservations.getContent());
        int[] pages = new int[reservations.getTotalPages()];
        model.addAttribute("pages", pages);
        model.addAttribute("keyword", keyword);
        model.addAttribute("currentPage", page);
        return "reservationLayout";
    }

    @GetMapping("/deleteReservation")
    public String deleteReservation(@RequestParam(name = "id") Integer id) {
        if (reservationManager.deleteReservation(id)) {
            return "redirect:/listReservation";
        } else {
            return "redirect:/error";
        }
    }

    @GetMapping("/editReservation")
    public String editReservation(Model model, @RequestParam(name = "id") Integer id) {
        Reservation reservation = reservationManager.getReservationById(id);
        if (reservation != null) {
            model.addAttribute("reservation", reservation);
            return "updateReservation";
        } else {
            return "error";
        }
    }

    @PostMapping("/updateReservation")
    public String updateReservation(@ModelAttribute Reservation reservation, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "updateReservation";
        }
        Reservation existingReservation = reservationManager.getReservationById(reservation.getId());
        if (existingReservation != null) {
            existingReservation.setDate(reservation.getDate());
            existingReservation.setPrix(reservation.getPrix());
            // Mettez à jour d'autres champs si nécessaire
            reservationManager.updateReservation(existingReservation);
        }
        return "redirect:/listClient";
    }
}
