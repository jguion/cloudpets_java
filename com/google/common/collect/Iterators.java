package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Function;
import com.google.common.base.Objects;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.util.Queue;
import javax.annotation.Nullable;

@GwtCompatible(emulated = true)
public final class Iterators {
    static final UnmodifiableListIterator<Object> EMPTY_LIST_ITERATOR = new UnmodifiableListIterator<Object>() {
        public boolean hasNext() {
            return false;
        }

        public Object next() {
            throw new NoSuchElementException();
        }

        public boolean hasPrevious() {
            return false;
        }

        public Object previous() {
            throw new NoSuchElementException();
        }

        public int nextIndex() {
            return 0;
        }

        public int previousIndex() {
            return -1;
        }
    };
    private static final Iterator<Object> EMPTY_MODIFIABLE_ITERATOR = new Iterator<Object>() {
        public boolean hasNext() {
            return false;
        }

        public Object next() {
            throw new NoSuchElementException();
        }

        public void remove() {
            CollectPreconditions.checkRemove(false);
        }
    };

    private static class MergingIterator<T> extends UnmodifiableIterator<T> {
        final Queue<PeekingIterator<T>> queue;

        public MergingIterator(Iterable<? extends Iterator<? extends T>> iterators, final Comparator<? super T> itemComparator) {
            this.queue = new PriorityQueue(2, new Comparator<PeekingIterator<T>>() {
                public int compare(PeekingIterator<T> o1, PeekingIterator<T> o2) {
                    return itemComparator.compare(o1.peek(), o2.peek());
                }
            });
            for (Iterator iterator : iterators) {
                if (iterator.hasNext()) {
                    this.queue.add(Iterators.peekingIterator(iterator));
                }
            }
        }

        public boolean hasNext() {
            return !this.queue.isEmpty();
        }

        public T next() {
            PeekingIterator<T> nextIter = (PeekingIterator) this.queue.remove();
            T next = nextIter.next();
            if (nextIter.hasNext()) {
                this.queue.add(nextIter);
            }
            return next;
        }
    }

    private static class PeekingImpl<E> implements PeekingIterator<E> {
        private boolean hasPeeked;
        private final Iterator<? extends E> iterator;
        private E peekedElement;

        public PeekingImpl(Iterator<? extends E> iterator) {
            this.iterator = (Iterator) Preconditions.checkNotNull(iterator);
        }

        public boolean hasNext() {
            return this.hasPeeked || this.iterator.hasNext();
        }

        public E next() {
            if (!this.hasPeeked) {
                return this.iterator.next();
            }
            E result = this.peekedElement;
            this.hasPeeked = false;
            this.peekedElement = null;
            return result;
        }

        public void remove() {
            Preconditions.checkState(!this.hasPeeked, "Can't remove after you've peeked at next");
            this.iterator.remove();
        }

        public E peek() {
            if (!this.hasPeeked) {
                this.peekedElement = this.iterator.next();
                this.hasPeeked = true;
            }
            return this.peekedElement;
        }
    }

    private Iterators() {
    }

    @Deprecated
    public static <T> UnmodifiableIterator<T> emptyIterator() {
        return emptyListIterator();
    }

    static <T> UnmodifiableListIterator<T> emptyListIterator() {
        return EMPTY_LIST_ITERATOR;
    }

    static <T> Iterator<T> emptyModifiableIterator() {
        return EMPTY_MODIFIABLE_ITERATOR;
    }

    public static <T> UnmodifiableIterator<T> unmodifiableIterator(final Iterator<T> iterator) {
        Preconditions.checkNotNull(iterator);
        if (iterator instanceof UnmodifiableIterator) {
            return (UnmodifiableIterator) iterator;
        }
        return new UnmodifiableIterator<T>() {
            public boolean hasNext() {
                return iterator.hasNext();
            }

            public T next() {
                return iterator.next();
            }
        };
    }

