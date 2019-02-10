package bg.sofia.fmi.uni.clubhub.controller;

import bg.sofia.fmi.uni.clubhub.model.Club;
import bg.sofia.fmi.uni.clubhub.model.Customer;
import bg.sofia.fmi.uni.clubhub.model.Login;
import bg.sofia.fmi.uni.clubhub.service.IClubService;
import bg.sofia.fmi.uni.clubhub.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("accounts")
public class AccountController {
    private final ICustomerService customerService;
    private final IClubService clubService;

    @Autowired
    public AccountController(ICustomerService customerService, IClubService clubService) {
        this.customerService = customerService;
        this.clubService = clubService;
    }

    @GetMapping("/register")
    public String register() {
        return "accounts/register";
    }

    @GetMapping("/register-customer")
    public String registerCustomer(@ModelAttribute("customer") Customer customer) { return "accounts/register-customer"; }

    @PostMapping(value = "/register-customer")
    public String registerCustomer(@Valid @ModelAttribute("customer")Customer customer, BindingResult result) {
        if (result.hasErrors()) {
            return "accounts/register-customer";
        }

        customerService.createNew(customer);
        return "redirect:/accounts/login-customer";
    }

    @GetMapping("/register-club")
    public String registerClub(@ModelAttribute("club") Club club) { return "accounts/register-club"; }

    @PostMapping(value = "/register-club")
    public String registerClub(@Valid @ModelAttribute("club")Club club, BindingResult result) {
        if (result.hasErrors()) {
            return "accounts/register-club";
        }

        clubService.createNew(club);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String login() {
        return "accounts/login";
    }

    @GetMapping("/login-customer")
    public String loginCustomer(@ModelAttribute("login") Login login) { return "accounts/login-customer"; }

    @PostMapping(value = "/login-customer")
    public String loginCustomer(@Valid @ModelAttribute("login")Login login, BindingResult result) {
        if (result.hasErrors()) {
            return "accounts/login-customer";
        }

        Optional<Customer> customer = customerService.getByUsername(login.getUsername());
        if (!customer.isPresent()) {
            return "redirect:/";
        }

        if (!customer.get().getPassword().equals(login.getPassword())) {
            return "redirect:/";
        }

        return "redirect:/";
    }

    @GetMapping("/login-club")
    public String loginClub(@ModelAttribute("club") Club club) { return "accounts/login-club"; }

    @PostMapping(value = "/login-club")
    public String loginClub(@Valid @ModelAttribute("club")Club club, BindingResult result) {
        if (result.hasErrors()) {
            return "accounts/login-club";
        }

        clubService.createNew(club);
        return "redirect:/";
    }
}