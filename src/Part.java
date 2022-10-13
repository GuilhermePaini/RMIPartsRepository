import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Part implements IPart, Serializable {

    private static final long serialVersionUID = -6952866792930229021L;
    private UUID id;
    private String name;
    private final String description;
    private HashMap<IPart, Integer> subParts;

    public Part(String name, String description, HashMap<IPart, Integer> subParts) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.description = description;
        this.subParts = subParts;
    }

    @Override
    public UUID getId() { return this.id; }
    @Override
    public String getName() { return this.name; }
    @Override
    public String getDescription() { return this.description; }
    @Override
    public Map<IPart, Integer> getSubParts() { return this.subParts; }
    @Override
    public boolean addSubPart(IPart part, Integer quantity) {
        try {
            this.subParts.put(part, quantity);
        } catch (Exception ex) {
            return false;
        }
        return true;
    }
    @Override
    public void clearSubParts() { this.subParts = new HashMap<>(); }
}
