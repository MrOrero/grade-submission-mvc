package com.ltp.gradesubmission.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ltp.gradesubmission.Grade;
import com.ltp.gradesubmission.service.GradeService;

@Controller
public class GradeController {

    @Autowired
    GradeService gradeService;
    // public GradeController(GradeRepository gradeRepository) {
    // this.gradeRepository = gradeRepository;
    // }

    // List<Grade> studentGrades = Arrays.asList(
    // new Grade("John", "Math", "A"),
    // new Grade("Hermione", "Arithmecy", "A+"),
    // new Grade("Neville", "Charms", "A-"));

    @GetMapping("/")
    public String gradeForm(Model model, @RequestParam(required = false) String id) {
        model.addAttribute("grade", gradeService.getGradeById(id));
        return "form";
    }

    @GetMapping("/grades")
    public String getGrades(Model model) {
        model.addAttribute("grades", gradeService.getStudentGrades());
        return "grades";
    }

    @PostMapping("/grades")
    public String submitGrades(@Valid Grade grade, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "form";
        }

        gradeService.submitGrade(grade);
        return "redirect:/grades";
    }

}
