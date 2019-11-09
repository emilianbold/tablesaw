package tech.tablesaw.plotly.components.threeD;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import tech.tablesaw.plotly.components.Component;

public class Camera extends Component {

  private final Center center;
  private final Up up;
  private final Eye eye;

  private Camera(CameraBuilder builder) {
    this.eye = builder.eye;
    this.up = builder.up;
    this.center = builder.center;
  }

  @JsonPropertyOrder({"eye", "center", "up"})
  static class Bean {

    private final Camera camera;

    Bean(Camera target) {
      this.camera = target;
    }

    @JsonInclude(Include.NON_NULL)
    public Object getUp() {
      return CameraComponent.Bean.of(camera.up);
    }

    @JsonInclude(Include.NON_NULL)
    public Object getEye() {
      return CameraComponent.Bean.of(camera.eye);
    }

    @JsonInclude(Include.NON_NULL)
    public Object getCenter() {
      return CameraComponent.Bean.of(camera.center);
    }
  }

  @Override
  public String asJavascript() {
    try {
      ObjectMapper mapper = new ObjectMapper();
      String js = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(new Bean(this));

      return js;
    } catch (JsonProcessingException ex) {
      throw new RuntimeException(ex);
    }
  }

  public static CameraBuilder cameraBuilder() {
    return new CameraBuilder();
  }

  public static class CameraBuilder {

    private Center center;
    private Up up;
    private Eye eye;

    public CameraBuilder xAxis(Center center) {
      this.center = center;
      return this;
    }

    public CameraBuilder yAxis(Up up) {
      this.up = up;
      return this;
    }

    public CameraBuilder zAxis(Eye eye) {
      this.eye = eye;
      return this;
    }

    public Camera build() {
      return new Camera(this);
    }
  }
}
