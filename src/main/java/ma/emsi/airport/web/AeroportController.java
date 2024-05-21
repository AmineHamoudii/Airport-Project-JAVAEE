package ma.emsi.airport.web;

import lombok.AllArgsConstructor;
import ma.emsi.airport.dao.entities.Aeroport;
import ma.emsi.airport.service.AeroportManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
public class AeroportController {
    @Autowired
    private AeroportManager aeroportManager;

    @GetMapping("/ajouterAeroport")
    public String ajouterAeroportForm(Model model) {
        model.addAttribute("aeroport", new Aeroport());
        return "ajouterAeroport";
    }
    @PostMapping("/ajouterAeroport")
    public String ajouterAeroport(@ModelAttribute("aeroport") Aeroport aeroport,
                                  BindingResult bindingResult,
                                  Model model) {
        if (bindingResult.hasErrors()) {
            return "ajouterAeroport";
        }
        aeroportManager.addAeroport(aeroport);
        return "redirect:/listAeroport";
    }
    @GetMapping("/listAeroports")
    public String listAeroport(Model model,
                               @RequestParam(name = "page", defaultValue = "0") int page,
                               @RequestParam(name = "taille", defaultValue = "6") int taille,
                               @RequestParam(name = "search", defaultValue = "") String keyword) {
        Page<Aeroport> aeroports = aeroportManager.searchAeroport(keyword, page, taille);
        model.addAttribute("listAeroport", aeroports.getContent());
        int[] pages = new int[aeroports.getTotalPages()];
        model.addAttribute("pages", pages);
        model.addAttribute("keyword", keyword);
        model.addAttribute("currentPage", page);
        return "listAeroports";
    }
    @GetMapping("/deleteAeroport")
    public String deleteAeroport(@RequestParam(name = "id") Integer id) {
        if (aeroportManager.deleteAeroport(id)) {
            return "redirect:/listAeroport";
        } else {
            return "redirect:/error";
        }
    }
    @GetMapping("/editAeroport")
    public String editAeroport(Model model, @RequestParam(name = "id") Integer id) {
        Aeroport aeroport = aeroportManager.getAeroportById(id);
        if (aeroport != null) {
            model.addAttribute("aeroport", aeroport);
            return "updateAeroport";
        } else {
            return "error";
        }
    }
    @PostMapping("/updateAeroport")
    public String updateAeroport(@ModelAttribute Aeroport aeroport, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "updateAeroport";
        }
        Aeroport existingAeroport = aeroportManager.getAeroportById(aeroport.getId());
        if (existingAeroport != null) {
            existingAeroport.setNom(aeroport.getNom());
            existingAeroport.setPays(aeroport.getPays());
            existingAeroport.setVille(aeroport.getVille());


            // Mettez à jour d'autres champs si nécessaire
            aeroportManager.updateAeroport(existingAeroport);
        }
        return "redirect:/listClient";
    }


}




