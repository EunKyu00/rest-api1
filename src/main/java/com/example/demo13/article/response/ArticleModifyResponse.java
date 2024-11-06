package com.example.demo13.article.response;

import com.example.demo13.article.entity.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ArticleModifyResponse {
    private final Article article;
}
