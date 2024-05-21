package ma.emsi.airport.web;

import ma.emsi.airport.dao.entities.Client;
import ma.emsi.airport.dao.entities.Passager;
import ma.emsi.airport.service.ClientManager;
import ma.emsi.airport.service.PassagerManager;
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
public class PassagerController {

    @Autowired
    PassagerManager passagerManager;

    @GetMapping("/ajouterPassager")
    public String ajouterPassagerForm(Model model) {
        model.addAttribute("passager", new Passager());
        return "ajouterPassager";
    }

    @PostMapping("/ajouterPassager")
    public String ajouterPassager(@ModelAttribute("passager") Passager passager,
                                BindingResult bindingResult,
                                Model model) {
        if (bindingResult.hasErrors()) {
            return "ajouterPassager";
        }
        passagerManager.addPassager(passager);
        return "redirect:/listPassager";
    }

    @GetMapping("/listPassager")
    public String listPassager(Model model,
                             @RequestParam(name = "page", defaultValue = "0") int page,
                             @RequestParam(name = "taille", defaultValue = "6") int taille,
                             @RequestParam(name = "search", defaultValue = "") String keyword) {
        Page<Passager> passagers = passagerManager.searchPassager(keyword, page, taille);
        model.addAttribute("listPassager", passagers.getContent());
        int[] pages = new int[passagers.getTotalPages()];
        model.addAttribute("pages", pages);
        model.addAttribute("keyword", keyword);
        model.addAttribute("currentPage", page);
        return "passagerLayout";
    }

    @GetMapping("/deletePassager")
    public String deletePassager(@RequestParam(name = "id") Integer id) {
        if (passagerManager.deletePassager(id)) {
            return "redirect:/listPassager";
        } else {
            return "redirect:/error";
        }
    }

    @GetMapping("/editPassager")
    public String editPassager(Model model, @RequestParam(name = "id") Integer id) {
        Passager passager = passagerManager.getPassagerById(id);
        if (passager != null) {
            model.addAttribute("passager", passager);
            return "updatePassager";
        } else {
            return "error";
        }
    }

    @PostMapping("/updatePassager")
    public String updatePassager(@ModelAttribute Passager passager, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "updatePassager";
        }
        Passager existingPassager = passagerManager.getPassagerById(passager.getId());
        if (existingPassager != null) {
            existingPassager.setNom(passager.getNom());
            // Mettez à jour d'autres champs si nécessaire
            passagerManager.updatePassager(existingPassager);
        }
        return "redirect:/listPassager";
    }
}
