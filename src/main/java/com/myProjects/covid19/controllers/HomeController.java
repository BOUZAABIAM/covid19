package com.myProjects.covid19.controllers;

import com.myProjects.covid19.models.LocationStats;
import com.myProjects.covid19.services.CoronaVirusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    CoronaVirusDataService coronaVirusDataService;

    @GetMapping("/")
    public String home(Model model){

        List<LocationStats> allStats = coronaVirusDataService.getAllStats();
        int totalReprotedCases = allStats.stream().mapToInt(stat ->stat.getLatestTotalCases()).sum();
        int totalNewCases = allStats.stream().mapToInt(stat ->stat.getDiffFromPrevDay()).sum();

        model.addAttribute("locationStats",allStats);
        model.addAttribute("totalReprotedCases",totalReprotedCases);
        model.addAttribute("totalNewCases",totalNewCases);
        return "home";
    }
}
