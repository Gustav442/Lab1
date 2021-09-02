import com.google.gson.Gson;
import dao.PizzaFunctions;
import entity.Pizza;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;


public class HandleRequest {

    public static void handleRequest(Request request, Response response) throws IOException {
            switch (request.type) {
            case GET -> get(request, response);
            case HEAD -> head(request, response);
            case POST -> post(request, response);
        };

    }

    private static void post(Request request, Response response) {

        if (request.url.equals("/pizzas")) {
            Gson gson = new Gson();
            String json = request.body;
            var p = gson.fromJson(json, Pizza.class);
            PizzaFunctions.addPizza(p);
            response.content = json;
                response.header = ("HTTP/1.1 200 OK\r\nContent-length: " + response.content.getBytes(StandardCharsets.UTF_8).length +
                        "\r\nContent-Type: application/json\r\n\r\n");


        }else
            response.header = ("HTTP/1.1 404 Not Found\r\nContent-length: 0");



    }

    private static void head(Request request, Response response) {
        List<Pizza> pizzas = null;
        if (request.url.equals("/pizzas")) {
            pizzas = PizzaFunctions.getAllPizzas();
            Gson gson = new Gson();
            String pizza = gson.toJson(pizzas);
            response.content = pizza;
            response.header = ("HTTP/1.1 200 OK\r\nContent-length: " + response.content.getBytes(StandardCharsets.UTF_8).length +
                    "\r\nContent-Type: application/json\r\n\r\n");

response.content = "";
        }else
            response.header = ("HTTP/1.1 404 Not Found\r\nContent-length: 0");

    }

    private static void get(Request request, Response response) throws IOException {

        List<Pizza> pizzas = null;

        if (request.url.equals("/pizzas")) {
            pizzas = PizzaFunctions.getAllPizzas();
            Gson gson = new Gson();
            String pizza = gson.toJson(pizzas);
            response.content = pizza;
                response.header = ("HTTP/1.1 200 OK\r\nContent-length: " + response.content.getBytes(StandardCharsets.UTF_8).length +
                        "\r\nContent-Type: application/json\r\n\r\n");


        }
        else if(request.url.equals("/blomma.jpg"))
            HandleConnection.sendImageResponse(response, request.url);
        else if (request.url.equals("/train.png"))
            HandleConnection.sendImageResponse(response, request.url);
        else if(request.url.equals("/wuppertal.jpg"))
            HandleConnection.sendImageResponse(response, request.url);
        else if(request.url.equals("/index.html"))
            HandleConnection.sendImageResponse(response, request.url);
        else
            response.header = ("HTTP/1.1 404 Not Found\r\nContent-length: 0");

    }


}


