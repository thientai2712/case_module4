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
import javax.validation.constraints.*;
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

    @NotBlank(message = "Price is not null")
    private String price;

    @NotBlank(message = "Quantity is not null")
    @Min(value = 0, message = "Minimum quantity is 0")
    @Min(value = 1000, message = "Maximum quantity is 1000")
    @Pattern(regexp = "^[0-9]+$", message = "Quantity only digit")
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
        String quantityStr =productDTO.getQuantity();

//        if (quantityStr == null){
//            errors.rejectValue("quantity", "quantity.null", "Quantity is not null");
//            return;
//        }
        if (priceStr == null){
            errors.rejectValue("price", "price.null", "Price is not null");
            return;
        }
        if (priceStr.isEmpty()){
            errors.rejectValue("price","price.isEmpty", "Price is not empty");
            return;
        }
//        if (quantityStr.isEmpty()){
//            errors.rejectValue("quantity","quantity.isEmpty", "Quantity is not empty");
//            return;
//        }
        if (!priceStr.matches("(^$|[0-9]*$)")){
            errors.rejectValue("price", "price.matches", "Price only digit");
            return;
        }
        BigDecimal price = new BigDecimal(Long.parseLong(priceStr));



        BigDecimal min = new BigDecimal(50L);
        BigDecimal max = new BigDecimal(1000000L);


        if (price.compareTo(min) < 0) {
            errors.rejectValue("price", "price.min", "Minimum price is 50");
            return;
        }
        if (price.compareTo(max) > 0) {
            errors.rejectValue("price", "price.min", "Maximum price is 1.000.000");
        }



    }
}
