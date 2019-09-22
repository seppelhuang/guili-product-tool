package cn.seppel.guiliproducttools.service.impl;

import cn.seppel.guiliproducttools.dto.ProductDTO;
import cn.seppel.guiliproducttools.dto.StandardPriceDTO;
import cn.seppel.guiliproducttools.entity.StandardPrice;
import cn.seppel.guiliproducttools.repository.StandardPriceRepository;
import cn.seppel.guiliproducttools.service.ProductService;
import cn.seppel.guiliproducttools.service.StandardPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: Huangsp
 * @create: 2019-09-22
 **/
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private StandardPriceService standardPriceService;

    @Override
    public boolean estimateBox(List<ProductDTO> productDTOList) {
        for (ProductDTO productDTO : productDTOList) {
            this.estimateBox(productDTO);
        }
        return true;
    }

    public boolean estimateBox(ProductDTO productDTO) {
        Integer maxX = getMaxX(productDTO.getAdjustLength());
        Integer maxY = getMaxY(productDTO.getAdjustWidth());
        Integer maxZ = getMaxZ(productDTO.getAdjustHeight());

        boolean isSuccess = false;
        int currentBoxingNum = 0;

        for (int x = maxX; x > 0; x--) {
            BigDecimal length = ((productDTO.getAdjustLength().add(new BigDecimal("0.3"))).multiply(BigDecimal.valueOf(x))).add(new BigDecimal("2"));
            if (length.compareTo(new BigDecimal("60")) > 0) {
                continue;
            }
            for (int y = maxY; y > 0; y--) {
                BigDecimal width = ((productDTO.getAdjustWidth().add(new BigDecimal("0.3"))).multiply(BigDecimal.valueOf(y))).add(new BigDecimal("2"));
                if (width.compareTo(new BigDecimal("40")) > 0) {
                    continue;
                }
                for (int z = maxZ; z > 0; z--) {
                    int boxingNum = x * y * z;
                    int mod = Math.floorMod(boxingNum, 6);
                    BigDecimal height = (BigDecimal.valueOf(z - 1).multiply(productDTO.getAdjustHeight().add(new BigDecimal("0.3")))).add(productDTO.getAdjustHeight()).add(new BigDecimal("3.3"));
                    BigDecimal volume = (length.multiply(width).multiply(height)).divide(new BigDecimal("1000000"), 6, BigDecimal.ROUND_HALF_UP);

                    if (isSuccess && (boxingNum < currentBoxingNum || (boxingNum == currentBoxingNum && volume.compareTo(productDTO.getVolume()) > 0))) {
                        break;
                    }
                    if (height.compareTo(new BigDecimal("50")) > 0) {
                        continue;
                    }
                    if (mod != 0 || volume.compareTo(new BigDecimal("0.085")) > 0) {
                        continue;
                    }
                    currentBoxingNum = boxingNum;
                    productDTO.setX(x);
                    productDTO.setY(y);
                    productDTO.setZ(z);
                    productDTO.setLength(length);
                    productDTO.setWidth(width);
                    productDTO.setHeight(height);
                    productDTO.setVolume(volume);
                    productDTO.setBoxingNum(boxingNum);
                    isSuccess = true;
                }
            }
        }
        return isSuccess;
    }


    @Override
    public boolean estimatePrice(List<ProductDTO> productDTOList) {
        Map<Integer, List<StandardPriceDTO>> standardPriceMap = this.standardPriceService.getStandardPriceMap();

        for (ProductDTO productDTO : productDTOList) {
            if (standardPriceMap.containsKey(productDTO.getCategory())) {
                StandardPriceDTO standardPriceDTO = getStandardPrice(productDTO, standardPriceMap.get(productDTO.getCategory()));
                if (standardPriceDTO == null || standardPriceDTO.getRate() == null || standardPriceDTO.getVolume() == null) {
                    continue;
                }
                BigDecimal price = ((productDTO.getAdjustVolume().subtract(standardPriceDTO.getVolume())).multiply(standardPriceDTO.getRate())).add(standardPriceDTO.getPrice());
                productDTO.setPrice(price);
                productDTO.setFinalPrice(price.multiply(new BigDecimal("1.15")));
                productDTO.setStandardPrice(standardPriceDTO.getPrice());
                productDTO.setStandardVolume(standardPriceDTO.getVolume());
                productDTO.setRate(standardPriceDTO.getRate());
            }
        }

        return true;
    }

    @Override
    public void estimate(List<ProductDTO> productDTOList) {
        this.estimateBox(productDTOList);
        this.estimatePrice(productDTOList);
    }

    private StandardPriceDTO getStandardPrice(ProductDTO productDTO, List<StandardPriceDTO> standardPrices) {
        if (CollectionUtils.isEmpty(standardPrices)) {
            return null;
        }
        for (StandardPriceDTO priceDTO : standardPrices) {
            if (productDTO.getAdjustVolume().compareTo(priceDTO.getVolume()) <= 0) {
                return priceDTO;
            }
        }
        return standardPrices.get(0);
    }


    private Integer getMaxX(BigDecimal length) {
        BigDecimal div = length.add(new BigDecimal("0.3"));
        return new BigDecimal("58").divide(div, 2, BigDecimal.ROUND_HALF_UP).intValue() + 1;
    }

    private Integer getMaxY(BigDecimal width) {
        BigDecimal div = width.add(new BigDecimal("0.3"));
        return new BigDecimal("38").divide(div, 2, BigDecimal.ROUND_HALF_UP).intValue() + 1;

    }

    private Integer getMaxZ(BigDecimal height) {
        return (new BigDecimal("46.7").divide(height.add(new BigDecimal("0.3")), 2, BigDecimal.ROUND_HALF_UP)).intValue() + 1;
    }
}
