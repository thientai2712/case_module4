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

    private Long id;

    private String title;

    private String price;

    private String quantity;


    private String urlImage;

    @Valid
    private CategoryDTO category;

    public ProductDTO(Long id, String title,String urlImage , Integer quantity,BigDecimal price, Category category) {
        this.id = id;
        this.title = title;
        this.price = price.toString();
        this.quantity = quantity.toString();
        this.urlImage = urlImage;
        this.category = category.toCategoryDTO();
    }

    public Product toProduct(){
        return new Product()
                .setId(id)
                .setTitle(title)
                .setUrlImage(urlImage)
                .setPrice(new BigDecimal(Long.parseLong(price)))
                .setQuantity(Integer.parseInt(quantity))
                .setCategory(category.toCategory());
    }
}
