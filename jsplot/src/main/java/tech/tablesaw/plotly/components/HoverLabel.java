package tech.tablesaw.plotly.components;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class HoverLabel extends Component {

  /** Sets the background color of all hover labels on graph */
  private final String bgColor;

  /** Sets the border color of all hover labels on graph. */
  private final String borderColor;

  /** Sets the default hover label font used by all traces on the graph. */
  private final Font font;

  /**
   * Sets the default length (in number of characters) of the trace name in the hover labels for all
   * traces. -1 shows the whole name regardless of length. 0-3 shows the first 0-3 characters, and
   * an integer >3 will show the whole name if it is less than that many characters, but if it is
   * longer, will truncate to `namelength - 3` characters and add an ellipsis.
   */
  private final int nameLength;

  HoverLabel(HoverLabelBuilder builder) {
    this.bgColor = builder.bgColor;
    this.borderColor = builder.borderColor;
    this.font = builder.font;
    this.nameLength = builder.nameLength;
  }

  public static HoverLabelBuilder builder() {
    return new HoverLabelBuilder();
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

  public static class HoverLabelBuilder {

    /** Sets the background color of all hover labels on graph */
    private String bgColor = "";

    /** Sets the border color of all hover labels on graph. */
    private String borderColor = "";

    /** Sets the default hover label font used by all traces on the graph. */
    private Font font;

    /**
     * Sets the default length (in number of characters) of the trace name in the hover labels for
     * all traces. -1 shows the whole name regardless of length. 0-3 shows the first 0-3 characters,
     * and an integer >3 will show the whole name if it is less than that many characters, but if it
     * is longer, will truncate to `namelength - 3` characters and add an ellipsis.
     */
    private int nameLength = 15;

    public HoverLabel build() {
      return new HoverLabel(this);
    }

    public HoverLabelBuilder nameLength(int nameLength) {
      this.nameLength = nameLength;
      return this;
    }

    public HoverLabelBuilder font(Font font) {
      this.font = font;
      return this;
    }

    public HoverLabelBuilder borderColor(String borderColor) {
      this.borderColor = borderColor;
      return this;
    }

    public HoverLabelBuilder bgColor(String bgColor) {
      this.bgColor = bgColor;
      return this;
    }
  }

  @JsonPropertyOrder({"namelength", "font", "bgcolor", "bordercolor"})
  static class Bean {

    private final HoverLabel label;

    Bean(HoverLabel label) {
      this.label = label;
    }

    static Bean of(HoverLabel label) {
      return new Bean(label);
    }

    public int getNamelength() {
      return label.nameLength;
    }

    @JsonInclude(Include.NON_NULL)
    public Object getFont() {
      return Font.Bean.of(label.font);
    }

    public String getBgcolor() {
      return label.bgColor;
    }

    public String getBordercolor() {
      return label.borderColor;
    }
  }
}
