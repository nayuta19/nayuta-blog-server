package org.nayutablogserver.service.impl;

import org.nayutablogserver.entity.Article;
import org.nayutablogserver.mapper.ArticleMapper;
import org.nayutablogserver.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public List<Article> getPublishedArticles() {
        return articleMapper.selectPublishedArticles();
    }

    @Override
    public Article getArticleDetail(Integer id) {
        return articleMapper.selectArticleById(id);
    }

    @Override
    public int addArticle(String title, String content) {
        Article article = new Article();
        article.setTitle(title);
        article.setContent(content);
        return articleMapper.insertArticle(article);
    }
}
