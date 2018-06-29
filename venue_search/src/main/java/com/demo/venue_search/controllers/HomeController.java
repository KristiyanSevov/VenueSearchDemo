package com.demo.venue_search.controllers;

import com.demo.venue_search.models.dto.VenueView;
import com.demo.venue_search.services.api.VenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Controller
public class HomeController {
    private final VenueService venueService;

    @Autowired
    public HomeController(VenueService venueService) {
        this.venueService = venueService;
    }

    @GetMapping("/")
    public String index(Model model, @ModelAttribute("errorMsg") String errorMsg) {
        model.addAttribute("dateString",
                LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        model.addAttribute("startHourString", "9");
        model.addAttribute("endHourString", "22");
        model.addAttribute("liveStream", "any");
        model.addAttribute("chairType", "any");
        model.addAttribute("tableType", "any");
        model.addAttribute("view", "views/index");
        model.addAttribute("errorMsg", errorMsg);
        return "base-layout";
    }

    @PostMapping("/")
    public String calculate(@RequestParam String dateString,
                            @RequestParam String startHourString,
                            @RequestParam String endHourString,
                            @RequestParam String liveStream,
                            @RequestParam String chairType,
                            @RequestParam String tableType,
                            RedirectAttributes redirectAttributes,
                            Model model) {
        Date date;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
        } catch (ParseException e) {
            redirectAttributes.addFlashAttribute("errorMsg",
                    "Please use date format of years-months-days (e.g. 2000-08-23)");
            return "redirect:/";
        }
        int startHour = Integer.parseInt(startHourString);
        int endHour = Integer.parseInt(endHourString);
        LocalDateTime searchDate = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                .atStartOfDay()
                .withHour(startHour);
        if (startHour >= endHour) {
            redirectAttributes.addFlashAttribute("errorMsg",
                    "Start hour must be earlier than end hour.");
            return "redirect:/";
        }
        if (searchDate.compareTo(LocalDateTime.now()) < 0) {
            redirectAttributes.addFlashAttribute("errorMsg",
                    "Date and time for search must be after the current date and time");
            return "redirect:/";
        }

//        //is search by capacity needed?
//        Set<VenueView> venues = venueService.findVenues(date, startHour, endHour, 0,
//                chairType, tableType, liveStream);

        Page<VenueView> venues = venueService.findVenuesByPage(date, startHour, endHour, 0, chairType,
                tableType, liveStream, PageRequest.of(0, 5));

        model.addAttribute("dateString", dateString);
        model.addAttribute("startHourString", startHourString);
        model.addAttribute("endHourString", endHourString);
        model.addAttribute("liveStream", liveStream);
        model.addAttribute("chairType", chairType);
        model.addAttribute("tableType", tableType);
        model.addAttribute("venues", venues);
        model.addAttribute("view", "views/venueResults");
        return "base-layout";
    }
}
