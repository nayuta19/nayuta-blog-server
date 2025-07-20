package org.nayutablogserver.controller;

import org.nayutablogserver.entity.Article;
import org.nayutablogserver.pojo.Result;
import org.nayutablogserver.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/articles")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @GetMapping
    public Result getArticleList() {
        List<Article> allList =  articleService.getPublishedArticles();
        return  Result.success(allList);
    }

    @GetMapping("/{id}")
    public Article getArticleDetail(@PathVariable Integer id) {
        return articleService.getArticleDetail(id);
    }

    @PostMapping
    public Result createArticle( @RequestBody Article requestData) {
        try {
            int result = articleService.addArticle(requestData.getTitle(), requestData.getContent());
            if (result > 0) {
                return Result.success();
            } else {
                return Result.error();
            }
        } catch (Exception e) {
            return Result.error();
        }

    }

    @DeleteMapping("/{id}")
    public Result delArticle(@PathVariable Integer id) {
        try {
            int result =  articleService.delArticle(id);
             if (result > 0) {
                return Result.success();
             } else {
              return Result.error();
             }
        } catch (Exception e) {
            return Result.error();
        }
    }
}
