package cn.seppel.guiliproducttools.repository;


import cn.seppel.guiliproducttools.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
