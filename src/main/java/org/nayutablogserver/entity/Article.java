package org.nayutablogserver.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Article {
    private Integer id;
    private String title;
    private String content;
    private Date createdAt;
    private Date updatedAt;
    private Integer status;
    private Integer authorId;
    private Integer views;
}
