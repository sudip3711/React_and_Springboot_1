package com.sudip.ecom_proj.service;

import com.sudip.ecom_proj.model.Product;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> readProducts();

    Product GetProductById(int id);

    Product addProduct(Product product, MultipartFile imageFile) throws IOException;

    Product updateProduct(int id, MultipartFile imageFile, Product product) throws IOException;

    void deleteProduct(int id);

    List<Product> searchProduct(String keyword);
}
