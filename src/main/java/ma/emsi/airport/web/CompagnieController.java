package ma.emsi.airport.web;

import lombok.AllArgsConstructor;
import ma.emsi.airport.dao.entities.Client;
import ma.emsi.airport.dao.entities.Compagnie;
import ma.emsi.airport.service.CompagnieManager;
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
@AllArgsConstructor
public class CompagnieController {
    @Autowired
    CompagnieManager compagnieManager;

    @GetMapping("/ajouterCompagnie")
    public String ajouterCompagnieForm(Model model) {
        model.addAttribute("compagnie", new Compagnie());
        return "ajouterCompagnie";
    }

    @PostMapping("/ajouterCompagnie")
    public String ajouterCompagnie(@ModelAttribute("compagnie") Compagnie compagnie,
                                BindingResult bindingResult,
                                Model model) {
        if (bindingResult.hasErrors()) {
            return "ajouterCompagnie";
        }
        compagnieManager.addCompagnie(compagnie);
        return "redirect:/listClient";
    }

    @GetMapping("/listCompagnie")
    public String listCompagnie(Model model,
                             @RequestParam(name = "page", defaultValue = "0") int page,
                             @RequestParam(name = "taille", defaultValue = "6") int taille,
                             @RequestParam(name = "search", defaultValue = "") String keyword) {
        Page<Compagnie> compagnies = compagnieManager.searchCompagnie(keyword, page, taille);
        model.addAttribute("listCompagnie", compagnies.getContent());
        int[] pages = new int[compagnies.getTotalPages()];
        model.addAttribute("pages", pages);
        model.addAttribute("keyword", keyword);
        model.addAttribute("currentPage", page);
        return "compagnieLayout";
    }

    @GetMapping("/deleteCompagnie")
    public String deleteCompagnie(@RequestParam(name = "id") Integer id) {
        if (compagnieManager.deleteCompagnie(id)) {
            return "redirect:/listCompagnie";
        } else {
            return "redirect:/error";
        }
    }

    @GetMapping("/editCompagnie")
    public String editCompagnie(Model model, @RequestParam(name = "id") Integer id) {
        Compagnie compagnie = compagnieManager.getCompagnieById(id);
        if (compagnie != null) {
            model.addAttribute("compagnie", compagnie);
            return "updateCompagnie";
        } else {
            return "error";
        }
    }

    @PostMapping("/updateCompagnie")
    public String updateCompagnie(@ModelAttribute Compagnie compagnie, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "updateCompagnie";
        }
        Compagnie existingCompagnie = compagnieManager.getCompagnieById(compagnie.getId());
        if (existingCompagnie != null) {
            existingCompagnie.setNom(compagnie.getNom());
            // Mettez à jour d'autres champs si nécessaire
            compagnieManager.updateCompagnie(existingCompagnie);
        }
        return "redirect:/listCompagnie";
    }
}
