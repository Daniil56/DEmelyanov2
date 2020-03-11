package ru.job4j.io;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Tree<T> {
    private T value;
    private List<Tree<T>> children;

    public Tree(T value) {
        this.value = value;
        this.children = new ArrayList<>();
    }

    public static <T> Tree<T> of(T value) {
        return new Tree<>(value);
    }

    public Tree<T> addChild(T value) {
        Tree<T> child = new Tree<>(value);
        children.add(child);
        return child;
    }

    public void addChildren(T[] childArray) {
        Arrays.stream(childArray).map(Tree::new).forEach(child -> this.children.add(child));
    }

    public T getValue() {
        return value;
    }

    public List<Tree<T>> getChildren() {
        return children;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Tree<?> tree = (Tree<?>) o;

        return new EqualsBuilder()
                .append(value, tree.value)
                .append(children, tree.children)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(value)
                .append(children)
                .toHashCode();
    }
}
