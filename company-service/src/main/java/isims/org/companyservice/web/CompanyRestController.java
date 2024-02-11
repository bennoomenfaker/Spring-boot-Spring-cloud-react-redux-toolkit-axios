package isims.org.companyservice.web;





import isims.org.companyservice.entities.Company;
import isims.org.companyservice.repositories.CompanyRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor

public class CompanyRestController {
    private CompanyRepository companyRepository;

    @GetMapping("/companies")
    public List<Company> findAllCompanies() {
        return companyRepository.findAll();
    }

    @GetMapping("/companies/{id}")
    public ResponseEntity<Company> findCompany(@PathVariable Long id) {
        Optional<Company> company = companyRepository.findById(id);

        if (company.isPresent()) {
            return ResponseEntity.ok(company.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

   /* @PostMapping("/companies")
    public Company save(@RequestBody Company company) {
      System.out.println("********** Save ***************");
        System.out.println(company.getName());
        System.out.println(company.getPrice());
        return companyRepository.save(company);


    }*/
   @PostMapping("/companies")
   public ResponseEntity<?> save(@RequestBody Company company) {
       try {
           // Save the company
           Company savedCompany = companyRepository.save(company);
           System.out.println(company);

           // Log saved company details
           System.out.println("********** Save ***************");
           System.out.println(savedCompany.getName());
           System.out.println(savedCompany.getPrice());

           // Return a ResponseEntity with the saved company and HTTP status 201 Created
           return ResponseEntity.status(HttpStatus.CREATED).body(savedCompany);

       } catch (Exception e) {
           // If an error occurs, return a ResponseEntity with an appropriate error message and HTTP status 500 Internal Server Error
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while saving the company.");
       }
   }


    /*@PutMapping("/companies/{name}")
    public Company upadteNameCompany(@PathVariable String name, @RequestBody Company company) {
        company.setName(name);
        return companyRepository.save(product);

    }*/


    @PutMapping("/companies/{id}")
    public ResponseEntity<Company> updateCompany(@PathVariable Long id, @RequestBody Company updatedCompany) {
        Optional<Company> existingCompany = companyRepository.findById(id);

        if (existingCompany.isPresent()) {
            Company company = existingCompany.get();
            company.setName(updatedCompany.getName());
            company.setPrice(updatedCompany.getPrice());
         companyRepository.save(company);
            return ResponseEntity.ok(company);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping(path = "/companies/{id}")
    public void delete(@PathVariable Long id) {

        companyRepository.deleteById(id);

    }
}
