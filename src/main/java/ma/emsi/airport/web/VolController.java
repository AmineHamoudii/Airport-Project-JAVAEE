package ma.emsi.airport.web;

import ma.emsi.airport.dao.entities.Vol;
import ma.emsi.airport.service.VolManager;
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
public class VolController {
    @Autowired
    VolManager volManager;
    @GetMapping("/ajouterVol")
    public String ajouterVolForm(Model model) {
        model.addAttribute("vol", new Vol());
        return "ajouterVol";
    }
    @PostMapping("/ajouterVol")
    public String ajouterVol(@ModelAttribute("vol") Vol vol,
                                BindingResult bindingResult,
                                Model model) {
        if (bindingResult.hasErrors()) {
            return "ajouterVol";
        }
        volManager.addVol(vol);
        return "redirect:/listVol";
    }

    @GetMapping("/listVol")
    public String listVol(Model model,
                             @RequestParam(name = "page", defaultValue = "0") int page,
                             @RequestParam(name = "taille", defaultValue = "6") int taille,
                             @RequestParam(name = "search", defaultValue = "") String keyword) {
        Page<Vol> vols = volManager.searchVol(keyword, page, taille);
        model.addAttribute("listVols", vols.getContent());
        int[] pages = new int[vols.getTotalPages()];
        model.addAttribute("pages", pages);
        model.addAttribute("keyword", keyword);
        model.addAttribute("currentPage", page);
        return "volLayout";
    }
    @GetMapping("/deleteVol")
    public String deleteVol(@RequestParam(name = "id") Integer id) {
        if (volManager.deleteVol(id)) {
            return "redirect:/listVol";
        } else {
            return "redirect:/error";
        }
    }
    @GetMapping("/editVol")
    public String editVol(Model model, @RequestParam(name = "id") Integer id) {
        Vol vol = volManager.getVolById(id);
        if (vol != null) {
            model.addAttribute("vol", vol);
            return "updateVol";
        } else {
            return "error";
        }
    }

    @PostMapping("/updateVol")
    public String updateVol(@ModelAttribute Vol vol, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "updateVol";
        }
        Vol existingVol = volManager.getVolById(vol.getId());
        if (existingVol != null) {
            existingVol.setJourdepart(vol.getJourdepart());
            existingVol.setJourarrive(vol.getJourarrive());
            existingVol.setHeurearrive(vol.getHeurearrive());
            existingVol.setHeuredepart(vol.getHeuredepart());
            // Mettez à jour d'autres champs si nécessaire
            volManager.updateVol(existingVol);
        }
        return "redirect:/listVol";
    }

}
