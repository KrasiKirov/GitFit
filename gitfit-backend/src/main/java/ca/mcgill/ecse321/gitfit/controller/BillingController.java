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
    @GetMapping("/customers/{customerId}/billing")
    public BillingResponseDto getBilling(@PathVariable("customerId") Integer customerId) {
        Billing billing = billingService.getBilling(customerId);
        return convertToResponseDto(billing);
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/customers/{customerId}/billing")
    public BillingResponseDto createBilling(@RequestBody Billing billing, @PathVariable("customerId") Integer customerId) {
        Billing createdBilling = billingService.createBilling(billing.getCountry(), billing.getState(), billing.getPostalCode(), billing.getCardNumber(), billing.getAddress(), customerId);
        return convertToResponseDto(createdBilling);
    }

    //VERIFY IF THE ENDPOINT IS CORRECT
    //VERIFY IF THE ENDPOINT IS CORRECT
    //VERIFY IF THE ENDPOINT IS CORRECT
    //VERIFY IF THE ENDPOINT IS CORRECT
    ///customers/{customerId}/billing/{billingId} ?????
    @PutMapping("/customers/{customerId}/billing")
    public BillingResponseDto updateBilling(@RequestBody Billing billing, @PathVariable("customerId") Integer customerId) {
        Billing updatedBilling = billingService.updateBilling(billing.getCountry(), billing.getState(), billing.getPostalCode(), billing.getCardNumber(), billing.getAddress(), customerId);
        return convertToResponseDto(updatedBilling);
    }

    @DeleteMapping("/customers/{customerId}/billing")
    public void deleteBilling(@PathVariable("customerId") Integer customerId) {
        billingService.deleteBilling(customerId);
    }

    public BillingResponseDto convertToResponseDto(Billing billing) {
        BillingResponseDto billingResponseDto = new BillingResponseDto(billing.getCountry(), billing.getState(), billing.getPostalCode(), billing.getCardNumberEnd());
        return billingResponseDto;
    }

    public BillingRequestDto convertToRequestDto(Billing billing) {
        BillingRequestDto billingRequestDto = new BillingRequestDto(billing.getCountry(), billing.getState(), billing.getPostalCode(), billing.getCardNumber(), billing.getAddress(), billing.getCustomer().getCustomerId());
        return billingRequestDto;
    }
}
