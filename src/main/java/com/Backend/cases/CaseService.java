package com.Backend.cases;

import com.Backend.users.User;
import com.Backend.exceptions.general.AppAlreadyExistsException;
import com.Backend.exceptions.general.AppNotFoundException;
import com.Backend.exceptions.general.AppInvalidFormatException;
import com.Backend.users.UserRepository;
import com.Backend.exceptions.cases.AppErrorCaseException;
import com.Backend.users.UsersException.AppUserNotFoundException;
import com.Backend.cases.status.Status;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import  java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Collections;
import java.util.Set;

@Service
public class CaseService {
    private final UserRepository userRepository;

    private final CaseRepository caseRepository;

    public CaseService(UserRepository userRepository, CaseRepository caseRepository) {
        this.userRepository = userRepository;
        this.caseRepository = caseRepository;
    }

    public CaseResponseDTO createCase(CaseRequestDTO caseRequestDTO) {
        //Take the user authenticated
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        //Search the authenticated user in the DB
        Optional<User> userOptional = userRepository.findByUsername(username);
        if(userOptional.isEmpty()){
            throw new AppNotFoundException("Authenticated user not found in the database.");
        }
        User authenticatedUser = userOptional.get();

        //Create and save the Case
        Case caseEntity = CaseMapper.toEntity(caseRequestDTO, authenticatedUser);
        Case savedCase = caseRepository.save(caseEntity);
        return CaseMapper.toResponseDTO(savedCase);
    }

    public CaseResponseDTO attendACase(Long caseId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Optional<User> userOptional = userRepository.findByUsername(username);
        if(userOptional.isEmpty()){
            throw new AppNotFoundException("Authenticated user not found in the database.");
        }
        User authenticatedUser = userOptional.get();

        Optional<Case> optionalCase = caseRepository.findById(caseId);
        if (optionalCase.isEmpty()) {
            throw new AppErrorCaseException("Case not found with ID: " + caseId);
        }
        Case caseEntity = optionalCase.get();

        User user = userOptional.get();
        caseEntity.getAttendants().add(user);
        Case caseToSave = caseRepository.save(caseEntity);
        return CaseMapper.toResponseDTO(caseToSave);
    }

    public List<CaseResponseDTO> listAllCases(){
        List<Case> caseList = caseRepository.findAll();
        List<CaseResponseDTO> responseDTOList = new java.util.ArrayList<>(Collections.emptyList());
        caseList.forEach((caseEntity) ->{
            CaseResponseDTO caseResponseDTO = CaseMapper.toResponseDTO(caseEntity);
            responseDTOList.add(caseResponseDTO);
        });

        if(caseList.isEmpty()){
            throw new AppNotFoundException("No cases found.");
        }
        return responseDTOList;
    }

    public CaseResponseDTO getCaseById(Long id) {
        Optional<Case> optionalCase = caseRepository.findById(id);
        if (optionalCase.isEmpty()) {
            throw new AppErrorCaseException("Case not found with ID: " + id);
        }
        Case caseEntity = optionalCase.get();
        return CaseMapper.toResponseDTO(caseEntity);
    }

    public List<CaseResponseDTO> getCasesByStatus(String status) {
        List<Case> caseList = caseRepository.findByStatus(status);
        if (caseList.isEmpty()) {
            throw new AppNotFoundException("No cases found with status: " + status);
        }
        List<CaseResponseDTO> responseDTOList = new java.util.ArrayList<>(Collections.emptyList());
        caseList.forEach((caseEntity) ->{
            CaseResponseDTO caseResponseDTO = CaseMapper.toResponseDTO(caseEntity);
            responseDTOList.add(caseResponseDTO);
        });
        return responseDTOList;
    }

