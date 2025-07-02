public class ParametrosInvalidosException extends Exception { // Estende Exception
    public ParametrosInvalidosException(String message) { // Definição correta do construtor
        super(message); // Chama o construtor da superclasse (Exception)
    }
}