package win.TIA202.www.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import win.TIA202.www.core.pojo.CoreMsg;
import win.TIA202.www.web.entity.RequestDataAndUser;
import win.TIA202.www.web.entity.User;
import win.TIA202.www.web.service.UserService;

import java.util.Objects;

@RestController
@RequestMapping("user")
public class EditController {
    @Autowired
    private UserService service;

    @PutMapping("user_edit")
    public User edit(@RequestBody RequestDataAndUser requestDataAndUser) {
        User requestData = requestDataAndUser.getRequestData();
        User user = requestDataAndUser.getUser();

        final Integer userId = user.getUserId();
        requestData.setUserId(userId);
        return service.edit(requestData);
    }

    @PostMapping("user_pwd/{password}")
    public CoreMsg checkPassword(@PathVariable String password, @RequestBody User seUser) {
        final CoreMsg core = new CoreMsg();
        if (seUser == null) {
            core.setMessage("無會員資訊");
            core.setSuccessfully(false);
        } else {
            User oldUser = service.selectById(seUser.getUserId());
            final String currentPassword = oldUser.getPassword();
            if (Objects.equals(password, currentPassword)) {
                core.setSuccessfully(true);
            } else {
                core.setMessage("舊密碼錯誤");
                core.setSuccessfully(false);
            }
        }
        return core;
    }

    @PutMapping("user_pwd/{newPassword}")
    public User editPassword(@PathVariable String newPassword, @RequestBody User seUser) {
        final Integer userId = seUser.getUserId();
        User reqUser = new User();
        reqUser.setUserId(userId);
        reqUser.setPassword(newPassword);
        return service.editPassword(reqUser);
    }

}
