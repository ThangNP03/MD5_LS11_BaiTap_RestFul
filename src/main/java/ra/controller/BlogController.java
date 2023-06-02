package ra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ra.model.Blog;
import ra.model.Catalog;
import ra.service.blog.IBlogService;
import ra.service.catalog.ICatalogService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/blog")
@CrossOrigin(origins = "*")
public class BlogController {
    @Autowired
    private IBlogService blogService;
    @Autowired
    private ICatalogService catalogService;
    @GetMapping
    public ResponseEntity<Iterable<Blog>> findAllBlog(){
        List<Blog> list = (List<Blog>) blogService.findAll();
        if (list.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Blog> findBlogById(@PathVariable Long id){
        Optional<Blog> blogOptional = blogService.findById(id);
        if (!blogOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(blogOptional.get(),HttpStatus.OK);
    }
    @GetMapping("/create-blog")
    public ModelAndView showFormCreate() {
        ModelAndView modelAndView = new ModelAndView("/blog/create");
        modelAndView.addObject("blog", new Blog());
        modelAndView.addObject("catalogs", catalogService.findAll());
        return modelAndView;
    }

    @PostMapping("/create-blog")
    public String saveBlog(@ModelAttribute("blog") Blog blog, @ModelAttribute("catalogs") Catalog catalogs) {
        blogService.save(blog);
        return "redirect:/create-blog";
    }



    @GetMapping("/edit-blog/{id}")
    public ModelAndView showFormEdit(@PathVariable("id") Long id) {
        Optional<Blog> blog = blogService.findById(id);
        if (blog != null) {
            ModelAndView modelAndView = new ModelAndView("/blog/edit");
            modelAndView.addObject("blog", blog);
            return modelAndView;
        } else {
            return new ModelAndView("/blog/list");
        }
    }

    @PostMapping("/edit-blog")
    public String updateBlog(@ModelAttribute("blog") Blog blog) {
        blogService.save(blog);
        return "redirect:/blog";
    }

    @GetMapping("/delete/{id}")
    public String deleteBlog(@PathVariable("id") Long id) {
        blogService.delete(id);
        return "redirect:/blog";
    }
}
