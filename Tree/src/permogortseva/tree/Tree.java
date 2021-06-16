package permogortseva.tree;

import java.util.*;
import java.util.function.Consumer;

public class Tree<T> {
    private final Comparator<? super T> comparator;

    private TreeNode<T> root;
    private int size = 0;

    public Tree(Comparator<? super T> comparator, T rootData) {
        this.comparator = comparator;
        root = new TreeNode<>(rootData);

        size++;
    }

    public Tree(T rootData) {
        comparator = null;
        root = new TreeNode<>(rootData);

        size++;
    }

    public void add(T data) {
        if (comparator == null) {
            throw new UnsupportedOperationException("Необходимо реализовать компаратор");
        }

        TreeNode<T> current = root;

        while (true) {

            if (comparator.compare(current.getData(), data) > 0) {
                if (current.getLeftChild() != null) {
                    current = current.getLeftChild();
                    continue;
                }

                current.setLeftChild(new TreeNode<>(data));
            } else {
                if (current.getRightChild() != null) {
                    current = current.getRightChild();
                    continue;
                }

                current.setRightChild(new TreeNode<>(data));
            }

            size++;

            return;
        }
    }

    public int size() {
        return size;
    }

    public boolean find(T data) {
        TreeNode<T> current = root;

        if (comparator == null) {
            throw new UnsupportedOperationException("Необходимо реализовать компаратор");
        }

        if (comparator.compare(current.getData(), data) == 0) {
            return true;
        }

        while (true) {
            if (comparator.compare(current.getData(), data) == 0) {
                return true;
            }

            if (comparator.compare(current.getData(), data) > 0) {
                if (current.getLeftChild() != null) {
                    current = current.getLeftChild();
                } else {
                    return false;
                }
            } else {
                if (current.getRightChild() != null) {
                    current = current.getRightChild();
                } else {
                    return false;
                }
            }
        }
    }

    public boolean remove(T data) {
        if (comparator == null) {
            throw new UnsupportedOperationException("Необходимо реализовать компаратор");
        }

        TreeNode<T> current = root;
        TreeNode<T> parent = current;

        if (size == 1) {
            if (comparator.compare(current.getData(), data) == 0) {
                root = null;
                size--;

                return true;
            } else {
                return false;
            }
        }

        for (int i = 0; i < size; i++) {
            if (comparator.compare(current.getData(), data) == 0) {
                if (current.getRightChild() == null & current.getLeftChild() == null) {
                    if (comparator.compare(parent.getData(), current.getData()) > 0) {
                        parent.setLeftChild(null);
                    } else {
                        parent.setRightChild(null);
                    }
                } else if (current.getLeftChild() != null && current.getRightChild() == null) {
                    if (comparator.compare(parent.getData(), current.getData()) > 0) {
                        parent.setLeftChild(current.getLeftChild());
                    } else {
                        parent.setRightChild(current.getLeftChild());
                    }
                } else if (current.getLeftChild() == null && current.getRightChild() != null) {
                    if (comparator.compare(parent.getData(), current.getData()) > 0) {
                        parent.setLeftChild(current.getRightChild());
                    } else {
                        parent.setRightChild(current.getRightChild());
                    }
                } else {

                    if (comparator.compare(root.getData(), current.getData()) == 0) {
                        root = root.getRightChild();
                        root.setLeftChild(current.getLeftChild());

                        size--;
                        return true;
                    }

                    TreeNode<T> minNode = current.getRightChild();
                    TreeNode<T> minNodeParent = current;

                    while (minNode.getLeftChild() != null) {
                        minNodeParent = minNode;
                        minNode = minNode.getLeftChild();
                    }

                    minNodeParent.setLeftChild(minNode.getRightChild());

                    minNode.setLeftChild(current.getLeftChild());
                    minNode.setRightChild(current.getRightChild());

                    parent.setRightChild(minNode);
                }

                size--;

                return true;
            } else if (comparator.compare(current.getData(), data) >= 0) {
                if (current.getLeftChild() != null) {
                    parent = current;
                    current = current.getLeftChild();
                } else {
                    return false;
                }
            } else if (current.getRightChild() != null) {
                parent = current;
                current = current.getRightChild();
            } else {
                return false;
            }
        }

        return false;
    }

    public void traverseBreadthFirst(Consumer<T> consumer) {
        Queue<TreeNode<T>> queue = new LinkedList<>();

        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode<T> current = queue.remove();
            consumer.accept(current.getData());

            if (current.getLeftChild() != null) {
                queue.add(current.getLeftChild());
            }

            if (current.getRightChild() != null) {
                queue.add(current.getRightChild());
            }
        }
    }

    public void traverseDeepFirst(Consumer<T> consumer) {
        Stack<TreeNode<T>> stack = new Stack<>();

        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode<T> current = stack.pop();
            consumer.accept(current.getData());


            if (current.getRightChild() != null) {
                stack.push(current.getRightChild());
            }

            if (current.getLeftChild() != null) {
                stack.push(current.getLeftChild());
            }
        }
    }

    public void traverseDeepFirstRecursive(Consumer<T> consumer) {
        visit(root, consumer);
    }

    private void visit(TreeNode<T> node, Consumer<T> consumer) {
        consumer.accept(node.getData());

        if (node.getLeftChild() != null) {
            visit(node.getLeftChild(), consumer);
        }

        if (node.getRightChild() != null) {
            visit(node.getRightChild(), consumer);
        }
    }
}
