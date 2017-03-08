package restlibrary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import restlibrary.exception.service.UserException;
import restlibrary.message.SuccessMessage;
import restlibrary.model.User;
import restlibrary.service.UserService;

@RestController
public class LibraryController {

    @Autowired
    private UserService userService;

    //TODO: add test for this

    @RequestMapping(value = "/addUser", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<SuccessMessage> test(@RequestBody User newUser) throws UserException {
        userService.addNewUser(newUser);
        SuccessMessage successMessage = new SuccessMessage(HttpStatus.OK.value(), "New user was created.");
        return new ResponseEntity<SuccessMessage>(successMessage, HttpStatus.OK);
    }
}

