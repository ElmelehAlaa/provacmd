package AlaaElmeleh.U2W2D3.controllers;

import AlaaElmeleh.U2W2D3.entities.Author;
import AlaaElmeleh.U2W2D3.entities.Blog;
import AlaaElmeleh.U2W2D3.services.BlogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
    @RequestMapping("/blogs")
    public class BlogsController{
        @Autowired
        private BlogsService blogsService;

    @GetMapping("")
    public Page<Blog> getBlog(@RequestParam(defaultValue = "0")int page,
                                  @RequestParam(defaultValue = "10")int size,
                                  @RequestParam(defaultValue = "id")String orderBy)
    {return blogsService.getBlogs(page,size,orderBy);}

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Blog saveBlog(@RequestBody Blog body){return blogsService.save(body);}

    @GetMapping("/{id}")
    public Blog findById(@PathVariable long id ){
            return blogsService.findById(id);
    }

    @PutMapping("/{id}")
    public Blog findByAndUpdate (@PathVariable  long id , @RequestBody Blog body){
            return blogsService.findByIdAndUpdate(id,body);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable long id){
            blogsService.findByIdAndDelete(id);
    }


    }

