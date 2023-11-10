package AlaaElmeleh.U2W2D3.controllers;


import AlaaElmeleh.U2W2D3.entities.Author;
import AlaaElmeleh.U2W2D3.exceptions.BadRequestException;
import AlaaElmeleh.U2W2D3.payloads.authors.NewAuthorDTO;
import AlaaElmeleh.U2W2D3.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorsController{
    @Autowired
    private AuthorService authorService;

    @GetMapping("")
    public Page<Author> getAuthor(@RequestParam(defaultValue = "0")int page,
                                    @RequestParam(defaultValue = "10")int size,
                                  @RequestParam(defaultValue = "id")String orderBy)
    {return authorService.getAuthors(page,size,orderBy);}

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Author saveAuthor(@RequestBody @Validated NewAuthorDTO body, BindingResult validation){
        if (validation.hasErrors()){
            throw new BadRequestException(validation.getAllErrors());
        }else {
            try{
                return authorService.save(body);
            }catch (IOException e){
                throw new RuntimeException(e);
            }
        }
    }

    @GetMapping("/{id}")
    public Author findById(@PathVariable long id ){
        return authorService.findById(id);
    }

    @PutMapping("/{id}")
    public Author findByAndUpdate (@PathVariable  long id , @RequestBody Author body){
        return authorService.findByIdAndUpdate(id,body);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable long id){
        authorService.findByIdAndDelete(id);
    }

    @PostMapping("/upload")
    public String uploadExample(@RequestParam("avatar") MultipartFile body) throws IOException {
        System.out.println(body.getSize());
        System.out.println(body.getContentType());
        return authorService.uploadPicture(body);
    }


}