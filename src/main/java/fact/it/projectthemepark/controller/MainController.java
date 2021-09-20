package fact.it.projectthemepark.controller;
import fact.it.projectthemepark.model.Visitor;
import fact.it.projectthemepark.model.Staff;
import fact.it.projectthemepark.model.ThemePark;
import fact.it.projectthemepark.model.Attraction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@Controller
public class MainController {
    private ArrayList<Staff> staffMembers;
    private ArrayList<Visitor> visitors;
    private ArrayList<ThemePark> themeParks;
        @PostConstruct
    public void Fill(){
            staffMembers = fillStaffMembers();
            visitors = fillVisitors();
            themeParks = fillThemeParks();
        }


//Write your code here

    @RequestMapping("/0_exam")
    public String WelcomeStaff(Model model, HttpServletRequest request) {
        String firstName = request.getParameter("firstname");
        String surName = request.getParameter("surname");
        String startDate = request.getParameter("startDate");
        boolean student = (request.getParameter("student") !=null);
        Staff s = new Staff(firstName, surName);
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        s.setStudent(student);
        s.setStartDate(LocalDate.parse(startDate,df));
        model.addAttribute("Staff", s);
        int themePark = Integer.parseInt(request.getParameter("themePark"));
        if(themePark<0){
            String error = "You didn't choose a theme park!";
            model.addAttribute("errorMessage", error);
            return "80_Error";
        }
        model.addAttribute("park", themeParks.get(themePark));
        s.setEmployedAt(themeParks.get(themePark));
        staffMembers.add(s);
        return "0_exam";
    }

    @RequestMapping("/1_NewVisitor")
    public String newVisitor(Model model)
    {
        model.addAttribute("themePark" , themeParks);
        return "1_NewVisitor";
    }
    @RequestMapping("/2_NewVisitor")
    public String WelcomeNewVisitor(Model model, HttpServletRequest request){
        String firstName = request.getParameter("firstname");
        String surName = request.getParameter("surname");
        String yearOfBirthStr = request.getParameter("yearofbirth");
        String totalThemePark = request.getParameter("themeParkIndex");
        int yearOfBirth;
        yearOfBirth = Integer.parseInt(yearOfBirthStr);
        Visitor v = new Visitor(firstName,surName);
        v.setYearOfBirth(yearOfBirth);
        int themeParkIndex = Integer.parseInt(totalThemePark);
        ThemePark s = themeParks.get(themeParkIndex);
        model.addAttribute("Visitor", v);
        s.registerVisitor(v);
        visitors.add(v);
        return "2_NewVisitor";
    }@RequestMapping("/3_NewStaff")
    public String newStaff(Model model)
    {
        model.addAttribute("themeParks", themeParks);
        return "3_NewStaff";
    }
    @RequestMapping("/4_NewStaff")
    public String WelcomeNewStaff(Model model, HttpServletRequest request) {
        String firstName = request.getParameter("firstname");
        String surName = request.getParameter("surname");
        String startDate = request.getParameter("startDate");
        boolean student = (request.getParameter("student") !=null);
        Staff s = new Staff(firstName, surName);
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        s.setStudent(student);
        s.setStartDate(LocalDate.parse(startDate,df));
        model.addAttribute("Staff", s);
        staffMembers.add(s);
        return "4_NewStaff";
    }
    @RequestMapping("/5_ShowStaff")
    public String showStaff(Model model){
            model.addAttribute("staffMember", staffMembers);
            return "5_ShowStaff";
    }
    @RequestMapping("/6_ShowVisitor")
    public String showVisitors(Model model){
            model.addAttribute("visitor",visitors);
            return "6_ShowVisitor";
    }
    @RequestMapping("/7_addThemePark")
    public String newThemePark(){ return "7_addThemePark";
    }
    @RequestMapping("/8_ShowThemeparks")
    public String themeParkNew(Model model, HttpServletRequest request){
            String newTheme = request.getParameter("themePark");
            if(newTheme != null){
                ThemePark t = new ThemePark(newTheme);
                themeParks.add(t);
            }
            model.addAttribute("themeParks", themeParks);
            return "8_ShowThemeparks";
    }
    @RequestMapping("/9_addAttraction")
    public String newAttraction(Model model){
            model.addAttribute("themeParks", themeParks);
            model.addAttribute("staffMembers", staffMembers);
            return "9_addAttraction";
    }
    @RequestMapping("/10_newAttraction")
    public String newAttraction(Model model, HttpServletRequest request){
        model.addAttribute("themePark" , themeParks);
        String attractionName = request.getParameter("attractionName");
        if(attractionName == ""){
            String error = "You didn't choose a Attraction name!";
            model.addAttribute("errorMessage", error);
            return "80_Error";
        }
        int durationint = Integer.parseInt(request.getParameter("duration"));
        String duration = request.getParameter("duration");
        if(duration == ""){
            String error = "You didn't choose the duration!";
            model.addAttribute("errorMessage", error);
            return "80_Error";
        }
        String attractionPhoto = request.getParameter("attractionPhoto");
        if(attractionPhoto == ""){
            String error = "You didn't choose the picture file!";
            model.addAttribute("errorMessage", error);
            return "80_Error";
        }
        String responsible = request.getParameter("responsible") ;
        if(responsible.equals("-1")){
            String error = "You didn't choose a staff member!";
            model.addAttribute("errorMessage", error);
            return "80_Error";
        }
        int themePark = Integer.parseInt(request.getParameter("themePark"));
        if(themePark<0){
            String error = "You didn't choose a theme park!";
            model.addAttribute("errorMessage", error);
            return "80_Error";
        }
        String[] nameResponsible = responsible.split(" ");
        Staff st = new Staff(nameResponsible[0],nameResponsible[1]);
        Attraction a = new Attraction(attractionName);
        a.setDuration(durationint);
        a.setPhoto(attractionPhoto);
        a.setResponsible(st);
        themeParks.get(themePark).addAttraction(a);
        model.addAttribute("park", themeParks.get(themePark));
        model.addAttribute("attraction",a);
        model.addAttribute("staffMembers",st);
        return "10_newAttraction";
    }
    @RequestMapping("/80_Error")
    public String Wrong(Model model, HttpServletRequest request){
        return "80_Error";
    }

