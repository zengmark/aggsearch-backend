package com.zeng.aggsearch.datasource;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zeng.aggsearch.common.ErrorCode;
import com.zeng.aggsearch.constant.CommonConstant;
import com.zeng.aggsearch.constant.UserConstant;
import com.zeng.aggsearch.exception.BusinessException;
import com.zeng.aggsearch.mapper.UserMapper;
import com.zeng.aggsearch.model.dto.user.UserQueryRequest;
import com.zeng.aggsearch.model.entity.User;
import com.zeng.aggsearch.model.enums.UserRoleEnum;
import com.zeng.aggsearch.model.vo.LoginUserVO;
import com.zeng.aggsearch.model.vo.UserVO;
import com.zeng.aggsearch.service.UserService;
import com.zeng.aggsearch.utils.SqlUtils;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.bean.WxOAuth2UserInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户服务实现
 *
 
 */
@Service
@Slf4j
public class UserDataSource implements DataSource<UserVO> {

    @Resource
    private UserService userService;

    @Override
    public Page<UserVO> doSearch(String searchText, long pageNum, long pageSize) {
        UserQueryRequest userQueryRequest = new UserQueryRequest();
        userQueryRequest.setUserName(searchText);
        userQueryRequest.setCurrent(pageNum);
        userQueryRequest.setPageSize(pageSize);
        Page<UserVO> userVOPage = userService.listUserVOByPage(userQueryRequest);
        return userVOPage;
    }

}
