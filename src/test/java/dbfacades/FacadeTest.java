package dbfacades;

import entity.Cus_order;
import entity.Customer;
import entity.ItemType;
import entity.OrderLine;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class FacadeTest {

    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu", null);

    Facade facade = new Facade(emf);

    @BeforeClass
    public static void setUpClass() {
        EntityManager em = emf.createEntityManager();

        Customer c1 = new Customer("Rasmus", "gmail.com");
        Customer c2 = new Customer("Kirsten", "homail.com");
        ItemType i1 = new ItemType("Dårligdommerne", "En podcast om dårlige danske film.", 0);
        ItemType i2 = new ItemType("Hakkedrengene", "En podcast der hylder amerikanske 80'er og 90'er actionfilm.", 0);
        
        c1.addOrder(new Cus_order());
        try {
            em.getTransaction().begin();
            em.merge(c1);
            em.merge(c2);
            em.merge(i1);
            em.merge(i2);
            
            em.getTransaction().commit();

        } finally {
            em.close();
        }
    }

//      Add an Order to a Customer
//      Find an Order
//      Find all Orders, for a specific Customer
//      Create an OrderLine, and add it to an Order
//      Create an ItemType, and add it to an OrderLine 
//      Find the total price of an order ….
    @Test
    public void testCreateCustomer() {
        facade.createCustomer("Jens", "jens@gmail.com");
        Customer c = facade.findCustomerByEmail("jens@gmail.com");
        assertEquals("Jens", c.getName());
    }

    @Test
    public void testFindCustomerByName() {
        List<Customer> cs = facade.findCustomerByName("Rasmus");
        assertEquals("Rasmus", cs.get(0).getName());
    }

    @Test
    public void testFindCustomerByEmail() {
        Customer c = facade.findCustomerByEmail("gmail.com");
        assertEquals("Rasmus", c.getName());
    }

    @Test
    public void testFindCustomerByPrimaryKey() {
        Customer c1 = facade.findCustomerByPrimaryKey(1);
        Assert.assertEquals(1, (int) c1.getId());
    }

    @Test
    public void testGetAllCustomers() {
        List<Customer> allCustomers = facade.getAllCustomers();
        Assert.assertEquals(2, (int) allCustomers.size());
        for (Customer customer : allCustomers) {
            System.out.println(customer.getName());
        }
    }

//    @Test
//    public void testCreateOrder() {
//        
//    
//    }

}
