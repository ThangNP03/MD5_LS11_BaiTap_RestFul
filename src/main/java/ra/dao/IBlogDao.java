package ra.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import ra.model.Blog;

import java.util.List;

public interface IBlogDao extends PagingAndSortingRepository<Blog, Long > {
    List<Blog> findByTitleContaining(String title);
}
