import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class HandleConnection {

    static Response response = new Response();

    public static void handleConnection(Socket client) {
        try {
            var inputFromClient = new BufferedInputStream((client.getInputStream()));
            Request request = ReadRequest.parseHttpRequest(inputFromClient);
            HandleRequest.handleRequest(request, response);

            var outputToClient = client.getOutputStream();

            if (!(response.bild.length == 0)) {
                outputToClient.write(response.header.getBytes(StandardCharsets.UTF_8));
                outputToClient.write(response.bild);
            } else
                outputToClient.write((response.header + response.content).getBytes(StandardCharsets.UTF_8));

            inputFromClient.close();
            outputToClient.close();
            client.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void sendImageResponse(Response response, String responseUrl) throws IOException {


        byte[] data = new byte[0];

        File file = Path.of("core", "src", "main", "resources", responseUrl).toFile();
        if (!(file.exists() && !file.isDirectory())) {
            response.header = "HTTP/1.1 404 Not Found\r\nContent-length: 0\r\n\r\n";
            return;
        } else {

            try (FileInputStream fileInputStream = new FileInputStream(file)) {
                data = new byte[(int) file.length()];
                fileInputStream.read(data);
                var contentType = Files.probeContentType(file.toPath());

                response.header = "HTTP/1.1 200 OK\r\nContent-Type: " + contentType + "\r\nContent-length: " + data.length + "\r\n\r\n";
            } catch (IOException e) {
                e.printStackTrace();
            }

            response.bild = data;


        }
    }
}





