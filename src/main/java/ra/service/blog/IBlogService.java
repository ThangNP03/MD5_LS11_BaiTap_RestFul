package ra.service.blog;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ra.model.Blog;
import ra.service.IGenricService;

import java.util.List;

public interface IBlogService extends IGenricService<Blog, Long > {
    List<Blog> findByTitleContaining(String title);

    Page<Blog> findAll(Pageable pageable);
}