    @Deprecated
    public static <T> UnmodifiableIterator<T> unmodifiableIterator(UnmodifiableIterator<T> iterator) {
        return (UnmodifiableIterator) Preconditions.checkNotNull(iterator);
    }

    public static int size(Iterator<?> iterator) {
        int count = 0;
        while (iterator.hasNext()) {
            iterator.next();
            count++;
        }
        return count;
    }

    public static boolean contains(Iterator<?> iterator, @Nullable Object element) {
        return any(iterator, Predicates.equalTo(element));
    }

    public static boolean removeAll(Iterator<?> removeFrom, Collection<?> elementsToRemove) {
        return removeIf(removeFrom, Predicates.in(elementsToRemove));
    }

    public static <T> boolean removeIf(Iterator<T> removeFrom, Predicate<? super T> predicate) {
        Preconditions.checkNotNull(predicate);
        boolean modified = false;
        while (removeFrom.hasNext()) {
            if (predicate.apply(removeFrom.next())) {
                removeFrom.remove();
                modified = true;
            }
        }
        return modified;
    }

    public static boolean retainAll(Iterator<?> removeFrom, Collection<?> elementsToRetain) {
        return removeIf(removeFrom, Predicates.not(Predicates.in(elementsToRetain)));
    }

    public static boolean elementsEqual(Iterator<?> iterator1, Iterator<?> iterator2) {
        while (iterator1.hasNext()) {
            if (!iterator2.hasNext()) {
                return false;
            }
            if (!Objects.equal(iterator1.next(), iterator2.next())) {
                return false;
            }
        }
        if (iterator2.hasNext()) {
            return false;
        }
        return true;
    }

    public static String toString(Iterator<?> iterator) {
        return Collections2.STANDARD_JOINER.appendTo(new StringBuilder().append('['), (Iterator) iterator).append(']').toString();
    }

    public static <T> T getOnlyElement(Iterator<T> iterator) {
        T first = iterator.next();
        if (!iterator.hasNext()) {
            return first;
        }
        StringBuilder sb = new StringBuilder();
        String valueOf = String.valueOf(String.valueOf(first));
        sb.append(new StringBuilder(valueOf.length() + 31).append("expected one element but was: <").append(valueOf).toString());
        for (int i = 0; i < 4 && iterator.hasNext(); i++) {
            valueOf = String.valueOf(String.valueOf(iterator.next()));
            sb.append(new StringBuilder(valueOf.length() + 2).append(", ").append(valueOf).toString());
        }
        if (iterator.hasNext()) {
            sb.append(", ...");
        }
        sb.append('>');
        throw new IllegalArgumentException(sb.toString());
    }

    @Nullable
    public static <T> T getOnlyElement(Iterator<? extends T> iterator, @Nullable T defaultValue) {
        return iterator.hasNext() ? getOnlyElement(iterator) : defaultValue;
    }

    @GwtIncompatible("Array.newInstance(Class, int)")
    public static <T> T[] toArray(Iterator<? extends T> iterator, Class<T> type) {
        return Iterables.toArray(Lists.newArrayList((Iterator) iterator), type);
    }

    public static <T> boolean addAll(Collection<T> addTo, Iterator<? extends T> iterator) {
        Preconditions.checkNotNull(addTo);
        Preconditions.checkNotNull(iterator);
        boolean wasModified = false;
        while (iterator.hasNext()) {
            wasModified |= addTo.add(iterator.next());
        }
        return wasModified;
    }

    public static int frequency(Iterator<?> iterator, @Nullable Object element) {
        return size(filter((Iterator) iterator, Predicates.equalTo(element)));
    }

