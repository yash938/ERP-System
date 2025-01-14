package com.Erp_System.serviceimplementation;

import com.Erp_System.Entity.Role;
import com.Erp_System.Entity.User;
import com.Erp_System.Helper.Helper;
import com.Erp_System.repository.RoleRepo;
import com.Erp_System.response.PaegableResponse;
import com.Erp_System.dto.UserDto;
import com.Erp_System.exception.ResourceNotFoundException;
import com.Erp_System.repository.UserRepo;
import com.Erp_System.response.Utils;
import com.Erp_System.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDto createUser(UserDto userDto) {
        User createUser = modelMapper.map(userDto, User.class);

        Role role = roleRepo.findByRoleName("ROLE_USER").orElseThrow(() -> new ResourceNotFoundException("Role is not found"));
        createUser.setRoles(List.of(role));
        createUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
        User saveUser = userRepo.save(createUser);

        UserDto createdUser = modelMapper.map(saveUser, UserDto.class);
        return createdUser;

    }

    @Override
    public UserDto findUserById(int userId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User is not found"));
        UserDto findUser = modelMapper.map(user, UserDto.class);
        return findUser;
    }

    @Override
    public UserDto updateUser(int userId, UserDto userDto) {
        User users = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User is not found"));
        UserDto createUser = modelMapper.map(users, UserDto.class);

        createUser.setAbout(userDto.getAbout());
        createUser.setGender(userDto.getGender());
        createUser.setAddress(userDto.getAddress());
        createUser.setEmail(userDto.getEmail());
        createUser.setDomain(userDto.getDomain());
        createUser.setName(userDto.getName());
        createUser.setPassword(userDto.getPassword());
        createUser.setPhoneNumber(userDto.getPhoneNumber());
        createUser.setEmployeeId(userDto.getEmployeeId());
        UserDto saveUser = modelMapper.map(createUser, UserDto.class);
        return saveUser;
    }



    @Override
    public PaegableResponse<UserDto> getAllUser(int pageNumber, int pageSize, String sortBy, String sortDir) {
        Sort sort = (sortDir.equalsIgnoreCase("asc"))?(Sort.by(sortBy).ascending()):(Sort.by(sortBy).descending());
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sort);
        Page<User> users = userRepo.findAll(pageRequest);

        PaegableResponse<UserDto> paegable = Helper.getPaegable(users, UserDto.class);

        return paegable;
    }

    @Override
    public UserDto findUserByEmail(String email) {
        User user = userRepo.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User is not found"));
        UserDto users = modelMapper.map(user, UserDto.class);
        return users;
    }

    @Override
    public void deleteUser(int userId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user is not found"));
        userRepo.delete(user);

    }


}
