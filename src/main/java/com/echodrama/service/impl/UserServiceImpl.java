package com.echodrama.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.echodrama.dao.UnitDao;
import com.echodrama.dao.UserDao;
import com.echodrama.model.NavigatorItem;
import com.echodrama.model.NavigatorModule;
import com.echodrama.model.Unit;
import com.echodrama.model.User;
import com.echodrama.pojo.UnitPojo;
import com.echodrama.pojo.UserPojo;
import com.echodrama.service.UserService;
import com.echodrama.utility.MD5Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {
    //    @Autowired
    //    private MemcachedClient memcachedClient;

    @Autowired
    private UnitDao unitDao;

    @Autowired
    private UserDao userDao;

    @Override
    public User register(User newUser) {
        newUser.setId("id");

        return newUser;
    }

    @Override
    public User login(String unitCode, String name, String password) throws Exception {

        String encyptPassword = MD5Utility.getMD5String(password);

        UnitPojo unitPojo = unitDao.getUnitByCode(unitCode);
        if (unitPojo == null) {
            throw new Exception("Could not find Unit by Code [" + unitCode + "]");
        }

        UserPojo userPojo = userDao.getUserByLoginParam(unitPojo.getId(), name, encyptPassword);
        if (userPojo == null) {
            throw new Exception("Could not login user by name [" + name + "] and password [" + password + "]");
        }

        User user = User.fromPojo(userPojo);
        user.setUnit(Unit.fromPojo(unitPojo));

        return user;
    }

    @Override
    public List<NavigatorModule> getNavigatorModules() throws Exception {
        List<NavigatorModule> navigatorModuleList = null;

            navigatorModuleList = new ArrayList<NavigatorModule>();

            NavigatorModule settingsModule = new NavigatorModule();
            settingsModule.setName("Settings");

            NavigatorItem codeJobTitleItem = new NavigatorItem();
            codeJobTitleItem.setName("Code - JobTitle");
            codeJobTitleItem.setActionUrl("/jsp/code/code.jsp?codeType=JobTitle");
            codeJobTitleItem.setImageUrl("/image/user/user.png");
            settingsModule.getItems().add(codeJobTitleItem);

            NavigatorItem departmentItem = new NavigatorItem();
            departmentItem.setName("Department Management");
            departmentItem.setActionUrl("/jsp/organization/department.jsp");
            departmentItem.setImageUrl("/image/user/user.png");
            settingsModule.getItems().add(departmentItem);

            NavigatorItem userManagementItem = new NavigatorItem();
            userManagementItem.setName("User Management");
            userManagementItem.setActionUrl("/jsp/organization/employee.jsp");
            userManagementItem.setImageUrl("/image/user/user.png");
            settingsModule.getItems().add(userManagementItem);

            NavigatorItem applicationStatisticsItem = new NavigatorItem();
            applicationStatisticsItem.setName("App Statistics");
            applicationStatisticsItem.setActionUrl("/jsp/application/applicationStatistics.jsp");
            applicationStatisticsItem.setImageUrl("/image/user/user.png");
            settingsModule.getItems().add(applicationStatisticsItem);

            navigatorModuleList.add(settingsModule);


        return navigatorModuleList;
    }

}
