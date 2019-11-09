package tech.tablesaw.plotly.components.threeD;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import tech.tablesaw.plotly.components.Component;

class CameraComponent extends Component {

  private final double x;
  private final double y;
  private final double z;

  CameraComponent(double x, double y, double z) {
    this.x = x;
    this.y = y;
    this.z = z;
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

  @JsonPropertyOrder({"x", "y", "z"})
  static class Bean {

    private final CameraComponent source;

    Bean(CameraComponent c) {
      this.source = c;
    }

    static Bean of(CameraComponent c) {
      if (c == null) {
        return null;
      } else {
        return new Bean(c);
      }
    }

    public double getX() {
      return source.x;
    }

    public double getY() {
      return source.y;
    }

    public double getZ() {
      return source.z;
    }
  }
}
