package cn.seppel.guiliproducttools.service;

import cn.seppel.guiliproducttools.dto.StandardPriceDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: Huangsp
 * @create: 2019-09-22
 **/

public interface StandardPriceService {
    Map<Integer, List<StandardPriceDTO>> getStandardPriceMap();
}
