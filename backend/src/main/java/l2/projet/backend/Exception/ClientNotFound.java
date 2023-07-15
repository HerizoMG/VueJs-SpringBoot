package l2.projet.backend.Exception;

public class ClientNotFound extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public ClientNotFound(String msg) {
        super(msg);
    }
}
