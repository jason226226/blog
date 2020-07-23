package com.jee.service.impl;

import com.jee.dao.BlogDao;
import com.jee.dao.CommentDao;
import com.jee.pojo.Comment;
import com.jee.service.CommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentDao commentDao;
    @Autowired
    private BlogDao blogDao;
    @Override
    public List<Comment> commends(Long blogId) {
        List<Comment> comments = commentDao.findParentCommentByBlogId(blogId,Long.parseLong("-1"));
        return eachComment(comments);
    }

    private List<Comment> eachComment(List<Comment> comments) {
        List<Comment> commentList = new ArrayList<>();
        for(Comment comment:comments){
            Comment c = new Comment();
            BeanUtils.copyProperties(comment,c);
            commentList.add(c);
        }
        combineChildren(commentList);
        return commentList;
    }

    private void combineChildren(List<Comment> commentList) {
        for(Comment comment:commentList){
            List<Comment> tempReplys = new ArrayList<>();
            List<Comment> replys = comment.getReplyComments();
            for(Comment reply:replys){
                recursively(reply);
            }
            comment.setReplyComments(tempReplys);
        }
    }

    private List<Comment> tempReplys = new ArrayList<>();

    private void recursively(Comment reply) {
        tempReplys.add(reply);
        if(reply.getReplyComments().size()>0){
            List<Comment> replys1 = reply.getReplyComments();
            for(Comment reply1:replys1){
                tempReplys.add(reply1);
                if(reply.getReplyComments().size()>0){
                    recursively(reply);
                }
            }
        }
    }

    @Override
    public int saveComment(Comment comment) {
        System.out.println("comment:"+comment);
        Long parentCommentId = comment.getParentCommentId();
        if(parentCommentId!=-1){
            comment.setParentComment(commentDao.findByParentCommentId(comment.getParentCommentId()));
        }else {
            comment.setParentComment(null);
        }
        comment.setCreateTime(new Date());
        comment.setBlog(blogDao.getDetailedBlog(comment.getBlogId()));
        return commentDao.saveComment(comment);
    }
}
