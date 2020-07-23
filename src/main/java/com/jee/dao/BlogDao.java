package com.jee.dao;

import com.jee.dto.*;
import com.jee.pojo.Blog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface BlogDao {
    int saveBlog(Blog blog);
    int deleteBlog(Long id);
    int updateBlog(ShowBlog showBlog);
    int saveBlogAndTag(BlogAndTag blogAndTag);
    int deleteBlogAndTag(Long blogId);
    List<BlogQuery> getAllBlogQuery();
    List<BlogQuery> searchByTitleOrTypeOrRecommend(SearchBlog searchBlog);
    List<FirstPageBlog> getFirstPageBlog();
    List<RecommendBlog> getAllRecommendBlog();
    List<FirstPageBlog> getSearchBlog(String query);
    ShowBlog getBlogById(Long id);
    DetailedBlog getDetailedBlog(Long id);
    List<FirstPageBlog> getByTypeId(Long typeId);
    List<FirstPageBlog> getByTagId(Long tagId);

}
