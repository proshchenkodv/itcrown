package ru.itcrown.repositories;

import ru.itcrown.models.Bank;
import ru.itcrown.models.BankAccount;

import java.util.List;

public interface BankRepository {

    List<Bank> findAll();
    Bank findById(Long id);
    Bank save(Bank bank);
    void removeById(Long id);

}
