package com.example.assignment1.resource;

import com.example.assignment1.model.Users;
import com.example.assignment1.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class UsersController {

    @Autowired
    private UsersRepository repository;

    @PostMapping("/addUser")
    public String saveUser(@RequestBody Users users){

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        users.setUserStatus(Users.UserStatusValues.ACTIVE);
        users.setCreatedOn(dateFormat.format(date).replace('/','-'));
        users.setUpdatedOn(dateFormat.format(date).replace('/','-'));
        repository.save(users);
        return "Added User With Id: "+users.getUserId();
    }

    @PostMapping("/updateUser/{id}")
    public String updateUser(@PathVariable int id, @RequestBody Users users){

        if(!repository.findById(id).isEmpty())
        {
            Users oldValues = repository.findById(id).get();
            String createdOn = oldValues.getCreatedOn();


            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date date = new Date();

            users.setUserId(id);
            users.setUserName(users.getUserName());
            users.setEmailId(users.getEmailId());
            users.setMobileNumber(users.getMobileNumber());

            users.setUserStatus(oldValues.getUserStatus());
            users.setCreatedOn(createdOn);
            users.setUpdatedOn(dateFormat.format(date).replace('/', '-'));
            repository.save(users);
            return "Updated User With Id: " + users.getUserId();
        }
        return "No User With Id: "+users.getUserId();
    }

    @GetMapping("/findAllUsers")
    public List<Users> getUsers(){
        return repository.findAll();
    }

    @GetMapping("/findAllUsers/{id}")
    public Optional<Users> getUser(@PathVariable int id){
        return repository.findById(id);
    }

    @GetMapping("/findAllUsersByCreation/{date}")
    public Optional<Users> getUserByCreationDate(@PathVariable String date){
        return repository.findByCreatedOn(date);
    }

    @GetMapping("/findByUserStatus/{userStatus}")
    public List<Users> getUserByUserStatus(@PathVariable String userStatus){
        return repository.findUsersByUserStatus(userStatus);
    }

    @GetMapping("/findByCreatedOnAndUserStatus/{userStatus}/{createdOn}")
    public List<Users> getUserByCreatedOnAndUserStatus(@PathVariable("createdOn") String createdOn,@PathVariable("userStatus") String userStatus){
        return repository.findUsersByCreatedOnAndUserStatus(createdOn,userStatus);
    }


    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable int id){

        Users users = new Users();
        if(!repository.findById(id).isEmpty())
        {
            Users oldValues = repository.findById(id).get();
            String createdOn = oldValues.getCreatedOn();


            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date date = new Date();

            users.setUserId(id);
            users.setUserName(oldValues.getUserName());
            users.setEmailId(oldValues.getEmailId());
            users.setMobileNumber(oldValues.getMobileNumber());

            users.setUserStatus(Users.UserStatusValues.DELETED);
            users.setCreatedOn(createdOn);
            users.setUpdatedOn(dateFormat.format(date).replace('/', '-'));
            repository.save(users);
            return "Deleted User With Id: " + users.getUserId();
        }
        return "No User to delete";
    }
}
