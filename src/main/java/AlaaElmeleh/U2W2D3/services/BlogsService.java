package AlaaElmeleh.U2W2D3.services;

import AlaaElmeleh.U2W2D3.entities.Blog;
import AlaaElmeleh.U2W2D3.exceptions.NotFoundException;
import AlaaElmeleh.U2W2D3.repositories.BlogsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ListIterator;
import java.util.Random;
@Service
public class BlogsService {
    @Autowired
    private BlogsRepository blogsRepository;

    public Blog save(Blog body){

        return blogsRepository.save(body);
    }

    public Page<Blog> getBlogs(int page , int size, String orderBy){
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));
        return blogsRepository.findAll(pageable);}

    public Blog findById(long id) throws NotFoundException{
        return blogsRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public void findByIdAndDelete(long id) throws  NotFoundException{
        Blog found = this.findById(id);
        blogsRepository.delete(found);

    }

    public Blog findByIdAndUpdate (long id , Blog body) throws  NotFoundException{
               Blog found = this.findById(id);
                found.setContenuto(body.getContenuto());
                found.setCategoria(body.getCategoria());
                found.setTitolo(body.getTitolo());
                found.setCover(body.getCover());
                found.setTempoDiLettura(body.getTempoDiLettura());
            return blogsRepository.save(found);
    }
}
