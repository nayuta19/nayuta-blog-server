package org.nayutablogserver.service;
import org.nayutablogserver.entity.Article;
import java.util.List;

public interface  ArticleService {
    List<Article> getPublishedArticles(); // 接口方法声明
    Article getArticleDetail(Integer id);
    int addArticle(String title, String content);

    int delArticle(Integer id);
}
