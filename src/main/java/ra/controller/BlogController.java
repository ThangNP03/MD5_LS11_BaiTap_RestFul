package ra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.model.Blog;
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
    public List<Blog> findAll(){
       return (List<Blog>) blogService.findAll();
   }
   @GetMapping("/{id}")
    public ResponseEntity<Blog>  findBlogById(@PathVariable("id") Long id){
       Optional<Blog> blogOptional= blogService.findById(id);
       if (blogOptional.isPresent()){
           return new ResponseEntity<>(blogOptional.get(), HttpStatus.OK);
       }else {
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }
   }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Blog> delete(@PathVariable("id") Long id ){
        Optional<Blog> studentOptional = blogService.findById(id);
        if (studentOptional.isPresent()){
            blogService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Blog> update(@PathVariable("id") Long id , @RequestBody Blog blog){
        Optional<Blog> studentOptional = blogService.findById(id);
        if (studentOptional.isPresent()){
            blog.setBlogId(id);
            blogService.save(blog);
            return new ResponseEntity<>(blog,HttpStatus.CREATED);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/create")
    public ResponseEntity<Blog> create(@RequestBody Blog blog){
        Blog b = blogService.save(blog);
        return new ResponseEntity<>(b, HttpStatus.CREATED);
    }
}
