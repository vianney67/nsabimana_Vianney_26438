package com.poggy.question6_user_profile_api.controller.user;

import com.poggy.question6_user_profile_api.model.response.ApiResponse;
import com.poggy.question6_user_profile_api.model.user.UserProfile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/user-profiles")
public class UserProfileController {

    private List<UserProfile> userProfiles = new ArrayList<>();

    public UserProfileController() {
        userProfiles.add(new UserProfile(1L, "alice123", "alice@example.com", "Alice Johnson", 22, "Kenya", "Computer science student", true));
        userProfiles.add(new UserProfile(2L, "bob_smith", "bob@example.com", "Bob Smith", 30, "USA", "Backend developer", true));
        userProfiles.add(new UserProfile(3L, "carol_d", "carol@example.com", "Carol Davis", 27, "UK", "UX designer", false));
        userProfiles.add(new UserProfile(4L, "davidw", "david@example.com", "David Wilson", 35, "Canada", "DevOps engineer", true));
        userProfiles.add(new UserProfile(5L, "eve_b", "eve@example.com", "Eve Brown", 19, "Kenya", "First year student", false));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<UserProfile>>> getAllUserProfiles() {
        ApiResponse<List<UserProfile>> response = new ApiResponse<>(true, "User profiles retrieved successfully", userProfiles);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse<UserProfile>> getUserProfileById(@PathVariable Long userId) {
        for (UserProfile profile : userProfiles) {
            if (profile.getUserId().equals(userId)) {
                ApiResponse<UserProfile> response = new ApiResponse<>(true, "User profile found", profile);
                return ResponseEntity.ok(response);
            }
        }
        ApiResponse<UserProfile> response = new ApiResponse<>(false, "User profile not found", null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<UserProfile>> createUserProfile(@RequestBody UserProfile userProfile) {
        userProfiles.add(userProfile);
        ApiResponse<UserProfile> response = new ApiResponse<>(true, "User profile created successfully", userProfile);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<ApiResponse<UserProfile>> updateUserProfile(@PathVariable Long userId, @RequestBody UserProfile updatedProfile) {
        for (UserProfile profile : userProfiles) {
            if (profile.getUserId().equals(userId)) {
                profile.setUsername(updatedProfile.getUsername());
                profile.setEmail(updatedProfile.getEmail());
                profile.setFullName(updatedProfile.getFullName());
                profile.setAge(updatedProfile.getAge());
                profile.setCountry(updatedProfile.getCountry());
                profile.setBio(updatedProfile.getBio());
                profile.setActive(updatedProfile.isActive());
                ApiResponse<UserProfile> response = new ApiResponse<>(true, "User profile updated successfully", profile);
                return ResponseEntity.ok(response);
            }
        }
        ApiResponse<UserProfile> response = new ApiResponse<>(false, "User profile not found", null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse<Void>> deleteUserProfile(@PathVariable Long userId) {
        for (UserProfile profile : userProfiles) {
            if (profile.getUserId().equals(userId)) {
                userProfiles.remove(profile);
                ApiResponse<Void> response = new ApiResponse<>(true, "User profile deleted successfully", null);
                return ResponseEntity.ok(response);
            }
        }
        ApiResponse<Void> response = new ApiResponse<>(false, "User profile not found", null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @GetMapping("/search/username/{username}")
    public ResponseEntity<ApiResponse<List<UserProfile>>> searchByUsername(@PathVariable String username) {
        List<UserProfile> result = new ArrayList<>();
        for (UserProfile profile : userProfiles) {
            if (profile.getUsername() != null && profile.getUsername().equalsIgnoreCase(username)) {
                result.add(profile);
            }
        }
        ApiResponse<List<UserProfile>> response = new ApiResponse<>(true, "User profiles filtered by username", result);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/search/country/{country}")
    public ResponseEntity<ApiResponse<List<UserProfile>>> searchByCountry(@PathVariable String country) {
        List<UserProfile> result = new ArrayList<>();
        for (UserProfile profile : userProfiles) {
            if (profile.getCountry() != null && profile.getCountry().equalsIgnoreCase(country)) {
                result.add(profile);
            }
        }
        ApiResponse<List<UserProfile>> response = new ApiResponse<>(true, "User profiles filtered by country", result);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/search/age-range")
    public ResponseEntity<ApiResponse<List<UserProfile>>> searchByAgeRange(
            @RequestParam("minAge") int minAge,
            @RequestParam("maxAge") int maxAge) {
        List<UserProfile> result = new ArrayList<>();
        for (UserProfile profile : userProfiles) {
            if (profile.getAge() >= minAge && profile.getAge() <= maxAge) {
                result.add(profile);
            }
        }
        ApiResponse<List<UserProfile>> response = new ApiResponse<>(true, "User profiles filtered by age range", result);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{userId}/activate")
    public ResponseEntity<ApiResponse<UserProfile>> activateUserProfile(@PathVariable Long userId) {
        for (UserProfile profile : userProfiles) {
            if (profile.getUserId().equals(userId)) {
                profile.setActive(true);
                ApiResponse<UserProfile> response = new ApiResponse<>(true, "User profile activated", profile);
                return ResponseEntity.ok(response);
            }
        }
        ApiResponse<UserProfile> response = new ApiResponse<>(false, "User profile not found", null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @PatchMapping("/{userId}/deactivate")
    public ResponseEntity<ApiResponse<UserProfile>> deactivateUserProfile(@PathVariable Long userId) {
        for (UserProfile profile : userProfiles) {
            if (profile.getUserId().equals(userId)) {
                profile.setActive(false);
                ApiResponse<UserProfile> response = new ApiResponse<>(true, "User profile deactivated", profile);
                return ResponseEntity.ok(response);
            }
        }
        ApiResponse<UserProfile> response = new ApiResponse<>(false, "User profile not found", null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
