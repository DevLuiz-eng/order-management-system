package com.luizdev.order_management_system.services;

import com.luizdev.order_management_system.DTO.request.UserRequestDTO;
import com.luizdev.order_management_system.DTO.response.UserResponseDTO;
import com.luizdev.order_management_system.domain.Order;
import com.luizdev.order_management_system.domain.User;
import com.luizdev.order_management_system.exceptions.NotFoundUserException;
import com.luizdev.order_management_system.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {


    private final UserRepository repository;

    public UserService(UserRepository repository) {

        this.repository = repository;
    }

    public UserResponseDTO createUser(UserRequestDTO request) {
        User user = new User();

        user.setAge(request.age());
        user.setName(request.name());

        repository.save(user);
        return new UserResponseDTO(user.getId(), user.getName(), user.getAge(), user.getOrders());
    }

    public UserResponseDTO getUserById(Long id) {
        var user = repository.
                findById(id).
                orElseThrow(() -> new NotFoundUserException("User was not found."));


        return new UserResponseDTO(user.getId(), user.getName(), user.getAge(), user.getOrders()
        );
    }

    public List<UserResponseDTO> getAllUsers() {
        return repository.
                findAll().
                stream().
                map(u -> new UserResponseDTO(
                        u.getId(),
                        u.getName(),
                        u.getAge(),
                        u.getOrders())).
                toList();
    }


    public UserResponseDTO udpateUser(Long id, UserRequestDTO request) {
        User user = repository.
                findById(id).
                orElseThrow(() -> new NotFoundUserException("User was not found."));

        user.setName(request.name());
        user.setAge(request.age());

        repository.save(user);
        return new UserResponseDTO(user.getId(), user.getName(), user.getAge(), user.getOrders());
    }

    public void deleteById(Long id) {
        User user = repository.
                findById(id).
                orElseThrow(() -> new NotFoundUserException("User was not found."));

        repository.deleteById(id);
    }

    public void deleteAll() {
        repository.deleteAll();
    }


    public User findUserForOrder(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundUserException("User was not found."));

    }

}
