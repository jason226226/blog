package com.jee.service;

import com.jee.dto.*;
import com.jee.pojo.Blog;

import java.util.List;

public interface BlogService {

    ShowBlog getBlogById(Long id);
    DetailedBlog getDetailedBlog(Long id);
    List<BlogQuery> getAllBlog();
    int saveBlog(Blog blog);
    int updateBlog(ShowBlog showBlog);
    int deleteBlog(Long id);
    List<BlogQuery> getBlogBySearch(SearchBlog searchBlog);
    void transformRecommend(SearchBlog searchBlog);
    List<FirstPageBlog> getAllFirstPageBlog();
    List<RecommendBlog> getRecommendBlog();
    List<FirstPageBlog> getSearchBlog(String query);

    List<FirstPageBlog> getByTypeId(Long typeId);
    List<FirstPageBlog> getByTagId(Long tagId);
}
