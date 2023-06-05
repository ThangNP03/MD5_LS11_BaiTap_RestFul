package ra.service.blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ra.dao.IBlogDao;
import ra.model.Blog;

import java.util.List;
import java.util.Optional;
@Service
public class BlogService implements IBlogService{
    @Autowired
    private IBlogDao blogDao;
    @Override
    public Iterable<Blog> findAll() {
        return blogDao.findAll();
    }

    @Override
    public Optional<Blog> findById(Long id) {
        return blogDao.findById(id);
    }

    @Override
    public Blog save(Blog blog) {
        return blogDao.save(blog);
    }

    @Override
    public List<Blog> findByTitleContaining(String title) {
        return blogDao.findByTitleContaining(title);
    }

    @Override
    public Page<Blog> findAll(Pageable pageable) {
        return blogDao.findAll(pageable);
    }

    @Override
    public void delete(Long id) {
        blogDao.deleteById(id);
    }
}
