import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;

@WebService
public class MyWebService {

    @WebMethod
    public String sayHello(String name) {
        return "Hello, " + name + "!";
    }

    public static void main(String[] args) {
        String url = "http://localhost:8080/mywebservice";
        Endpoint.publish(url, new MyWebService());
        System.out.println("Web service is running at " + url);
    }
}

