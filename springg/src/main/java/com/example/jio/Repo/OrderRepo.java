package com.example.jio.Repo;

import com.example.jio.model.Order;
import org.springframework.data.annotation.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
//

@Repository
public interface OrderRepo extends JpaRepository<Order,String> {

@Query(value = "select *from orders",nativeQuery = true)
List<Order> fetch();
}
