package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.math.IntMath;
import java.util.AbstractQueue;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;

@Beta
public final class MinMaxPriorityQueue<E> extends AbstractQueue<E> {
    private static final int DEFAULT_CAPACITY = 11;
    private static final int EVEN_POWERS_OF_TWO = 1431655765;
    private static final int ODD_POWERS_OF_TWO = -1431655766;
    private final Heap maxHeap;
    @VisibleForTesting
    final int maximumSize;
    private final Heap minHeap;
    private int modCount;
    private Object[] queue;
    private int size;

    @Beta
    public static final class Builder<B> {
        private static final int UNSET_EXPECTED_SIZE = -1;
        private final Comparator<B> comparator;
        private int expectedSize;
        private int maximumSize;

        private Builder(Comparator<B> comparator) {
            this.expectedSize = -1;
            this.maximumSize = Integer.MAX_VALUE;
            this.comparator = (Comparator) Preconditions.checkNotNull(comparator);
        }

        public Builder<B> expectedSize(int expectedSize) {
            Preconditions.checkArgument(expectedSize >= 0);
            this.expectedSize = expectedSize;
            return this;
        }

        public Builder<B> maximumSize(int maximumSize) {
            Preconditions.checkArgument(maximumSize > 0);
            this.maximumSize = maximumSize;
            return this;
        }

        public <T extends B> MinMaxPriorityQueue<T> create() {
            return create(Collections.emptySet());
        }

        public <T extends B> MinMaxPriorityQueue<T> create(Iterable<? extends T> initialContents) {
            MinMaxPriorityQueue<T> queue = new MinMaxPriorityQueue(this, MinMaxPriorityQueue.initialQueueSize(this.expectedSize, this.maximumSize, initialContents));
            for (T element : initialContents) {
                queue.offer(element);
            }
            return queue;
        }

        private <T extends B> Ordering<T> ordering() {
            return Ordering.from(this.comparator);
        }
    }

    private class Heap {
        final Ordering<E> ordering;
        Heap otherHeap;

        Heap(Ordering<E> ordering) {
            this.ordering = ordering;
        }

        int compareElements(int a, int b) {
            return this.ordering.compare(MinMaxPriorityQueue.this.elementData(a), MinMaxPriorityQueue.this.elementData(b));
        }

        MoveDesc<E> tryCrossOverAndBubbleUp(int removeIndex, int vacated, E toTrickle) {
            int crossOver = crossOver(vacated, toTrickle);
            if (crossOver == vacated) {
                return null;
            }
            E parent;
            if (crossOver < removeIndex) {
                parent = MinMaxPriorityQueue.this.elementData(removeIndex);
            } else {
                parent = MinMaxPriorityQueue.this.elementData(getParentIndex(removeIndex));
            }
            if (this.otherHeap.bubbleUpAlternatingLevels(crossOver, toTrickle) < removeIndex) {
                return new MoveDesc(toTrickle, parent);
            }
            return null;
        }

        void bubbleUp(int index, E x) {
            Heap heap;
            int crossOver = crossOverUp(index, x);
            if (crossOver == index) {
                heap = this;
            } else {
                index = crossOver;
                heap = this.otherHeap;
            }
            heap.bubbleUpAlternatingLevels(index, x);
        }

        int bubbleUpAlternatingLevels(int index, E x) {
            while (index > 2) {
                int grandParentIndex = getGrandparentIndex(index);
                E e = MinMaxPriorityQueue.this.elementData(grandParentIndex);
                if (this.ordering.compare(e, x) <= 0) {
                    break;
                }
                MinMaxPriorityQueue.this.queue[index] = e;
                index = grandParentIndex;
            }
            MinMaxPriorityQueue.this.queue[index] = x;
            return index;
        }

        int findMin(int index, int len) {
            if (index >= MinMaxPriorityQueue.this.size) {
                return -1;
            }
            Preconditions.checkState(index > 0);
            int limit = Math.min(index, MinMaxPriorityQueue.this.size - len) + len;
            int minIndex = index;
            for (int i = index + 1; i < limit; i++) {
                if (compareElements(i, minIndex) < 0) {
                    minIndex = i;
                }
            }
            return minIndex;
        }

        int findMinChild(int index) {
            return findMin(getLeftChildIndex(index), 2);
        }

        int findMinGrandChild(int index) {
            int leftChildIndex = getLeftChildIndex(index);
            if (leftChildIndex < 0) {
                return -1;
            }
            return findMin(getLeftChildIndex(leftChildIndex), 4);
        }

