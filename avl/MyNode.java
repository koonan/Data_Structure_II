package eg.edu.alexu.csd.filestructure.avl;

import eg.edu.alexu.csd.filestructure.avl.INode;

/**
 * The Class MyNode.
 *
 * @param <T>
 *          the generic type
 */
public class MyNode<T extends Comparable<T>> implements INode<T> {

  /** The left child. */
  private MyNode<T> leftChild = null;

  /** The right child. */
  private MyNode<T> rightChild = null;

  /** The height. */
  private int height = 0;

  /** The value. */
  private T value;

  /**
   * Instantiates a new my node.
   *
   * @param left
   *          the left
   * @param right
   *          the right
   */
  public MyNode(MyNode<T> left, MyNode<T> right) {
    this.leftChild = left;
    this.rightChild = right;
    this.height = 1;
  }

  /**
   * Sets the height.
   *
   * @param height
   *          the new height
   */
  public void setHeight(int height) {
    this.height = height;

  }

  /**
   * Sets the value.
   *
   * @param value
   *          the new value
   */
  @Override
  public void setValue(T value) {
    this.value = value;

  }

  /**
   * Gets the value.
   *
   * @return the value
   */
  @Override
  public T getValue() {
    return this.value;
  }

  /**
   * sets the left child.
   *
   * @param left
   *          the new left child
   */
  public void setLeftChild(MyNode<T> left) {
    this.leftChild = left;
  }

  /**
   * sets the right child.
   *
   * @param right
   *          the new right child
   */
  public void setRightChild(MyNode<T> right) {
    this.rightChild = right;
  }

  /**
   * Gets the left child.
   *
   * @return the left child
   */
  @Override
  public MyNode<T> getLeftChild() {
    return this.leftChild;
  }

  /**
   * Gets the right child.
   *
   * @return the right child
   */
  @Override
  public MyNode<T> getRightChild() {
    return this.rightChild;
  }

  /**
   * Gets the balance factor.
   *
   * @return the balance factor
   */
  public int getBalanceFactor() {
    return this.leftChild.getHeight() - this.rightChild.getHeight();
  }

  /**
   * Gets the height.
   *
   * @return the height
   */
  public int getHeight() {
    return this.height;
  }

  /**
   * Checks if is leaf.
   *
   * @return true, if is leaf
   */
  public boolean isLeaf() {
    if (this.leftChild != null || this.rightChild != null) {
      return false;
    }
    return true;
  }

}
