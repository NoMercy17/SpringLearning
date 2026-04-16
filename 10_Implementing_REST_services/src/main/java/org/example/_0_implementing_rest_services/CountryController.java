package org.example._0_implementing_rest_services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CountryController {

    @GetMapping("/all")
    public List<Country> all(){
         Country c1 = Country.of("France", 67);
         Country c2 = Country.of("Romania", 35);

         return List.of(c1, c2);
    }

    @GetMapping("/france")
    public ResponseEntity<Country> france(){
        Country c =  Country.of("France", 67);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .header("continent", "Europe")
                .header("favorite_food", "Fries")
                .header("capital", "Paris")
                .body(c);
    }
}
