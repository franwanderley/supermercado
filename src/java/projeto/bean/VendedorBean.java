/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto.bean;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import projeto.modelo.Vendedor;
import java.util.List;
import projeto.dao.DAO;
/**
 *
 * @author wanderley, savio,kaynan,atyla
 */
@ManagedBean
@ViewScoped
public class VendedorBean implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private Vendedor vendedor = new Vendedor();
    private Integer vendedorId;

    public void setVendedorId(Integer vendedorId) {
        this.vendedorId = vendedorId;
    }

    public void gravar() {
             if (vendedor.getId()== null)
                new DAO<>(Vendedor.class).adiciona(vendedor);
            else
                new DAO<>(Vendedor.class).atualiza(vendedor);
 	    this.vendedor = new Vendedor();
	}

	public void carregar(Vendedor vendedor) {
		System.out.println("Carregando vendedor");
		this.vendedor = vendedor;
	}

	public void remover(Vendedor vendedor) {
		System.out.println("Removendo vendedor");
               new DAO<>(Vendedor.class).remove(vendedor);
	}
        
        public Vendedor buscarVendedor(){
            return new DAO<>(Vendedor.class).buscaPorId(vendedorId);
        }
        
        public List<Vendedor> getVendedores(){
            return new DAO<>(Vendedor.class).listaTodos();
        }

        public Vendedor getVendedor() {
            return vendedor;
        }

        public Integer getVendedorId() {
            return vendedorId;
        }

}
