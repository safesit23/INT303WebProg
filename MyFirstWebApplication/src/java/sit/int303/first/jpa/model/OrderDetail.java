/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sit.int303.first.jpa.model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author INT303
 */
@Entity
@Table(name = "ORDERDETAIL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrderDetail.findAll", query = "SELECT o FROM OrderDetail o")
    , @NamedQuery(name = "OrderDetail.findByOrdernumber", query = "SELECT o FROM OrderDetail o WHERE o.orderDetailPK.ordernumber = :ordernumber")
    , @NamedQuery(name = "OrderDetail.findByProductcode", query = "SELECT o FROM OrderDetail o WHERE o.orderDetailPK.productcode = :productcode")
    , @NamedQuery(name = "OrderDetail.findByQuantityordered", query = "SELECT o FROM OrderDetail o WHERE o.quantityordered = :quantityordered")
    , @NamedQuery(name = "OrderDetail.findByPriceeach", query = "SELECT o FROM OrderDetail o WHERE o.priceeach = :priceeach")
    , @NamedQuery(name = "OrderDetail.findByOrderlinenumber", query = "SELECT o FROM OrderDetail o WHERE o.orderlinenumber = :orderlinenumber")})
public class OrderDetail implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected OrderDetailPK orderDetailPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "QUANTITYORDERED")
    private int quantityordered;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRICEEACH")
    private BigDecimal priceeach;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ORDERLINENUMBER")
    private short orderlinenumber;
    @JoinColumn(name = "ORDERNUMBER", referencedColumnName = "ORDERNUMBER", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Orders orders;
    @JoinColumn(name = "PRODUCTCODE", referencedColumnName = "PRODUCTCODE", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Product product;

    public OrderDetail() {
    }

    public OrderDetail(OrderDetailPK orderDetailPK) {
        this.orderDetailPK = orderDetailPK;
    }

    public OrderDetail(OrderDetailPK orderDetailPK, int quantityordered, BigDecimal priceeach, short orderlinenumber) {
        this.orderDetailPK = orderDetailPK;
        this.quantityordered = quantityordered;
        this.priceeach = priceeach;
        this.orderlinenumber = orderlinenumber;
    }

    public OrderDetail(int ordernumber, String productcode) {
        this.orderDetailPK = new OrderDetailPK(ordernumber, productcode);
    }

    public OrderDetailPK getOrderDetailPK() {
        return orderDetailPK;
    }

    public void setOrderDetailPK(OrderDetailPK orderDetailPK) {
        this.orderDetailPK = orderDetailPK;
    }

    public int getQuantityordered() {
        return quantityordered;
    }

    public void setQuantityordered(int quantityordered) {
        this.quantityordered = quantityordered;
    }

    public BigDecimal getPriceeach() {
        return priceeach;
    }

    public void setPriceeach(BigDecimal priceeach) {
        this.priceeach = priceeach;
    }

    public short getOrderlinenumber() {
        return orderlinenumber;
    }

    public void setOrderlinenumber(short orderlinenumber) {
        this.orderlinenumber = orderlinenumber;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orderDetailPK != null ? orderDetailPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrderDetail)) {
            return false;
        }
        OrderDetail other = (OrderDetail) object;
        if ((this.orderDetailPK == null && other.orderDetailPK != null) || (this.orderDetailPK != null && !this.orderDetailPK.equals(other.orderDetailPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sit.int303.first.jpa.model.OrderDetail[ orderDetailPK=" + orderDetailPK + " ]";
    }
    
}
