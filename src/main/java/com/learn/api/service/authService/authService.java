package com.learn.api.service.AuthService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.learn.api.dto.AuthDto.UserDTO;
import com.learn.api.dto.AuthDto.UserDTORequestCreate;
import com.learn.api.dto.RoleDTO.RoleDTO;
import com.learn.api.models.AuthModel.RoleModel;
import com.learn.api.models.AuthModel.TokenBlackListModel;
import com.learn.api.models.AuthModel.UserModel;
import com.learn.api.repositorys.AuthRepository.TokenBlacklistRepository;
import com.learn.api.repositorys.AuthRepository.UserRepository;
import com.learn.api.repositorys.UserManagementRepository.RoleRepository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.validation.Valid;

@Service
public class authService {

    private static final Logger logger = LoggerFactory.getLogger(authService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenBlacklistRepository tokenBlacklistRepository;

    @Autowired
    private RoleRepository roleRepository; // Autowire RoleRepository

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserModel createUserWithRole(@Valid UserDTORequestCreate request) {

        if (request.getFName() == null || request.getFName().isEmpty()) {
            throw new IllegalArgumentException("First Name is required");
        }

        if (request.getLName() == null || request.getLName().isEmpty()) {
            throw new IllegalArgumentException("Last Name is required");
        }

        // Check if email is null or empty
        if (request.getEmail() == null || request.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Email is required");
        }

        // Check if password is null or empty
        if (request.getPassword() == null || request.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Password is required");
        }

        // Check if roleId is null or invalid
        Long roleId = request.getRoleId();
        if (roleId == null || roleId <= 0) {
            throw new IllegalArgumentException("Role ID is required");
        }

        // Find the RoleModel by roleId
        RoleModel role = roleRepository.findById(roleId).orElse(null);
        if (role != null) {
            // Create and set up UserModel
            UserModel user = new UserModel();
            user.setFName(request.getFName());
            user.setLName(request.getLName());
            user.setEmail(request.getEmail());
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            user.setRole(role);

            logger.debug("UserModel before saving: {}", user);
            return userRepository.save(user);
        } else {
            logger.warn("Role not found with id: {}", roleId);
            return null;
        }
    }

    public UserModel findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    public List<UserDTO> getAllUsers() {
        List<UserModel> users = userRepository.findAll();
        logger.info("Fetching all users: {}", users);
        return users.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public UserDTO getUserById(Long id) {
        Optional<UserModel> user = userRepository.findById(id);
        if (user.isPresent()) {
            logger.info("Fetching user by id: {} - {}", id, user.get());
            return convertToDTO(user.get());
        } else {
            logger.warn("User not found with id: {}", id);
            return null;
        }
    }

    public boolean deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            logger.info("Deleting user by id: {}", id);
            userRepository.deleteById(id);
            return true;
        } else {
            logger.warn("User not found with id: {}", id);
            return false;
        }
    }

    public UserModel updatePassword(Long id, String password) {
        Optional<UserModel> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            UserModel user = optionalUser.get();
            user.setPassword(password);
            logger.info("Updating password for user id: {}", id);
            return userRepository.save(user);
        } else {
            logger.warn("User not found with id: {}", id);
            return null;
        }
    }

    public UserModel updateUserRole(Long userId, Long roleId) {
        Optional<UserModel> optionalUser = userRepository.findById(userId);
        Optional<RoleModel> optionalRole = roleRepository.findById(roleId);
        if (optionalUser.isPresent() && optionalRole.isPresent()) {
            UserModel user = optionalUser.get();
            RoleModel role = optionalRole.get();
            user.setRole(role); // Assuming there's a setter for role in the User entity
            logger.info("Updating role for user id: {} with role id: {}", userId, roleId);
            return userRepository.save(user);
        } else {
            logger.warn("User or Role not found with id: {}", userId);
            return null;
        }
    }

    // private UserDTO convertToDTO(User user) {
    // return new UserDTO(user.getId(), user.getName(), user.getEmail(), role);
    // }

    private UserDTO convertToDTO(UserModel user) {
        RoleModel role = user.getRole();
        RoleDTO roleDTO = null;
        if (role != null) {
            roleDTO = new RoleDTO(role.getId(), role.getRoleName(), role.getDescriptionRole());
        }
        // return new UserDTO(user.getUserId(), user.getFName(), user.getLName()
        // ,user.getEmail(), roleDTO);
        return new UserDTO(user.getUserId(), roleDTO, user.getFName(), user.getLName(), user.getEmail(),
                user.getImage());
    }

    public UserModel updateUserPartial(Long id, UserModel partialUser) {
        UserModel existingUser = userRepository.findById(id).orElse(null);
        if (existingUser != null) {
            if (partialUser.getEmail() != null) {
                existingUser.setEmail(partialUser.getEmail());
            }
            if (partialUser.getEmail() != null) {
                existingUser.setEmail(partialUser.getEmail());
            }
            return userRepository.save(existingUser);
        } else {
            return null;
        }
    }

    public UserDTO getUserByEmail(String email) {
        UserModel user = userRepository.findByEmail(email).orElse(null);
        if (user == null) {
            return null; // Handle the case where the user is not found
        }
        return convertToDTOProfile(user);
    }

    private UserDTO convertToDTOProfile(UserModel user) {
        RoleModel role = user.getRole();
        RoleDTO roleDTO = null;
        if (role != null) {
            // Use the constructor with arguments
            roleDTO = new RoleDTO(
                    role.getId(), // Assuming you don't want to include the id
                    role.getRoleName(),
                    role.getDescriptionRole());
        }
        return new UserDTO(user.getUserId(), roleDTO, user.getFName(), user.getLName(), user.getEmail(),
                user.getImage());
    }

    public void blacklistToken(String token) {
        // Create a new TokenBlackListModel with the token and current date
        TokenBlackListModel tokenBlackListModel = new TokenBlackListModel(token, Instant.now());
        // Save the token to the blacklist
        tokenBlacklistRepository.save(tokenBlackListModel);
    }

}
