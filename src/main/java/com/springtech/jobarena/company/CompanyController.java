package com.springtech.jobarena.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/companies")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @PostMapping("/add")
    public ResponseEntity<String> createCompany(@RequestBody Company company){
        companyService.createCompany(company);
        return new ResponseEntity<>("Company created successfully", HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Company>> getAllCompanies(){
        return new ResponseEntity<>(companyService.getAllCompanies(), HttpStatus.OK);
    }

    @PutMapping("/{companyId}")
    public ResponseEntity<String> updateCompany(@PathVariable Long companyId,
                                         @RequestBody Company company){
        Boolean updated = companyService.updateCompany(companyId, company);
        if (updated){
            return new ResponseEntity<>("Company updated successfully", HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{companyId}")
    public ResponseEntity<String> deleteCompany(@PathVariable Long companyId){
        Boolean companyDeleted = companyService.deleteCompany(companyId);
        if (companyDeleted){
            return new ResponseEntity<>("Company successfully deleted",
                    HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Company not found",
                    HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/{companyId}")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long companyId){
        Company company = companyService.getCompanyById(companyId);
        if (company != null){
            return new ResponseEntity<>(company, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
