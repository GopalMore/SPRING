package com.example.spring.Batch.BatchConfig;

import com.example.spring.Batch.Entity.Customer;
import com.example.spring.Batch.Entity.Repo.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

@Configuration
@EnableBatchProcessing
@AllArgsConstructor
public class SpringBatchConfig {

    //for Job
    private JobBuilderFactory jobBuilderFactory;
    //for step
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    //for repo of customer
    private CustomerRepository customerRepository;


    //Create 1st step of Reader
    @Bean
    //Define a FlatfileItemReader of Customer Class
    public FlatFileItemReader<Customer> reader(){
        //Create a Customer object of FlatfileItemReader
        FlatFileItemReader<Customer> itemReader = new FlatFileItemReader<>();

        itemReader.setResource(new FileSystemResource("src/main/resources/customers.csv"));
        itemReader.setName("csvReader");
        itemReader.setLinesToSkip(1);
        itemReader.setLineMapper(lineMapper());
return itemReader;
    }
private LineMapper<Customer> lineMapper(){
    DefaultLineMapper<Customer> lineMapper = new DefaultLineMapper<>();
    DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
    lineTokenizer.setDelimiter(",");
    lineTokenizer.setStrict(false);
    lineTokenizer.setNames("id","firstName","lastName","email","gender","contactNo","country","job");

    BeanWrapperFieldSetMapper<Customer> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
    fieldSetMapper.setTargetType(Customer.class);

    lineMapper.setLineTokenizer(lineTokenizer);
    lineMapper.setFieldSetMapper(fieldSetMapper);
    return lineMapper;

}
    @Bean
    public CustomerProcessor processor() {
        return new CustomerProcessor();
    }


public RepositoryItemWriter<Customer> writer(){
        RepositoryItemWriter<Customer> writer = new RepositoryItemWriter<>();
        writer.setRepository(customerRepository);
        writer.setMethodName("save");
        return writer;
}

@Bean
public Step step1(){
        return stepBuilderFactory.get("csv-step").<Customer,Customer>chunk(5)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
}
@Bean
public Job runjob(){
        return jobBuilderFactory.get("importCustomers")
                .flow(step1())
                .next(step1()).end().build();
}

}

