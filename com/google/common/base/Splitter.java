package com.google.common.base;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.CheckReturnValue;

@GwtCompatible(emulated = true)
public final class Splitter {
    private final int limit;
    private final boolean omitEmptyStrings;
    private final Strategy strategy;
    private final CharMatcher trimmer;

    private static abstract class SplittingIterator extends AbstractIterator<String> {
        int limit;
        int offset = 0;
        final boolean omitEmptyStrings;
        final CharSequence toSplit;
        final CharMatcher trimmer;

        abstract int separatorEnd(int i);

        abstract int separatorStart(int i);

        protected SplittingIterator(Splitter splitter, CharSequence toSplit) {
            this.trimmer = splitter.trimmer;
            this.omitEmptyStrings = splitter.omitEmptyStrings;
            this.limit = splitter.limit;
            this.toSplit = toSplit;
        }

        protected String computeNext() {
            int nextStart = this.offset;
            while (this.offset != -1) {
                int end;
                int start = nextStart;
                int separatorPosition = separatorStart(this.offset);
                if (separatorPosition == -1) {
                    end = this.toSplit.length();
                    this.offset = -1;
                } else {
                    end = separatorPosition;
                    this.offset = separatorEnd(separatorPosition);
                }
                if (this.offset == nextStart) {
                    this.offset++;
                    if (this.offset >= this.toSplit.length()) {
                        this.offset = -1;
                    }
                } else {
                    while (start < end && this.trimmer.matches(this.toSplit.charAt(start))) {
                        start++;
                    }
                    while (end > start && this.trimmer.matches(this.toSplit.charAt(end - 1))) {
                        end--;
                    }
                    if (this.omitEmptyStrings && start == end) {
                        nextStart = this.offset;
                    } else {
                        if (this.limit == 1) {
                            end = this.toSplit.length();
                            this.offset = -1;
                            while (end > start && this.trimmer.matches(this.toSplit.charAt(end - 1))) {
                                end--;
                            }
                        } else {
                            this.limit--;
                        }
                        return this.toSplit.subSequence(start, end).toString();
                    }
                }
            }
            return (String) endOfData();
        }
    }

    private interface Strategy {
        Iterator<String> iterator(Splitter splitter, CharSequence charSequence);
    }

    @Beta
    public static final class MapSplitter {
        private static final String INVALID_ENTRY_MESSAGE = "Chunk [%s] is not a valid entry";
        private final Splitter entrySplitter;
        private final Splitter outerSplitter;

        private MapSplitter(Splitter outerSplitter, Splitter entrySplitter) {
            this.outerSplitter = outerSplitter;
            this.entrySplitter = (Splitter) Preconditions.checkNotNull(entrySplitter);
        }

        public Map<String, String> split(CharSequence sequence) {
            Map<String, String> map = new LinkedHashMap();
            for (String entry : this.outerSplitter.split(sequence)) {
                boolean z;
                Iterator<String> entryFields = this.entrySplitter.splittingIterator(entry);
                Preconditions.checkArgument(entryFields.hasNext(), INVALID_ENTRY_MESSAGE, entry);
                String key = (String) entryFields.next();
                if (map.containsKey(key)) {
                    z = false;
                } else {
                    z = true;
                }
                Preconditions.checkArgument(z, "Duplicate key [%s] found.", key);
                Preconditions.checkArgument(entryFields.hasNext(), INVALID_ENTRY_MESSAGE, entry);
                map.put(key, (String) entryFields.next());
                if (entryFields.hasNext()) {
                    z = false;
                } else {
                    z = true;
                }
                Preconditions.checkArgument(z, INVALID_ENTRY_MESSAGE, entry);
            }
            return Collections.unmodifiableMap(map);
        }
    }

    private Splitter(Strategy strategy) {
        this(strategy, false, CharMatcher.NONE, Integer.MAX_VALUE);
    }

    private Splitter(Strategy strategy, boolean omitEmptyStrings, CharMatcher trimmer, int limit) {
        this.strategy = strategy;
        this.omitEmptyStrings = omitEmptyStrings;
        this.trimmer = trimmer;
        this.limit = limit;
    }

    public static Splitter on(char separator) {
        return on(CharMatcher.is(separator));
    }

    public static Splitter on(final CharMatcher separatorMatcher) {
        Preconditions.checkNotNull(separatorMatcher);
        return new Splitter(new Strategy() {
            public SplittingIterator iterator(Splitter splitter, CharSequence toSplit) {
                return new SplittingIterator(splitter, toSplit) {
                    int separatorStart(int start) {
                        return separatorMatcher.indexIn(this.toSplit, start);
                    }

                    int separatorEnd(int separatorPosition) {
                        return separatorPosition + 1;
                    }
                };
            }
        });
    }

