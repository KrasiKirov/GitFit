package ca.mcgill.ecse321.gitfit.controller;

import ca.mcgill.ecse321.gitfit.dto.BillingDto;
import ca.mcgill.ecse321.gitfit.model.Billing;
import ca.mcgill.ecse321.gitfit.service.BillingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class BillingController {
    @Autowired
    private BillingService billingService;

    //VERIFY IF THE ENDPOINT IS CORRECT
    //VERIFY IF THE ENDPOINT IS CORRECT
    //VERIFY IF THE ENDPOINT IS CORRECT
    //VERIFY IF THE ENDPOINT IS CORRECT
    @GetMapping("/customers/{customerId}/billing")
    public BillingDto getBilling(@PathVariable("customerId") Integer customerId) {
        Billing billing = billingService.getBilling(customerId);
        return convertToDto(billing);
    }

    @PostMapping("/customers/{customerId}/billing")
    public BillingDto createBilling(@RequestBody Billing billing, @PathVariable("customerId") Integer customerId) {
        Billing createdBilling = billingService.createBilling(billing.getCountry(), billing.getState(), billing.getPostalCode(), billing.getCardNumber(), billing.getAddress(), customerId);
        return convertToDto(createdBilling);
    }

    //VERIFY IF THE ENDPOINT IS CORRECT
    //VERIFY IF THE ENDPOINT IS CORRECT
    //VERIFY IF THE ENDPOINT IS CORRECT
    //VERIFY IF THE ENDPOINT IS CORRECT
    @PutMapping("/customers/{customerId}/billing")
    public BillingDto updateBilling(@RequestBody Billing billing, @PathVariable("customerId") Integer customerId) {
        Billing updatedBilling = billingService.updateBilling(billing.getCountry(), billing.getState(), billing.getPostalCode(), billing.getCardNumber(), billing.getAddress(), customerId);
        return convertToDto(updatedBilling);
    }

    @DeleteMapping("/customers/{customerId}/billing")
    public void deleteBilling(@PathVariable("customerId") Integer customerId) {
        billingService.deleteBilling(customerId);
    }

    public BillingDto convertToDto(Billing billing) {
        BillingDto billingDto = new BillingDto(billing.getCountry(), billing.getState(), billing.getPostalCode(), billing.getCardNumberEnd());
        return billingDto;
    }
}
