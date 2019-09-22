package cn.seppel.guiliproducttools.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * @description:
 * @author: Huangsp
 * @create: 2019-09-21
 **/
@Entity
@Data
@DynamicUpdate
public class StandardPrice implements java.io.Serializable {
    @Id
    @GeneratedValue
    private Integer id;

    private Integer category;

    private BigDecimal rate;

    private BigDecimal price;

    private BigDecimal volume;
}
