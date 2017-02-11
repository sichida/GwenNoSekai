package fr.ichida.cms.mongo;

import fr.ichida.cms.domain.Picture;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by shoun on 06/02/2017.
 */
@Repository
public interface PictureRepository extends PagingAndSortingRepository<Picture, String> {
    Picture findByLocation(String location);
}
