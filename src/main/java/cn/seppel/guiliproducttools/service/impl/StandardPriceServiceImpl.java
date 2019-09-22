package cn.seppel.guiliproducttools.service.impl;

import cn.seppel.guiliproducttools.converter.StandardPrice2StandardPriceDTOConverter;
import cn.seppel.guiliproducttools.dto.StandardPriceDTO;
import cn.seppel.guiliproducttools.entity.StandardPrice;
import cn.seppel.guiliproducttools.repository.StandardPriceRepository;
import cn.seppel.guiliproducttools.service.StandardPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: Huangsp
 * @create: 2019-09-22
 **/
@Service
public class StandardPriceServiceImpl implements StandardPriceService {
    @Autowired
    private StandardPriceRepository standardPriceRepository;


    @Override
    public Map<Integer, List<StandardPriceDTO>> getStandardPriceMap() {
        Sort.Order order1 = new Sort.Order(Sort.Direction.ASC, "category");
        Sort.Order order2 = new Sort.Order(Sort.Direction.ASC, "volume");
        Sort sort = Sort.by(order1, order2);
        List<StandardPrice> standardPrices = this.standardPriceRepository.findAll(sort);
        List<StandardPriceDTO> standardPriceDTOS = StandardPrice2StandardPriceDTOConverter.convert(standardPrices);
        return standardPriceDTOS.stream()
                .collect(Collectors.groupingBy(StandardPriceDTO::getCategory));
    }
}
