import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
    
    private Client() {
        super();
    }
    
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            RemoteService stub = (RemoteService) registry.lookup("RemoteService");
            
            String response = stub.sayHello("John");
            System.out.println("Response: " + response);
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}

