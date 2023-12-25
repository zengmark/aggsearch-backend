package com.zeng.aggsearch.datasource;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.gson.Gson;
import com.zeng.aggsearch.common.ErrorCode;
import com.zeng.aggsearch.constant.CommonConstant;
import com.zeng.aggsearch.exception.BusinessException;
import com.zeng.aggsearch.exception.ThrowUtils;
import com.zeng.aggsearch.mapper.PostFavourMapper;
import com.zeng.aggsearch.mapper.PostMapper;
import com.zeng.aggsearch.mapper.PostThumbMapper;
import com.zeng.aggsearch.model.dto.post.PostEsDTO;
import com.zeng.aggsearch.model.dto.post.PostQueryRequest;
import com.zeng.aggsearch.model.entity.Post;
import com.zeng.aggsearch.model.entity.PostFavour;
import com.zeng.aggsearch.model.entity.PostThumb;
import com.zeng.aggsearch.model.entity.User;
import com.zeng.aggsearch.model.vo.PostVO;
import com.zeng.aggsearch.model.vo.UserVO;
import com.zeng.aggsearch.service.PostService;
import com.zeng.aggsearch.service.UserService;
import com.zeng.aggsearch.utils.SqlUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 帖子服务实现
 *
 
 */
@Service
@Slf4j
public class PostDataSource implements DataSource<PostVO> {

    @Resource
    private PostService postService;

    @Override
    public Page<PostVO> doSearch(String searchText, long pageNum, long pageSize) {
        PostQueryRequest postQueryRequest = new PostQueryRequest();
        postQueryRequest.setSearchText(searchText);
        postQueryRequest.setCurrent(pageNum);
        postQueryRequest.setPageSize(pageSize);
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        Page<Post> postPage = postService.searchFromEs(postQueryRequest);
        return postService.getPostVOPage(postPage, request);
    }

}