    @RequestMapping("/showDetailsThemePark")
    public String showDetailsThemePark(Model model, HttpServletRequest request){
            int themePark = Integer.parseInt(request.getParameter("themeParkIndex"));
            model.addAttribute("park",themeParks.get(themePark));
            return "10_newAttraction";

    }
    @RequestMapping("/11_searchAttraction")
    public String searchAttraction(Model model, HttpServletRequest request){
            String attraction = request.getParameter("attraction");
            for (ThemePark themepark : themeParks){
                Attraction atr = themepark.searchAttractionByName(attraction);
                if(atr !=null){
                    model.addAttribute("attraction",atr);
                    return "11_searchAttraction";
                }
            }

            String error = "There is no attraction with the name '"+attraction+"'";
            model.addAttribute("errorMessage",error);
            return "80_Error";
    }
    private ArrayList<Staff> fillStaffMembers() {
        ArrayList<Staff> staffMembers = new ArrayList<>();

        Staff staff1 = new Staff("Johan", "Bertels");
        staff1.setStartDate(LocalDate.of(2002, 5, 1));
        Staff staff2 = new Staff("An", "Van Herck");
        staff2.setStartDate(LocalDate.of(2019, 3, 15));
        staff2.setStudent(true);
        Staff staff3 = new Staff("Bruno", "Coenen");
        staff3.setStartDate(LocalDate.of(1995,1,1));
        Staff staff4 = new Staff("Wout", "Dayaert");
        staff4.setStartDate(LocalDate.of(2002, 12, 15));
        Staff staff5 = new Staff("Louis", "Petit");
        staff5.setStartDate(LocalDate.of(2020, 8, 1));
        staff5.setStudent(true);
        Staff staff6 = new Staff("Jean", "Pinot");
        staff6.setStartDate(LocalDate.of(1999,4,1));
        Staff staff7 = new Staff("Ahmad", "Bezeri");
        staff7.setStartDate(LocalDate.of(2009, 5, 1));
        Staff staff8 = new Staff("Hans", "Volzky");
        staff8.setStartDate(LocalDate.of(2015, 6, 10));
        staff8.setStudent(true);
        Staff staff9 = new Staff("Joachim", "Henau");
        staff9.setStartDate(LocalDate.of(2007,9,18));
        staffMembers.add(staff1);
        staffMembers.add(staff2);
        staffMembers.add(staff3);
        staffMembers.add(staff4);
        staffMembers.add(staff5);
        staffMembers.add(staff6);
        staffMembers.add(staff7);
        staffMembers.add(staff8);
        staffMembers.add(staff9);
        return staffMembers;
    }

