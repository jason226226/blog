package com.jee.pojo;

import com.jee.dto.DetailedBlog;
import com.jee.dto.ShowBlog;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    private Long id;
    private String nickname;
    private String email;
    private String content;
    private String avatar;
    private Date createTime;
    private Long blogId;
    private Long parentCommentId;
    private Long parentNickname;

    private List<Comment> replyComments = new ArrayList<>();
    private Comment parentComment;

    private DetailedBlog blog;
}
