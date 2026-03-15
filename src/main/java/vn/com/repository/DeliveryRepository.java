package vn.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.com.entity.Delivery;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, Long>{

}
