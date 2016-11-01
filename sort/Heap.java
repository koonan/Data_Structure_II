/*
 * 
 */

package eg.edu.alexu.csd.filestructure.sort;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

/**
 * The Class Heap.
 *
 * @param <T>
 *          the generic type
 */
public class Heap<T extends Comparable<T>> implements IHeap<T> {

  /** The size. */
  private int size = 0;

  /** The last. */
  private int last = -1;

  /** The heap list. */
  private LinkedList<INode<T>> heapList = new LinkedList<INode<T>>();

  /** The right. */
  private INode<T> right;

  /** The left. */
  private INode<T> left;

  /** The parent. */
  private INode<T> parent;

  /** The new node. */
  private Heap<T>.Node newNode;

  /** The right val. */
  private T rightVal;

  /** The left val. */
  private T leftVal;

  /** The node val. */
  private T nodeVal;

  /** The temp val. */
  private T tempVal;

  /**
   * The Class Node.
   */
  // Inner class of Node into IHeap to have an access to other nodes //
  private class Node implements INode<T> {

    /** The index. */
    private int index;

    /** The value. */
    private T value;

    /**
     * Instantiates a new node.
     *
     * @param index
     *          the index
     */
    // Set Index as it was sent from the collection
    public Node(int newIndex) {
      this.index = newIndex;
    }

    /*
     * get left children if the node \
     */
    @Override
    public INode<T> getLeftChild() {
      if ((index * 2) + 1 < size) {
        return heapList.get((2 * index) + 1);
      }
      return null;
    }

    /*
     * ( get right children of the node
     */
    @Override
    public INode<T> getRightChild() {
      if ((index * 2) + 2 < size) {
        return heapList.get((2 * index) + 2);
      }
      return null;
    }

    /*
     * ( get parent of the node
     */
    @Override
    public INode<T> getParent() {
      if (index != 0 || size != 0) {
        return heapList.get((index - 1) / 2);
      }
      return null;
    }

    /*
     * get value of the node T
     */
    @Override
    public T getValue() {
      return this.value;
    }

    /*
     * Set the value of the node
     */
    @Override
    public void setValue(T newValue) {
      this.value = newValue;
    }

  }

  // Get the Roots of the Heap << it is always at the index 0 of the//
  // MaxHeapList//

  @Override
  public INode<T> getRoot() {
    if (this.size != 0) {
      return heapList.get(0);
    }
    return null;
  }

  // Get the size of this heap from the heapList //

  @Override
  public int size() {
    return this.size;
  }

  // Runs in O(log n) time, is the key to maintaining the max-heap property//
  // the reference to the tree to be heapified //

  @Override
  public void heapify(INode<T> node) {
    right = null;
    if (node.getRightChild() != null) {
      right = node.getRightChild();
    }

    left = node.getLeftChild();

    while ((right != null || left != null)) {
      nodeVal = (T) node.getValue();
      leftVal = (T) left.getValue();
      if (right != null) {
        rightVal = (T) right.getValue();
        if (nodeVal.compareTo(rightVal) >= 0 && nodeVal.compareTo(leftVal) >= 0) {
          break;
        }
        if (rightVal.compareTo(leftVal) >= 0) {
          tempVal = node.getValue();
          node.setValue(rightVal);
          right.setValue(tempVal);
          node = right;
        } else if (leftVal.compareTo(rightVal) >= 0) {
          tempVal = node.getValue();
          node.setValue(leftVal);
          left.setValue(tempVal);
          node = left;
        }
      } else {
        if (nodeVal.compareTo(leftVal) >= 0) {
          break;
        } else {
          tempVal = node.getValue();
          node.setValue(leftVal);
          left.setValue(tempVal);
        }
      }
      right = node.getRightChild();
      left = node.getLeftChild();
    }
  }

  // Extract the maximum element out of the heap, and remove it from the//
  // heap//

  // Run in O(log n) time //
  @Override
  public T extract() {
    if (this.size == 0) {
      throw new RuntimeException("Size is empty");
    }
    T tempVal;
    if (this.size == 1) {
      tempVal = heapList.get(0).getValue();
      heapList.remove(0);
      this.last--;
      this.size--;
      return tempVal;
    } else {
      INode<T> node = heapList.get(0);
      tempVal = heapList.get(0).getValue();
      INode<T> temp = heapList.get(last);
      node.setValue(temp.getValue());
      heapList.remove(last);
      this.last--;
      this.size--;
      heapify(heapList.get(0));
      return tempVal;
    }
  }

  // Insert Element to heap << Always Inserted at last Index then Call//
  // heapifyUp to check the MaxHeap procedure //
  @Override
  public void insert(T element) {
    if (element != null) {
      if (this.size == 0) {
        newNode = new Node(0);
        this.last++;
        this.size++;
        newNode.setValue(element);
        heapList.add(newNode);
      } else {
        this.last++;
        this.size++;
        newNode = new Node(last);
        newNode.setValue(element);
        heapList.add(newNode);
        newNode.setValue(element);
        heapifyUp(newNode);
      }
    }
  }

  // Build heap from any Collection //
  @Override
  public void build(Collection<T> unordered) {
    // size and last should be changed here
    this.size = unordered.size();
    this.last = unordered.size() - 1;
    int index = 0;
    for (T elem : unordered) {
      newNode = new Node(index++);
      newNode.setValue(elem);
      heapList.add(newNode);
    }

    // Starting build heap o INode from the parent of the last element
    for (int count = this.last; count > 0; count--) {
      parent = heapList.get(count).getParent();
      heapify(parent);

    }
    // Print for checking :"D//
    // for (int i = 0; i < size; i++)
    // System.out.print(heapList.get(i).getValue() + ",");
    // System.out.println();

  }

  /**
   * Heapify Up.
   *
   * @param node
   *          the node
   */
  // Check heap from down to up //
  public void heapifyUp(INode<T> node) {
    if (node.getParent() != null) {
      parent = node.getParent();
    } else {
      return;
    }

    while (node.getValue().compareTo(parent.getValue()) > 0) {
      tempVal = node.getValue();
      node.setValue(parent.getValue());
      parent.setValue(tempVal);
      node = node.getParent();
      // heapify(node);
      if (node.getParent() != null) {
        parent = node.getParent();
      } else {
        return;
      }

    }
  }

  /**
   * Sort.
   *
   * @param unordered
   *          the unordered
   */
  // Sorting by using heap :/ :/ //
  public void sort(ArrayList<T> unordered) {
    build(unordered);
    int num = this.size;
    for (int index = num - 1; index >= 0; index--) {
      tempVal = heapList.get(0).getValue();
      heapList.get(0).setValue(heapList.get(index).getValue());
      heapList.get(index).setValue(tempVal);
      this.size--;
      heapify(heapList.get(0));
    }
    this.size = num;
  }

}
