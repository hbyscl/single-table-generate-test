package org.cheng.devtool.controller;

import org.cheng.devtool.entity.UserInfo;
import org.cheng.devtool.service.basic.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author li.cheng
 * @version 1.0.0 2017年04月24日
 * @since soter 1.0.0
 */
@Controller
@RequestMapping("/userinfo")
public class UserInfoController {
    @Autowired
    private UserInfoService service;

    @RequestMapping("/page")
    public String page() {
        return "userinfo";
    }

    @RequestMapping("/get")
    @ResponseBody
    public DataVo getUserInfo(
            @RequestParam Integer userId) {
        return DataVo.set(service.getUserInfo(userId));
    }

    @RequestMapping("/delete")
    @ResponseBody
    public DataVo deleteUserInfo(
            @RequestParam Integer userId) {
        return DataVo.set(service.deleteUserInfo(userId));
    }

    @RequestMapping("/add")
    @ResponseBody
    public DataVo addUserInfo(
            @RequestParam String userName,
            @RequestParam(required = false) Float score) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName(userName);
        userInfo.setScore(score);

        return DataVo.set(service.addUserInfo(userInfo));
    }

    @RequestMapping("/update")
    @ResponseBody
    public DataVo updateUserInfo(
            @RequestParam Integer userId,
            @RequestParam(required = false) String userName,
            @RequestParam(required = false) Float score) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(userId);
        userInfo.setUserName(userName);
        userInfo.setScore(score);
        return DataVo.set(service.updateUserInfo(userInfo));
    }

}
