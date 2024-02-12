package com.example.demo.controller;

import com.example.demo.entities.Location;
import com.example.demo.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

@Controller
public class LocationController {


    @Autowired
    LocationService service;

    @RequestMapping("/showCreate")
    public String showCreate(){
        return "createLocation";
    }

    @RequestMapping("/saveLoc")
    public String saveLocation(@ModelAttribute("location") Location location, RedirectAttributes redirectAttributes) {
        Location locationSaved = service.saveLocation(location);
        String msg = "Location saved successfully!";

        // Add the message as a flash attribute
        redirectAttributes.addFlashAttribute("msg", msg);

        return "redirect:showCreate";
    }

    @RequestMapping("/displayLocations")
    public String displayLocations(ModelMap modelMap) {
         List<Location> locationList = service.getAllLocation();
         modelMap.addAttribute("locations", locationList);
         return "displayLocation";
    }

    @RequestMapping("deleteLocation")
    public String deleteLocation(@RequestParam("id") int id, ModelMap modelMap){
        Location location = new Location();
        location.setId(id);
        service.deleteLocation(location);
        List<Location> locations = service.getAllLocation();
        modelMap.addAttribute("locations", locations);
        return "displayLocation";
    }

    @RequestMapping("/showUpdate")
    public String showUpdate(@RequestParam("id") int id, ModelMap modelMap) {
        Optional<Location> locationOptional = service.getLocationById(id);
        if (locationOptional.isPresent()) {
            Location location = locationOptional.get();
            modelMap.addAttribute("location", location);
        } else {
            System.out.println("Error!");
        }
        return "updateLocation";
    }

    @RequestMapping("/updateLoc")
    public String updateLocation(@ModelAttribute("location") Location location, ModelMap modelMap) {
        service.updateLocation(location);
        List<Location> locations = service.getAllLocation();
        modelMap.addAttribute("locations", locations);
        return "displayLocation";
    }

    @ModelAttribute("showMessage")
    public boolean getMessageStatus() {
        return true; // Change to true if you want message to be displayed initially
    }

}
