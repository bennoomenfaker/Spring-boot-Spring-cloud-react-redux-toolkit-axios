package isims.org.companyservice.web;





import isims.org.companyservice.entities.Company;
import isims.org.companyservice.repositories.CompanyRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@Slf4j
@AllArgsConstructor
public class CompanyRestController {
    private CompanyRepository companyRepository;

    @GetMapping("/companies")
    public List<Company> findAllCompanies() {
        // Log a message
        log.info("Fetching all companies");

        return companyRepository.findAll();
    }

    @GetMapping("/companies/{id}")
    public ResponseEntity<Company> findCompany(@PathVariable Long id) {
        Optional<Company> company = companyRepository.findById(id);

        if (company.isPresent()) {
            // Log a message
            log.info("Found company with id {}", id);

            return ResponseEntity.ok(company.get());
        } else {
            // Log a message
            log.warn("Company with id {} not found", id);

            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/companies")
    public ResponseEntity<?> save(@RequestBody Company company) {
        try {
            // Save the company
            Company savedCompany = companyRepository.save(company);

            // Log saved company details
            log.info("Saved company with id {}", savedCompany.getId());
            log.debug("Company details: {}", savedCompany);

            // Return a ResponseEntity with the saved company and HTTP status 201 Created
            return ResponseEntity.status(HttpStatus.CREATED).body(savedCompany);
        } catch (Exception e) {
            // Log the error
            log.error("Error occurred while saving company", e);

            // If an error occurs, return a ResponseEntity with an appropriate error message and HTTP status 500 Internal Server Error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while saving the company.");
        }
    }

    @PutMapping("/companies/{id}")
    public ResponseEntity<Company> updateCompany(@PathVariable Long id, @RequestBody Company updatedCompany) {
        Optional<Company> existingCompany = companyRepository.findById(id);

        if (existingCompany.isPresent()) {
            Company company = existingCompany.get();
            company.setName(updatedCompany.getName());
            company.setPrice(updatedCompany.getPrice());
            companyRepository.save(company);

            // Log a message
            log.info("Updated company with id {}", id);

            return ResponseEntity.ok(company);
        } else {
            // Log a message
            log.warn("Company with id {} not found for update", id);

            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(path = "/companies/{id}")
    public void delete(@PathVariable Long id) {
        // Log a message
        log.trace("Deleting company with id {}", id);

        companyRepository.deleteById(id);
    }
}
