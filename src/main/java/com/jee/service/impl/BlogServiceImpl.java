package com.jee.service.impl;

import com.jee.dao.BlogDao;
import com.jee.dto.*;
import com.jee.pojo.Blog;
import com.jee.pojo.Tag;
import com.jee.service.BlogService;
import com.jee.utils.MarkdownUtils;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogDao blogDao;
    @Override
    public ShowBlog getBlogById(Long id) {
        return blogDao.getBlogById(id);
    }

    @Override
    public DetailedBlog getDetailedBlog(Long id) {
        DetailedBlog detailedBlog = blogDao.getDetailedBlog(id);
        String content = detailedBlog.getContent();
        detailedBlog.setContent(MarkdownUtils.markdownToHtmlExtensions(content));
        return detailedBlog;
    }

    @Override
    public List<BlogQuery> getAllBlog() {
        return blogDao.getAllBlogQuery();
    }

    @Override
    public int saveBlog(Blog blog) {
        blog.setCreateTime(new Date());
        blog.setUpdateTime(new Date());
        blog.setViews(0);
        List<Tag> tags = blog.getTags();
        BlogAndTag blogAndTag;
        for(Tag tag:tags){
            blogAndTag = new BlogAndTag(tag.getId(), blog.getId());
            blogDao.saveBlogAndTag(blogAndTag);
        }
        return blogDao.saveBlog(blog);
    }

    @Override
    public int updateBlog(ShowBlog showblog) {
        return blogDao.updateBlog(showblog);
    }

    @Override
    public int deleteBlog(Long id) {
        blogDao.deleteBlogAndTag(id);
        return blogDao.deleteBlog(id);
    }

    @Override
    public List<BlogQuery> getBlogBySearch(SearchBlog searchBlog) {
        return blogDao.searchByTitleOrTypeOrRecommend(searchBlog);
    }

    @Override
    public void transformRecommend(SearchBlog searchBlog) {
        if(!"".equals(searchBlog.getRecommend())&& null!=searchBlog.getRecommend()){
            searchBlog.setRecommend2(1);
        }
    }

    @Override
    public List<FirstPageBlog> getAllFirstPageBlog() {
        return blogDao.getFirstPageBlog();
    }

    @Override
    public List<RecommendBlog> getRecommendBlog() {
         return blogDao.getAllRecommendBlog();

    }

    @Override
    public List<FirstPageBlog> getSearchBlog(String query) {
        return blogDao.getSearchBlog(query);
    }

    @Override
    public List<FirstPageBlog> getByTypeId(Long typeId) {
        return blogDao.getByTypeId(typeId);
    }

    @Override
    public List<FirstPageBlog> getByTagId(Long tagId) {
        return blogDao.getByTagId(tagId);
    }
}
