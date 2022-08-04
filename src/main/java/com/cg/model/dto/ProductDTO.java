package com.cg.model.dto;

import com.cg.model.Category;
import com.cg.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ProductDTO {

    private String id;

    @Column(nullable = false)
    private String title;

    @Digits(integer = 12, fraction = 0)
    private String price;

    private String quantity;


    private String urlImage;

    @Valid
    private CategoryDTO categoryDTO;


    public Product toProduct(){
        return new Product()
                .setId(Long.parseLong(id))
                .setTitle(title)
                .setUrlImage(urlImage)
                .setPrice(new BigDecimal(Long.parseLong(price)))
                .setQuantity(Integer.parseInt(quantity))
                .setCategory(categoryDTO.toCategory());
    }
}
