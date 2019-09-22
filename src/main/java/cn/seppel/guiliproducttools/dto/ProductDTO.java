package cn.seppel.guiliproducttools.dto;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;

/**
 * @description:
 * @author: Huangsp
 * @create: 2019-09-21
 **/
@Data
public class ProductDTO implements java.io.Serializable {
    @Excel(name = "参考图片", type = 2, width = 30, height = 30, imageType = 1)
    private String image;
    @Excel(name = "货号")
    private Integer id;
    @Excel(name = "分类编码")
    private Integer category;
    @Excel(name = "规格")
    private String spec;
    private BigDecimal realLength = BigDecimal.ZERO;
    private BigDecimal realWidth = BigDecimal.ZERO;
    private BigDecimal realHeight = BigDecimal.ZERO;
    @Excel(name = "参考斜率")
    private BigDecimal rate = BigDecimal.ZERO;
    @Excel(name = "参考标准价格")
    private BigDecimal standardPrice = BigDecimal.ZERO;
    @Excel(name = "参考体积")
    private BigDecimal standardVolume = BigDecimal.ZERO;
    @Excel(name = "体积")
    private BigDecimal adjustVolume = BigDecimal.ZERO;
    @Excel(name = "价格")
    private BigDecimal price = BigDecimal.ZERO;
    @Excel(name = "价格*1.15")
    private BigDecimal finalPrice = BigDecimal.ZERO;
    @Excel(name = "产品最长")
    private BigDecimal adjustLength = BigDecimal.ZERO;
    @Excel(name = "产品最宽")
    private BigDecimal adjustWidth = BigDecimal.ZERO;
    @Excel(name = "产品最高")
    private BigDecimal adjustHeight = BigDecimal.ZERO;
    @Excel(name = "套装增量")
    private BigDecimal increment = BigDecimal.ZERO;

    @Excel(name = "长度方向数")
    private Integer x;
    @Excel(name = "宽度方向数")
    private Integer y;
    @Excel(name = "高度方向数")
    private Integer z;
    @Excel(name = "装箱数")
    private Integer boxingNum;
    @Excel(name = "箱宽")
    private BigDecimal length;
    @Excel(name = "箱宽")
    private BigDecimal width;
    @Excel(name = "箱高")
    private BigDecimal height;
    @Excel(name = "箱体积")
    private BigDecimal volume;

}
