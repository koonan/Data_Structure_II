package eg.edu.alexu.csd.filestructure.hash;

import java.util.ArrayList;

public class IHashDoublImp<K, V> implements IHash<K, V>, IHashDouble {
  private int primeSize;
  private int capacity;
  private int size;
  private Node<K, V>[] data;
  private int collisions;
  @SuppressWarnings("unchecked")
  private K k = (K) "";
  @SuppressWarnings("unchecked")
  private V v = (V) "";
  private Node<K, V> DELETED = new Node<K, V>(k, v);
  private ArrayList<K> keys;

  @SuppressWarnings("unchecked")
  IHashDoublImp() {
    capacity = 1200;
    collisions = 0;
    size = 0;
    keys = new ArrayList<>();
    data = new Node[capacity];
    primeSize = 1193;
  }

  @Override
  public void put(K key, V value) {
    if (size == capacity) {
      collisions += size + 1;
      rehash();
    }
    int index = getHash(key, true);
    if (index == -1) {
      rehash();
      collisions++;
      // collisions += size + 1;

      index = getHash(key, true);
    }
    Node<K, V> node = data[index];
    if ((node == null) || (node == DELETED)) {
      data[index] = new Node<K, V>(key, value);
      size++;
    } else {
      node.value = value;
      collisions++;
    }
  }

  @SuppressWarnings("unchecked")
  private void rehash() {
    Node<K, V>[] oldTable = data;
    int oldSize = capacity;
    capacity = capacity * 2;
    data = new Node[capacity];

    for (int index1 = 0; index1 < oldSize; index1++) {
      if (oldTable[index1] != null) {
        int index = getHash(oldTable[index1].key, true);
        Node<K, V> node = data[index];
        if (node == null) {
          data[index] = new Node<K, V>(oldTable[index1].key,
                  oldTable[index1].value);
        }
      }
    }
  }

  public int getHash(K key, boolean stopAtDeleted) {
    int index1 = myHash1(key);
    int index2 = myHash2(key);
    int index = index1;
    for (int i = 1; i <= capacity; i++) {

      if (data[index] == null) {

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
      index = (index1 + i * index2) % capacity;
      if (stopAtDeleted)
        collisions++;
    }
    return -1;
  }

  public int myHash1(K key) {
    int hashVal = key.hashCode();
    hashVal %= capacity;

    return hashVal;
  }

  public int myHash2(K key) {
    int hashVal = (Integer) key;
    return primeSize - hashVal % primeSize;
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
    return get(key) != null;
  }

  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public int capacity() {
    return capacity;
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
