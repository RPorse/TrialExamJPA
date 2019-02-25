package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class Cus_order implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderid;
    @ManyToOne
    private Customer customer;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id", referencedColumnName = "orderid")
    List<OrderLine> orderlines = new ArrayList();

    public Cus_order() {
    }

    public Cus_order(Customer customer) {
        this.customer = customer;
    }

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<OrderLine> getOrderlines() {
        return orderlines;
    }

    public void setOrderlines(List<OrderLine> orderlines) {
        this.orderlines = orderlines;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void addOrderLine(OrderLine ol) {
        orderlines.add(ol);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orderid != null ? orderid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cus_order)) {
            return false;
        }
        Cus_order other = (Cus_order) object;
        if ((this.orderid == null && other.orderid != null) || (this.orderid != null && !this.orderid.equals(other.orderid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Order[ id=" + orderid + " ]";
    }

}
