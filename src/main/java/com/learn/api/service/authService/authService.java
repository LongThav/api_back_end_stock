package com.learn.api.service.authService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.learn.api.dto.RoleDTO;
import com.learn.api.dto.authDto.UserDTO;
import com.learn.api.dto.authDto.UserDTORequestCreate;
import com.learn.api.repositorys.UserRepository;
import com.learn.api.models.authModel.RoleModel;
import com.learn.api.models.authModel.UserModel;
import com.learn.api.repositorys.RoleRepository; // Import the RoleRepository

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class authService {

    private static final Logger logger = LoggerFactory.getLogger(authService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository; // Autowire RoleRepository

    @Autowired
    private PasswordEncoder passwordEncoder;

   
    public UserModel createUserWithRole(UserDTORequestCreate request) {
        Long roleId = request.getRoleId();
        RoleModel role = roleRepository.findById(roleId).orElse(null);
        if (role != null) {
            UserModel user = new UserModel();
            user.setFName(request.getFName());
            user.setLName(request.getLName());
            user.setEmail(request.getEmail());
            user.setPassword(passwordEncoder.encode(request.getPassword())); // Hash the password
            user.setRole(role);
            logger.info("Creating user with role: {}", user);
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
        // return new UserDTO(user.getUserId(), user.getFName(), user.getLName() ,user.getEmail(), roleDTO);
        return new UserDTO(user.getUserId(), roleDTO, user.getFName(), user.getLName(), user.getEmail());
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
}
