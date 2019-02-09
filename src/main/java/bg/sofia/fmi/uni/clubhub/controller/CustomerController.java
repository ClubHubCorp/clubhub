package bg.sofia.fmi.uni.clubhub.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import bg.sofia.fmi.uni.clubhub.model.Customer;
import bg.sofia.fmi.uni.clubhub.service.ICustomerService;

@Controller
@RequestMapping("customers")
public class CustomerController {

    private final ICustomerService customerService;

    @Autowired
    public CustomerController(ICustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/register")
    public String register() {
        return "customers/register";
    }

    @GetMapping(value = "{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> getUser(@PathVariable("id") UUID id) {
        Optional<Customer> customer = customerService.getById(id);
        if (!customer.isPresent()) {
            return notFound().build();
        }

        return ok(customer.get());
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getUsers(@RequestParam("page") int page, @RequestParam("size") int size) {
        if (page < 0 || size <= 0) {
            return badRequest().build();
        }

        return ok(customerService.getAll(PageRequest.of(page, size)));
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> createUser(@Valid @RequestBody Customer customer) {
        return status(CREATED).body(customerService.createNew(customer));
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteUser(UUID id) {
        customerService.deleteById(id);
        return ok().build();
    }
}
