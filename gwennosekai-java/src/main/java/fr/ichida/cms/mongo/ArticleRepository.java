package fr.ichida.cms.mongo;

import fr.ichida.cms.domain.Article;
import fr.ichida.cms.domain.TagProjection;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by shoun on 31/01/2017.
 */
@Repository
public interface ArticleRepository extends PagingAndSortingRepository<Article, String> {
    @Query("{ $text: { $search: ?0 } }")
    List<TagProjection> findDistinctTags(String query);
}
