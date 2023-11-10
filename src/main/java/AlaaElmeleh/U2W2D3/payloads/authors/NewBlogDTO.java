package AlaaElmeleh.U2W2D3.payloads.authors;

import jakarta.validation.constraints.NotEmpty;

public record NewBlogDTO(@NotEmpty(message = "categoria non valida")
                         String categoria,
                         @NotEmpty(message = "titolo non valido")
                         String titolo,
                         @NotEmpty(message = "Contenuto non valido")
                         String contenuto,
                         @NotEmpty(message = "tempo di lettura non valido")
                         int tempoDiLettura,
                         @NotEmpty(message = "Author id non valido")
                         long autore_id) {

}
