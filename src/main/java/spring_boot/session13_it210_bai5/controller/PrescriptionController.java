package spring_boot.session13_it210_bai5.controller;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import spring_boot.session13_it210_bai5.model.Prescription;
import spring_boot.session13_it210_bai5.repo.impl.PrescriptionRepositoryImpl;

import java.util.List;

@Controller
@RequestMapping("/prescriptions")
public class PrescriptionController {

    @Autowired
    private PrescriptionRepositoryImpl prescriptionRepo;

    @GetMapping
    public String list(Model model) {
        List<Prescription> prescriptions = prescriptionRepo.findAll();
        model.addAttribute("prescriptions", prescriptions);

        return "prescription/list";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute Prescription prescription, @RequestParam List<Integer> quantities) {
        if (quantities != null) {
            for (Integer q : quantities) {
                if (q < 0) return "redirect:/prescriptions?error=negative_quantity";
            }
        }

        prescriptionRepo.save(prescription);
        return "redirect:/prescriptions";
    }
}