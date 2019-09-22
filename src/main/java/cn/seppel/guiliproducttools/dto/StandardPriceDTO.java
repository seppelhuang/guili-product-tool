package cn.seppel.guiliproducttools.dto;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @description:
 * @author: Huangsp
 * @create: 2019-09-21
 **/
@Data()
public class StandardPriceDTO implements java.io.Serializable{
    private Integer id;
    @Excel(name = "分类编码")
    private Integer category;
    @Excel(name = "斜率")
    private BigDecimal rate;
    @Excel(name = "价格")
    private BigDecimal price;
    @Excel(name = "产品体积")
    private BigDecimal volume;
}
