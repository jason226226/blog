package com.jee.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SearchBlog {
    private String title;
    private Long typeId;
    private String recommend;
    private Integer recommend2;
}
