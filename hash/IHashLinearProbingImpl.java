package eg.edu.alexu.csd.filestructure.hash;

import java.util.ArrayList;

public class IHashLinearProbingImpl<K, V> implements IHash<K, V>,
        IHashLinearProbing {

  private int capacity = 1200;
  private int size = 0;
  private Node<K, V>[] data;
  private int collisions = 0;
  @SuppressWarnings("unchecked")
  private K k = (K) "";
  @SuppressWarnings("unchecked")
  private V v = (V) "";
  private Node<K, V> DELETED = new Node<K, V>(k, v);
  private ArrayList<K> keys;

  @SuppressWarnings("unchecked")
  IHashLinearProbingImpl() {
    keys = new ArrayList<>();
    data = new Node[capacity];
  }

  @Override
  public void put(K key, V value) {
    if (size == capacity) {
      collisions += capacity;
      collisions++;
      rehash();
    }
    int index = getHash(key, true);
    Node<K, V> node = data[index];
    if ((node == null) || (node == DELETED)) {
      data[index] = new Node<K, V>(key, value);
      size++;
    } else {
      node.value = value;
      collisions++;
      size++;
    }
  }

  @SuppressWarnings("unchecked")
  private void rehash() {
    Node<K, V>[] oldTable = data;
    int oldSize = capacity;
    capacity = capacity * 2;
    data = new Node[capacity];

    for (int index1 = 0; index1 < oldSize; index1++) {
      int index = getHash(oldTable[index1].key, true);
      Node<K, V> node = data[index];
      if (node == null) {
        data[index] = new Node<K, V>(oldTable[index1].key,
                oldTable[index1].value);
      }
    }
  }

  public int getIndex(K key) {

    return key.hashCode() % capacity;

  }

  public int getHash(K key, boolean stopAtDeleted) {
    int index = getIndex(key);
    for (int i = 0; i < capacity; i++) {
      if (data[index] == null) {
        if (i != 0) {
          collisions++;
        }
        return index;
      }
      if (stopAtDeleted && (data[index] == DELETED)) {
        return index;
      }
      if (data[index] != DELETED) {
        if (data[index].key.equals(key)) {
          return index;
        }
      }
      index = (index + 1) % capacity;
      if (stopAtDeleted)
        collisions++;

    }
    return -1;
  }

  @Override
  public String get(K key) {
    int index = getHash(key, false);
    if ((index != -1) && (data[index] != null)) {
      return (String) data[index].value;
    } else {
      return null;
    }
  }

  @Override
  public void delete(K key) {
    int index = getHash(key, false);
    if ((index != -1) && (data[index] != null)) {
      size--;
      data[index] = DELETED;
    }
  }

  @Override
  public boolean contains(K key) {
    int index = getHash(key, false);
    if ((index != -1) && (data[index] != null)) {
      return true;
    }
    return false;
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
    return collisions;
  }

  @Override
  public Iterable<K> keys() {
    for (int index = 0; index < capacity; index++) {
      if (data[index] != null) {
        keys.add(data[index].key);
      }
    }
    return keys;
  }
}
