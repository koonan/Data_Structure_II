package eg.edu.alexu.csd.filestructure.avl;

import eg.edu.alexu.csd.filestructure.avl.IAVLTree;
import eg.edu.alexu.csd.filestructure.avl.INode;

/**
 * The Class MyAVL.
 *
 * @param <T>
 *          the generic type
 */
public class MyAVL<T extends Comparable<T>> implements IAVLTree<T> {

  /** The node. */
  private MyNode<T> node;

  /** The root. */
  private MyNode<T> root = null;

  /** The Constant ALLOWED_IMBALANCE. */
  private static final int ALLOWED_IMBALANCE = 1;

  /** The compare result. */
  private int compareResult;

  /** The search node. */
  private MyNode<T> searchNode;

  /** The new node. */
  private INode<T> newNode;

  /** The size. */
  private int size = 0;

  /**
   * Return the root of your AVL tree.
   * 
   * @return root of the AVL tree.
   */
  @Override
  public INode<T> getTree() {
    if (root != null) {
      newNode = new MyNode<T>(root.getLeftChild(), root.getRightChild());
      newNode.setValue(root.getValue());
      return newNode;
    }
    return null;
  }

  /**
   * Return the height of the AVL tree. This is the longest path from the root
   * to a leaf-node
   * 
   * @return tree height
   */
  @Override
  public int height() {
    if (root != null) {
      return Math.max(getNodeHeight(root.getRightChild()),
              getNodeHeight(root.getLeftChild())) + 1;
    }
    return 0;
  }

  /**
   * Gets the node height.
   *
   * @param node
   *          the node
   * @return the node height
   */
  public int getNodeHeight(MyNode<T> node) {
    return node == null ? 0 : node.getHeight();
  }

  /**
   * Insert the given value using the key.
   *
   * @param key
   *          the value to be inserted in the tree
   */
  @Override
  public void insert(T key) {
    node = new MyNode<T>(null, null);
    if (key != null) {
      node.setValue(key);
      root = insert(node, root);
      size++;
    }

  }

  /**
   * Insert.
   *
   * @param node
   *          the node
   * @param rootTree
   *          the root tree
   * @return the my node
   */
  public MyNode<T> insert(MyNode<T> node, MyNode<T> rootTree) {
    if (rootTree != null) {

      compareResult = node.getValue().compareTo(rootTree.getValue());
      if (compareResult >= 0) {
        rootTree.setRightChild(insert(node, rootTree.getRightChild()));

      } else if (compareResult < 0) {
        rootTree.setLeftChild(insert(node, rootTree.getLeftChild()));
      }
      return balance(rootTree);
    }
    return node;

  }

  /**
   * Balance.
   *
   * @param rootTree
   *          the root tree
   * @return the my node
   */
  public MyNode<T> balance(MyNode<T> rootTree) {
    if (rootTree != null) {
      if (getNodeHeight(rootTree.getLeftChild())
              - getNodeHeight(rootTree.getRightChild()) > ALLOWED_IMBALANCE) {
        if (getNodeHeight(rootTree.getLeftChild().getLeftChild()) >= getNodeHeight(rootTree
                .getLeftChild().getRightChild())) {
          rootTree = rotateWithLeft(rootTree);

        } else {
          rootTree = doubleWithLeft(rootTree);
        }

      }
      if (getNodeHeight(rootTree.getRightChild())
              - getNodeHeight(rootTree.getLeftChild()) > ALLOWED_IMBALANCE) {

        if (getNodeHeight(rootTree.getRightChild().getRightChild()) >= getNodeHeight(rootTree
                .getRightChild().getLeftChild())) {
          rootTree = rotateWithRight(rootTree);
        } else {
          rootTree = doubleWithRight(rootTree);
        }

      }

      rootTree.setHeight(Math.max(getNodeHeight(rootTree.getLeftChild()),
              getNodeHeight(rootTree.getRightChild())) + 1);

      return rootTree;
    }
    return rootTree;
  }

  /**
   * Rotate with left.
   *
   * @param node1
   *          the node1
   * @return the my node
   */
  public MyNode<T> rotateWithLeft(MyNode<T> node1) {

    MyNode<T> node2 = node1.getLeftChild();
    node1.setLeftChild(node2.getRightChild());

    node2.setRightChild(node1);
    node2.setHeight(Math.max(getNodeHeight(node2.getLeftChild()),
            getNodeHeight(node2.getRightChild())) + 1);
    node1.setHeight(Math.max(getNodeHeight(node1.getLeftChild()),
            getNodeHeight(node1.getRightChild())) + 1);
    return node2;
  }

