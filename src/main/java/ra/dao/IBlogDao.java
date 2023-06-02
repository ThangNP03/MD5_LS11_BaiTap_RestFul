package ra.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import ra.model.Blog;

public interface IBlogDao extends PagingAndSortingRepository<Blog, Long > {
}
