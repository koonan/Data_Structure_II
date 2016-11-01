/*
 * 
 */

package eg.edu.alexu.csd.filestructure.sort;

import java.util.ArrayList;
import java.util.Collections;

/**
 * The Class Sort.
 *
 * @param <T>
 *          the generic type
 */
public class Sort<T extends Comparable<T>> implements ISort<T> {

  /** The heap. */
  /**
   * Sorts the given collection of elements using heap-sort algorithm in-place,
   * and returns a clone of the complete heap that you constructed during the
   * sorting, and before you empty it. Runs in O(n lg n) time
   */

  private Heap<T> heap;

  @Override
  public IHeap<T> heapSort(ArrayList<T> unordered) {
    if (unordered == null || unordered.size() == 0) {
      throw new RuntimeException("Empty");
    }
    heap = new Heap<T>();
    heap.sort(unordered);
    return heap;
  }

  /*
   * 
   * Sorts the given collection of elements using any O(n^2) algorithm
   */
  @Override
  public void sortSlow(ArrayList<T> unordered) {
    if (unordered == null || unordered.size() == 0) {
      return;
    }
    for (int index = 0; index < unordered.size() - 1; index++) {
      for (int indexInner = 0; indexInner < unordered.size() - index - 1; indexInner++) {
        if (unordered.get(indexInner).compareTo(unordered.get(indexInner + 1)) > 0) {
          Collections.swap(unordered, indexInner, indexInner + 1);
        }
      }
    }
  }

  /*
   * Sorts the given collection of elements using any O(n lg n) algorithm
   */
  @Override
  public void sortFast(ArrayList<T> unordered) {
    if (unordered == null || unordered.size() == 0) {
      return;
    }
    quick(unordered, 0, unordered.size() - 1);

  }

  /**
   * Quick.
   *
   * @param unordered
   *          the unordered
   * @param lower
   *          the lower
   * @param upper
   *          the upper
   */
  public void quick(ArrayList<T> unordered, int lower, int upper) {
    int loc;
    if (lower < upper) {
      loc = partition(unordered, lower, upper);
      quick(unordered, lower, loc);
      quick(unordered, loc + 1, upper);

    }
  }

  /*
   * Return type: int Parameters passed: Unsorted array and its lower and upper
   * bounds
   */

  /**
   * Partition.
   *
   * @param unordered
   *          the unordered
   * @param low
   *          the low
   * @param high
   *          the high
   * @return the int
   */
  public int partition(ArrayList<T> unordered, int low, int high) {
    T pivot;
    int index1;
    int index2;
    pivot = unordered.get(low);
    index1 = low - 1;
    index2 = high + 1;
    while (true) {
      index1++;
      while (index1 < high && (unordered.get(index1).compareTo(pivot) < 0)) {
        index1++;
      }
      index2--;
      while (index2 > low && (unordered.get(index2).compareTo(pivot) > 0)) {
        index2--;
      }

      if (index1 < index2) {
        Collections.swap(unordered, index1, index2);
      } else {
        return index2;
      }
    }

  }
}
