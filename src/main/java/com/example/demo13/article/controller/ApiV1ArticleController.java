package com.example.demo13.article.controller;

import com.example.demo13.article.dto.ArticleDTO;
import com.example.demo13.article.entity.Article;
import com.example.demo13.article.request.ArticleCreateRequest;
import com.example.demo13.article.request.ArticleModifyRequest;
import com.example.demo13.article.response.ArticleCreateResponse;
import com.example.demo13.article.response.ArticleModifyResponse;
import com.example.demo13.article.response.ArticleResponse;
import com.example.demo13.article.response.ArticlesResponse;
import com.example.demo13.article.service.ArticleService;
import com.example.demo13.global.RsData;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/article")
public class ApiV1ArticleController {
    private final ArticleService articleService;

    @GetMapping("")
    public RsData<ArticlesResponse> getList(){
        List<ArticleDTO> articleDTOList = this.articleService.getList();

        return RsData.of("200", "다건 조회 성공",  new ArticlesResponse(articleDTOList));
    }
    @GetMapping("/{id}")
    public RsData<ArticleResponse> getArticle(@RequestParam("id") Long id){
        Article article = this.articleService.getArticle(id);
        ArticleDTO articleDTO = new ArticleDTO(article);
        return RsData.of("200","단건 조회 성공", new ArticleResponse(articleDTO));
    }
    @PostMapping("")
    public RsData<ArticleCreateResponse> write(@Valid @RequestBody ArticleCreateRequest articleCreateRequest){
        Article article = this.articleService.write(articleCreateRequest.getSubject(), articleCreateRequest.getContent());
        return RsData.of("200", "등록 성공", new ArticleCreateResponse(article));
    }
    @PatchMapping("/{id}")
    public RsData<ArticleModifyResponse> update(@PathVariable("id") Long id, @Valid @RequestBody ArticleModifyRequest articleModifyRequest){
        Article article = this.articleService.getArticle(id);
        if(article == null) return RsData.of(
                "500",
                "%d번 게시물이 없습니다.".formatted(id),
                null
        );
        article = this.articleService.update(article,articleModifyRequest.getSubject(), articleModifyRequest.getContent());
        return RsData.of("200", "수정 성공", new ArticleModifyResponse(article));
    }
    @DeleteMapping("/{id}")
    public RsData<ArticleResponse> delete(@PathVariable("id") Long id){
        Article article = this.articleService.getArticle(id);
        if (article == null) return RsData.of(
                "500",
                "%d번 게시물이 없습니다.".formatted(id),
                null
        );
        this.articleService.delete(article);
        ArticleDTO articleDTO = new ArticleDTO(article);
        return RsData.of("200","게시물 삭제 성공", new ArticleResponse(articleDTO));
    }
}
