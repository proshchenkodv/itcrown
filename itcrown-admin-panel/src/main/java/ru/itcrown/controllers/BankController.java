package ru.itcrown.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.itcrown.models.Bank;
import ru.itcrown.services.BankService;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/banks")
@Slf4j
public class BankController {

    private final BankService bankService;


    @GetMapping()
    public String showAllBanks(Model model) {
        List<Bank> bankList = bankService.findAll();
        model.addAttribute("bankList", bankList);

        return "banks";
    }

    @GetMapping("/banks/edit/{id}")
    public String showBankEditForm(@PathVariable Long id, Model model) {
        Bank bank = bankService.findById(id);
        model.addAttribute("bank", bank);
        return "bank";
    }

    @GetMapping("/banks/add")
    public String addBank(
            Model model
    ) {
        model.addAttribute("bank", new Bank());
        return "bank_create_form";
    }

    @PostMapping("/banks/add")
    public String addBank(
            @ModelAttribute Bank bank,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return "banks_add_form";
        }
        bankService.saveOrUpdate(bank);
        return "redirect:/banks";
    }


    @PostMapping("/banks/edit")
    public String showEditForm(@ModelAttribute Bank bank) {
        bankService.saveOrUpdate(bank);
        return "redirect:/banks";
    }

    @DeleteMapping("/banks/remove/{id}")
    public String removeBank(
            @PathVariable("id") Long id,
            Model model
    ) {
        bankService.removeById(id);
        return "redirect:/banks";
    }

}
