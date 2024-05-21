package ma.emsi.airport.web;

import ma.emsi.airport.dao.entities.Client;
import ma.emsi.airport.dao.entities.Escale;
import ma.emsi.airport.service.ClientManager;
import ma.emsi.airport.service.EscaleManager;
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
public class EscaleController {
    @Autowired
    EscaleManager escaleManager;

    @GetMapping("/ajouterEscale")
    public String ajouterEscaleForm(Model model) {
        model.addAttribute("escale", new Escale());
        return "ajouterEscalet";
    }

    @PostMapping("/ajouterEscale")
    public String ajouterEscale(@ModelAttribute("escale") Escale escale,
                                BindingResult bindingResult,
                                Model model) {
        if (bindingResult.hasErrors()) {
            return "ajouterEscale";
        }
        escaleManager.addEscale(escale);
        return "redirect:/listEscale";
    }

    @GetMapping("/listEscale")
    public String listClient(Model model,
                             @RequestParam(name = "page", defaultValue = "0") int page,
                             @RequestParam(name = "taille", defaultValue = "6") int taille,
                             @RequestParam(name = "search", defaultValue = "") String keyword) {
        Page<Escale> escales = escaleManager.searchEscale(keyword, page, taille);
        model.addAttribute("listEscale", escales.getContent());
        int[] pages = new int[escales.getTotalPages()];
        model.addAttribute("pages", pages);
        model.addAttribute("keyword", keyword);
        model.addAttribute("currentPage", page);
        return "escaleLayout";
    }

    @GetMapping("/deleteEscale")
    public String deleteEscale(@RequestParam(name = "id") Integer id) {
        if (escaleManager.deleteEscale(id)) {
            return "redirect:/listEscale";
        } else {
            return "redirect:/error";
        }
    }

    @GetMapping("/editEscale")
    public String editEscale(Model model, @RequestParam(name = "id") Integer id) {
        Escale escale = escaleManager.getEscaleById(id);
        if (escale != null) {
            model.addAttribute("escale", escale);
            return "updateEscale";
        } else {
            return "error";
        }
    }

    @PostMapping("/updateEscale")
    public String updateEscale(@ModelAttribute Escale escale, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "updateEscale";
        }
        Escale existingEscale = escaleManager.getEscaleById(escale.getId());
        if (existingEscale != null) {
            existingEscale.setNombre(escale.getNombre());
            // Mettez à jour d'autres champs si nécessaire
            escaleManager.updateEscale(existingEscale);
        }
        return "redirect:/listClient";
    }
}
