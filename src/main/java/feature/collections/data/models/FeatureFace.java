package feature.collections.data.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FeatureFace {
    private String id;

    private String timestamp;

    private String beginViewingDate;

    private String endViewingDate;

    private String missionName;
}
