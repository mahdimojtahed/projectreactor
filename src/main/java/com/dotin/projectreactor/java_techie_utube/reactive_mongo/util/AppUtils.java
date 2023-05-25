package com.dotin.projectreactor.java_techie_utube.reactive_mongo.util;

import com.dotin.projectreactor.java_techie_utube.reactive_mongo.domain.Product;
import com.dotin.projectreactor.java_techie_utube.reactive_mongo.dto.ProductDTO;
import org.springframework.beans.BeanUtils;

public class AppUtils {

    public static ProductDTO toDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        BeanUtils.copyProperties(product, productDTO);
        return productDTO;
    }

    public static Product toEntity(ProductDTO productDTO) {
        Product product = new Product();
        BeanUtils.copyProperties(productDTO, product);
        return product;
    }
}