    public static <T> Iterator<T> cycle(final Iterable<T> iterable) {
        Preconditions.checkNotNull(iterable);
        return new Iterator<T>() {
            Iterator<T> iterator = Iterators.emptyIterator();
            Iterator<T> removeFrom;

            public boolean hasNext() {
                if (!this.iterator.hasNext()) {
                    this.iterator = iterable.iterator();
                }
                return this.iterator.hasNext();
            }

            public T next() {
                if (hasNext()) {
                    this.removeFrom = this.iterator;
                    return this.iterator.next();
                }
                throw new NoSuchElementException();
            }

            public void remove() {
                CollectPreconditions.checkRemove(this.removeFrom != null);
                this.removeFrom.remove();
                this.removeFrom = null;
            }
        };
    }

    public static <T> Iterator<T> cycle(T... elements) {
        return cycle(Lists.newArrayList((Object[]) elements));
    }

    public static <T> Iterator<T> concat(Iterator<? extends T> a, Iterator<? extends T> b) {
        return concat(ImmutableList.of(a, b).iterator());
    }

    public static <T> Iterator<T> concat(Iterator<? extends T> a, Iterator<? extends T> b, Iterator<? extends T> c) {
        return concat(ImmutableList.of(a, b, c).iterator());
    }

    public static <T> Iterator<T> concat(Iterator<? extends T> a, Iterator<? extends T> b, Iterator<? extends T> c, Iterator<? extends T> d) {
        return concat(ImmutableList.of(a, b, c, d).iterator());
    }

    public static <T> Iterator<T> concat(Iterator<? extends T>... inputs) {
        return concat(ImmutableList.copyOf((Object[]) inputs).iterator());
    }

    public static <T> Iterator<T> concat(final Iterator<? extends Iterator<? extends T>> inputs) {
        Preconditions.checkNotNull(inputs);
        return new Iterator<T>() {
            Iterator<? extends T> current = Iterators.emptyIterator();
            Iterator<? extends T> removeFrom;

            public boolean hasNext() {
                boolean currentHasNext;
                while (true) {
                    currentHasNext = ((Iterator) Preconditions.checkNotNull(this.current)).hasNext();
                    if (currentHasNext || !inputs.hasNext()) {
                        return currentHasNext;
                    }
                    this.current = (Iterator) inputs.next();
                }
                return currentHasNext;
            }

            public T next() {
                if (hasNext()) {
                    this.removeFrom = this.current;
                    return this.current.next();
                }
                throw new NoSuchElementException();
            }

            public void remove() {
                CollectPreconditions.checkRemove(this.removeFrom != null);
                this.removeFrom.remove();
                this.removeFrom = null;
            }
        };
    }

    public static <T> UnmodifiableIterator<List<T>> partition(Iterator<T> iterator, int size) {
        return partitionImpl(iterator, size, false);
    }

    public static <T> UnmodifiableIterator<List<T>> paddedPartition(Iterator<T> iterator, int size) {
        return partitionImpl(iterator, size, true);
    }

    private static <T> UnmodifiableIterator<List<T>> partitionImpl(final Iterator<T> iterator, final int size, final boolean pad) {
        Preconditions.checkNotNull(iterator);
        Preconditions.checkArgument(size > 0);
        return new UnmodifiableIterator<List<T>>() {
            public boolean hasNext() {
                return iterator.hasNext();
            }

            public List<T> next() {
                if (hasNext()) {
                    Object[] array = new Object[size];
                    int count = 0;
                    while (count < size && iterator.hasNext()) {
                        array[count] = iterator.next();
                        count++;
                    }
                    for (int i = count; i < size; i++) {
                        array[i] = null;
                    }
                    List<T> list = Collections.unmodifiableList(Arrays.asList(array));
                    return (pad || count == size) ? list : list.subList(0, count);
                } else {
                    throw new NoSuchElementException();
                }
            }
        };
    }

    public static <T> UnmodifiableIterator<T> filter(final Iterator<T> unfiltered, final Predicate<? super T> predicate) {
        Preconditions.checkNotNull(unfiltered);
        Preconditions.checkNotNull(predicate);
        return new AbstractIterator<T>() {
            protected T computeNext() {
                while (unfiltered.hasNext()) {
                    T element = unfiltered.next();
                    if (predicate.apply(element)) {
                        return element;
                    }
                }
                return endOfData();
            }
        };
    }

