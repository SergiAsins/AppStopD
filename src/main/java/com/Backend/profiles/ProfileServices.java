package com.Backend.profiles;

import org.springframework.stereotype.Service;
import com.Backend.users.User;
import com.Backend.exceptions.AppNotFoundException;
import com.Backend.exceptions.AppAlreadyExistsException;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileServices {

    private final ProfileRepository profileRepository;

    public ProfileServices(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public Object createProfile(ProfileRequestDTO profileRequestDTO, User user) throws AppAlreadyExistsException {
        Optional<Profile> existsProfile = profileRepository.findByEmail(profileRequestDTO.email());
        if (existsProfile.isPresent())
            throw new AppAlreadyExistsException("Profile already exists with this email.");

        Profile profile = ProfileMapper.toEntity(profileRequestDTO, user);
        Profile savedProfile = profileRepository.save(profile);
        return ProfileMapper.toResponse(savedProfile);
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
}

