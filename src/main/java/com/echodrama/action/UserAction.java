package com.echodrama.action;

import com.echodrama.form.JSONMsg;
import com.echodrama.model.User;
import com.echodrama.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by shuyi on 14-3-1.
 */
@Controller
@RequestMapping("/user")
public class UserAction extends BaseAction {


    private UserService userService;

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public
    @ResponseBody
    JSONMsg register(@RequestBody User userParam) {
        JSONMsg result = new JSONMsg();
        try {
            User user = new User();
            result.setResultEntry("user", user);
        } catch (Exception ex) {
            result.setErrorInfo("ERROR 1001", ex.getLocalizedMessage());
        }
        return result;

    }

    @RequestMapping("login")
    public void login() {
//        Map<String, Object> dataMap = new HashMap<String, Object>();
//        try {
//            User user = userService.login(userForm.getUnitCode(), userForm.getName(), userForm.getPassword());
//
//            dataMap.put("user", user);
//            super.writeJson(dataMap);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            super.writeJsonErrorMessage(ex);
//        }
    }


    @RequestMapping("loadNavigator")
    public void loadNavi() {
//        try {
//            List<NavigatorModule> modules = userService.getNavigatorModules();
//            super.writeJson(modules);
//        } catch (Exception ex) {
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