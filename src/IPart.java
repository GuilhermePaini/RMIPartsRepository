import java.util.Map;
import java.util.UUID;

public interface IPart {
    UUID getId();
    String getName();
    String getDescription();
    Map<IPart, Integer> getSubParts();
    boolean addSubPart(IPart part, Integer quantity);
    void clearSubParts();
}