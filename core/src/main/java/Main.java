import dao.PizzaDao;
import entity.Pizza;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {

        PizzaDao pizzaDao = new PizzaDao();
        pizzaDao.addPizza(new Pizza("Margarita", "Ost, Tomatsås", 80));

        ExecutorService executorService = Executors.newCachedThreadPool();

        try (ServerSocket serverSocket = new ServerSocket(80)) {
            while (true) {
                Socket client = serverSocket.accept();
                executorService.submit(() -> HandleConnection.handleConnection(client));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
