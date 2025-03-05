package win.TIA202.www.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import win.TIA202.www.web.entity.User;
import win.TIA202.www.web.service.UserService;

@RestController
@RequestMapping("user/edit")
public class EditController {
    @Autowired
    private UserService service;

    @PostMapping
    public User edit(@RequestBody User reqUser, @SessionAttribute("user") User seUser) {
        final String username = seUser.getName();
        reqUser.setName(username);
        return service.edit(reqUser);
    }

//    @GetMapping("")

}
