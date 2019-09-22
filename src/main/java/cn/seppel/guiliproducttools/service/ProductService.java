package cn.seppel.guiliproducttools.service;

import cn.seppel.guiliproducttools.dto.ProductDTO;

import java.util.List;

public interface ProductService {
    boolean estimateBox(List<ProductDTO> productDTOList);


    boolean estimatePrice(List<ProductDTO> productDTOList);

    void estimate(List<ProductDTO> productDTOList);

}
