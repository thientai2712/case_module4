package com.cg.model;

import javax.persistence.Id;

public class Product extends BaseEntities {

    @Id
    private Long id;
    private String productName;
    private String price;
    private String quantity;

}
