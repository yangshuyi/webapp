package com.echodrama.web.action;

import java.util.HashMap;
import java.util.Map;

import com.echodrama.web.form.JSONMsg;
import com.echodrama.model.User;
import com.echodrama.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by shuyi on 14-3-1.
 */
@Controller
@RequestMapping("/user")
public class UserAction extends BaseAction {


    private UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
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

    @RequestMapping(value="/login")
    public
    @ResponseBody
    Map<String, Object> login(@RequestParam String mobileNo, @RequestParam String encyptPassword) {
        Map<String, Object> dataMap = new HashMap<String, Object>();
        try {
            User user = userService.login(mobileNo, encyptPassword);
            dataMap.put("user", user);
            return buildSuccessMap(dataMap);
        } catch (Exception ex) {
            return buildFailureMap(dataMap, ex);
        }
    }

    @RequestMapping("/loadNavigator")
    public
    @ResponseBody Map<String, Object> loadNavi() {
        Map<String, Object> dataMap = new HashMap<String, Object>();
        try {
            return buildSuccessMap(dataMap);
        } catch (Exception ex) {
            return buildFailureMap(dataMap, ex);
        }
    }

    public UserService getUserService() {
        return userService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }


}