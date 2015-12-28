package com.echodrama.service;

import com.echodrama.model.NavigatorModule;
import com.echodrama.model.User;

import java.util.List;

public interface UserService {

    public User register(User newUser) throws Exception;

    public User login(String name, String password) throws Exception;

    public List<NavigatorModule> getNavigatorModules() throws Exception;
}
