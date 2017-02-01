package fr.ichida.cms.mongo;

import fr.ichida.cms.domain.Article;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by shoun on 31/01/2017.
 */
@Repository
public interface ArticleRepository extends PagingAndSortingRepository<Article, String> {
}
