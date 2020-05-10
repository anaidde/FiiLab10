import java.util.ArrayList;
import java.util.List;

public class Client {
    private String name;
    private List<Integer> roomCodes = new ArrayList();

    public Client(String name, List<Integer> roomCodes) {
        this.name = name;
        this.roomCodes = roomCodes;
    }

    public String getName() {
        return name;
    }

    public List<Integer> getRoomCodes() {
        return roomCodes;
    }
}