        int crossOverUp(int index, E x) {
            if (index == 0) {
                MinMaxPriorityQueue.this.queue[0] = x;
                return 0;
            }
            int parentIndex = getParentIndex(index);
            E parentElement = MinMaxPriorityQueue.this.elementData(parentIndex);
            if (parentIndex != 0) {
                int uncleIndex = getRightChildIndex(getParentIndex(parentIndex));
                if (uncleIndex != parentIndex && getLeftChildIndex(uncleIndex) >= MinMaxPriorityQueue.this.size) {
                    E uncleElement = MinMaxPriorityQueue.this.elementData(uncleIndex);
                    if (this.ordering.compare(uncleElement, parentElement) < 0) {
                        parentIndex = uncleIndex;
                        parentElement = uncleElement;
                    }
                }
            }
            if (this.ordering.compare(parentElement, x) < 0) {
                MinMaxPriorityQueue.this.queue[index] = parentElement;
                MinMaxPriorityQueue.this.queue[parentIndex] = x;
                return parentIndex;
            }
            MinMaxPriorityQueue.this.queue[index] = x;
            return index;
        }

        int getCorrectLastElement(E actualLastElement) {
            int parentIndex = getParentIndex(MinMaxPriorityQueue.this.size);
            if (parentIndex != 0) {
                int uncleIndex = getRightChildIndex(getParentIndex(parentIndex));
                if (uncleIndex != parentIndex && getLeftChildIndex(uncleIndex) >= MinMaxPriorityQueue.this.size) {
                    E uncleElement = MinMaxPriorityQueue.this.elementData(uncleIndex);
                    if (this.ordering.compare(uncleElement, actualLastElement) < 0) {
                        MinMaxPriorityQueue.this.queue[uncleIndex] = actualLastElement;
                        MinMaxPriorityQueue.this.queue[MinMaxPriorityQueue.this.size] = uncleElement;
                        return uncleIndex;
                    }
                }
            }
            return MinMaxPriorityQueue.this.size;
        }

        int crossOver(int index, E x) {
            int minChildIndex = findMinChild(index);
            if (minChildIndex <= 0 || this.ordering.compare(MinMaxPriorityQueue.this.elementData(minChildIndex), x) >= 0) {
                return crossOverUp(index, x);
            }
            MinMaxPriorityQueue.this.queue[index] = MinMaxPriorityQueue.this.elementData(minChildIndex);
            MinMaxPriorityQueue.this.queue[minChildIndex] = x;
            return minChildIndex;
        }

        int fillHoleAt(int index) {
            while (true) {
                int minGrandchildIndex = findMinGrandChild(index);
                if (minGrandchildIndex <= 0) {
                    return index;
                }
                MinMaxPriorityQueue.this.queue[index] = MinMaxPriorityQueue.this.elementData(minGrandchildIndex);
                index = minGrandchildIndex;
            }
        }

        private boolean verifyIndex(int i) {
            if (getLeftChildIndex(i) < MinMaxPriorityQueue.this.size && compareElements(i, getLeftChildIndex(i)) > 0) {
                return false;
            }
            if (getRightChildIndex(i) < MinMaxPriorityQueue.this.size && compareElements(i, getRightChildIndex(i)) > 0) {
                return false;
            }
            if (i > 0 && compareElements(i, getParentIndex(i)) > 0) {
                return false;
            }
            if (i <= 2 || compareElements(getGrandparentIndex(i), i) <= 0) {
                return true;
            }
            return false;
        }

        private int getLeftChildIndex(int i) {
            return (i * 2) + 1;
        }

        private int getRightChildIndex(int i) {
            return (i * 2) + 2;
        }

        private int getParentIndex(int i) {
            return (i - 1) / 2;
        }

        private int getGrandparentIndex(int i) {
            return getParentIndex(getParentIndex(i));
        }
    }

    static class MoveDesc<E> {
        final E replaced;
        final E toTrickle;

        MoveDesc(E toTrickle, E replaced) {
            this.toTrickle = toTrickle;
            this.replaced = replaced;
        }
    }

    private class QueueIterator implements Iterator<E> {
        private boolean canRemove;
        private int cursor;
        private int expectedModCount;
        private Queue<E> forgetMeNot;
        private E lastFromForgetMeNot;
        private List<E> skipMe;

        private QueueIterator() {
            this.cursor = -1;
            this.expectedModCount = MinMaxPriorityQueue.this.modCount;
        }

        public boolean hasNext() {
            checkModCount();
            return nextNotInSkipMe(this.cursor + 1) < MinMaxPriorityQueue.this.size() || !(this.forgetMeNot == null || this.forgetMeNot.isEmpty());
        }

