package com.zeng.aggsearch.model.vo;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zeng.aggsearch.model.entity.Picture;
import com.zeng.aggsearch.model.entity.Post;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 帖子视图
 *
 
 */
@Data
public class SearchVO implements Serializable {

    private List<UserVO> userList;

    private List<PostVO> postList;

    private List<Picture> pictureList;

    private static final long serialVersionUID = 1L;
}
