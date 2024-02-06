package com.example.demo.controller;

import com.example.demo.entities.Location;
import com.example.demo.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Controller
public class LocationController {


    @Autowired
    LocationService service;

    @RequestMapping("/showCreate")
    public String showCreate(){
        return "createLocation";
    }

    @RequestMapping("/saveLoc")
    public String saveLocation(@ModelAttribute("location") Location location, ModelMap modelMap) {
        Location locationSaved = service.saveLocation(location);
        String msg = "Location saved with id: " + locationSaved.getId();
        modelMap.addAttribute("msg", msg);
        return "redirect:/showCreate?msg=" + URLEncoder.encode(msg, StandardCharsets.UTF_8);
    }

    @RequestMapping("/displayLocations")
    public String displayLocations(ModelMap modelMap) {
         List<Location> locationList = service.getAllLocation();
         modelMap.addAttribute("locations", locationList);
         return "displayLocation";
    }

}
