/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author wanderley
 */
@Entity
public class Venda implements Serializable {
     private static final long serialVersionUID = 1L;
    @GeneratedValue
    @Id
    private Integer id;
    private float total;
    @ManyToMany(fetch=FetchType.EAGER)
    private List<Produto> produtos = new ArrayList<Produto>();
    
    @OneToOne
    @JoinColumn(name = "vendedorcpf", referencedColumnName = "cpf")
    private Vendedor vendedor;
    
    @Temporal(TemporalType.DATE)
    private Date datav;
    
    @OneToOne
    @JoinColumn(name = "clientecpf", referencedColumnName = "cpf")
    private Cliente cliente;

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total+= total;
    }

    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
   

    public List<Produto> getProdutos() {
        return produtos;
    }
    
    
    public Date getDatav() {
        return datav;
    }

    public void setDatav(Date datav) {
        this.datav = datav;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    public Venda() {total = 0;}
    
    public void limpaProdutos(){
        produtos.clear();
    }
    
        @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cliente)) {
            return false;
        }
        Venda other = (Venda) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    public void adicionaProduto(Produto produto) {
        produtos.add(produto);
    }

    public void removeProduto(Produto produto) {
        produtos.remove(produto);
    }
    
}
