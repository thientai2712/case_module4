package com.cg.service.product;

import com.cg.model.Product;
import com.cg.model.dto.ProductDTO;
import com.cg.service.IGeneralService;

import java.util.List;
import java.util.Optional;

public interface ProductService extends IGeneralService<Product> {

    List<ProductDTO> findAllProductDTO();

    List<ProductDTO> findAllProductDTOdeleteFalse();


    Optional<Product> findProductById (Long id);

    Boolean exitsById(Long id);

    List<ProductDTO> findProductDTOByTitle(String keySearch);
}
