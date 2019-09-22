package cn.seppel.guiliproducttools.vo.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @description:
 * @author: Huangsp
 * @create: 2019-09-21
 **/
@Data
public class ProductExcelVO implements java.io.Serializable {
    @Excel(name = "参考图片", type = 2, width = 40, height = 40, imageType = 1)
    private String image;
    @Excel(name = "货号")
    private Integer id;
    @Excel(name = "分类编码")
    private Integer category;
    @Excel(name = "规格")
    private String spec;

}
