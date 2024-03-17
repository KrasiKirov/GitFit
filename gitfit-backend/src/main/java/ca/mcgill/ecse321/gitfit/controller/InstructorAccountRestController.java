package ca.mcgill.ecse321.gitfit.controller;

import java.util.List;
import java.util.ArrayList;

import ca.mcgill.ecse321.gitfit.service.InstructorAccountService;
import ca.mcgill.ecse321.gitfit.dto.InstructorAccountDto;
import ca.mcgill.ecse321.gitfit.model.Instructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class InstructorAccountRestController {

    @Autowired
    private InstructorAccountService instructorAccountService;

    @GetMapping(value = { "/instructor", "/instructor/" })
    public InstructorAccountDto getInstructor(@RequestBody String username) {
        Instructor instructor = instructorAccountService.getInstructor(username);
        return convertToDto(instructor);
    }

    @GetMapping(value = { "/instructor/all", "/instructor/all/" })
    public List<InstructorAccountDto> getAllInstructors() {
        List<Instructor> list = instructorAccountService.getAllInstructors();

        List<InstructorAccountDto> dtoList = new ArrayList<>();
        for (Instructor instructor : list) {
            dtoList.add(convertToDto(instructor));
        }
        return dtoList;
    }

    @PutMapping(value = { "/instructor/updatePassword", "/instructor/updatePassword/" })
    public InstructorAccountDto updateInstructorPassword(@RequestBody String newPassword,
            @RequestBody String username) {
        Instructor instructor = instructorAccountService.getInstructor(username);
        instructor = instructorAccountService.updateInstructorPassword(username, newPassword);
        return convertToDto(instructor);
    }

    @PostMapping(value = { "/instructor/create", "/instructor/create/" })
    public InstructorAccountDto createInstructor(@RequestBody String username, @RequestBody String email,
            @RequestBody String password, @RequestBody String lastName, @RequestBody String firstName) {
        Instructor instructor = instructorAccountService.createInstructor(username, email, password, lastName,
                firstName);
        return convertToDto(instructor);
    }

    private InstructorAccountDto convertToDto(Instructor o) {
        return new InstructorAccountDto(o.getUsername(), o.getEmail(), o.getFirstName(), o.getLastName());
    }
}