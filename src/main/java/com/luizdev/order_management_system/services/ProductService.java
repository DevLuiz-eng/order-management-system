package com.luizdev.order_management_system.services;

import com.luizdev.order_management_system.DTO.request.ProductRequestDTO;
import com.luizdev.order_management_system.DTO.response.ProductResponseDTO;
import com.luizdev.order_management_system.domain.Product;
import com.luizdev.order_management_system.exceptions.NotFoundProductException;
import com.luizdev.order_management_system.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Service

public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }


    public ProductResponseDTO createProduct(ProductRequestDTO requestDTO) {
        Product product = new Product();

        product.setStock(requestDTO.stock());
        product.setName(requestDTO.name());
        product.setPrice(requestDTO.price());

        repository.save(product);
        return returnResponse(product);
    }

    public ProductResponseDTO getProduct(Long id) {
        var product = searchProduct(id);
        return returnResponse(product);
    }

    @Transactional
    public ProductResponseDTO updateProduct(Long id, ProductRequestDTO requestDTO) {
        var product = searchProduct(id);

        product.setPrice(requestDTO.price());
        product.setStock(requestDTO.stock());
        product.setPrice(requestDTO.price());

        
        return returnResponse(product);
    }

    public void deleteProduct(Long id){
        var product = searchProduct(id);
        repository.delete(product);
    }

    public Product findForOrder (Long id) {
        return searchProduct(id);
    }

    private Product searchProduct (Long id) {
        return repository.
                findById(id).
                orElseThrow(() -> new NotFoundProductException("Product was not found."));
    }

    private ProductResponseDTO returnResponse(Product product) {
        return new ProductResponseDTO(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getStock());
    }






}
