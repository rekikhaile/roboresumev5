package com.riri.roboresume;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class ResumeController {

    @Autowired
    ResumeRepository resumeRepository;

    @Autowired
    AchievementRepository achievementRepository;

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
    public String processForm(@Valid Resume resume, BindingResult result) {
        if (result.hasErrors()) {
            return "resumeform";
        }
        resumeRepository.save(resume);
        return "redirect:/";
    }

    @RequestMapping("/achieveall")
    public String listAchievement(Model model) {
        model.addAttribute("achievements", achievementRepository.findAll());
        return "listachieve";
    }

    @GetMapping("/addachieve")
    public String addAchievement(Model model) {
        model.addAttribute("achievement", new Resume());
        return "achievementform";
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


    @GetMapping("/search")
    public String getSearch()
    {
        return "resumesearchform";
    }

    @PostMapping("/search")
    public String showSearchResults(HttpServletRequest request, Model model)
    {
        //Get the search string from the result form
        String searchString = request.getParameter("search");
        model.addAttribute("search",searchString);
        model.addAttribute("resumes",resumeRepository.findAllByNameContainingIgnoreCase(searchString));
        return "list";
    }
}
