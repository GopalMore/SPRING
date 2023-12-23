package com.example.jio.controller;

import com.example.jio.ServiceImpl.OrderImpl;
import com.example.jio.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app")
public class Controller {
//
    @Autowired
    OrderImpl order;

@GetMapping("/getmap")
public List<Order> GetAll(){
return order.find();
    }






}
