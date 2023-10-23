public class Play {

  public enum PlayType {
    TRAGEDY,
    COMEDY,
  }

  private String name;
  private PlayType type;

  public Play(String name, PlayType type) {
      this.name = name;
      this.type = type;
  }

  public String getName() {
      return name;
  }

  public PlayType getType() {
      return type;
  }
}
