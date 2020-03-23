package com.example.demo.Controller;

import com.example.demo.Service.MainService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainRestController {

    MainService mainService;

    public MainRestController(MainService mainService) {
        this.mainService = mainService;
    }

    @GetMapping("/number/random")
    public String generateNumber(){
        mainService.generate();
        return mainService.generateCarNumber();
    }

    @GetMapping("/number/next")
    public String getNext(){
        return mainService.nextNumber();
    }
}