    @GwtIncompatible("Class.isInstance")
    public static <T> UnmodifiableIterator<T> filter(Iterator<?> unfiltered, Class<T> type) {
        return filter((Iterator) unfiltered, Predicates.instanceOf(type));
    }

    public static <T> boolean any(Iterator<T> iterator, Predicate<? super T> predicate) {
        return indexOf(iterator, predicate) != -1;
    }

    public static <T> boolean all(Iterator<T> iterator, Predicate<? super T> predicate) {
        Preconditions.checkNotNull(predicate);
        while (iterator.hasNext()) {
            if (!predicate.apply(iterator.next())) {
                return false;
            }
        }
        return true;
    }

    public static <T> T find(Iterator<T> iterator, Predicate<? super T> predicate) {
        return filter((Iterator) iterator, (Predicate) predicate).next();
    }

    @Nullable
    public static <T> T find(Iterator<? extends T> iterator, Predicate<? super T> predicate, @Nullable T defaultValue) {
        return getNext(filter((Iterator) iterator, (Predicate) predicate), defaultValue);
    }

    public static <T> Optional<T> tryFind(Iterator<T> iterator, Predicate<? super T> predicate) {
        UnmodifiableIterator<T> filteredIterator = filter((Iterator) iterator, (Predicate) predicate);
        return filteredIterator.hasNext() ? Optional.of(filteredIterator.next()) : Optional.absent();
    }

