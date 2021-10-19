package br.bkraujo.engine;

public class EngineException extends RuntimeException {
    private String code;

    public EngineException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public EngineException(String code, String message) {
        this(code, message, null);
    }

    public EngineException(String message) {
        this(null, message);
    }
}