        public E next() {
            checkModCount();
            int tempCursor = nextNotInSkipMe(this.cursor + 1);
            if (tempCursor < MinMaxPriorityQueue.this.size()) {
                this.cursor = tempCursor;
                this.canRemove = true;
                return MinMaxPriorityQueue.this.elementData(this.cursor);
            }
            if (this.forgetMeNot != null) {
                this.cursor = MinMaxPriorityQueue.this.size();
                this.lastFromForgetMeNot = this.forgetMeNot.poll();
                if (this.lastFromForgetMeNot != null) {
                    this.canRemove = true;
                    return this.lastFromForgetMeNot;
                }
            }
            throw new NoSuchElementException("iterator moved past last element in queue.");
        }

        public void remove() {
            CollectPreconditions.checkRemove(this.canRemove);
            checkModCount();
            this.canRemove = false;
            this.expectedModCount++;
            if (this.cursor < MinMaxPriorityQueue.this.size()) {
                MoveDesc<E> moved = MinMaxPriorityQueue.this.removeAt(this.cursor);
                if (moved != null) {
                    if (this.forgetMeNot == null) {
                        this.forgetMeNot = new ArrayDeque();
                        this.skipMe = new ArrayList(3);
                    }
                    this.forgetMeNot.add(moved.toTrickle);
                    this.skipMe.add(moved.replaced);
                }
                this.cursor--;
                return;
            }
            Preconditions.checkState(removeExact(this.lastFromForgetMeNot));
            this.lastFromForgetMeNot = null;
        }

        private boolean containsExact(Iterable<E> elements, E target) {
            for (E element : elements) {
                if (element == target) {
                    return true;
                }
            }
            return false;
        }

        boolean removeExact(Object target) {
            for (int i = 0; i < MinMaxPriorityQueue.this.size; i++) {
                if (MinMaxPriorityQueue.this.queue[i] == target) {
                    MinMaxPriorityQueue.this.removeAt(i);
                    return true;
                }
            }
            return false;
        }

        void checkModCount() {
            if (MinMaxPriorityQueue.this.modCount != this.expectedModCount) {
                throw new ConcurrentModificationException();
            }
        }

        private int nextNotInSkipMe(int c) {
            if (this.skipMe != null) {
                while (c < MinMaxPriorityQueue.this.size() && containsExact(this.skipMe, MinMaxPriorityQueue.this.elementData(c))) {
                    c++;
                }
            }
            return c;
        }
    }

    public static <E extends Comparable<E>> MinMaxPriorityQueue<E> create() {
        return new Builder(Ordering.natural()).create();
    }

    public static <E extends Comparable<E>> MinMaxPriorityQueue<E> create(Iterable<? extends E> initialContents) {
        return new Builder(Ordering.natural()).create(initialContents);
    }

    public static <B> Builder<B> orderedBy(Comparator<B> comparator) {
        return new Builder(comparator);
    }

    public static Builder<Comparable> expectedSize(int expectedSize) {
        return new Builder(Ordering.natural()).expectedSize(expectedSize);
    }

    public static Builder<Comparable> maximumSize(int maximumSize) {
        return new Builder(Ordering.natural()).maximumSize(maximumSize);
    }

    private MinMaxPriorityQueue(Builder<? super E> builder, int queueSize) {
        Ordering<E> ordering = builder.ordering();
        this.minHeap = new Heap(ordering);
        this.maxHeap = new Heap(ordering.reverse());
        this.minHeap.otherHeap = this.maxHeap;
        this.maxHeap.otherHeap = this.minHeap;
        this.maximumSize = builder.maximumSize;
        this.queue = new Object[queueSize];
    }

    public int size() {
        return this.size;
    }

    public boolean add(E element) {
        offer(element);
        return true;
    }

    public boolean addAll(Collection<? extends E> newElements) {
        boolean modified = false;
        for (E element : newElements) {
            offer(element);
            modified = true;
        }
        return modified;
    }

    public boolean offer(E element) {
        Preconditions.checkNotNull(element);
        this.modCount++;
        int insertIndex = this.size;
        this.size = insertIndex + 1;
        growIfNeeded();
        heapForIndex(insertIndex).bubbleUp(insertIndex, element);
        return this.size <= this.maximumSize || pollLast() != element;
    }

    public E poll() {
        return isEmpty() ? null : removeAndGet(0);
    }

    E elementData(int index) {
        return this.queue[index];
    }

    public E peek() {
        return isEmpty() ? null : elementData(0);
    }

