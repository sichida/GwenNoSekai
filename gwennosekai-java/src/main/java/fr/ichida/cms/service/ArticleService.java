package fr.ichida.cms.service;

import fr.ichida.cms.domain.Article;
import fr.ichida.cms.mongo.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by shoun on 31/01/2017.
 */
@Service
public class ArticleService {
    private final ArticleRepository articleRepository;

    @Autowired
    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Transactional
    public Article save(Article article) {
        if (null == article.getId()) {
            article.setCreationDate(LocalDateTime.now());
        }
        return articleRepository.save(article);
    }

    @Transactional(readOnly = true)
    public Page<Article> find(int page, int pageSize) {
        return articleRepository.findAll(new PageRequest(page, pageSize));
    }
}
