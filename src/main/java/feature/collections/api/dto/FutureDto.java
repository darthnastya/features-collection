package feature.collections.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FutureDto {

    private String id;

    private int timestamp;

    private int beginViewingDate;

    private int endViewingDate;

    private String missionName;
}
