package restlibrary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import restlibrary.exception.service.UserException;
import restlibrary.message.SuccessMessageResponse;
import restlibrary.model.User;
import restlibrary.service.UserService;

@RestController
public class LibraryController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/addNewUser", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<SuccessMessageResponse> addNewUser(@RequestBody User newUser) throws UserException {
        userService.addNewUser(newUser);
        SuccessMessageResponse successMessageResponse = new SuccessMessageResponse(HttpStatus.OK.value(), "New user was created.");
        return new ResponseEntity<SuccessMessageResponse>(successMessageResponse, HttpStatus.OK);
    }
}

