package eg.edu.alexu.csd.filestructure.hash;

import java.util.ArrayList;

public class IHashChainingImpl<K, V> implements IHash<K, V>, IHashChaining {

  private int size;
  private int capacity;
  private ArrayList<HashNode<K, V>> table;
  private int collisions;
  private ArrayList<K> keys;

  IHashChainingImpl() {
    table = new ArrayList<>();
    keys = new ArrayList<K>();

    size = 0;
    collisions = 0;
    capacity = 1200;
    for (int i = 0; i < capacity; i++)
      table.add(null);
  }

  @Override
  public void put(K key, V value) {
    // if ((1.0*size)/capacity >= 0.7)
    // {
    // ArrayList<HashNode<K, V>> temp = table;
    // table= new ArrayList<>();
    // capacity = 2 * capacity;
    // size = 0;
    // for (int i = 0; i < capacity; i++){
    // table.add(null);
    // }
    //
    // for (HashNode<K, V> headNode : temp)
    // {
    // while (headNode != null)
    // {
    // table.add(headNode);
    // headNode = headNode.next;
    // }
    // }
    // }
    int index = getIndex(key);
    int count = 0;
    HashNode<K, V> head = table.get(index);
    while (head != null) {
      count++;
      if (head.key.equals(key)) {
        head.value = value;
        size++;
        return;
      }
      head = head.next;
    }

    size++;
    keys.add(key);
    collisions += count;
    head = table.get(index);

    HashNode<K, V> newNode = new HashNode<K, V>(key, value);
    newNode.next = head;
    table.set(index, newNode);

    // If load factor goes beyond threshold, then
    // double hash table size

  }

  @Override
  public String get(K key) {

    int index = getIndex(key);
    HashNode<K, V> head = table.get(index);

    while (head != null) {
      if (head.key.equals(key))
        return (String) head.value;
      head = head.next;
    }

    return null;

  }

  @Override
  public void delete(K key) {
    int index = getIndex(key);
    boolean found = false;
    HashNode<K, V> head = table.get(index);
    HashNode<K, V> prev = null;
    while (head != null) {
      if (head.key.equals(key)) {
        found = true;
        break;
      }
      prev = head;
      head = head.next;
    }

    if (head != null && found) {
      size--;
     // keys.remove(key);
      if (prev != null)
        prev.next = head.next;
      else
        table.set(index, head.next);

    }
  }

  @Override
  public boolean contains(K key) {
    // int index = getIndex(key);
    // HashNode<K, V> head = table.get(index);
    //
    // while (head != null) {
    // if (head.key.equals(key))
    // return true;
    // head = head.next;
    // }
    // return false;
    return get(key) != null;
  }

  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  @Override
  public int size() {
    return this.size;
  }

  @Override
  public int capacity() {
    return this.capacity;
  }

  @Override
  public int collisions() {
    return this.collisions;
  }

  @Override
  public Iterable<K> keys() {
//    for (int index=0 ; index <capacity ;index++){
//    HashNode<K, V> head = table.get(index);
//
//    while (head != null) {
//      keys.add(head.key);
//      head = head.next;
//    }
//    }
    return keys;
    
  }

  public int getIndex(K key) {
    int hash = key.hashCode();
    int index = hash % capacity;
    return index;
  }

}
