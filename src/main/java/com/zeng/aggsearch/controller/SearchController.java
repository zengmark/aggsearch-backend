package com.zeng.aggsearch.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zeng.aggsearch.common.BaseResponse;
import com.zeng.aggsearch.common.ErrorCode;
import com.zeng.aggsearch.common.ResultUtils;
import com.zeng.aggsearch.exception.BusinessException;
import com.zeng.aggsearch.exception.ThrowUtils;
import com.zeng.aggsearch.manager.SearchFacade;
import com.zeng.aggsearch.model.dto.post.PostQueryRequest;
import com.zeng.aggsearch.model.dto.search.SearchRequest;
import com.zeng.aggsearch.model.dto.user.UserQueryRequest;
import com.zeng.aggsearch.model.entity.Picture;
import com.zeng.aggsearch.model.enums.SearchTypeEnum;
import com.zeng.aggsearch.model.vo.PostVO;
import com.zeng.aggsearch.model.vo.SearchVO;
import com.zeng.aggsearch.model.vo.UserVO;
import com.zeng.aggsearch.service.PictureService;
import com.zeng.aggsearch.service.PostService;
import com.zeng.aggsearch.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/search")
@Slf4j
public class SearchController {

    @Resource
    private UserService userService;

    @Resource
    private PostService postService;

    @Resource
    private PictureService pictureService;

    @Resource
    private SearchFacade searchFacade;

    @PostMapping("/all")
    public BaseResponse<SearchVO> searchAll(@RequestBody SearchRequest searchRequest, HttpServletRequest request) {
        return ResultUtils.success(searchFacade.searchAll(searchRequest, request));
    }

}
