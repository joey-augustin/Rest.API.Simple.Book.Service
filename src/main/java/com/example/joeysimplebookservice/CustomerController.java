package com.example.joeysimplebookservice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final List<Customer> customers = new ArrayList<Customer>();
    private int nextId = 4;

    public CustomerController() {
        customers.add(new Customer(
                1,
                "Jon Smith",
                "jonsmith@notreal.email",
                "555-555-55555",
                "Fresno"
        ));

        customers.add(new Customer(
                2,
                "Rebecca Johnson",
                "rebeccajohnson@notreal.email",
                "333-333-33333",
                "Sacramento"
        ));

        customers.add(new Customer(
                3,
                "George Robinson",
                "georgerobinson@notreal.email",
                "888-888-88888",
                "Oakland"
        ));
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getCustomers() {
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable int id) {
        for (Customer customer : customers) {
            if (customer.getId() == id) {
                return ResponseEntity.ok(customer);
            }
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
        customer.setId(nextId++);
        customers.add(customer);
        return ResponseEntity.status(201).body(customer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable int id) {
        for (Customer customer : customers) {
            if (customer.getId() == id) {
                customers.remove(customer);
                return ResponseEntity.status(204).build();
            }
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable int id, @RequestBody Customer updateCustomer) {
        for (Customer customer : customers) {
            if (customer.getId() == id) {
                customer.setName(updateCustomer.getName());
                customer.setEmail(updateCustomer.getEmail());
                customer.setPhone(updateCustomer.getPhone());
                customer.setCity(updateCustomer.getCity());

                return ResponseEntity.ok(customer);
            }
        }

        return ResponseEntity.notFound().build();
    }
}