    public static Splitter on(final String separator) {
        Preconditions.checkArgument(separator.length() != 0, "The separator may not be the empty string.");
        return new Splitter(new Strategy() {
            public SplittingIterator iterator(Splitter splitter, CharSequence toSplit) {
                return new SplittingIterator(splitter, toSplit) {
                    public int separatorStart(int start) {
                        int separatorLength = separator.length();
                        int p = start;
                        int last = this.toSplit.length() - separatorLength;
                        while (p <= last) {
                            int i = 0;
                            while (i < separatorLength) {
                                if (this.toSplit.charAt(i + p) != separator.charAt(i)) {
                                    p++;
                                } else {
                                    i++;
                                }
                            }
                            return p;
                        }
                        return -1;
                    }

                    public int separatorEnd(int separatorPosition) {
                        return separator.length() + separatorPosition;
                    }
                };
            }
        });
    }

    @GwtIncompatible("java.util.regex")
    public static Splitter on(final Pattern separatorPattern) {
        boolean z;
        Preconditions.checkNotNull(separatorPattern);
        if (separatorPattern.matcher("").matches()) {
            z = false;
        } else {
            z = true;
        }
        Preconditions.checkArgument(z, "The pattern may not match the empty string: %s", separatorPattern);
        return new Splitter(new Strategy() {
            public SplittingIterator iterator(Splitter splitter, CharSequence toSplit) {
                final Matcher matcher = separatorPattern.matcher(toSplit);
                return new SplittingIterator(splitter, toSplit) {
                    public int separatorStart(int start) {
                        return matcher.find(start) ? matcher.start() : -1;
                    }

                    public int separatorEnd(int separatorPosition) {
                        return matcher.end();
                    }
                };
            }
        });
    }

    @GwtIncompatible("java.util.regex")
    public static Splitter onPattern(String separatorPattern) {
        return on(Pattern.compile(separatorPattern));
    }

    public static Splitter fixedLength(final int length) {
        Preconditions.checkArgument(length > 0, "The length may not be less than 1");
        return new Splitter(new Strategy() {
            public SplittingIterator iterator(Splitter splitter, CharSequence toSplit) {
                return new SplittingIterator(splitter, toSplit) {
                    public int separatorStart(int start) {
                        int nextChunkStart = start + length;
                        return nextChunkStart < this.toSplit.length() ? nextChunkStart : -1;
                    }

                    public int separatorEnd(int separatorPosition) {
                        return separatorPosition;
                    }
                };
            }
        });
    }

    @CheckReturnValue
    public Splitter omitEmptyStrings() {
        return new Splitter(this.strategy, true, this.trimmer, this.limit);
    }

    @CheckReturnValue
    public Splitter limit(int limit) {
        boolean z;
        if (limit > 0) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.checkArgument(z, "must be greater than zero: %s", Integer.valueOf(limit));
        return new Splitter(this.strategy, this.omitEmptyStrings, this.trimmer, limit);
    }

    @CheckReturnValue
    public Splitter trimResults() {
        return trimResults(CharMatcher.WHITESPACE);
    }

    @CheckReturnValue
    public Splitter trimResults(CharMatcher trimmer) {
        Preconditions.checkNotNull(trimmer);
        return new Splitter(this.strategy, this.omitEmptyStrings, trimmer, this.limit);
    }

    public Iterable<String> split(final CharSequence sequence) {
        Preconditions.checkNotNull(sequence);
        return new Iterable<String>() {
            public Iterator<String> iterator() {
                return Splitter.this.splittingIterator(sequence);
            }

            public String toString() {
                return Joiner.on(", ").appendTo(new StringBuilder().append('['), (Iterable) this).append(']').toString();
            }
        };
    }

    private Iterator<String> splittingIterator(CharSequence sequence) {
        return this.strategy.iterator(this, sequence);
    }

    @Beta
    public List<String> splitToList(CharSequence sequence) {
        Preconditions.checkNotNull(sequence);
        Iterator<String> iterator = splittingIterator(sequence);
        List<String> result = new ArrayList();
        while (iterator.hasNext()) {
            result.add(iterator.next());
        }
        return Collections.unmodifiableList(result);
    }

    @CheckReturnValue
    @Beta
    public MapSplitter withKeyValueSeparator(String separator) {
        return withKeyValueSeparator(on(separator));
    }

    @CheckReturnValue
    @Beta
    public MapSplitter withKeyValueSeparator(char separator) {
        return withKeyValueSeparator(on(separator));
    }

    @CheckReturnValue
    @Beta
    public MapSplitter withKeyValueSeparator(Splitter keyValueSplitter) {
        return new MapSplitter(keyValueSplitter);
    }
}
