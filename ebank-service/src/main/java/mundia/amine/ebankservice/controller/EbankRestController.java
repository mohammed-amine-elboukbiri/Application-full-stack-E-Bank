package mundia.amine.ebankservice.controller;

import mundia.amine.ebankservice.entities.BankAccount;
import mundia.amine.ebankservice.service.EbankService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EbankRestController {
    private EbankService ebankService;

    public EbankRestController(EbankService ebankService) {
        this.ebankService = ebankService;
    }

    @GetMapping("/accounts")
    public List<BankAccount> getAllBankAccounts() {
        return ebankService.getAllBankAccounts();
    }

    @GetMapping("/accounts/{id}")
    public BankAccount findBankAccountById(@PathVariable String id) {
        return ebankService.findBankAccountById(id);
    }

    @PostMapping("/accounts")
    public BankAccount saveBankAccount(@RequestBody BankAccount bankAccount) {
        return ebankService.saveBankAccount(bankAccount);
    }
}
