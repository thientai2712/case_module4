package com.cg.controller.rest;

import com.cg.exception.DataInputException;
import com.cg.exception.ResourceNotFoundException;
import com.cg.model.Product;
import com.cg.model.User;
import com.cg.model.dto.ProductDTO;
import com.cg.service.product.ProductService;
import com.cg.util.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.Repository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductRestController {

    @Autowired
    private AppUtil appUtil;

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<?> getAllProducts(){
        List<ProductDTO> productDTOS = productService.findAllProductDTO();

        return new ResponseEntity<>(productDTOS, HttpStatus.CREATED);

    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable long id){
        Optional<Product>productOptional = productService.findById(id);
        if (!productOptional.isPresent()){
            throw new ResourceNotFoundException("Invalid customer ID");
        }
        return new ResponseEntity<>(productOptional.get().toProductDTO(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> doCreate(@Validated @RequestBody ProductDTO productDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return appUtil.mapErrorToResponse(bindingResult);
        }
        productDTO.setId(0L);

        Product newProduct = productService.save(productDTO.toProduct());


        return new ResponseEntity<>(newProduct.toProductDTO(), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<?> doUpdate(@Validated @RequestBody ProductDTO productDTO, BindingResult bindingResult){

        new ProductDTO().validate(productDTO, bindingResult);

        if (bindingResult.hasErrors()){
            return appUtil.mapErrorToResponse(bindingResult);
        }



        Boolean existId =productService.exitsById(productDTO.getId());

        if (!existId){
            throw new ResourceNotFoundException("Product ID invalid");
        }

        Product newProduct = productService.save(productDTO.toProduct());


        return new ResponseEntity<>(newProduct.toProductDTO() ,HttpStatus.ACCEPTED);
    }
}