    public static <T> int indexOf(Iterator<T> iterator, Predicate<? super T> predicate) {
        Preconditions.checkNotNull(predicate, "predicate");
        int i = 0;
        while (iterator.hasNext()) {
            if (predicate.apply(iterator.next())) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public static <F, T> Iterator<T> transform(Iterator<F> fromIterator, final Function<? super F, ? extends T> function) {
        Preconditions.checkNotNull(function);
        return new TransformedIterator<F, T>(fromIterator) {
            T transform(F from) {
                return function.apply(from);
            }
        };
    }

    public static <T> T get(Iterator<T> iterator, int position) {
        checkNonnegative(position);
        int skipped = advance(iterator, position);
        if (iterator.hasNext()) {
            return iterator.next();
        }
        throw new IndexOutOfBoundsException("position (" + position + ") must be less than the number of elements that remained (" + skipped + ")");
    }

    static void checkNonnegative(int position) {
        if (position < 0) {
            throw new IndexOutOfBoundsException("position (" + position + ") must not be negative");
        }
    }

    @Nullable
    public static <T> T get(Iterator<? extends T> iterator, int position, @Nullable T defaultValue) {
        checkNonnegative(position);
        advance(iterator, position);
        return getNext(iterator, defaultValue);
    }

    @Nullable
    public static <T> T getNext(Iterator<? extends T> iterator, @Nullable T defaultValue) {
        return iterator.hasNext() ? iterator.next() : defaultValue;
    }

    public static <T> T getLast(Iterator<T> iterator) {
        T current;
        do {
            current = iterator.next();
        } while (iterator.hasNext());
        return current;
    }

    @Nullable
    public static <T> T getLast(Iterator<? extends T> iterator, @Nullable T defaultValue) {
        return iterator.hasNext() ? getLast(iterator) : defaultValue;
    }

    public static int advance(Iterator<?> iterator, int numberToAdvance) {
        Preconditions.checkNotNull(iterator);
        Preconditions.checkArgument(numberToAdvance >= 0, "numberToAdvance must be nonnegative");
        int i = 0;
        while (i < numberToAdvance && iterator.hasNext()) {
            iterator.next();
            i++;
        }
        return i;
    }

    public static <T> Iterator<T> limit(final Iterator<T> iterator, final int limitSize) {
        Preconditions.checkNotNull(iterator);
        Preconditions.checkArgument(limitSize >= 0, "limit is negative");
        return new Iterator<T>() {
            private int count;

            public boolean hasNext() {
                return this.count < limitSize && iterator.hasNext();
            }

            public T next() {
                if (hasNext()) {
                    this.count++;
                    return iterator.next();
                }
                throw new NoSuchElementException();
            }

            public void remove() {
                iterator.remove();
            }
        };
    }

    public static <T> Iterator<T> consumingIterator(final Iterator<T> iterator) {
        Preconditions.checkNotNull(iterator);
        return new UnmodifiableIterator<T>() {
            public boolean hasNext() {
                return iterator.hasNext();
            }

            public T next() {
                T next = iterator.next();
                iterator.remove();
                return next;
            }

            public String toString() {
                return "Iterators.consumingIterator(...)";
            }
        };
    }

    @Nullable
    static <T> T pollNext(Iterator<T> iterator) {
        if (!iterator.hasNext()) {
            return null;
        }
        T result = iterator.next();
        iterator.remove();
        return result;
    }

    static void clear(Iterator<?> iterator) {
        Preconditions.checkNotNull(iterator);
        while (iterator.hasNext()) {
            iterator.next();
            iterator.remove();
        }
    }

    public static <T> UnmodifiableIterator<T> forArray(T... array) {
        return forArray(array, 0, array.length, 0);
    }

    static <T> UnmodifiableListIterator<T> forArray(final T[] array, final int offset, int length, int index) {
        Preconditions.checkArgument(length >= 0);
        Preconditions.checkPositionIndexes(offset, offset + length, array.length);
        Preconditions.checkPositionIndex(index, length);
        if (length == 0) {
            return emptyListIterator();
        }
        return new AbstractIndexedListIterator<T>(length, index) {
            protected T get(int index) {
                return array[offset + index];
            }
        };
    }

    public static <T> UnmodifiableIterator<T> singletonIterator(@Nullable final T value) {
        return new UnmodifiableIterator<T>() {
            boolean done;

            public boolean hasNext() {
                return !this.done;
            }

            public T next() {
                if (this.done) {
                    throw new NoSuchElementException();
                }
                this.done = true;
                return value;
            }
        };
    }

    public static <T> UnmodifiableIterator<T> forEnumeration(final Enumeration<T> enumeration) {
        Preconditions.checkNotNull(enumeration);
        return new UnmodifiableIterator<T>() {
            public boolean hasNext() {
                return enumeration.hasMoreElements();
            }

            public T next() {
                return enumeration.nextElement();
            }
        };
    }

    public static <T> Enumeration<T> asEnumeration(final Iterator<T> iterator) {
        Preconditions.checkNotNull(iterator);
        return new Enumeration<T>() {
            public boolean hasMoreElements() {
                return iterator.hasNext();
            }

            public T nextElement() {
                return iterator.next();
            }
        };
    }

    public static <T> PeekingIterator<T> peekingIterator(Iterator<? extends T> iterator) {
        if (iterator instanceof PeekingImpl) {
            return (PeekingImpl) iterator;
        }
        return new PeekingImpl(iterator);
    }

    @Deprecated
    public static <T> PeekingIterator<T> peekingIterator(PeekingIterator<T> iterator) {
        return (PeekingIterator) Preconditions.checkNotNull(iterator);
    }

    @Beta
    public static <T> UnmodifiableIterator<T> mergeSorted(Iterable<? extends Iterator<? extends T>> iterators, Comparator<? super T> comparator) {
        Preconditions.checkNotNull(iterators, "iterators");
        Preconditions.checkNotNull(comparator, "comparator");
        return new MergingIterator(iterators, comparator);
    }

    static <T> ListIterator<T> cast(Iterator<T> iterator) {
        return (ListIterator) iterator;
    }
}
