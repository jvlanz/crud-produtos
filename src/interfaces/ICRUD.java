package interfaces;

import java.util.ArrayList;

public interface ICRUD<T, t> {
    T salvar(T obj);

    void deletar(t id);

    void alterar(T obj);

    T consultar(t id);

    ArrayList<T> consultarTodos();
}
