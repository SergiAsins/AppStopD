package com.Backend.profiles;

import com.Backend.users.UsersException.AppUserNotFoundException;
import com.Backend.users.User;
import com.Backend.users.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.Backend.exceptions.general.AppNotFoundException;
import com.Backend.exceptions.general.AppAlreadyExistsException;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileServices {

    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;

    public ProfileServices(ProfileRepository profileRepository, UserRepository userRepository) {
        this.profileRepository = profileRepository;
        this.userRepository = userRepository;
    }


    public Object createProfile(ProfileRequestDTO profileRequestDTO) throws AppAlreadyExistsException, AppUserNotFoundException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        //Search the authenticated user in the DB
        Optional<User> userOptional = userRepository.findByUsername(username);
        if(userOptional.isEmpty()){
            throw new AppNotFoundException("Authenticated user not found in the database.");
        }
        User authenticatedUser = userOptional.get();

        //Verify email
        Optional<Profile> existsProfile = profileRepository.findByEmail(profileRequestDTO.email());
        if (existsProfile.isPresent())
            throw new AppAlreadyExistsException("Profile already exists with this email.");

        //Creates and save the profile
        Profile profile = ProfileMapper.toEntity(profileRequestDTO, authenticatedUser);
        Profile savedProfile = profileRepository.save(profile);
        return ProfileMapper.toResponse(savedProfile);
    }


    public Object modifyProfile(ProfileRequestDTO profileRequestDTO) throws AppUserNotFoundException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isEmpty()){
            throw new AppNotFoundException("Authenticated user not found in the database.");
        }
        User authenticatedUser = userOptional.get();

        Optional<Profile> optionalProfile = profileRepository.findByUserId(authenticatedUser.getId());
        if (optionalProfile.isEmpty()){
            throw new AppUserNotFoundException("No profile found for the authenticated user.");
        }
        Profile profileToModify = optionalProfile.get();
        if (profileRequestDTO.name() != null && !profileRequestDTO.name().isEmpty()) {
            profileToModify.setName(profileRequestDTO.name());
        }
        if (profileRequestDTO.phone() != null && !profileRequestDTO.phone().isEmpty()) {
            profileToModify.setPhone(profileRequestDTO.phone());
        }
        if (profileRequestDTO.email() != null && !profileRequestDTO.email().isEmpty()) {
            profileToModify.setEmail(profileRequestDTO.email());
        }
        if (profileRequestDTO.address() != null && !profileRequestDTO.address().isEmpty()) {
            profileToModify.setAddress(profileRequestDTO.address());
        }
        if (profileRequestDTO.picture() == null || profileRequestDTO.picture().isEmpty()) {
            profileToModify.setPicture("www.aircompany.images/default.ma");
        } else {
            profileToModify.setPicture(profileRequestDTO.picture());
        }
        Profile updatedProfile = profileRepository.save(profileToModify);
        return ProfileMapper.toResponse(updatedProfile);
    }


    public ProfileResponseDTO findById(Long id) {
        Optional<Profile> optionalProfile = profileRepository.findById(id);

        if (optionalProfile.isEmpty()) {
            throw new AppNotFoundException("The profile with id " + id + " does not exist.");
        }

        Profile profile = optionalProfile.get();
        return ProfileMapper.toResponse(profile);
    }


    public List<ProfileResponseDTO> findall() {
        List<Profile> profileList = profileRepository.findAll();
        return profileList.stream()
                .map(ProfileMapper::toResponse)
                .toList();
    }


    public List<ProfileResponseDTO> findByEmailIgnoreCaseContaining(String email) {
        List<Profile> profileList = profileRepository.findByEmailIgnoreCaseContaining(email);

        if (profileList.isEmpty()) {
            throw new AppNotFoundException("The profile with email " + email + " does not exist.");
        }
        return profileList.stream()
                .map(ProfileMapper::toResponse)
                .toList();
    }

    /*public void deleteProfileById() throws AppUserNotFoundException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Optional<User> userOptional = userRepository.findByUsername(username);
        if(userOptional.isEmpty()){
            throw new AppNotFoundException("Authenticated user not found in the database.");
        }
        User authenticatedUser = userOptional.get();

        Optional<Profile> optionalProfile = profileRepository.findByUserId(authenticatedUser.getId());
        if (optionalProfile.isEmpty()) {
            throw new AppUserNotFoundException("No profile found for the authenticated user.");
        }

        profileRepository.deleteById(optionalProfile.get().getId());
    }*/
}

