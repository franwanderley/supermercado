package projeto.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import projeto.modelo.Produto;
import java.util.List;
import projeto.dao.DAO;

@ManagedBean
@ViewScoped

public class ProdutoBean {
    private  Produto produto = new Produto();
    private Integer produtoId;

    public Produto getProduto() {
        return produto;
    }

    public Integer getProdutoId() {
        return produtoId;
    }
    
    public List<Produto> getProdutos(){
        return new DAO<>(Produto.class).listaTodos();
    }
    
    public void gravar() {
             if (null == this.produto.getId())
                new DAO<>(Produto.class).adiciona(produto);
            else
                new DAO<>(Produto.class).atualiza(produto);
 	    this.produto = new Produto();
            System.out.println("Gravou");
	}

	public void carregar(Produto produto) {
		System.out.println("Carregando produto");
		this.produto = produto;
	}

	public void remover(Produto produto) {
		System.out.println("Removendo produto");
                new DAO<>(Produto.class).remove(produto);
	}
}
