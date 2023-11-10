package AlaaElmeleh.U2W2D3.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;


import java.util.Date;

@Getter
@Setter
@ToString
@Entity
@AllArgsConstructor
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String categoria;
    private String titolo;
    private String cover;
    private String contenuto;
    private double tempoDiLettura;
    @CreationTimestamp
    private Date createdAt;
    @ManyToOne
    @JoinColumn(name = "author_id" , nullable = false)
    private Author author;

    public Blog() {

    }
}
