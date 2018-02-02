package com.riri.roboresume;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class ResumeController {

    @Autowired
    ResumeRepository resumeRepository;

    @Autowired
    EduRepository eduRepository;

    @RequestMapping("/")
    public String listResumes(Model model) {
        model.addAttribute("resumes", resumeRepository.findAll());
        return "list";
    }

    @GetMapping("/add")
    public String resumeForm(Model model) {
        model.addAttribute("resume", new Resume());
        return "resumeform";
    }


    @PostMapping("/process")
    public String processForm(@Valid @ModelAttribute("resume") Resume resume, BindingResult result) {

        if (result.hasErrors()) {
            return "resumeform";
        }
        resumeRepository.save(resume);
        return "redirect:/addedu";
       // return "eduform";
    }

    @GetMapping("/addedu")
    public String eduForm(Model model) {
        model.addAttribute("education", new Edu());
        return "eduform";
    }

    @PostMapping("/process2")
    public String processForm2(@Valid @ModelAttribute("education") Edu education, BindingResult result) {
        if (result.hasErrors()) {
            return "resumeform";
        }
        eduRepository.save(education);
       return "redirect:";


    }

    @GetMapping("/display")
    public String displayResume(Model model){
        model.addAttribute("resumes", resumeRepository.findAll());
        model.addAttribute("educations", eduRepository.findAll());

        return "list";

    }



    @RequestMapping("/detail/{id}")
    public String showResume(@PathVariable("id") long id, Model model) {
        model.addAttribute("resume", resumeRepository.findOne(id));
        return "show";
    }

    @RequestMapping("/update/{id}")
    public String updateResume(@PathVariable("id") long id, Model model) {
        model.addAttribute("resume", resumeRepository.findOne(id));
        return "resumeform";
    }

    @RequestMapping("/delete/{id}")
    public String delResume(@PathVariable("id") long id){
        resumeRepository.delete(id);
        return "redirect:/";
    }

//
//
//    @GetMapping("/search")
//    public String getSearch()
//    {
//        return "resumesearchform";
//    }
//
//    @PostMapping("/search")
//    public String showSearchResults(HttpServletRequest request, Model model)
//    {
//        //Get the search string from the result form
//        String searchString = request.getParameter("search");
//        model.addAttribute("search",searchString);
//        //model.addAttribute("resumes",resumeRepository.findAllByNameContainingIgnoreCase(searchString));
//        return "list";
//    }
}
