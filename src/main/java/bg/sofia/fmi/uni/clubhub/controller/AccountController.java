package bg.sofia.fmi.uni.clubhub.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import bg.sofia.fmi.uni.clubhub.model.Club;
import bg.sofia.fmi.uni.clubhub.model.Customer;
import bg.sofia.fmi.uni.clubhub.model.Login;
import bg.sofia.fmi.uni.clubhub.service.IClubService;
import bg.sofia.fmi.uni.clubhub.service.ICustomerService;

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

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/register")
    public String register() {
        return "accounts/register";
    }

    @GetMapping("/register-customer")
    public String registerCustomer(@ModelAttribute("customer") Customer customer) {
        return "accounts/register-customer";
    }

    @PostMapping(value = "/register-customer")
    public String registerCustomer(@Valid @ModelAttribute("customer") Customer customer, BindingResult result) {
        if (result.hasErrors()) {
            return "accounts/register-customer";
        }

        customerService.createNew(customer);
        return "redirect:/accounts/login";
    }

    @GetMapping("/register-club")
    public String registerClub(@ModelAttribute("club") Club club) {
        return "accounts/register-club";
    }

    @PostMapping("/register-club")
    public String registerClub(@Valid @ModelAttribute("club") Club club, BindingResult result) {
        if (result.hasErrors()) {
            return "accounts/register-club";
        }

        clubService.createNew(club);
        return "redirect:/accounts/login";
    }

    @GetMapping("/login")
    public String login(@ModelAttribute("login") Login login) {
        return "accounts/login";
    }
}
