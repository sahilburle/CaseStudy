package com.websitecontroller.security.modelclass;

import lombok.*;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Product {

    private String prId;
    private String prName;
    private String prType;
    private String prcategory;
    private Map<Integer, Double> prating;
    private Map<Integer, String> prreview;
    private List<String> primage;
    private Double prprice;
    private String prdescription;
    private Map<String, String> prspecifications;
}
