package ca.mcgill.ecse321.gitfit.controller;

import ca.mcgill.ecse321.gitfit.dto.BillingRequestDto;
import ca.mcgill.ecse321.gitfit.dto.BillingResponseDto;
import ca.mcgill.ecse321.gitfit.model.Billing;
import ca.mcgill.ecse321.gitfit.service.BillingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class BillingController {
    @Autowired
    private BillingService billingService;

    //VERIFY IF THE ENDPOINT IS CORRECT
    //VERIFY IF THE ENDPOINT IS CORRECT
    //VERIFY IF THE ENDPOINT IS CORRECT
    //VERIFY IF THE ENDPOINT IS CORRECT
    // /customers/{customerId}/billing/{billingId}
    @GetMapping("/customers/{username}/billing")
    public BillingResponseDto getBilling(@PathVariable("username") String username) {
        Billing billing = billingService.getBilling(username);
        return convertToResponseDto(billing);
    }
//    @ResponseStatus(HttpStatus.CREATED)
//    @PostMapping("/customers/{username}/billing")
//    public BillingResponseDto createBilling(@RequestBody Billing billing, @PathVariable("username") String username) {
//        Billing createdBilling = billingService.createBilling(billing.getCountry(), billing.getState(), billing.getPostalCode(), billing.getCardNumber(), billing.getAddress(), username);
//        return convertToResponseDto(createdBilling);
//    }

    //VERIFY IF THE ENDPOINT IS CORRECT
    //VERIFY IF THE ENDPOINT IS CORRECT
    //VERIFY IF THE ENDPOINT IS CORRECT
    //VERIFY IF THE ENDPOINT IS CORRECT
    ///customers/{username}/billing/{billingId} ?????
    @PutMapping("/customers/{username}/billing")
    public BillingResponseDto createOrUpdateBilling(@RequestBody Billing billing, @PathVariable("username") String username) {
        Billing updatedBilling = billingService.createOrUpdateBilling(billing.getCountry(), billing.getState(), billing.getPostalCode(), billing.getCardNumber(), billing.getAddress(), username);
        return convertToResponseDto(updatedBilling);
    }

    @DeleteMapping("/customers/{username}/billing")
    public void deleteBilling(@PathVariable("username") String username) {
        billingService.deleteBilling(username);
    }

    public BillingResponseDto convertToResponseDto(Billing billing) {
        BillingResponseDto billingResponseDto = new BillingResponseDto(billing.getCountry(), billing.getState(), billing.getPostalCode(), billing.getCardNumberEnd());
        return billingResponseDto;
    }

    public BillingRequestDto convertToRequestDto(Billing billing) {
        BillingRequestDto billingRequestDto = new BillingRequestDto(billing.getCountry(), billing.getState(), billing.getPostalCode(), billing.getCardNumber(), billing.getAddress(), billing.getCustomer().getUsername());
        return billingRequestDto;
    }
}
