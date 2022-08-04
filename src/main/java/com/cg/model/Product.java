package com.cg.model;

import javax.persistence.*;
import javax.validation.constraints.Digits;

import com.cg.model.dto.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
@Accessors(chain = true)
public class Product extends BaseEntities {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Digits(integer = 12, fraction = 0)
    private BigDecimal price;

    private int quantity;

    @Column(name = "url_image",nullable = false)
    private String urlImage;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;


    public ProductDTO toProductDTO(){
        return new ProductDTO()
                .setId(id.toString())
                .setUrlImage(urlImage)
                .setTitle(title)
                .setPrice(price.toString())
                .setQuantity(String.valueOf(quantity))
                .setCategoryDTO(category.toCategoryDTO());
    }

}
