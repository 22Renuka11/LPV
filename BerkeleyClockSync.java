import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BerkeleyClockSync {
    private static final int NUM_CLIENTS = 5; // Number of clients participating in the synchronization
    private static final int MAX_CLOCK_OFFSET = 10; // Maximum random clock offset (in milliseconds)

    public static void main(String[] args) {
        // Create the clients
        List<Client> clients = new ArrayList<>();
        for (int i = 0; i < NUM_CLIENTS; i++) {
            clients.add(new Client("Client " + (i + 1)));
        }

        // Start the clock synchronization
        BerkeleyAlgorithm algorithm = new BerkeleyAlgorithm(clients);
        algorithm.synchronizeClocks();
    }

    // Client class representing a participant in the clock synchronization
    static class Client {
        private final String name;
        private long clockOffset; // Offset of the client's clock

        public Client(String name) {
            this.name = name;
            this.clockOffset = 0;
        }

        public String getName() {
            return name;
        }

        public long getClockOffset() {
            return clockOffset;
        }

        public void setClockOffset(long offset) {
            clockOffset = offset;
        }

        public void adjustClock(long adjustment) {
            clockOffset += adjustment;
        }

        public void printTime() {
            System.out.println(name + " - Current Time: " + (System.currentTimeMillis() + clockOffset) + "ms");
        }
    }

    // BerkeleyAlgorithm class for clock synchronization
    static class BerkeleyAlgorithm {
        private final List<Client> clients;

        public BerkeleyAlgorithm(List<Client> clients) {
            this.clients = clients;
        }

        public void synchronizeClocks() {
            // Randomly assign clock offsets to clients
            Random random = new Random();
            for (Client client : clients) {
                long offset = random.nextInt(2 * MAX_CLOCK_OFFSET) - MAX_CLOCK_OFFSET;
                client.setClockOffset(offset);
            }

            // Perform clock synchronization iterations
            for (int i = 0; i < 5; i++) {
                System.out.println("Iteration " + (i + 1));

                // Request current time from all clients
                for (Client client : clients) {
                    client.printTime();
                }

                // Calculate average clock offset
                long sumOffset = 0;
                for (Client client : clients) {
                    sumOffset += client.getClockOffset();
                }
                long avgOffset = sumOffset / clients.size();

                // Adjust clocks of all clients based on average offset
                for (Client client : clients) {
                    long adjustment = avgOffset - client.getClockOffset();
                    client.adjustClock(adjustment);
                }

                System.out.println("Clocks Adjusted");
                System.out.println("----------------");
            }

            // Print final clock values after synchronization
            System.out.println("\nFinal Clock Values");
            System.out.println("------------------");
            for (Client client : clients) {
                client.printTime();
            }
        }
    }
}

