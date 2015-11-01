package com.echodrama.dao.impl;

import com.echodrama.dao.UserDao;
import com.echodrama.pojo.UserPojo;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<UserPojo> implements UserDao {
    @Override
    public UserPojo getUserByLoginParam(String unitId, String userName, String encyptPassword) {
        final String HQL = "from UserPojo user where user.unitId=:unitId and name=:userName and password=:encyptPassword";

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("unitId", unitId);
        map.put("userName", userName);
        map.put("encyptPassword", encyptPassword);

        List<UserPojo> userPojoList = this.queryByHql(HQL, map);
        if (userPojoList.size() == 1) {
            return userPojoList.get(0);
        } else {
            return null;
        }
    }
}
