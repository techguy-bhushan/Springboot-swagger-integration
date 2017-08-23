package com.swagger.swagger.api;

import com.swagger.swagger.domain.User;
import com.swagger.swagger.repository.UserRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@Api(basePath = "/api/user", value = "User Api", description = "Operations with User Api",consumes ="application/json", produces = "application/json")
@RequestMapping(value = "/api/user",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserApi {
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ApiOperation(value = "Create User")
    public ResponseEntity<User> create(@RequestBody User user) {


        if (user.getId() != null || userRepository.findByEmail(user.getEmail()) != null) {
            return new ResponseEntity<User>(HttpStatus.CONFLICT);
        }

        user =userRepository.save(user);

        return new ResponseEntity<User>(user, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ApiOperation(value = "Get all user list.")
    public ResponseEntity<List<User>> listAll() {
        Iterable<User> iterable = userRepository.findAll();
        List<User> users = StreamSupport.stream(iterable.spliterator(), false)
                .collect(Collectors.toList());
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "Get user by id.")
    public ResponseEntity<User> get(@PathVariable("id") long id) {
        User user = userRepository.findById(id);
        if (user == null) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "delete user by id.")
    public ResponseEntity<User> delete(@PathVariable("id") long id) {
        User user = userRepository.findById(id);
        if (user == null) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }

        userRepository.delete(id);
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }

}
