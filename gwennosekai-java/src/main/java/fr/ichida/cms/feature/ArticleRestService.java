package fr.ichida.cms.feature;

import fr.ichida.cms.domain.Article;
import fr.ichida.cms.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by shoun on 31/01/2017.
 */
@RestController
@RequestMapping("/api/v1/article")
public class ArticleRestService {

    private final ArticleService articleService;

    @Autowired
    public ArticleRestService(ArticleService articleService) {
        this.articleService = articleService;
    }

    @RequestMapping(value = {"", "/"}, method = POST)
    public Article save(@RequestBody Article article) {
        return articleService.save(article);
    }
}
