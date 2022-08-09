package com.cg.service.product;

import com.cg.model.Category;
import com.cg.model.Product;
import com.cg.model.dto.ProductDTO;
import com.cg.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService{

    @Autowired ProductRepository productRepository;

    @Override
    public List<Product> findAll() {
        return null;
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Product getById(Long id) {
        return null;
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void remove(Long id) {
    }

    @Override
    public void softDelete(Product product) {
        product.setDeleted(true);
        productRepository.save(product);
    }


    @Override
    public List<ProductDTO> findAllProductDTO() {
        return productRepository.findAllProductDTO();
    }

    @Override
    public List<ProductDTO> findAllProductDTOdeleteFalse() {
        return productRepository.findAllProductDTOdeleteFalse();
    }

    @Override
    public Optional<Product> findProductById(Long id) {
        return productRepository.findProductById(id);
    }

    @Override
    public Boolean exitsById(Long id) {
        return productRepository.existsById(id);
    }

    @Override
    public List<ProductDTO> findProductDTOByTitle(String keySearch) {
        return productRepository.findProductDTOByTitle(keySearch);
    }
}
