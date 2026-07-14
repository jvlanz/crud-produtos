import java.util.List;

import dao.ProdutoDao;
import modelos.Produto;

public class Main {
    public static void main(String[] args) {
        Produto p1 = new Produto("Picanha", 89.99);

        ProdutoDao dao = new ProdutoDao();

        Produto retorno = dao.salvar(p1);
        System.out.println(retorno.getId() + " " + retorno.getDescricao());

        /*
         * Produto p = dao.consultar(3);
         * p.setDescricao("Alcatra");
         * p.setPreco(49.99);
         * dao.alterar(p);
         */

        /*
         * List<Produto> lista = dao.consultarTodos();
         * for (Produto p : lista) {
         * System.out.println(p.getId() + " " + p.getDescricao());
         * }
         */
    }
}
