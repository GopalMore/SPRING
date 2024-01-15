package com.example.spring.Batch.BatchConfig;

import com.example.spring.Batch.Entity.Customer;
import org.springframework.batch.item.ItemProcessor;

public class CustomerProcessor implements ItemProcessor<Customer,Customer> {
    @Override
    public Customer process(Customer Customer) throws Exception {
        return Customer;
    }
}
