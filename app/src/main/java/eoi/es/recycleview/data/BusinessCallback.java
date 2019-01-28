package eoi.es.recycleview.data;

/**
 * Interfaz para la comunicación de capa de negocio y vista
 *
 * @param <T> típo genérico que devuelve el callback
 */
public interface BusinessCallback<T> {
    /**
     * Se ejecuta si el callback devuelve correcto
     *
     * @param t objeto que devuelve el callback
     */
    void success(T t);

    /**
     * Se jecuta si el calback devuelve fallo
     *
     * @param error objeto con el error, puede ser el Exception o un mensaje por ejemplo
     */
    void failure(Object error);

}