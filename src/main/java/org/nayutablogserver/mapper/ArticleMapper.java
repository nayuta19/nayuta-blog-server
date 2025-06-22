package org.nayutablogserver.mapper;
import org.apache.ibatis.annotations.*;
import org.nayutablogserver.entity.Article;
import java.util.List;


@Mapper
public interface  ArticleMapper {
    // 查询已发布文章列表
    @Select("SELECT * FROM articles WHERE status = 1 ORDER BY created_at DESC")
    List<Article> selectPublishedArticles();

    // 根据ID查询文章详情
    @Select("SELECT * FROM articles WHERE id = #{id}")
    Article selectArticleById(@Param("id") Integer id);

    // 新增文章方法
    @Insert("INSERT INTO articles(title, content, status, author_id, views, created_at, updated_at) " +
            "VALUES(#{title}, #{content}, 1, #{authorId}, 0, NOW(), NOW())")
    @Options(useGeneratedKeys = true, keyProperty = "id") // 获取自增ID
    int insertArticle(Article article);
}
