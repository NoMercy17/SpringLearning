//package org.example._8_webapps_springboot_springmvc;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//@Controller
//public class Controller1 {
//
//    // using request param to set the color through HTTP
////    @RequestMapping("/home")
////    public String home(
////            @RequestParam("color") String color,
////            @RequestParam("name") String name,
////            Model model) {
////        model.addAttribute("username", name);
////        model.addAttribute("color", color);
//
//        // using request param to set the color through HTTP
//    @RequestMapping("/home/{color}/{name}")
//    public String home(
//            @PathVariable("color") String color,
//            @PathVariable("name") String name,
//            Model model) {
//        model.addAttribute("username", name);
//        model.addAttribute("color", color);
//
//        return "home.html";
//    }
//}
