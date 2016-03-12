package fr.ichida.cms.service;

import fr.ichida.cms.domain.Article;
import fr.ichida.cms.repository.ArticleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing Article.
 */
@Service
public class ArticleService {

    private final Logger log = LoggerFactory.getLogger(ArticleService.class);
    
    @Inject
    private ArticleRepository articleRepository;
    
    /**
     * Save a article.
     * @return the persisted entity
     */
    public Article save(Article article) {
        log.debug("Request to save Article : {}", article);
        Article result = articleRepository.save(article);
        return result;
    }

    /**
     *  get all the articles.
     *  @return the list of entities
     */
    public List<Article> findAll() {
        log.debug("Request to get all Articles");
        List<Article> result = articleRepository.findAll();
        return result;
    }

    /**
     *  get one article by id.
     *  @return the entity
     */
    public Article findOne(String id) {
        log.debug("Request to get Article : {}", id);
        Article article = articleRepository.findOne(id);
        return article;
    }

    /**
     *  delete the  article by id.
     */
    public void delete(String id) {
        log.debug("Request to delete Article : {}", id);
        articleRepository.delete(id);
    }
}
