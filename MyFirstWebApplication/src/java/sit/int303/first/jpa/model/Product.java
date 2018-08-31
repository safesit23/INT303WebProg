/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sit.int303.first.jpa.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author INT303
 */
@Entity
@Table(name = "PRODUCT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p")
    , @NamedQuery(name = "Product.findByProductcode", query = "SELECT p FROM Product p WHERE p.productcode = :productcode")
    , @NamedQuery(name = "Product.findByProductname", query = "SELECT p FROM Product p WHERE p.productname = :productname")
    , @NamedQuery(name = "Product.findByProductscale", query = "SELECT p FROM Product p WHERE p.productscale = :productscale")
    , @NamedQuery(name = "Product.findByProductvendor", query = "SELECT p FROM Product p WHERE p.productvendor = :productvendor")
    , @NamedQuery(name = "Product.findByQuantityinstock", query = "SELECT p FROM Product p WHERE p.quantityinstock = :quantityinstock")
    , @NamedQuery(name = "Product.findByBuyprice", query = "SELECT p FROM Product p WHERE p.buyprice = :buyprice")
    , @NamedQuery(name = "Product.findByMsrp", query = "SELECT p FROM Product p WHERE p.msrp = :msrp")})
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "PRODUCTCODE")
    private String productcode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 70)
    @Column(name = "PRODUCTNAME")
    private String productname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "PRODUCTSCALE")
    private String productscale;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "PRODUCTVENDOR")
    private String productvendor;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "PRODUCTDESCRIPTION")
    private String productdescription;
    @Basic(optional = false)
    @NotNull
    @Column(name = "QUANTITYINSTOCK")
    private short quantityinstock;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "BUYPRICE")
    private BigDecimal buyprice;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MSRP")
    private BigDecimal msrp;
    @JoinColumn(name = "PRODUCTLINE", referencedColumnName = "PRODUCTLINE")
    @ManyToOne(optional = false)
    private ProductLine productline;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private List<OrderDetail> orderDetailList;

    public Product() {
    }

    public Product(String productcode) {
        this.productcode = productcode;
    }

    public Product(String productcode, String productname, String productscale, String productvendor, String productdescription, short quantityinstock, BigDecimal buyprice, BigDecimal msrp) {
        this.productcode = productcode;
        this.productname = productname;
        this.productscale = productscale;
        this.productvendor = productvendor;
        this.productdescription = productdescription;
        this.quantityinstock = quantityinstock;
        this.buyprice = buyprice;
        this.msrp = msrp;
    }

    public String getProductcode() {
        return productcode;
    }

    public void setProductcode(String productcode) {
        this.productcode = productcode;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getProductscale() {
        return productscale;
    }

    public void setProductscale(String productscale) {
        this.productscale = productscale;
    }

    public String getProductvendor() {
        return productvendor;
    }

    public void setProductvendor(String productvendor) {
        this.productvendor = productvendor;
    }

    public String getProductdescription() {
        return productdescription;
    }

    public void setProductdescription(String productdescription) {
        this.productdescription = productdescription;
    }

    public short getQuantityinstock() {
        return quantityinstock;
    }

    public void setQuantityinstock(short quantityinstock) {
        this.quantityinstock = quantityinstock;
    }

    public BigDecimal getBuyprice() {
        return buyprice;
    }

    public void setBuyprice(BigDecimal buyprice) {
        this.buyprice = buyprice;
    }

    public BigDecimal getMsrp() {
        return msrp;
    }

    public void setMsrp(BigDecimal msrp) {
        this.msrp = msrp;
    }

    public ProductLine getProductline() {
        return productline;
    }

    public void setProductline(ProductLine productline) {
        this.productline = productline;
    }

    @XmlTransient
    public List<OrderDetail> getOrderDetailList() {
        return orderDetailList;
    }

    public void setOrderDetailList(List<OrderDetail> orderDetailList) {
        this.orderDetailList = orderDetailList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (productcode != null ? productcode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Product)) {
            return false;
        }
        Product other = (Product) object;
        if ((this.productcode == null && other.productcode != null) || (this.productcode != null && !this.productcode.equals(other.productcode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sit.int303.first.jpa.model.Product[ productcode=" + productcode + " ]";
    }
    
}
