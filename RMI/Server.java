import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server implements RemoteService {
    
    public Server() {
        super();
    }
    
    public String sayHello(String name) {
        return "Hello, " + name + "!";
    }
    
    public static void main(String[] args) {
        try {
            Server obj = new Server();
            RemoteService stub = (RemoteService) UnicastRemoteObject.exportObject(obj, 0);
            
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.bind("RemoteService", stub);
            
            System.out.println("Server ready!");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}

