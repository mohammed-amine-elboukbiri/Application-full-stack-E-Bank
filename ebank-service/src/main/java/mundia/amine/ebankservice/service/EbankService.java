package mundia.amine.ebankservice.service;

import mundia.amine.ebankservice.entities.BankAccount;
import mundia.amine.ebankservice.repository.BankAccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EbankService {
    private BankAccountRepository bankAccountRepository;

    public EbankService(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    public List<BankAccount> getAllBankAccounts() {
        return bankAccountRepository.findAll();
    }

    public BankAccount findBankAccountById(String id) {
        return bankAccountRepository.findById(id).orElseThrow(()->new RuntimeException("No Bank Account with this id"));
    }

    public BankAccount saveBankAccount(BankAccount bankAccount) {
        return bankAccountRepository.save(bankAccount);
    }
}
