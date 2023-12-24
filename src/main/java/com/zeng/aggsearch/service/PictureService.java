package com.zeng.aggsearch.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zeng.aggsearch.model.entity.Picture;

import java.util.List;

public interface PictureService {
    Page<Picture> searchPicture(String searchText, long pageNum, long pageSize);
}
