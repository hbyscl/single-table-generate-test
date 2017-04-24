package org.cheng.devtool.service.basic;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.cheng.devtool.dao.basic.UserInfoMapper;
import org.cheng.devtool.entity.UserInfo;
import org.cheng.devtool.entity.UserInfoExample;
import org.cheng.devtool.util.BeanEntityHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author li.cheng
 * @version 1.0.0 2017年04月24日
 * @since soter 1.0.0
 */
@Service
public class UserInfoService {
    @Autowired
    private UserInfoMapper mapper;

    public UserInfo addUserInfo(UserInfo userInfo){
        userInfo.setCreateTime(new Date());
        mapper.insert(userInfo);
        return userInfo;
    }

    public boolean deleteUserInfo(Integer userId){
        return mapper.deleteByPrimaryKey(userId) > 0;
    }

    public UserInfo updateUserInfo(UserInfo userInfo){
        userInfo.setUpdateTime(new Date());
        UserInfo old = mapper.selectByPrimaryKey(userInfo.getUserId());
        BeanEntityHelper.copyNotNull(userInfo,old);
        mapper.updateByPrimaryKey(old);
        return userInfo;
    }

    public UserInfo getUserInfo(Integer userId){
        return mapper.selectByPrimaryKey(userId);
    }

    public PageInfo<UserInfo> queryUserInfo(UserInfo userInfo, int pageNum, int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        UserInfoExample example = new UserInfoExample();
        UserInfoExample.Criteria criteria = example.createCriteria();

        if(null != userInfo.getUserId()){
            criteria.andUserIdEqualTo(userInfo.getUserId());
        }
        if(null != userInfo.getUserName()){
            criteria.andUserNameEqualTo(userInfo.getUserName());
        }
        if(null != userInfo.getCreateTime()){
            criteria.andCreateTimeEqualTo(userInfo.getCreateTime());
        }
        if(null != userInfo.getUpdateTime()){
            criteria.andUpdateTimeEqualTo(userInfo.getUpdateTime());
        }
        if(null != userInfo.getScore()){
            criteria.andScoreEqualTo(userInfo.getScore());
        }
        List<UserInfo> userInfos = mapper.selectByExample(example);
        return new PageInfo<>(userInfos);
    }
}
