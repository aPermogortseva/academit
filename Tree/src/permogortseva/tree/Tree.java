package permogortseva.tree;

import java.util.*;
import java.util.function.Consumer;

public class Tree<T> {
    private final Comparator<? super T> comparator;

    private TreeNode<T> root;
    private int size = 0;

    public Tree() {
        comparator = null;
    }

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
        if (size == 0) {
            root = new TreeNode<>(data);
            size++;

            return;
        }

        TreeNode<T> current = root;

        while (true) {
            if (comparator == null) {
                //noinspection unchecked
                Comparable<? super T> comparableData = (Comparable<? super T>) data;

                if (comparableData.compareTo(current.getData()) < 0) {
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
            } else {
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
            }

            size++;

            return;
        }
    }

    public int size() {
        return size;
    }

    private TreeNode<T> getNodeParent(TreeNode<T> node) {
        T data = node.getData();

        if (size == 1) {
            throw new IllegalArgumentException("Невозможно получить узел родителя, т.к. в дереве только один элемент.");
        }

        if (getNode(data) == null) {
            throw new IllegalArgumentException("Невозможно получить узел родителя, т.к. узел ребёнка отсутствует в дереве.");
        }

        TreeNode<T> current = root;
        TreeNode<T> parent = current;

        if (comparator == null) {
            //noinspection unchecked
            Comparable<? super T> comparableData = (Comparable<? super T>) data;

            while (true) {
                if (comparableData.compareTo(current.getData()) == 0) {
                    return parent;
                }

                if (comparableData.compareTo(current.getData()) < 0) {
                    if (current.getLeftChild() != null) {
                        parent = current;
                        current = current.getLeftChild();
                    }
                } else {
                    if (current.getRightChild() != null) {
                        parent = current;
                        current = current.getRightChild();
                    }
                }
            }
        } else {
            if (comparator.compare(current.getData(), data) == 0) {
                return current;
            }

            while (true) {
                if (comparator.compare(current.getData(), data) == 0) {
                    return parent;
                }

                if (comparator.compare(current.getData(), data) > 0) {
                    if (current.getLeftChild() != null) {
                        parent = current;
                        current = current.getLeftChild();
                    }
                } else {
                    if (current.getRightChild() != null) {
                        parent = current;
                        current = current.getRightChild();
                    }
                }
            }
        }
    }

    private TreeNode<T> getNode(T data) {
        TreeNode<T> current = root;

        if (comparator == null) {
            //noinspection unchecked
            Comparable<? super T> comparableData = (Comparable<? super T>) data;

            while (true) {
                if (comparableData.compareTo(current.getData()) == 0) {
                    return current;
                }

                if (comparableData.compareTo(current.getData()) < 0) {
                    if (current.getLeftChild() != null) {
                        current = current.getLeftChild();
                    } else {
                        return null;
                    }
                } else {
                    if (current.getRightChild() != null) {
                        current = current.getRightChild();
                    } else {
                        return null;
                    }
                }
            }
        } else {
            while (true) {
                if (comparator.compare(current.getData(), data) == 0) {
                    return current;
                }

                if (comparator.compare(current.getData(), data) > 0) {
                    if (current.getLeftChild() != null) {
                        current = current.getLeftChild();
                    } else {
                        return null;
                    }
                } else {
                    if (current.getRightChild() != null) {
                        current = current.getRightChild();
                    } else {
                        return null;
                    }
                }
            }
        }
    }

    public boolean find(T data) {
        if (size == 0) {
            return false;
        }

        return getNode(data) != null;
    }

