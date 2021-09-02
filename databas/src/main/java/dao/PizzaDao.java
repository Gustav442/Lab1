package dao;

import entity.Pizza;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.List;


public class PizzaDao {

    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU");







    public static List<Pizza> getAllPizzas() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Pizza> query = em.createNamedQuery("Pizza.findAll", Pizza.class);
        List<Pizza> pizzas= null;
                pizzas = query.getResultList();

        em.close();
        System.out.println(pizzas);
        return pizzas;

    }


    public void addPizza(Pizza pizza) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(pizza);
        em.getTransaction().commit();
        em.close();

    }


}
