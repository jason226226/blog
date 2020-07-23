package com.jee.dao;

import com.jee.pojo.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CommentDao {

    List<Comment> findParentCommentByBlogId(@Param("blogId") Long blogId,
                               @Param("blogParentId") Long blogParentId);

    Comment findByParentCommentId(Long parentCommentId);

    int saveComment(Comment comment);
}