  /**
   * Rotate with right.
   *
   * @param node1
   *          the node1
   * @return the my node
   */
  public MyNode<T> rotateWithRight(MyNode<T> node1) {
    MyNode<T> node2 = node1.getRightChild();
    node1.setRightChild(node2.getLeftChild());
    node2.setLeftChild(node1);
    node2.setHeight(Math.max(getNodeHeight(node2.getLeftChild()),
            getNodeHeight(node2.getRightChild())) + 1);
    node1.setHeight(Math.max(getNodeHeight(node1.getLeftChild()),
            getNodeHeight(node1.getRightChild())) + 1);
    return node2;
  }

  /**
   * Double with left.
   *
   * @param node1
   *          the node1
   * @return the my node
   */
  public MyNode<T> doubleWithLeft(MyNode<T> node1) {
    node1.setLeftChild(rotateWithRight(node1.getLeftChild()));
    return rotateWithLeft(node1);
  }

  /**
   * Double with right.
   *
   * @param node1
   *          the node1
   * @return the my node
   */
  public MyNode<T> doubleWithRight(MyNode<T> node1) {
    node1.setRightChild(rotateWithLeft(node1.getRightChild()));
    return rotateWithRight(node1);
  }

  /**
   * Delete the key (if exists).
   *
   * @param key
   *          the key of the node
   * @return true if node deleted, false if not exists
   */
  @Override
  public boolean delete(T key) {

    if (key == null || root == null) {
      return false;
    }
    if (search(key)) {
      MyNode<T> node = (MyNode<T>) getTree();
      if (key.compareTo(node.getValue()) == 0 && node.isLeaf()) {
        root = null;
        size--;
        return true;
      } else if (key.compareTo(node.getValue()) != 0 && node.isLeaf()) {
        return false;
      }
      root = remove(key, node);
      size--;
      return true;
    }
    return false;
  }

  /**
   * Removes the.
   *
   * @param key
   *          the key
   * @param rootTree
   *          the root tree
   * @return the my node
   */
  public MyNode<T> remove(T key, MyNode<T> rootTree) {
    if (rootTree != null) {
      compareResult = key.compareTo(rootTree.getValue());
      if (compareResult < 0) {
        rootTree.setLeftChild(remove(key, rootTree.getLeftChild()));

      } else if (compareResult > 0) {
        rootTree.setRightChild(remove(key, rootTree.getRightChild()));

      } else if (rootTree.getLeftChild() != null
              && rootTree.getRightChild() != null) {
        rootTree.setValue(findMin(rootTree.getRightChild()).getValue());
        rootTree.setRightChild(remove(rootTree.getValue(),
                rootTree.getRightChild()));

      } else {
        rootTree = (rootTree.getLeftChild() != null) ? rootTree.getLeftChild()
                : rootTree.getRightChild();

      }
      return balance(rootTree);

    }
    return rootTree;
  }

  /**
   * Find min.
   *
   * @param rootTree
   *          the root tree
   * @return the my node
   */
  public MyNode<T> findMin(MyNode<T> rootTree) {
    if (rootTree.getLeftChild() != null) {
      searchNode = findMin(rootTree.getLeftChild());
      // System.out.println(newNode.getValue());
      return searchNode;
    }
    return rootTree;
  }

  /**
   * Search for a specific element using the key in the tree.
   *
   * @param key
   *          the key of the node
   * @return true if the key exists, false otherwise
   */
  @Override
  public boolean search(T key) {
    if (key != null && root != null) {
      return search(key, root);
    }
    return false;
  }

  /**
   * Search.
   *
   * @param key
   *          the key
   * @param rootTree
   *          the root tree
   * @return true, if successful
   */
  public boolean search(T key, MyNode<T> rootTree) {
    if (key.compareTo(rootTree.getValue()) < 0) {
      if (rootTree.getLeftChild() != null) {
        return search(key, rootTree.getLeftChild());
      }
      return false;

    } else if (key.compareTo(rootTree.getValue()) > 0) {
      if (rootTree.getRightChild() != null) {
        return search(key, rootTree.getRightChild());
      }
      return false;

    }
    return true;
  }

  /**
   * Count nodes.
   *
   * @param node
   *          the node
   * @return the int
   */
  public int countNodes(MyNode<T> node) {
    if (node == null) {
      return 0;
    } else {
      int num = 1;
      num += countNodes(node.getLeftChild());
      num += countNodes(node.getRightChild());
      return num;
    }
  }

  /**
   * Gets the size.
   *
   * @return the size
   */
  public int getSize() {
    return size;
  }
}
