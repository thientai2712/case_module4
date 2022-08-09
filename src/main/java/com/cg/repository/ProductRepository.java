package com.cg.repository;

import com.cg.model.Product;
import com.cg.model.User;
import com.cg.model.dto.ProductDTO;
import com.cg.model.dto.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    @Query("SELECT new com.cg.model.dto.ProductDTO (" +
            "p.id, " +
            "p.title, " +
            "p.urlImage, " +
            "p.quantity, " +
            "p.price, " +
            "p.category " +
            ") " +
            "FROM Product AS p"
    )
    List<ProductDTO> findAllProductDTO();

    @Query("SELECT new com.cg.model.dto.ProductDTO (" +
            "p.id, " +
            "p.title, " +
            "p.urlImage, " +
            "p.quantity, " +
            "p.price, " +
            "p.category " +
            ") " +
            "FROM Product AS p WHERE p.deleted = false"
    )
    List<ProductDTO> findAllProductDTOdeleteFalse();

    Optional<Product> findProductById (Long id);

    @Query("SELECT new com.cg.model.dto.ProductDTO (" +
            "p.id, " +
            "p.title, " +
            "p.urlImage, " +
            "p.quantity, " +
            "p.price, " +
            "p.category " +
            ") " +
            "FROM Product AS p " +
            "WHERE p.title LIKE %?1% " +
            "ORDER BY p.title DESC"
    )
    List<ProductDTO> findProductDTOByTitle(String keySearch);

}
