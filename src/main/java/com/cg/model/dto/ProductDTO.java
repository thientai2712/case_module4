package com.cg.model.dto;

import com.cg.model.Category;
import com.cg.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ProductDTO implements Validator {

    private Long id;

    @NotBlank(message = "Product Name is not required")
    private String title;

//    @Size(max = 7, message = "Maximum price is 1000000")
//    @Size(min = 2, message = "Maximum price is 100")
    @NotBlank(message = "Price is not null")
    private String price;


    @Size(max = 4, message = "Maximum price is 10000")
    @Size(min = 1, message = "Maximum quantity is 10")
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

    @Override
    public boolean supports(Class<?> aClass) {
        return ProductDTO.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {

        ProductDTO productDTO =(ProductDTO) target;

        String priceStr =productDTO.getPrice();

        if (priceStr == null){
            errors.rejectValue("price", "price.null", "Price is not null");
            return;
        }
        if (priceStr.isEmpty()){
            errors.rejectValue("price","price.isEmpty", "Price is not empty");
            return;
        }
        if (!priceStr.matches("(^$|[0-9]*$)")){
            errors.rejectValue("price", "price.matches", "Price only digit");
            return;
        }
        BigDecimal price = new BigDecimal(Long.parseLong(priceStr));
        BigDecimal min = new BigDecimal(50L);
        BigDecimal max = new BigDecimal(1000000L);

        if (price.compareTo(min) < 0) {
            errors.rejectValue("price", "price.min", "Price min 50");
            return;
        }
        if (price.compareTo(max) > 0) {
            errors.rejectValue("price", "price.min", "Price min 1.000.000");
        }

    }
}