    public boolean remove(T data) {
        TreeNode<T> current = root;
        TreeNode<T> parent;

        if (comparator == null) {
            //noinspection unchecked
            Comparable<? super T> comparableCurrentData = (Comparable<? super T>) current.getData();

            if (size == 1) {
                if (comparableCurrentData.compareTo(data) == 0) {
                    root = null;
                    size--;

                    return true;
                }

                return false;
            }

            current = getNode(data);

            if (current != null) {
                //noinspection unchecked
                comparableCurrentData = (Comparable<? super T>) current.getData();

                if (comparableCurrentData.compareTo(root.getData()) == 0) {
                    if (root.getRightChild() == null) {
                        root = root.getLeftChild();
                    } else {
                        root = root.getRightChild();
                        root.setLeftChild(current.getLeftChild());
                    }

                    size--;
                    return true;
                }

                parent = getNodeParent(current);

                if (current.getRightChild() == null && current.getLeftChild() == null) {
                    if (comparableCurrentData.compareTo(parent.getData()) < 0) {
                        parent.setLeftChild(null);
                    } else {
                        parent.setRightChild(null);
                    }
                } else if (current.getLeftChild() != null && current.getRightChild() == null) {
                    if (comparableCurrentData.compareTo(parent.getData()) < 0) {
                        parent.setLeftChild(current.getLeftChild());
                    } else {
                        parent.setRightChild(current.getLeftChild());
                    }
                } else if (current.getLeftChild() == null && current.getRightChild() != null) {
                    if (comparableCurrentData.compareTo(parent.getData()) < 0) {
                        parent.setLeftChild(current.getRightChild());
                    } else {
                        parent.setRightChild(current.getRightChild());
                    }
                } else if (current.getRightChild() != null) {
                    TreeNode<T> minNode = current.getRightChild();
                    TreeNode<T> minNodeParent = current;

                    if (minNode.getLeftChild() == null) {
                        if (comparableCurrentData.compareTo(parent.getData()) < 0) {
                            parent.setLeftChild(minNode);
                        } else {
                            parent.setRightChild(minNode);
                        }

                        minNode.setLeftChild(current.getLeftChild());
                    } else {
                        while (minNode.getLeftChild() != null) {
                            minNodeParent = minNode;
                            minNode = minNode.getLeftChild();
                        }

                        minNodeParent.setLeftChild(minNode.getRightChild());

                        minNode.setLeftChild(current.getLeftChild());
                        minNode.setRightChild(current.getRightChild());

                        if (comparableCurrentData.compareTo(parent.getData()) < 0) {
                            parent.setLeftChild(minNode);
                        } else {
                            parent.setRightChild(minNode);
                        }
                    }
                }

                size--;

                return true;
            }

            return false;
        } else {
            if (size == 1) {
                if (comparator.compare(current.getData(), data) == 0) {
                    root = null;
                    size--;

                    return true;
                }
                return false;
            }

            if (comparator.compare(root.getData(), data) == 0) {
                if (root.getRightChild() == null) {
                    root = root.getLeftChild();
                } else {
                    root = root.getRightChild();
                    root.setLeftChild(current.getLeftChild());
                }

                size--;
                return true;
            }

            current = getNode(data);

            if (current != null) {
                parent = getNodeParent(current);

                if (current.getRightChild() == null && current.getLeftChild() == null) {
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
                } else if (current.getRightChild() != null) {
                    TreeNode<T> minNode = current.getRightChild();
                    TreeNode<T> minNodeParent = current;

                    if (minNode.getLeftChild() == null) {
                        if (comparator.compare(current.getData(), parent.getData()) < 0) {
                            parent.setLeftChild(minNode);
                        } else {
                            parent.setRightChild(minNode);
                        }

                        minNode.setLeftChild(current.getLeftChild());
                    } else {
                        while (minNode.getLeftChild() != null) {
                            minNodeParent = minNode;
                            minNode = minNode.getLeftChild();
                        }

                        minNodeParent.setLeftChild(minNode.getRightChild());

                        minNode.setLeftChild(current.getLeftChild());
                        minNode.setRightChild(current.getRightChild());

                        if (comparator.compare(current.getData(), parent.getData()) < 0) {
                            parent.setLeftChild(minNode);
                        } else {
                            parent.setRightChild(minNode);
                        }
                    }
                }

                size--;

                return true;
            }
            return false;
        }
    }

    public void traverseBreadthFirst(Consumer<T> consumer) {
        if (size == 0) {
            return;
        }

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
        if (size == 0) {
            return;
        }

        Deque<TreeNode<T>> deque = new LinkedList<>();

        deque.addFirst(root);

        while (!deque.isEmpty()) {
            TreeNode<T> current = deque.removeLast();
            consumer.accept(current.getData());


            if (current.getRightChild() != null) {
                deque.addLast(current.getRightChild());
            }

            if (current.getLeftChild() != null) {
                deque.addLast(current.getLeftChild());
            }
        }
    }

    public void traverseDeepFirstRecursive(Consumer<T> consumer) {
        if (size == 0) {
            return;
        }

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
