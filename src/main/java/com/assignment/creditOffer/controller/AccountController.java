package com.assignment.creditOffer.controller;

import com.assignment.creditOffer.model.AccountDetail;
import com.assignment.creditOffer.repository.AccountRepository;
import com.assignment.creditOffer.repository.OfferRepository;
import com.assignment.creditOffer.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class AccountController {

    @Autowired
    private AccountService accountService;



    @GetMapping("/accounts")
    public List<AccountDetail> getAllAccount(){
        return accountService.fetchAllAccounts();
    }

    //create account
    @PostMapping("/createAccount")
    public long createAccount(@RequestBody AccountDetail accountDetail){
        //TODO: handle if it already exists
        return accountService.addAccount(accountDetail);
    }

//    @GetMapping("{id}")
//    public ResponseEntity<Emp> getEmployeeById(@PathVariable long id){
//        Emp emp = offerRepository.findById(id).orElseThrow(()->
//                new ResourceNotFoundException("Employee not found with ID"+id));
//        return ResponseEntity.ok(emp);
//    }
//
//    @PutMapping("{id}")
//    public ResponseEntity<Emp> updateEmp(@PathVariable long id,@RequestBody Emp empDetail){
//            Emp emp = offerRepository.findById(id).orElseThrow(()->
//                    new ResourceNotFoundException("Employee not found with ID"+id));
//            emp.setEmail(empDetail.getEmail());
//            offerRepository.save(emp);
//            return ResponseEntity.ok(emp);
//    }


}
