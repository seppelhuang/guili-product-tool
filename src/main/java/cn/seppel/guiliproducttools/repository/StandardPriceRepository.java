package cn.seppel.guiliproducttools.repository;

import cn.seppel.guiliproducttools.entity.StandardPrice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StandardPriceRepository extends JpaRepository<StandardPrice, Integer> {

    List<StandardPrice> findAllByCategoryOrderByVolume(Integer category);

}
