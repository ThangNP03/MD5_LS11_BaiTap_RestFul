package ra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ra.model.Blog;

import ra.model.ResponOject;
import ra.service.blog.IBlogService;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/blog")
@CrossOrigin(origins = "*")
public class BlogController {
    @Autowired
    private IBlogService blogService;

    @GetMapping("")
    public ResponseEntity<ResponOject> getAllBlog() {
        List<Blog> blogs = (List<Blog>) blogService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponOject("ok", "Query Successfully!", blogs)
        );
    }

    @GetMapping("/page/{number}")
    public ResponseEntity<ResponOject> getPageBlog(@PathVariable int number) {
        Pageable page = PageRequest.of(number, 20);
        Page<Blog> blogs = blogService.findAll(page);
        List<Blog> blogList = blogs.getContent();
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponOject("ok", "Query Successfully!", blogList)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponOject> getBlogById(@PathVariable Long id) {
        Optional<Blog> blogOptional = blogService.findById(id);
        return blogOptional.map(blog -> ResponseEntity.status(HttpStatus.OK).body(
                        new ResponOject("ok", "Query Successfully!", blog)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponOject("fail", "Cannot find blog with id = " + id, "")));
    }

    @GetMapping("/search/{title}")
    public ResponseEntity<ResponOject> getBlogByTitle(@PathVariable String title) {
        List<Blog> blogs = blogService.findByTitleContaining(title);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponOject("ok", "Query Successfully!", blogs));
    }

    @PostMapping("")
    public ResponseEntity<ResponOject> postForBlog(@RequestBody Blog blog) {
        Blog newBlog = blogService.save(blog);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ResponOject("ok", "Post blog successfully!", newBlog)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponOject> putForBlog(@RequestBody Blog blog, @PathVariable Long id) {
        Optional<Blog> blogOptional = blogService.findById(id);
        return blogOptional.isPresent()
                ? ResponseEntity.status(HttpStatus.OK).body(
                new ResponOject("ok", "Update blog successfully!", blogService.save(blog)))
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponOject("fail", "Cannot find blog with id = " + id, ""));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponOject> deleteForBlog(@PathVariable Long id) {
        Optional<Blog> blogOptional = blogService.findById(id);
        return blogOptional.isPresent()
                ? ResponseEntity.status(HttpStatus.OK).body(
                new ResponOject("ok", "Delete blog successfully!", ""))
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponOject("fail", "Cannot find product with id = " + id, ""));
    }
}
