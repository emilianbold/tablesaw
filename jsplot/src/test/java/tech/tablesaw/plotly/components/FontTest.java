package tech.tablesaw.plotly.components;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class FontTest {

  @Test
  public void testJS() {
    Font f = Font.builder().family(Font.Family.ARIAL).color("red").size(12).build();
    assertEquals(
        "{\n"
            + "  \"family\" : \"arial\",\n"
            + "  \"size\" : 12,\n"
            + "  \"color\" : \"red\"\n"
            + "}",
        f.asJavascript());
  }
}
