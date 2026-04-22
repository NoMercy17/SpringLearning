package org.example._4_datapersistance_springdata;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@org.springframework.web.bind.annotation.RestController
public class AccountController {

    private final TransferService transferService;
    public AccountController(TransferService transferService) {
        this.transferService = transferService;
    }


    @PostMapping("/transfer")
    public void transferMoney(
            @RequestBody TransferRequest request
    ){
        transferService.transferMoney(
                request.getSenderId(),
                request.getReceiverId(),
                request.getAmount()
        );
    }
    @GetMapping("/accounts")
    public Iterable<Account> getAllAccounts(
            @RequestParam(required = false) String name
    ){
        if(name == null){
            return transferService.findAllAccounts();
        }else{
            return transferService.findAccountsByName(name);
        }
    }
}