    public List<CaseResponseDTO> getCasesByCaseDate(LocalDate caseDate) {
        List<Case> caseList = caseRepository.findByCaseDate(caseDate);
        if (caseList.isEmpty()) {
            throw new AppNotFoundException("No cases found with case date: " + caseDate);
        }
        List<CaseResponseDTO> responseDTOList = new java.util.ArrayList<>(Collections.emptyList());
        caseList.forEach((caseEntity) ->{
            CaseResponseDTO caseResponseDTO = CaseMapper.toResponseDTO(caseEntity);
            responseDTOList.add(caseResponseDTO);
        });
        return responseDTOList;
    }

    public List<CaseResponseDTO> getCasesByCity(String city) {
        List<Case> caseList = caseRepository.findByCity(city);
        if (caseList.isEmpty()) {
            throw new AppNotFoundException("No cases found with city: " + city);
        }
        List<CaseResponseDTO> responseDTOList = new java.util.ArrayList<>(Collections.emptyList());
        caseList.forEach((caseEntity) ->{
            CaseResponseDTO caseResponseDTO = CaseMapper.toResponseDTO(caseEntity);
            responseDTOList.add(caseResponseDTO);
        });
        return responseDTOList;
    }

    public List<CaseResponseDTO> getCasesByRegion(String region) {
        List<Case> caseList = caseRepository.findByRegion(region);
        if (caseList.isEmpty()) {
            throw new AppNotFoundException("No cases found with region: " + region);
        }
        List<CaseResponseDTO> responseDTOList = new java.util.ArrayList<>(Collections.emptyList());
        caseList.forEach((caseEntity) ->{
            CaseResponseDTO caseResponseDTO = CaseMapper.toResponseDTO(caseEntity);
            responseDTOList.add(caseResponseDTO);
        });
        return responseDTOList;
    }

    public CaseResponseDTO modifyCase(Long caseId, CaseRequestDTO caseRequestDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isEmpty()) {
            throw new AppNotFoundException("Authenticated user not found in the database.");
        }
        User authenticatedUser = userOptional.get();

        // Check if the case exists
        Optional<Case> optionalCase = caseRepository.findById(caseId);
        if (optionalCase.isEmpty()) {
            throw new AppNotFoundException("Case not found with the ID: " + caseId);
        }
        Case caseToModify = optionalCase.get();

        // Verify if the authenticated user is within the case tenants
        if (!caseToModify.getTenants().contains(authenticatedUser)) {
            throw new AppErrorCaseException("The user is not authorized to modify this case.");
        }

        // Modify the case fields if they are present in the DTO
        if (caseRequestDTO.status() != null) {
            caseToModify.setStatus(caseRequestDTO.status());
        }
        if (caseRequestDTO.address() != null && !caseRequestDTO.address().isEmpty()) {
            caseToModify.setAddress(caseRequestDTO.address());
        }
        if (caseRequestDTO.region() != null && !caseRequestDTO.region().isEmpty()) {
            caseToModify.setRegion(caseRequestDTO.region());
        }
        if (caseRequestDTO.city() != null && !caseRequestDTO.city().isEmpty()) {
            caseToModify.setCity(caseRequestDTO.city());
        }
        if (caseRequestDTO.caseDate() != null) {
            caseToModify.setCaseDate(caseRequestDTO.caseDate());
        }
        if (caseRequestDTO.description() != null && !caseRequestDTO.description().isEmpty()) {
            caseToModify.setDescription(caseRequestDTO.description());
        }


        Case updatedCase = caseRepository.save(caseToModify);
        return CaseMapper.toResponseDTO(updatedCase);
    }

    public void deleteCase(Long caseId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isEmpty()) {
            throw new AppNotFoundException("Authenticated user not found in the database.");
        }
        User authenticatedUser = userOptional.get();

        // Check if the case exists
        Optional<Case> optionalCase = caseRepository.findById(caseId);
        if (optionalCase.isEmpty()) {
            throw new AppNotFoundException("Case not found with the ID: " + caseId);
        }
        Case caseToDelete = optionalCase.get();

        // Verify if the authenticated user is within the case tenants
        if (!caseToDelete.getTenants().contains(authenticatedUser)) {
            throw new AppErrorCaseException("The user is not authorized to delete this case.");
        }

        caseRepository.delete(caseToDelete);
    }

}
