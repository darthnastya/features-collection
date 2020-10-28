package feature.collections.data;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import feature.collections.data.models.Acquisition;
import feature.collections.data.models.Feature;
import feature.collections.data.models.FeatureCollection;
import feature.collections.data.models.FeatureFace;
import feature.collections.data.models.Properties;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.List;
import java.util.Map;


public class FeatureReader {

    public void readToMaps(String filePath, Map<String, FeatureFace> featureFaceMap, Map<String, byte[]> imagesMap)
            throws IOException {
        ObjectMapper mapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try (InputStream in = getClass().getClassLoader().getResourceAsStream(filePath)) {

            CollectionType SOURCE_TYPE = mapper.getTypeFactory()
                    .constructCollectionType(List.class, FeatureCollection.class);
            List<FeatureCollection> featureCollections = mapper.readValue(in, SOURCE_TYPE);
            for (FeatureCollection featureCollection : featureCollections) {
                for (Feature feature : featureCollection.getFeatures()) {
                    Properties properties = feature.getProperties();
                    Acquisition acquisition = properties.getAcquisition();

                    FeatureFace featureFace = FeatureFace.builder()
                            .id(properties.getId())
                            .beginViewingDate(acquisition.getBeginViewingDate())
                            .endViewingDate(acquisition.getEndViewingDate())
                            .missionName(acquisition.getMissionName())
                            .timestamp(properties.getTimestamp())
                            .build();

                    featureFaceMap.put(featureFace.getId(), featureFace);

                    if (properties.getQuicklook() != null) {
                        imagesMap.put(featureFace.getId(), Base64
                                .getDecoder()
                                .decode(properties.getQuicklook()));
                    }
                }
            }

        }
    }
}
