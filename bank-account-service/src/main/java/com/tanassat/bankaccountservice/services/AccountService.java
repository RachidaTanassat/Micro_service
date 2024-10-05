package com.tanassat.bankaccountservice.services;

import com.tanassat.bankaccountservice.dto.BankAccountRequestDTO;
import com.tanassat.bankaccountservice.dto.BankAccountResponseDTO;
import com.tanassat.bankaccountservice.entities.BankAccount;

public interface AccountService {
    public BankAccountResponseDTO addAccount(BankAccountRequestDTO bankAccountDTO);

    BankAccountResponseDTO updateAccount(String id, BankAccountRequestDTO bankAccountDTO);
}
