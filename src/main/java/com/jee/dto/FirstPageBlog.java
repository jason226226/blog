package com.jee.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FirstPageBlog {

    private Long id;
    private String title;
    private String firstPicture;
    private Integer views;
    private Date updateTime;
    private String description;
    private String typeName;
    private String nickname;
    private String avatar;
}
