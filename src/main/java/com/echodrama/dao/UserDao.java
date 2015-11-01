package com.echodrama.dao;


import com.echodrama.pojo.UserPojo;

public interface UserDao extends BaseDao<UserPojo> {

    public UserPojo getUserByLoginParam(String unitId, String userName, String encyptPassword);
}
