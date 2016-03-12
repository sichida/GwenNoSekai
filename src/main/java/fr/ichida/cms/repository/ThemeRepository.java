package fr.ichida.cms.repository;

import fr.ichida.cms.domain.Theme;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for the Theme entity.
 */
public interface ThemeRepository extends MongoRepository<Theme,String> {

}
