package com.echodrama.action;

import com.echodrama.form.UserForm;
import com.echodrama.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class NavigatorAction extends BaseAction {

    private UserService userService;
    private UserForm userForm = new UserForm();

    public void reg() {
//        try {
//            User newUser = new User();
//            newUser.setName(this.userForm.getName());
//            newUser.setPassword(this.userForm.getPassword());
//
//            User user = userService.register(newUser);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            super.writeJsonErrorMessage(ex);
//        }
    }

    @RequestMapping("userLogin")
    public void login() {
//        try {
//            User user = userService.login(userForm.getUnitCode(), userForm.getName(), userForm.getPassword());
//
//            super.writeJson(this.userForm);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            super.writeJsonErrorMessage(ex);
//        }
    }

    public UserService getUserService() {
        return userService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

}
