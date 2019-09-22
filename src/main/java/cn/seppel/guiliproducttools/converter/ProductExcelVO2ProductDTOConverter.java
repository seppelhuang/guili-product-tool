package cn.seppel.guiliproducttools.converter;

import cn.seppel.guiliproducttools.dto.ProductDTO;
import cn.seppel.guiliproducttools.vo.excel.ProductExcelVO;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: Huangsp
 * @create: 2019-03-17
 **/
public class ProductExcelVO2ProductDTOConverter {

    public static ProductDTO convert(ProductExcelVO productExcelVO) {
        ProductDTO productDTO = new ProductDTO();
        BeanUtils.copyProperties(productExcelVO, productDTO);
        String spec = productExcelVO.getSpec();
        String[] a = splitSpec(spec);
        if(a==null){
            return productDTO;
        }
        if (a.length == 3) {
            productDTO.setRealLength(new BigDecimal(a[0]));
            productDTO.setRealWidth(new BigDecimal(a[1]));
            productDTO.setRealHeight(new BigDecimal(a[2]));
            productDTO.setAdjustLength(new BigDecimal(a[0]));
            productDTO.setAdjustWidth(new BigDecimal(a[1]));
            productDTO.setAdjustHeight(new BigDecimal(a[2]));
            if (productDTO.getCategory() != null && (productDTO.getCategory() == 1 || productDTO.getCategory() == 3 || productDTO.getCategory() == 4)) {
                BigDecimal aj = productDTO.getRealLength().compareTo(productDTO.getRealWidth()) > 0 ? productDTO.getRealLength() : productDTO.getRealWidth();
                productDTO.setAdjustWidth(aj);
                productDTO.setAdjustLength(aj);
            }
        }
        if (a.length == 2) {
            productDTO.setRealLength(new BigDecimal(a[0]));
            productDTO.setRealWidth(new BigDecimal(a[0]));
            productDTO.setRealHeight(new BigDecimal(a[1]));
            productDTO.setAdjustLength(new BigDecimal(a[0]));
            productDTO.setAdjustWidth(new BigDecimal(a[0]));
            productDTO.setAdjustHeight(new BigDecimal(a[1]));
        }
        productDTO.setIncrement(productDTO.getAdjustHeight());
        productDTO.setAdjustVolume(productDTO.getAdjustLength().multiply(productDTO.getAdjustWidth()).multiply(productDTO.getAdjustHeight()).divide(BigDecimal.valueOf(1000)));
        return productDTO;
    }

    private static String[] splitSpec(String spec) {
        String s = spec.replaceAll("[^0-9|.]", " ").trim();
        if (StringUtils.hasText(s)) {
            return s.split("\\s+");
        }
        return null;
    }

    public static List<ProductDTO> convert(List<ProductExcelVO> productExcelVOS) {
        return productExcelVOS.stream().map(e ->
                convert(e)
        ).collect(Collectors.toList());
    }
}
