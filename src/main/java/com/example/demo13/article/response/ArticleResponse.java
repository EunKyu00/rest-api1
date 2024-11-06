package com.example.demo13.article.response;

import com.example.demo13.article.dto.ArticleDTO;
import com.example.demo13.article.entity.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ArticleResponse {
    private final ArticleDTO article;
}
