package dao;

import entity.*;
import java.util.List;

public class PizzaFunctions {
    static PizzaDao pizzaDao = new PizzaDao();


    public static List<Pizza> getAllPizzas() {
        return pizzaDao.getAllPizzas();
    }


    public static void addPizza(Pizza pizza) {
        pizzaDao.addPizza(pizza);
    }
}