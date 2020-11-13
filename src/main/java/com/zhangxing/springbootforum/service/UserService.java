package com.zhangxing.springbootforum.service;

import com.zhangxing.springbootforum.mapper.UserMapper;
import com.zhangxing.springbootforum.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhangxing
 * @Description:
 * @date 2020/11/12 20:44
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public void createOrUpdate(User user) {
        User dbUser = userMapper.findByAccountID(user.getACCOUNT_ID());
        //判断dbUser的存在情况
        if (dbUser==null){
            user.setCREATE_DATE(System.currentTimeMillis());
            user.setMODIFIED_DATE(user.getCREATE_DATE());
            userMapper.insert(user);
        }else {
            dbUser.setLOGIN_ID(user.getLOGIN_ID());
            dbUser.setTOKEN(user.getTOKEN());
            dbUser.setMODIFIED_DATE(System.currentTimeMillis());
            dbUser.setBIO(user.getBIO());
            dbUser.setAVATAR_URL(user.getAVATAR_URL());
            userMapper.update(dbUser);
        }
    }
}
