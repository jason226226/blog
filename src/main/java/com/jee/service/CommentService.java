package com.jee.service;

import com.jee.pojo.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> commends(Long blogId);

    int saveComment(Comment comment);

}
