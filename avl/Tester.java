package eg.edu.alexu.csd.filestructure.avl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class Tester {

  IAVLTree<Integer> tree = new MyAVL<Integer>();

  @Test
  public void test() {

    INode<Integer> node = tree.getTree();
    assertEquals(null, node);
  }

  @Test
  public void test2() {
    tree.insert(5);
    INode<Integer> node = tree.getTree();
    int result = node.getValue();
    assertEquals(5, result);
  }

  @Test
  public void test2a() {
    tree.insert(5);
    MyNode<Integer> node = (MyNode<Integer>) tree.getTree();
    int result = node.getHeight();
    assertEquals(1, result);
  }

  @Test
  public void test2b() {
    tree.insert(5);
    // MyNode<Integer> node = (MyNode<Integer>) tree.getTree();
    int result = tree.height();
    assertEquals(1, result);
  }

  @Test
  public void test3() {
    tree.insert(5);
    tree.insert(2);
    MyNode<Integer> node = (MyNode<Integer>) tree.getTree();
    int result = node.getValue();
    assertEquals(5, result);
  }

  @Test
  public void test3a() {
    tree.insert(5);
    tree.insert(2);
    int result = tree.height();
    assertEquals(2, result);
  }

  @Test
  public void test3b() {
    tree.insert(5);
    tree.insert(2);
    MyNode<Integer> node = (MyNode<Integer>) tree.getTree();
    MyNode<Integer> left = (MyNode<Integer>) node.getLeftChild();
    int result = left.getValue();
    assertEquals(2, result);
  }

  @Test
  public void test4() {// with left children
    tree.insert(5);
    tree.insert(2);
    tree.insert(1);
    MyNode<Integer> node = (MyNode<Integer>) tree.getTree();
    int result = node.getValue();
    assertEquals(2, result);
  }

  @Test
  public void test4a() {
    tree.insert(5);
    tree.insert(2);
    tree.insert(1);
    int result = tree.height();
    assertEquals(2, result);
  }

  @Test
  public void test5() {
    IAVLTree<Integer> tree = new MyAVL<Integer>();
    tree.insert(5);
    tree.insert(7);
    MyNode<Integer> node = (MyNode<Integer>) tree.getTree();
    int result = node.getValue();
    assertEquals(5, result);
  }

  @Test
  public void test5a() {
    IAVLTree<Integer> tree = new MyAVL<Integer>();
    tree.insert(5);
    tree.insert(7);
    MyNode<Integer> node = (MyNode<Integer>) tree.getTree();
    MyNode<Integer> right = (MyNode<Integer>) node.getRightChild();
    int result = right.getValue();
    assertEquals(7, result);
  }

  @Test
  public void test5b() {
    IAVLTree<Integer> tree = new MyAVL<Integer>();
    tree.insert(5);
    tree.insert(7);
    MyNode<Integer> node = (MyNode<Integer>) tree.getTree();
    MyNode<Integer> right = (MyNode<Integer>) node.getRightChild();
    int result = right.getHeight();
    assertEquals(1, result);
  }

  @Test
  public void test5c() {// no rotation
    tree.insert(5);
    tree.insert(2);
    tree.insert(7);
    int result = tree.height();
    assertEquals(2, result);
  }

  @Test
  public void test6() {// with right children
    tree.insert(5);
    tree.insert(7);
    tree.insert(8);
    MyNode<Integer> node = (MyNode<Integer>) tree.getTree();
    int result = node.getValue();
    assertEquals(7, result);
  }

  @Test
  public void test6a() {// with right children
    tree.insert(5);
    tree.insert(7);
    tree.insert(8);
    int result = tree.height();
    assertEquals(2, result);
  }

  @Test
  public void test7() {// with double (left child then right child)
    tree.insert(9);
    tree.insert(5);
    tree.insert(11);
    tree.insert(3);
    tree.insert(4);
    int result = tree.height();
    assertEquals(3, result);
  }

  @Test
  public void test7a() {// with double (left child then right child)
    tree.insert(9);
    tree.insert(5);
    tree.insert(11);
    tree.insert(3);
    tree.insert(4);
    MyNode<Integer> node = (MyNode<Integer>) tree.getTree();
    int result = node.getValue();
    assertEquals(9, result);
  }

  @Test
  public void test8() {// with double (right child then left child)
    tree.insert(9);
    tree.insert(5);
    tree.insert(11);
    tree.insert(15);
    tree.insert(12);
    int result = tree.height();
    assertEquals(3, result);
  }

  @Test
  public void test8a() {// with double (right child then left child)
    tree.insert(9);
    tree.insert(5);
    tree.insert(11);
    tree.insert(15);
    tree.insert(12);
    MyNode<Integer> node = (MyNode<Integer>) tree.getTree();
    int result = node.getValue();
    assertEquals(9, result);
  }

  // ======================================testSearch=======================================
  @Test
  public void test9() {
    tree.insert(5);
    tree.insert(2);
    tree.insert(7);
    boolean result = tree.search(5);
    assertEquals(true, result);
  }

  @Test
  public void test9a() {
    tree.insert(5);
    tree.insert(2);
    tree.insert(7);
    boolean result = tree.search(2);
    assertEquals(true, result);
  }

  @Test
  public void test9b() {
    tree.insert(5);
    tree.insert(2);
    tree.insert(7);
    boolean result = tree.search(7);
    assertEquals(true, result);
  }

  @Test
  public void test9c() {
    tree.insert(5);
    tree.insert(2);
    tree.insert(7);
    boolean result = tree.search(8);
    assertEquals(false, result);
  }

  @Test
  public void test10() {
    IAVLTree<Integer> tree = new MyAVL<Integer>();
    boolean result = tree.search(8);
    assertEquals(false, result);
  }

  // ======================================testdelete==============================================
  @Test
  public void test11() {
    IAVLTree<Integer> tree = new MyAVL<Integer>();
    boolean result = tree.delete(5);
    assertEquals(false, result);
  }

  @Test
  public void test12() {
    IAVLTree<Integer> tree = new MyAVL<Integer>();
    tree.insert(5);
    boolean result = tree.delete(5);
    assertEquals(true, result);
  }

  @Test
  public void test13() {
    IAVLTree<Integer> tree = new MyAVL<Integer>();
    tree.insert(5);
    tree.insert(2);
    boolean result = tree.delete(2);
    assertEquals(true, result);
  }

  @Test
  public void test13a() {
    IAVLTree<Integer> tree = new MyAVL<Integer>();
    tree.insert(5);
    tree.insert(2);
    tree.delete(2);
    int result = tree.height();
    assertEquals(1, result);
  }

  @Test
  public void test14() {
    IAVLTree<Integer> tree = new MyAVL<Integer>();
    tree.insert(5);
    tree.insert(2);
    boolean result = tree.delete(10);
    assertEquals(false, result);
  }

  @Test
  public void test15() {
    IAVLTree<Integer> tree = new MyAVL<Integer>();
    int result = tree.height();
    assertEquals(0, result);
  }

  @Test
  public void test16() {
    IAVLTree<Integer> tree = new MyAVL<Integer>();
    tree.insert(5);
    tree.delete(5);
    int result = tree.height();
    assertEquals(0, result);
  }

  @Test
  public void test17() {
    IAVLTree<Integer> tree = new MyAVL<Integer>();
    tree.insert(5);
    tree.insert(2);
    tree.delete(5);
    MyNode<Integer> node = (MyNode<Integer>) tree.getTree();
    int result = node.getValue();
    assertEquals(2, result);
  }

  @Test
  public void test17a() {
    IAVLTree<Integer> tree = new MyAVL<Integer>();
    tree.insert(5);
    tree.insert(2);
    tree.delete(5);
    int result = tree.height();
    assertEquals(1, result);
  }

  @Test
  public void test18() {
    tree.insert(9);
    tree.insert(5);
    tree.insert(11);
    tree.insert(15);
    tree.insert(12);
    tree.delete(9);
    MyNode<Integer> node = (MyNode<Integer>) tree.getTree();
    int result = node.getValue();
    assertEquals(11, result);
  }

  @Test
  public void test18a() {
    tree.insert(9);
    tree.insert(5);
    tree.insert(11);
    tree.insert(15);
    tree.insert(12);
    tree.delete(9);
    int result = tree.height();
    assertEquals(3, result);
  }

  @Test
  // =====================================duplicate=============================
  public void test19() {
    IAVLTree<Integer> tree = new MyAVL<Integer>();
    tree.insert(5);
    tree.insert(5);
    int result = tree.height();
    assertEquals(2, result);
  }

  @Test
  public void test20() {
    IAVLTree<Integer> tree = new MyAVL<Integer>();
    boolean answer = tree.delete(null);
    assertEquals(false, answer);

  }

  @Test
  public void test21() {
    IAVLTree<Integer> tree = new MyAVL<Integer>();
    tree.insert(null);
    // MyNode<Integer> node = (MyNode<Integer>) tree.getTree();
    // assertEquals(null, result);
  }

  @Test
  public void test22() {
    IAVLTree<Integer> tree = new MyAVL<Integer>();
    tree.insert(5);
    boolean ans = tree.search(null);
    assertEquals(false, ans);
  }

  @Test
  public void test23() {
    IAVLTree<Integer> tree = new MyAVL<Integer>();
    tree.insert(5);
    tree.insert(null);

  }

  @Test
  public void test24() {
    IAVLTree<Integer> tree = new MyAVL<Integer>();
    tree.insert(5);
    tree.insert(2);
    tree.insert(1);
    tree.delete(5);
    tree.delete(2);
    INode<Integer> root = tree.getTree();
    int result = (int) root.getValue();
    assertEquals(1, result);

  }

  @Test
  public void test25() {
    IAVLTree<Integer> tree = new MyAVL<Integer>();
    tree.insert(20);
    tree.insert(40);
    tree.insert(60);
    tree.insert(10);
    tree.insert(15);
    tree.insert(12);
    tree.insert(11);
    INode<Integer> root = tree.getTree();
    int result = (int) root.getValue();
    assertEquals(15, result);

  }

  @Test
  public void test25a() {
    IAVLTree<Integer> tree = new MyAVL<Integer>();
    tree.insert(20);
    tree.insert(40);
    tree.insert(60);
    tree.insert(10);
    tree.insert(15);
    tree.insert(12);
    tree.insert(11);
    int result = tree.height();
    assertEquals(3, result);

  }

}
