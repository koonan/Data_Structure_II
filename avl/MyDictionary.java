package eg.edu.alexu.csd.filestructure.avl;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import eg.edu.alexu.csd.filestructure.avl.IDictionary;

/**
 * The Class MyDictionary. An application on AVL Tree
 * 
 * @param <T>
 *          the generic type
 */
public class MyDictionary<T extends Comparable<T>> implements IDictionary {

  /** The tree. */
  private MyAVL<String> tree;

  /** The reader. */
  private Scanner reader;

  /**
   * Instantiates a new my dictionary.
   */
  public MyDictionary() {
    tree = new MyAVL<String>();
  }

  /*
   * Read Words from file (Dictionary) not allowed duplicated words
   */
  @Override
  public void load(File file) {
    try {
      reader = new Scanner(file);
      while (reader.hasNextLine()) {
        insert(reader.nextLine());
      }

      reader.close();
    } catch (FileNotFoundException e) {
      System.out.println("Unable to load dictionary missing load file !");
      throw new RuntimeException();
    }
  }

  /*
   * Insert into the dictionary if the word doesn't exist only return true if it
   * successfully inserted or false otherwise
   */
  @Override
  public boolean insert(String word) {
    if (tree.search(word)) {
      System.out.println("ERROR: Word alreadyin the dictionary!");
      return false;
    }
    tree.insert(word);
    return true;
  }

  /*
   * Show if the dictionary contains this word
   */
  @Override
  public boolean exists(String word) {
    if (tree.search(word)) {
      return true;

    }
    return false;
  }

  /*
   * Delete word from the dictionary
   */
  @Override
  public boolean delete(String word) {
    return tree.delete(word);
  }

  /*
   * Retutn the size of this dictionary
   */
  @Override
  public int size() {
    return tree.getSize();

  }

  /*
   * Return the height of the tree that conatins words
   */
  @Override
  public int height() {
    return tree.height();
  }

}
