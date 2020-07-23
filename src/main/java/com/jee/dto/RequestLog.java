package com.jee.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestLog {

    private String url;
    private String ip;
    private String classMethod;
    private Object[] args;
}
