package com.dotin.projectreactor.java_techie_utube.reactive_mongo.service;

import com.dotin.projectreactor.java_techie_utube.reactive_mongo.dto.ProductDTO;
import com.dotin.projectreactor.java_techie_utube.reactive_mongo.repository.ProductRepository;
import com.dotin.projectreactor.java_techie_utube.reactive_mongo.util.AppUtils;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Flux<ProductDTO> getProducts() {
        return productRepository.findAll().map(AppUtils::toDTO);
    }

    public Mono<ProductDTO> getProduct(String id) {
        return productRepository.findById(id).map(AppUtils::toDTO);
    }

//    public Flux<ProductDTO> getProductInRange(double min, double max) {
//        return productRepository.findByPr
//    }

}
