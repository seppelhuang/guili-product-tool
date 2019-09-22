package cn.seppel.guiliproducttools.converter;

import cn.seppel.guiliproducttools.dto.ProductDTO;
import cn.seppel.guiliproducttools.entity.Product;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: Huangsp
 * @create: 2019-03-17
 **/
public class Product2ProductDTOConverter {
    public static ProductDTO convert(Product product) {
        ProductDTO productDTO = new ProductDTO();
        BeanUtils.copyProperties(product, productDTO);
        return productDTO;
    }

    public static List<ProductDTO> convert(List<Product> orderList) {
        return orderList.stream().map(e ->
                convert(e)
        ).collect(Collectors.toList());
    }
}
