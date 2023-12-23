package com.example.jio.ServiceImpl;

import com.example.jio.Repo.OrderRepo;
import com.example.jio.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderImpl  {

    @Autowired
    OrderRepo orderRepo;

    @Autowired

    public List<Order> find() {
        List<Order> order = orderRepo.fetch();
        return order;

    }


}
