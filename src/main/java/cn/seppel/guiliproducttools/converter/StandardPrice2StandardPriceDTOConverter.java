package cn.seppel.guiliproducttools.converter;

import cn.seppel.guiliproducttools.dto.ProductDTO;
import cn.seppel.guiliproducttools.dto.StandardPriceDTO;
import cn.seppel.guiliproducttools.entity.Product;
import cn.seppel.guiliproducttools.entity.StandardPrice;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: Huangsp
 * @create: 2019-03-17
 **/
public class StandardPrice2StandardPriceDTOConverter {
    public static StandardPriceDTO convert(StandardPrice standardPrice) {
        StandardPriceDTO standardPriceDTO = new StandardPriceDTO();
        BeanUtils.copyProperties(standardPrice, standardPriceDTO);
        return standardPriceDTO;
    }

    public static List<StandardPriceDTO> convert(List<StandardPrice> standardPriceList) {
        return standardPriceList.stream().map(e ->
                convert(e)
        ).collect(Collectors.toList());
    }
}
