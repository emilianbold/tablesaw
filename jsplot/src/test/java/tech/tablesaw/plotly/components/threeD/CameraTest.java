package tech.tablesaw.plotly.components.threeD;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CameraTest {

  @Test
  public void testJS() {
    Camera c = Camera.cameraBuilder().yAxis(Up.DEFAULT).build();
    assertEquals(
        "{\n"
            + "  \"up\" : {\n"
            + "    \"x\" : 0.0,\n"
            + "    \"y\" : 0.0,\n"
            + "    \"z\" : 1.0\n"
            + "  }\n"
            + "}",
        c.asJavascript());
  }
}
