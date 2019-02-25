package dbfacades;

import entity.Cus_order;
import entity.Customer;
import entity.ItemType;
import entity.OrderLine;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;


public class Facade {

    EntityManagerFactory emf;

    public Facade(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");

    }

    public Customer findCustomerByEmail(String email) {
        EntityManager em = emf.createEntityManager();

        Query q = em.createQuery("SELECT c FROM Customer c WHERE c.email = :email");
        q.setParameter("email", email);
        Customer c = (Customer) q.getSingleResult();
        return c;
    }

    public List<Customer> findCustomerByName(String name) {
        EntityManager em = emf.createEntityManager();

        Query q = em.createQuery("SELECT c FROM Customer c WHERE c.name = :name");
        q.setParameter("name", name);
        List<Customer> customers = q.getResultList();
        return customers;
    }

    public Customer findCustomerByPrimaryKey(int id) {
        EntityManager em = emf.createEntityManager();

        Customer c = em.find(Customer.class, id);
        return c;
    }

    public Customer createCustomer(String name, String email) {
        EntityManager em = emf.createEntityManager();
        Customer c = new Customer(name, email);
        try {
            em.getTransaction().begin();
            em.persist(c);
            em.getTransaction().commit();
            return c;
        } finally {
            em.close();
        }
    }

    public Cus_order createOrder() {
        EntityManager em = emf.createEntityManager();
        Cus_order o = new Cus_order();
        try {
            em.getTransaction().begin();
            em.persist(o);
            em.getTransaction().commit();
            return o;
        } finally {
            em.close();
        }
    }

    public List<Customer> getAllCustomers() {
        EntityManager em = emf.createEntityManager();

        Query q = em.createQuery("SELECT c FROM Customer c");
        List<Customer> allCustomers = q.getResultList();
        return allCustomers;
    }

    public Long countAllOrders() {
        EntityManager em = emf.createEntityManager();

        Query q = em.createQuery("SELECT count(o) FROM C_Order o");
        Long orderCount = (Long) q.getSingleResult();
        return orderCount;
    }

    public Customer addOrderToCustomer(Cus_order order, Customer c) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
           // c.addOrder(order);
            em.merge(c);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return c;
    }

    public Cus_order findOrderById(int id) {
        EntityManager em = emf.createEntityManager();

        Cus_order o = em.find(Cus_order.class, id);
        return o;
    }

    public List<Cus_order> ordersForCustomer(Customer c) {
        EntityManager em = emf.createEntityManager();

        Query q = em.createQuery("SELECT o FROM C_Order o WHERE o.customer.id = :customerid");
        q.setParameter("customerid", c.getId());
        List<Cus_order> orders = q.getResultList();

        return orders;
    }

    public Cus_order createOrderLine(Cus_order o, OrderLine oLine) {
        EntityManager em = emf.createEntityManager();
        
        try {
            em.getTransaction().begin();
            o.addOrderLine(oLine);
            em.merge(o);
            em.getTransaction().commit();
            return o;
        } finally {
            em.close();
        }
    }
    
    public ItemType createItemType(OrderLine oLine, ItemType item) {
        EntityManager em = emf.createEntityManager();
//        Customer c1 = em.find(Customer.class, 1);
        
        try {
            em.getTransaction().begin();
            oLine.setItemtype(item);
            item.addOrderLine(oLine);
            em.merge(oLine);
            em.getTransaction().commit();
            return oLine.getItemtype();
        } finally {
            em.close();
        }
    }

}
