package org.intel.dcg.leslie;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.CommandLineRunner;
import org.intel.dcg.leslie.dao.CustomerRepository;
import org.intel.dcg.leslie.domain.Customer;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.web.support.SpringBootServletInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class Application extends SpringBootServletInitializer {
    private static final Logger log = LoggerFactory.getLogger(Application.class);

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

    public static void main(String[] args){
        System.out.println("Project Start!");
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner demo(CustomerRepository repository) {
        return (args) -> {
            // save a couple of customers
            if (repository.count() == 0){
                repository.save(new Customer("Jack"));
                repository.save(new Customer("Leslie"));
                repository.save(new Customer("Mango"));

            }
            // fetch all customers
            log.info("Customers found with findAll():");
            log.info("-------------------------------");
            for (Customer customer : repository.findAll()) {
                log.info(customer.toString());
            }
            log.info("");

            // fetch an individual customer by ID
            Customer customer = repository.findOne(1L);
            log.info("Customer found with findOne(1L):");
            log.info("--------------------------------");
            log.info(customer.toString());
            log.info("");

            // fetch customers by last name
            log.info("Customer found with findByLastName('Leslie'):");
            log.info("--------------------------------------------");
            for (Customer leslie : repository.findByName("Leslie")) {
                log.info(leslie.toString());
            }
            log.info("");
        };
    }
}
