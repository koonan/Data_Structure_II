package eg.edu.alexu.csd.filestructure.hash;

import java.util.ArrayList;

public class IHashQuadraticProbingImpl<K, V> implements IHash<K, V>,
        IHashQuadraticProbing {
  //
  // private int capacity = 1200;
  // public TableEntry<K, V>[] hashTable;
  // private int numberOfEntries;
  // private int locationsUsed;
  // private static final double MAX_LOAD_FACTOR = 0.5;
  // private int collisions = 0;
  // private ArrayList<K> keys;
  //
  // @SuppressWarnings("unchecked")
  // IHashQuadraticProbingImpl() {
  // hashTable = new TableEntry[capacity];
  // keys = new ArrayList<>();
  // numberOfEntries = 0;
  // locationsUsed = 0;
  //
  // }
  //
  // @Override
  // public void put(K key, V value) {
  // if (isHashTableTooFull())
  // rehash();
  // int index = getHashIndex(key);
  //
  // index = quadraticProbe(index, key);
  // if ((hashTable[index] == null) || hashTable[index].isRemoved()) {
  // hashTable[index] = new TableEntry<K, V>(key, value);
  // numberOfEntries++;
  // } else {
  // numberOfEntries++;
  // hashTable[index].setValue(value);
  // collisions++;
  // }
  //
  // }
  //
  // @Override
  // public String get(K key) {
  // V result = null;
  //
  // int index = getHashIndex(key);
  // index = locate(index, key);
  //
  // if (index != -1)
  // result = hashTable[index].getValue();
  //
  // return (String) result;
  // }
  //
  // @Override
  // public void delete(K key) {
  //
  // int index = getHashIndex(key);
  // index = locate(index, key);
  //
  // if (index != -1) {
  // hashTable[index].setToRemoved();
  // numberOfEntries--;
  // }
  //
  // }
  //
  // @Override
  // public boolean contains(K key) {
  // return get(key) != null;
  // }
  //
  // @Override
  // public boolean isEmpty() {
  // return numberOfEntries == 0;
  // }
  //
  // @Override
  // public int size() {
  // return numberOfEntries;
  // }
  //
  // @Override
  // public int capacity() {
  // return hashTable.length;
  // }
  //
  // @Override
  // public int collisions() {
  // return collisions;
  // }
  //
  // @Override
  // public Iterable<K> keys() {
  // for (int index = 0; index < hashTable.length; index++) {
  // if (hashTable[index] != null || hashTable[index].isRemoved()) {
  // keys.add(hashTable[index].key);
  // }
  // }
  // return keys;
  // }
  //
  // public int getHashIndex(K key) {
  //
  // int hashIndex = key.hashCode() % hashTable.length;
  //
  // return hashIndex;
  // }
  //
  // private int quadraticProbe(int index, K key) {
  // boolean found = false;
  // int removedStateIndex = -1;
  // int i = 0;
  // while (!found && (hashTable[index] != null) && i < hashTable.length) {
  // if (hashTable[index].isIn()) {
  // if (key.equals(hashTable[index].getKey()))
  // found = true;
  // else {
  // index = (index + i * i) % hashTable.length;
  // i++;
  // collisions++;
  // }
  // } else {
  // if (removedStateIndex == -1)
  // removedStateIndex = index;
  //
  // index = (index + i * i) % hashTable.length;
  // i++;
  // collisions++;
  // }
  // }
  //
  // if (found || (removedStateIndex == -1))
  // return index;
  // return removedStateIndex;
  //
  // }
  //
  // public int locate(int index, K key) {
  // boolean found = false;
  // int i = 0;
  // while (!found && (hashTable[index] != null) && i < hashTable.length) {
  // if (hashTable[index].isIn() && key.equals(hashTable[index].getKey()))
  // found = true; // key found
  // else // follow probe sequence
  // {
  // index = (index + i * i) % hashTable.length;
  // i++;
  // }
  // }
  //
  // int result = -1;
  // if (found)
  // result = index;
  //
  // return result;
  // }
  //
  // @SuppressWarnings("unchecked")
  // private void rehash() {
  // TableEntry<K, V>[] oldTable = hashTable;
  // int oldSize = hashTable.length;
  // int newSize = oldSize + oldSize;
  // capacity = newSize;
  // hashTable = new TableEntry[newSize];
  // locationsUsed = 0;
  // collisions = 0;
  // //
  // for (int index = 0; index < oldSize; index++) {
  // // if ( (oldTable[index] != null) && oldTable[index].isIn() )
  // // hashTable[index]= new TableEntry<K,V>(oldTable[index].getKey(),
  // // oldTable[index].getValue());
  // put(oldTable[index].key, oldTable[index].value);
  // }
  // }
  //
  // private boolean isHashTableTooFull() {
  // return locationsUsed > MAX_LOAD_FACTOR * hashTable.length;
  // }
  //
  // @SuppressWarnings("hiding")
  // class TableEntry<K, V> {
  // private K key;
  // private V value;
  // private boolean inTable;
  //
  // private TableEntry(K searchKey, V dataValue) {
  // key = searchKey;
  // value = dataValue;
  // inTable = true;
  // }
  //
  // public K getKey() {
  // return key;
  // }
  //
  // private V getValue() {
  // return value;
  // }
  //
  // private void setValue(V newValue) {
  // value = newValue;
  // }
  //
  // private boolean isIn() {
  // return inTable;
  // }
  //
  // private boolean isRemoved() {
  // return !inTable;
  // }
  //
  // private void setToRemoved() {
  // key = null;
  // value = null;
  // inTable = false;
  // }
  //
  // }
  // //////////////////////////////////////
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
  IHashQuadraticProbingImpl() {
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
    if (index == -1) {
      rehash();
      collisions++;
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

  public int getIndex(K key) {

    return key.hashCode() % capacity;

  }

  public int getHash(K key, boolean stopAtDeleted) {
    int index = getIndex(key);
    for (int i = 1; i <= capacity; i++) {
      if (data[index] == null) {
        if (i != 1) {
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
      index = (getIndex(key)+ i * i) % capacity;
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
