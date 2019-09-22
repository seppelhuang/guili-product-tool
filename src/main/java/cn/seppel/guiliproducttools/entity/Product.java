package cn.seppel.guiliproducttools.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * @description: 商品实体类
 * @author: Huangsp
 * @create: 2019-03-16
 **/
@Entity
@Data
@DynamicUpdate
public class Product {
    @Id
    private Integer id;
    private Integer category;
    private String spec;
    private BigDecimal realLength;
    private BigDecimal realWidth;
    private BigDecimal realHeight;
    private BigDecimal adjustLength;
    private BigDecimal adjustWidth;
    private BigDecimal adjustHeight;
    private BigDecimal adjustVolume;
    private BigDecimal standardVolume;
    private BigDecimal price;
    private BigDecimal finalPrice;
    private BigDecimal standardPrice;
    private BigDecimal rate;
    private Integer x;
    private Integer y;
    private Integer z;
    private BigDecimal length;
    private BigDecimal width;
    private BigDecimal height;
    private BigDecimal volume;
    private Integer boxingNum;

}
