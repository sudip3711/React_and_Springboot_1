package com.sudip.ecom_proj.controller;


import com.sudip.ecom_proj.model.Product;
import com.sudip.ecom_proj.service.ProductServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductServiceImp productService;

    @GetMapping("/products")
    public List<Product>GetProduct(){
        return productService.readProducts();
    }

    @GetMapping("/product/{id}")
    public Product getProductById(@PathVariable int id){
        return productService.GetProductById(id);
    }

    @PostMapping("/product")
    public Product addProduct(@RequestPart Product product, @RequestPart MultipartFile imageFile){
        try {
            System.out.println(product);
            return productService.addProduct(product,imageFile);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/product/{id}/image")
    public byte[] getImageProductId(@PathVariable int id){
        Product product = productService.GetProductById(id);
        return product.getImageData();
    }

    @PutMapping("/product/{id}")
    public String updateProduct(@PathVariable int id, @RequestPart Product product, @RequestPart MultipartFile imageFile) throws IOException {

        Product product1 = productService.updateProduct(id,imageFile,product);
        return product1!=null?"Updated":"Failed to update";
    }

    @DeleteMapping("/product/{id}")
    public  String deleteProduct(@PathVariable int id){
        Product product = productService.GetProductById(id);
        if(product!=null){
            productService.deleteProduct(id);
            return "Deleted";
        }else
            return "Product not found";
    }

    @GetMapping("/products/search")
    public  List<Product> searchProduct(@RequestParam String keyword){
        return productService.searchProduct(keyword);
    }

}
