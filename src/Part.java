import java.util.Map;
import java.util.UUID;

public class Part implements IPart {
    private UUID id;
    private String name;
    private String description;
    private Map<IPart, Integer> subParts;

    public Part(UUID id, String name, String description, Map<IPart, Integer> subParts) {
        this.id = id;
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
        return false;
    }
}