    private int getMaxElementIndex() {
        switch (this.size) {
            case 1:
                return 0;
            case 2:
                return 1;
            default:
                if (this.maxHeap.compareElements(1, 2) <= 0) {
                    return 1;
                }
                return 2;
        }
    }

    public E pollFirst() {
        return poll();
    }

    public E removeFirst() {
        return remove();
    }

    public E peekFirst() {
        return peek();
    }

    public E pollLast() {
        return isEmpty() ? null : removeAndGet(getMaxElementIndex());
    }

    public E removeLast() {
        if (!isEmpty()) {
            return removeAndGet(getMaxElementIndex());
        }
        throw new NoSuchElementException();
    }

    public E peekLast() {
        return isEmpty() ? null : elementData(getMaxElementIndex());
    }

    @VisibleForTesting
    MoveDesc<E> removeAt(int index) {
        Preconditions.checkPositionIndex(index, this.size);
        this.modCount++;
        this.size--;
        if (this.size == index) {
            this.queue[this.size] = null;
            return null;
        }
        E actualLastElement = elementData(this.size);
        int lastElementAt = heapForIndex(this.size).getCorrectLastElement(actualLastElement);
        E toTrickle = elementData(this.size);
        this.queue[this.size] = null;
        MoveDesc<E> changes = fillHole(index, toTrickle);
        if (lastElementAt >= index) {
            return changes;
        }
        if (changes == null) {
            return new MoveDesc(actualLastElement, toTrickle);
        }
        return new MoveDesc(actualLastElement, changes.replaced);
    }

    private MoveDesc<E> fillHole(int index, E toTrickle) {
        Heap heap = heapForIndex(index);
        int vacated = heap.fillHoleAt(index);
        int bubbledTo = heap.bubbleUpAlternatingLevels(vacated, toTrickle);
        if (bubbledTo == vacated) {
            return heap.tryCrossOverAndBubbleUp(index, vacated, toTrickle);
        }
        return bubbledTo < index ? new MoveDesc(toTrickle, elementData(index)) : null;
    }

    private E removeAndGet(int index) {
        E value = elementData(index);
        removeAt(index);
        return value;
    }

    private Heap heapForIndex(int i) {
        return isEvenLevel(i) ? this.minHeap : this.maxHeap;
    }

    @VisibleForTesting
    static boolean isEvenLevel(int index) {
        boolean z;
        int oneBased = index + 1;
        if (oneBased > 0) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.checkState(z, "negative index");
        if ((EVEN_POWERS_OF_TWO & oneBased) > (ODD_POWERS_OF_TWO & oneBased)) {
            return true;
        }
        return false;
    }

    @VisibleForTesting
    boolean isIntact() {
        for (int i = 1; i < this.size; i++) {
            if (!heapForIndex(i).verifyIndex(i)) {
                return false;
            }
        }
        return true;
    }

    public Iterator<E> iterator() {
        return new QueueIterator();
    }

    public void clear() {
        for (int i = 0; i < this.size; i++) {
            this.queue[i] = null;
        }
        this.size = 0;
    }

    public Object[] toArray() {
        Object[] copyTo = new Object[this.size];
        System.arraycopy(this.queue, 0, copyTo, 0, this.size);
        return copyTo;
    }

    public Comparator<? super E> comparator() {
        return this.minHeap.ordering;
    }

    @VisibleForTesting
    int capacity() {
        return this.queue.length;
    }

    @VisibleForTesting
    static int initialQueueSize(int configuredExpectedSize, int maximumSize, Iterable<?> initialContents) {
        int result;
        if (configuredExpectedSize == -1) {
            result = 11;
        } else {
            result = configuredExpectedSize;
        }
        if (initialContents instanceof Collection) {
            result = Math.max(result, ((Collection) initialContents).size());
        }
        return capAtMaximumSize(result, maximumSize);
    }

    private void growIfNeeded() {
        if (this.size > this.queue.length) {
            Object[] newQueue = new Object[calculateNewCapacity()];
            System.arraycopy(this.queue, 0, newQueue, 0, this.queue.length);
            this.queue = newQueue;
        }
    }

    private int calculateNewCapacity() {
        int oldCapacity = this.queue.length;
        return capAtMaximumSize(oldCapacity < 64 ? (oldCapacity + 1) * 2 : IntMath.checkedMultiply(oldCapacity / 2, 3), this.maximumSize);
    }

    private static int capAtMaximumSize(int queueSize, int maximumSize) {
        return Math.min(queueSize - 1, maximumSize) + 1;
    }
}
