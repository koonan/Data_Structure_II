package eg.edu.alexu.csd.filestructure.hash;

public class Node <K,V>{

  /**
   * Key object.
   */
  public K key;
  /**
   * Data object.
   */
  public V value;

  /**
   * Constructor for an Entry object
   *
   * @param key
   *          key object reference
   * @param val
   *          data object reference
   */
  public Node(K key, V value) {
    this.key = key;
    this.value = value;
  }
}
