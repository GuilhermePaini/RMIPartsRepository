import java.util.Map;
import java.util.UUID;

public interface IPart {
    public UUID getId();
    public String getName();
    public String getDescription();
    public Map<IPart, Integer> getSubParts();
    public boolean addSubPart(IPart part, Integer quantity);
}