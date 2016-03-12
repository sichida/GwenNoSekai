package fr.ichida.cms.repository;

import fr.ichida.cms.domain.Article;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for the Article entity.
 */
public interface ArticleRepository extends MongoRepository<Article,String> {

}
