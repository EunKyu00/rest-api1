package com.example.demo13.article.response;

import com.example.demo13.article.entity.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ArticleCreateResponse {
    private final Article article;
}
