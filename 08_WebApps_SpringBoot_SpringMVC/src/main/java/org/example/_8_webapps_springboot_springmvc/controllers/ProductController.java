package org.example._8_webapps_springboot_springmvc.controllers;


import org.example._8_webapps_springboot_springmvc.model.Product;
import org.example._8_webapps_springboot_springmvc.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProductController {

    private final ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;

    }


    @RequestMapping("/products")
    public String viewProducts(Model model) {
        var products = productService.findAll();
        model.addAttribute("products", products);
        return "products.html";
    }

    @RequestMapping(path = "/products",
            method = RequestMethod.POST)
    public String addProduct(
            @RequestParam("name") String name,
            @RequestParam("price") double price,
            Model model
    ) {
        Product p = new Product();
        p.setName(name);
        p.setPrice(price);
        productService.addProduct(p);

        var products = productService.findAll();
        model.addAttribute("products", products);
        return "products.html";
    }
}
