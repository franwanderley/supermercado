	/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto.bean;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import projeto.modelo.Cliente;
import java.util.List;
import projeto.dao.DAO;
/**
 *
 * @author wanderley, savio,kaynan,atyla
 */
@ManagedBean
@ViewScoped

public class ClienteBean implements Serializable{
    private Cliente cliente = new Cliente();
    private Integer ClienteId;

    public void setClienteId(Integer ClienteId) {
        this.ClienteId = ClienteId;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Integer getClienteId() {
        return ClienteId;
    }

    public List<Cliente> getClientes(){
        return new DAO<Cliente>(Cliente.class).listaTodos();
    }
    
    public void gravar(){
        if(cliente.getId()== null)
            new DAO<>(Cliente.class).adiciona(cliente);
        else
            new DAO<>(Cliente.class).atualiza(cliente);
        cliente = new Cliente();
    }
    public void carregar(Cliente cliente){
        this.cliente = cliente;
    }
    
    public Cliente buscarCliente(){
        return new DAO<>(Cliente.class).buscaPorId(ClienteId);
    }
    
    public void remover(Cliente cliente){
        new DAO<>(Cliente.class).remove(cliente);
    }

}
