package AlaaElmeleh.U2W2D3.services;

//import AlaaElmeleh.U2W2D3.config.EmailSender;
import AlaaElmeleh.U2W2D3.entities.Author;

import AlaaElmeleh.U2W2D3.exceptions.BadRequestException;
import AlaaElmeleh.U2W2D3.exceptions.NotFoundException;
import AlaaElmeleh.U2W2D3.payloads.authors.NewAuthorDTO;
import AlaaElmeleh.U2W2D3.repositories.AuthorsRepository;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Service
public class AuthorService {
    @Autowired
    private AuthorsRepository authorsRepository;
//    @Autowired
//    EmailSender emailSender;
    @Autowired
    private Cloudinary cloudinary;


    public Author save(NewAuthorDTO body) throws IOException {
authorsRepository.findByEmail(body.email()).ifPresent(author -> {
    throw new BadRequestException("L'email"+author.getEmail()+"è già utilizzata!");
});
Author newAuthor = new Author();
newAuthor.setAvatar("http://ui-avatars.com/api/?name="+body.name() + "+" + body.surname());
       newAuthor.setNome(body.name());
       newAuthor.setCognome(body.surname());
       newAuthor.setEmail(body.email());
       newAuthor.setDataNascita(body.dataNascita());
       Author savedAuthor = authorsRepository.save(newAuthor);
//       emailSender.sendRegistrationEmail(body.email());
       return savedAuthor;
    }

    public Page<Author> getAuthors(int page , int size, String orderBy){
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));
        return authorsRepository.findAll(pageable);}

    public Author findById(long id) throws NotFoundException{
       return authorsRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public void findByIdAndDelete(long id) throws NotFoundException{
        Author found = this.findById(id);
        authorsRepository.delete(found);
    }


    public Author findByIdAndUpdate (long id , Author body) throws NotFoundException{
               Author found = this.findById(id);
                found.setNome(body.getNome());
                found.setCognome(body.getCognome());
                found.setEmail(body.getEmail());
                found.setDataNascita(body.getDataNascita());
                found.setAvatar(body.getAvatar());
            return authorsRepository.save(found);


    }
    public String uploadPicture(MultipartFile file) throws IOException {
        return (String) cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("url");
    }
}
