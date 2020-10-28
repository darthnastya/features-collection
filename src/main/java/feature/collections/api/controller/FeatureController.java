package feature.collections.api.controller;

import feature.collections.data.FeatureRepository;
import feature.collections.data.models.FeatureFace;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class FeatureController {

    @Autowired
    private FeatureRepository featuresRepository;

    @GetMapping("/features")
    public List<FeatureFace> getAllFeatures() {
        return featuresRepository.getAll();
    }

    @GetMapping("/feature/{id}")
    public FeatureFace getFeature(@PathVariable String id) {
        return featuresRepository.getById(id);
    }

    @GetMapping("/feature/{id}/quicklook")
    public void getFeatureQuicklook(@PathVariable String id, HttpServletResponse response) throws IOException {
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        IOUtils.copy(new ByteArrayInputStream(featuresRepository.getQuicklookById(id)), response.getOutputStream());
    }
}
