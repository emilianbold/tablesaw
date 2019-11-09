package tech.tablesaw.components;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import tech.tablesaw.plotly.components.Font;
import tech.tablesaw.plotly.components.HoverLabel;

public class HoverLabelTest {

  @Test
  public void asJavascript() {
    HoverLabel x =
        HoverLabel.builder()
            .nameLength(10)
            .bgColor("blue")
            .borderColor("green")
            .font(Font.builder().family(Font.Family.ARIAL).size(8).color("red").build())
            .build();

    assertEquals(
        "{\n"
            + "  \"namelength\" : 10,\n"
            + "  \"font\" : {\n"
            + "    \"family\" : \"arial\",\n"
            + "    \"size\" : 8,\n"
            + "    \"color\" : \"red\"\n"
            + "  },\n"
            + "  \"bgcolor\" : \"blue\",\n"
            + "  \"bordercolor\" : \"green\"\n"
            + "}",
        x.asJavascript());
  }
}
