package ca.mcgill.ecse321.gitfit.controller;

import ca.mcgill.ecse321.gitfit.service.OwnerAccountService;
import ca.mcgill.ecse321.gitfit.dto.OwnerAccountDto;
import ca.mcgill.ecse321.gitfit.model.Owner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * This class is responsible for handling HTTP requests for owner operations
 * 
 * @author Jatin Patel (Jatin-Pat)
 */
@CrossOrigin(origins = "*")
@RestController
public class OwnerAccountRestController {

    @Autowired
    private OwnerAccountService ownerAccountService;

    /**
     * Retrieve the owner
     * 
     * @author Jatin Patel (Jatin-Pat)
     * @return OwnerAccountDto
     */
    @GetMapping(value = { "/owner", "/owner/" })
    public OwnerAccountDto getOwner() {
        Owner owner = ownerAccountService.getOwner();
        return convertToDto(owner);
    }

    /**
     * Update the owner's password
     * 
     * @author Jatin Patel (Jatin-Pat)
     * @param newPassword
     * @return OwnerAccountDto
     */
    @PutMapping(value = { "/owner/updatePassword", "/owner/updatePassword/" })
    public OwnerAccountDto updateOwnerPassword(@RequestBody String newPassword) {
        Owner owner = ownerAccountService.getOwner();
        owner = ownerAccountService.updateOwnerPassword(newPassword);
        return convertToDto(owner);
    }

    private OwnerAccountDto convertToDto(Owner owner) {
        return new OwnerAccountDto(owner.getFirstName(), owner.getLastName(), owner.getEmail(), owner.getPassword());
    }
}