    private ArrayList<Visitor> fillVisitors() {
        ArrayList<Visitor> visitors = new ArrayList<>();
        Visitor visitor1 = new Visitor("Dominik", "Mioens");
        visitor1.setYearOfBirth(2001);
        Visitor visitor2 = new Visitor("Zion", "Noops");
        visitor2.setYearOfBirth(1996);
        Visitor visitor3 = new Visitor("Maria", "Bonetta");
        visitor3.setYearOfBirth(1998);
        visitors.add(visitor1);
        visitors.add(visitor2);
        visitors.add(visitor3);
        visitors.get(0).addToWishList("De grote golf");
        visitors.get(0).addToWishList("Sky Scream");
        visitors.get(1).addToWishList("Piratenboot");
        visitors.get(1).addToWishList("Sky Scream");
        visitors.get(1).addToWishList("Halvar the ride");
        visitors.get(1).addToWishList("DreamCatcher");
        visitors.get(2).addToWishList("DinoSplash");
        return visitors;
    }

    private ArrayList<ThemePark> fillThemeParks() {
        ArrayList<ThemePark> themeparks = new ArrayList<>();
        ThemePark themepark1 = new ThemePark("Plopsaland");
        ThemePark themepark2 = new ThemePark("Plopsa Coo");
        ThemePark themepark3 = new ThemePark("Holiday Park");
        Attraction attraction1 = new Attraction("Anubis the Ride", 60);
        Attraction attraction2 = new Attraction("De grote golf", 180);
        Attraction attraction3 = new Attraction("Piratenboot",150);
        Attraction attraction4 = new Attraction("SuperSplash", 258);
        Attraction attraction5 = new Attraction("Dansende fonteinen");
        Attraction attraction6 = new Attraction("Halvar the ride",130);
        Attraction attraction7 = new Attraction("DinoSplash", 240);
        Attraction attraction8 = new Attraction("Bounty Tower", 180);
        Attraction attraction9 = new Attraction("Sky Scream",50);
        attraction1.setPhoto("/img/anubis the ride.jpg");
        attraction2.setPhoto("/img/de grote golf.jpg");
        attraction3.setPhoto("/img/piratenboot.jpg");
        attraction4.setPhoto("/img/supersplash.jpg");
        attraction5.setPhoto("/img/dansende fonteinen.jpg");
        attraction6.setPhoto("/img/halvar the ride.jpg");
        attraction7.setPhoto("/img/dinosplash.jpg");
        attraction8.setPhoto("/img/bountytower.jpg");
        attraction9.setPhoto("/img/sky scream.jpg");
        attraction1.setResponsible(staffMembers.get(0));
        attraction2.setResponsible(staffMembers.get(1));
        attraction3.setResponsible(staffMembers.get(2));
        attraction4.setResponsible(staffMembers.get(3));
        attraction5.setResponsible(staffMembers.get(4));
        attraction6.setResponsible(staffMembers.get(5));
        attraction7.setResponsible(staffMembers.get(6));
        attraction8.setResponsible(staffMembers.get(7));
        attraction9.setResponsible(staffMembers.get(8));
        themepark1.addAttraction(attraction1);
        themepark1.addAttraction(attraction2);
        themepark1.addAttraction(attraction3);
        themepark1.addAttraction(attraction4);
        themepark2.addAttraction(attraction5);
        themepark2.addAttraction(attraction6);
        themepark3.addAttraction(attraction7);
        themepark3.addAttraction(attraction8);
        themepark3.addAttraction(attraction9);
        themeparks.add(themepark1);
        themeparks.add(themepark2);
        themeparks.add(themepark3);
        return themeparks;
    }
}

