package spring_boot.session13_it210_bai5.controller;

import ch.qos.logback.core.model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import spring_boot.session13_it210_bai5.repo.impl.PrescriptionRepositoryImpl;

import java.util.List;

@Controller
@RequestMapping("/prescriptions")
public class PrescriptionController {
    @Autowired
    private PrescriptionRepositoryImpl prescriptionRepo;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("prescriptions", prescriptionRepo.findAll());
        return "prescription/list";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute Prescription prescription, @RequestParam List<Integer> quantities) {
        for (Integer q : quantities) {
            if (q < 0) return "redirect:/prescriptions?error=negative_quantity";
        }
        prescriptionRepo.save(prescription);
        return "redirect:/prescriptions";
    }
}