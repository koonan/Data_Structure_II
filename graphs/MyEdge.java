package eg.edu.alexu.csd.filestructure.graphs;

public class MyEdge<f,s, w> {
  private f from;

  private s to;
  private w weight;

  public MyEdge(f from ,s to, w weight) {
    this.from=from;
    this.to = to;
    this.weight = weight;

  }
  public f getFrom() {
    return this.from;
  }

  public s getTo() {
    return this.to;
  }

  public w getWeight() {
    return this.weight;
  }
  public void setFrom(f value) {
    this.from = value;
  }
  public void setTo(s value) {
    this.to = value;
  }

  public void setWeight(w value) {
    this.weight = value;
  }

}
