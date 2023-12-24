package com.zeng.aggsearch.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.gson.Gson;
import com.zeng.aggsearch.annotation.AuthCheck;
import com.zeng.aggsearch.common.BaseResponse;
import com.zeng.aggsearch.common.DeleteRequest;
import com.zeng.aggsearch.common.ErrorCode;
import com.zeng.aggsearch.common.ResultUtils;
import com.zeng.aggsearch.constant.UserConstant;
import com.zeng.aggsearch.exception.BusinessException;
import com.zeng.aggsearch.exception.ThrowUtils;
import com.zeng.aggsearch.model.dto.picture.PictureQueryRequest;
import com.zeng.aggsearch.model.dto.post.PostAddRequest;
import com.zeng.aggsearch.model.dto.post.PostEditRequest;
import com.zeng.aggsearch.model.dto.post.PostQueryRequest;
import com.zeng.aggsearch.model.dto.post.PostUpdateRequest;
import com.zeng.aggsearch.model.entity.Picture;
import com.zeng.aggsearch.model.entity.Post;
import com.zeng.aggsearch.model.entity.User;
import com.zeng.aggsearch.model.vo.PostVO;
import com.zeng.aggsearch.service.PictureService;
import com.zeng.aggsearch.service.PostService;
import com.zeng.aggsearch.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 帖子接口
 *
 
 */
@RestController
@RequestMapping("/picture")
@Slf4j
public class PictureController {

    @Resource
    private PictureService pictureService;

    private final static Gson GSON = new Gson();

    /**
     * 分页获取列表（封装类）
     *
     * @param pictureQueryRequest
     * @param request
     * @return
     */
    @PostMapping("/list/page/vo")
    public BaseResponse<Page<Picture>> listPictureVOByPage(@RequestBody PictureQueryRequest pictureQueryRequest,
                                                        HttpServletRequest request) {
        long current = pictureQueryRequest.getCurrent();
        long size = pictureQueryRequest.getPageSize();
        // 限制爬虫
        ThrowUtils.throwIf(size > 20, ErrorCode.PARAMS_ERROR);
        Page<Picture> picturePage = pictureService.searchPicture(pictureQueryRequest.getSearchText(), current, size);
        return ResultUtils.success(picturePage);
    }

}
