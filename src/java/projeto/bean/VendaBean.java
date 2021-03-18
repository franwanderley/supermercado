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
import projeto.modelo.Cliente;
import projeto.modelo.Produto;
import projeto.modelo.Venda;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import projeto.dao.DAO;
@ManagedBean
@ViewScoped
public class VendaBean implements Serializable{
    private Venda venda = new Venda();
    private Integer produtoId;
    
    public Venda getVenda() {
        return venda;
    }

    public Integer getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Integer produtoId) {
        this.produtoId = produtoId;
    }

    public void gravar() {
             if (venda.getId() == null){
                 new DAO<>(Venda.class).adiciona(venda);
                
             }
             else{
                 new DAO<>(Venda.class).atualiza(venda);
             }
 	    this.venda = new Venda();
    }
    
    public void gravarProduto() {

                Produto produto = new DAO<>(Produto.class).buscaPorId(this.produtoId);
		venda.setTotal(produto.getPreco());
                this.venda.adicionaProduto(produto);
	}
    
    public void carregar(Venda venda){
        System.out.println("Carregando venda");
        this.venda = venda;
    }
    
    public List<Venda> getVendas() {
        return new DAO<>(Venda.class).listaTodos();
    }
    
    public List<Vendedor> getVendedores(){
        return new DAO<>(Vendedor.class).listaTodos();
    }
    
    public void remover(Venda venda) {
		System.out.println("Removendo venda");
               new DAO<>(Venda.class).remove(venda);
    }
    
    public void removerProdutoDaVenda(Produto produto) {
		this.venda.removeProduto(produto);
    }

    public List<Produto> getProdutos() {
        return new DAO<>(Produto.class).listaTodos();
    }
        
    public List<Cliente> getClientes() {
        return new DAO<>(Cliente.class).listaTodos();
    }
    
    public List<Produto> getProdutosDaVenda(){
        return venda.getProdutos();
    }
    
    public Cliente buscarCliente(Integer id){
        return new DAO<>(Cliente.class).buscaPorId(id);
    }
    
    public Vendedor buscarVendedor(Integer id){
        return new DAO<>(Vendedor.class).buscaPorId(id);
    }
    
//Metodos para converter de relacional para poo
@FacesConverter(forClass = Cliente.class)
    public static class ClienteControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            VendaBean controller = new VendaBean();
            return controller.buscarCliente(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Cliente) {
                Cliente o = (Cliente) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Cliente.class.getName());
            }
        }
    }
    
    @FacesConverter(forClass = Vendedor.class)
    public static class VendedorControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            VendaBean controller = new VendaBean();
            return controller.buscarVendedor(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Vendedor) {
                Vendedor o = (Vendedor) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Vendedor.class.getName());
            }
        }

    }

}
