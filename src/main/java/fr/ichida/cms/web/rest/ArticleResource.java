package fr.ichida.cms.web.rest;

import com.codahale.metrics.annotation.Timed;
import fr.ichida.cms.domain.Article;
import fr.ichida.cms.service.ArticleService;
import fr.ichida.cms.web.rest.util.HeaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Article.
 */
@RestController
@RequestMapping("/api")
public class ArticleResource {

    private final Logger log = LoggerFactory.getLogger(ArticleResource.class);
        
    @Inject
    private ArticleService articleService;
    
    /**
     * POST  /articles -> Create a new article.
     */
    @RequestMapping(value = "/articles",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Article> createArticle(@Valid @RequestBody Article article) throws URISyntaxException {
        log.debug("REST request to save Article : {}", article);
        if (article.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("article", "idexists", "A new article cannot already have an ID")).body(null);
        }
        Article result = articleService.save(article);
        return ResponseEntity.created(new URI("/api/articles/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("article", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /articles -> Updates an existing article.
     */
    @RequestMapping(value = "/articles",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Article> updateArticle(@Valid @RequestBody Article article) throws URISyntaxException {
        log.debug("REST request to update Article : {}", article);
        if (article.getId() == null) {
            return createArticle(article);
        }
        Article result = articleService.save(article);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("article", article.getId().toString()))
            .body(result);
    }

    /**
     * GET  /articles -> get all the articles.
     */
    @RequestMapping(value = "/articles",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<Article> getAllArticles() {
        log.debug("REST request to get all Articles");
        return articleService.findAll();
            }

    /**
     * GET  /articles/:id -> get the "id" article.
     */
    @RequestMapping(value = "/articles/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Article> getArticle(@PathVariable String id) {
        log.debug("REST request to get Article : {}", id);
        Article article = articleService.findOne(id);
        return Optional.ofNullable(article)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /articles/:id -> delete the "id" article.
     */
    @RequestMapping(value = "/articles/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteArticle(@PathVariable String id) {
        log.debug("REST request to delete Article : {}", id);
        articleService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("article", id.toString())).build();
    }
}
