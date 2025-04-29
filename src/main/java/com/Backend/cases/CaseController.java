package com.Backend.cases;

import jakarta.validation.Valid;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.time.LocalDate;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/cases")
public class CaseController {

    public final CaseService caseService;

    public CaseController(CaseService caseService) {
        this.caseService = caseService;
    }

        @PostMapping
        public ResponseEntity<CaseResponseDTO> createCase(@RequestBody @Valid CaseRequestDTO caseRequestDTO){
            CaseResponseDTO caseResponseDTO = caseService.createCase(caseRequestDTO);
            return new ResponseEntity<>(caseResponseDTO, HttpStatus.CREATED);
        }

        @PostMapping("/attend/{caseId}")
        public ResponseEntity<CaseResponseDTO> attendCase(@PathVariable Long caseId){
            CaseResponseDTO response = caseService.attendACase(caseId);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        @GetMapping()
        public  ResponseEntity<List<CaseResponseDTO>> getAllCases(){
            List<CaseResponseDTO> allCases = caseService.listAllCases();
            return new ResponseEntity<>(allCases, HttpStatus.OK);
        }

        @GetMapping("/by-id/{id}")
        public ResponseEntity<CaseResponseDTO> getCaseById(@PathVariable Long id){
            CaseResponseDTO caseResponseDTO = caseService.getCaseById(id);
            return new ResponseEntity<>(caseResponseDTO, HttpStatus.OK);
        }

        @PutMapping("/{caseId}")
        public ResponseEntity<CaseResponseDTO> modifyCase(@PathVariable Long caseId, @RequestBody @Valid CaseRequestDTO caseRequestDTO) {
            CaseResponseDTO updatedCase = caseService.modifyCase(caseId, caseRequestDTO);
            return new ResponseEntity<>(updatedCase, HttpStatus.OK);
        }

        @GetMapping("/by-city")
        public ResponseEntity<List<CaseResponseDTO>> getCasesByCity(@RequestParam String city) {
            List<CaseResponseDTO> cases = caseService.getCasesByCity(city);
            return new ResponseEntity<>(cases, HttpStatus.OK);
        }

        @GetMapping("/by-region")
        public ResponseEntity<List<CaseResponseDTO>> getCasesByRegion(@RequestParam String region) {
            List<CaseResponseDTO> cases = caseService.getCasesByRegion(region);
            return new ResponseEntity<>(cases, HttpStatus.OK);
        }

        @GetMapping("/by-status")
        public ResponseEntity<List<CaseResponseDTO>> getCasesByStatus(@RequestParam String status) {
            List<CaseResponseDTO> cases = caseService.getCasesByStatus(status);
            return new ResponseEntity<>(cases, HttpStatus.OK);
        }

        @DeleteMapping("/{caseId}")
        public ResponseEntity<Void> deleteCase(@PathVariable Long caseId) {
            caseService.deleteCase(caseId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
}
