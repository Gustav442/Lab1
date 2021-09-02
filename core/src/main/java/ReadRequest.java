import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URLDecoder;

import static java.nio.charset.StandardCharsets.UTF_8;

public class ReadRequest {


//    public static String readLine(BufferedInputStream inputStream) throws IOException {
//        final int MAX_READ = 4096;
//        byte[] buffer = new byte[MAX_READ];
//        int bytesRead = 0;
//
//        while (bytesRead < MAX_READ) {
//            buffer[bytesRead++] = (byte) inputStream.read();
//            if (buffer[bytesRead - 1] == '\r') {
//                buffer[bytesRead++] = (byte) inputStream.read();
//                if( buffer[bytesRead - 1] == '\n')
//                    break;
//            }
//        }
    //       return new String(buffer,0,bytesRead-2, StandardCharsets.UTF_8);
    //   }

    public static Request parseHttpRequest(BufferedInputStream inputFromClient) throws IOException {
        var request = new Request();

        int count = 0;
        StringBuilder result = new StringBuilder();
        do {
            result.append((char) inputFromClient.read());
            count++;
        } while (inputFromClient.available() > 0);


        request.type = parseHttpRequestType(result.toString());
        request.url = parseUrl(result.toString(), request);

        if (request.type.equals(HTTPType.POST))
            request.body = getBody(result.toString());


            return request;
    }




    private static String getBody(String result){
        String result1 = result.toString();
        var body = result1.split("\r\n\r\n" );
        return body[1];
    }




    private static String parseUrl(String input, Request request) {


        String[] result = input.split(" ");


        String[] url = result[1].split("\\?");

        if (url.length > 1) {

            String[] params = url[1].split("&");
            for (String u : params) {
                String[] values = u.split("=");
                request.urlParams.put(URLDecoder.decode(values[0], UTF_8), URLDecoder.decode(values[1], UTF_8));
            }


        }

        return URLDecoder.decode(url[0], UTF_8);
    }

    private static HTTPType parseHttpRequestType(String input) {
        if (input.startsWith("G"))
            return HTTPType.GET;
        else if (input.startsWith("H"))
            return HTTPType.HEAD;
        else if (input.startsWith("PO"))
            return HTTPType.POST;

        throw new RuntimeException("Invalid type");
    }



}
