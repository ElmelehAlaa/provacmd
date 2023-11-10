package AlaaElmeleh.U2W2D3.exceptions;

public class NotFoundException extends RuntimeException{

    public NotFoundException(double id){
        super("Elemento con id " + id + " non trovato!");
    }
}