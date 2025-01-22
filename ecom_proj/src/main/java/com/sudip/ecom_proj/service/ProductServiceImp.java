package com.sudip.ecom_proj.service;

import com.sudip.ecom_proj.model.Product;
import com.sudip.ecom_proj.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImp implements ProductService {

    @Autowired
    private ProductRepo productRepo;

    @Override
    public List<Product> readProducts() {
        return productRepo.findAll();
    }

    @Override
    public Product GetProductById(int id) {
        return productRepo.findById(id).get();
    }

    @Override
    public Product addProduct(Product product, MultipartFile imageFile) throws IOException {
        product.setImageName(imageFile.getOriginalFilename());
        product.setImageType(imageFile.getContentType());
        product.setImageData(imageFile.getBytes());

       return productRepo.save(product);
    }

    @Override
    public Product updateProduct(int id, MultipartFile imageFile, Product product) throws IOException {
        product.setImageName(imageFile.getOriginalFilename());
        product.setImageType(imageFile.getContentType());
        product.setImageData(imageFile.getBytes());
        return productRepo.save(product);
    }

    @Override
    public void deleteProduct(int id) {
       productRepo.deleteById(id);
    }

    @Override
    public List<Product> searchProduct(String keyword) {
        return productRepo.searchProduct(keyword);
    }


}
