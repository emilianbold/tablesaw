package tech.tablesaw.plotly.components;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Preconditions;
import java.util.Objects;

public class Font extends Component {

  /**
   * HTML font family - the typeface that will be applied by the web browser. The web browser will
   * only be able to apply a font if it is available on the system which it operates. Provide
   * multiple font families, separated by commas, to indicate the preference in which to apply fonts
   * if they aren't available on the system. The plotly service (at https://plot.ly or on-premise)
   * generates images on a server, where only a select number of fonts are installed and supported.
   * These include "Arial", "Balto", "Courier New", "Droid Sans",, "Droid Serif", "Droid Sans Mono",
   * "Gravitas One", "Old Standard TT", "Open Sans", "Overpass", "PT Sans Narrow", "Raleway", "Times
   * New Roman".
   */
  public enum Family {
    OPEN_SANS("Open Sans"),
    VERDANA("verdana"),
    ARIAL("arial"),
    SANS_SERIF("sans-serif");

    private final String value;

    Family(String value) {
      this.value = value;
    }

    @Override
    public String toString() {
      return value;
    }
  }

  private final Family fontFamily;

  private final int size; // number greater than or equal to 1

  private final String color;

  private Font(FontBuilder builder) {
    this.color = builder.color;
    this.fontFamily = builder.fontFamily;
    this.size = builder.size;
  }

  public static FontBuilder builder() {
    return new FontBuilder();
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

  @JsonPropertyOrder({"family", "size", "color"})
  static class Bean {

    private final Font font;

    Bean(Font f) {
      this.font = f;
    }

    static Bean of(Font f) {
      return f == null ? null : new Bean(f);
    }

    public String getFamily() {
      return String.valueOf(font.fontFamily);
    }

    public int getSize() {
      return font.size;
    }

    public String getColor() {
      return font.color;
    }
  }

  public static class FontBuilder {

    private Family fontFamily = Family.OPEN_SANS;

    private int size = 12; // number greater than or equal to 1

    private String color = "#444";

    private FontBuilder() {}

    public FontBuilder size(int size) {
      Preconditions.checkArgument(size >= 1);
      this.size = size;
      return this;
    }

    public FontBuilder color(String color) {
      this.color = color;
      return this;
    }

    public FontBuilder family(Family family) {
      this.fontFamily = family;
      return this;
    }

    public Font build() {
      return new Font(this);
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Font font = (Font) o;
    return size == font.size && fontFamily == font.fontFamily && Objects.equals(color, font.color);
  }

  @Override
  public int hashCode() {

    return Objects.hash(fontFamily, size, color);
  }
}
