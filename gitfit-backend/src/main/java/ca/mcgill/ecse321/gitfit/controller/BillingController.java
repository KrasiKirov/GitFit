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

    /**
     * Get billing information for a customer
     *
     * @author Li Yang Lei (LeiLiYang)
     * @param username
     * @return billing information for given customer
     */
    @GetMapping("/customers/{username}/billing")
    public BillingResponseDto getBilling(@PathVariable("username") String username) {
        Billing billing = billingService.getBilling(username);
        return convertToResponseDto(billing);
    }


    /**
     * Create or update billing information for a customer
     * @author Li Yang Lei (LeiLiYang)
     * @param billing
     * @param username
     * @return persisted billing information for given customer
     */
    @PutMapping("/customers/{username}/billing")
    public BillingResponseDto createOrUpdateBilling(@RequestBody BillingRequestDto billing, @PathVariable("username") String username) {
        Billing updatedBilling = billingService.createOrUpdateBilling(billing.getCountry(), billing.getState(), billing.getPostalCode(), billing.getCardNumber(), billing.getAddress(), username);
        return convertToResponseDto(updatedBilling);
    }

    /**
     * Delete billing information for a customer
     *
     * @author Li Yang Lei (LeiLiYang)
     * @param username
     */
    @DeleteMapping("/customers/{username}/billing")
    public void deleteBilling(@PathVariable("username") String username) {
        billingService.deleteBilling(username);
    }

    /**
     * Convert billing to response dto
     *
     * @author Li Yang Lei (LeiLiYang)
     * @param billing
     * @return billing response dto
     */
    public BillingResponseDto convertToResponseDto(Billing billing) {
        BillingResponseDto billingResponseDto = new BillingResponseDto(billing.getCountry(), billing.getState(), billing.getPostalCode(), billing.getCardNumberEnd());
        return billingResponseDto;
    }
}
