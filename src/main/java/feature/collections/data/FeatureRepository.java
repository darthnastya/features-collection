package feature.collections.data;

import feature.collections.data.models.FeatureFace;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class FeatureRepository {

    private final Map<String, FeatureFace> FEATURE_MAP = new HashMap<>();

    private final Map<String, byte[]> QUICKLOOK_MAP = new HashMap<>();


    public FeatureRepository() {
        FeatureReader reader = new FeatureReader();
        try {
            reader.readToMaps("source-data.json", FEATURE_MAP, QUICKLOOK_MAP);
        } catch (IOException e) {
            log.error("Can't read file");
        }
    }

    public FeatureFace getById(String id) {
        return FEATURE_MAP.get(id);
    }

    public List<FeatureFace> getAll() {
        return new ArrayList<>(FEATURE_MAP.values());
    }

    public byte[] getQuicklookById(String id) {
        return QUICKLOOK_MAP.get(id);
    }
}
