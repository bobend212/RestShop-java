package com.example.shopcart;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @GetMapping("/test")
    public String testMethod() {
        return "spring!!";
    }
}
