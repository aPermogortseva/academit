package permogortseva.tree_main;

import permogortseva.tree.Tree;

public class Main {
    public static void main(String[] args) {
        Tree<Integer> tree = new Tree<>(18);
        tree.add(10);

        tree.add(4);
        tree.add(14);

        tree.add(12);

        tree.add(16);
        tree.add(13);

        System.out.println("Количество элементов в дереве: " + tree.size());

        System.out.println("Заданное число найдено: " + tree.find(2));

        System.out.println("Число найдено и удалёно: " + tree.remove(10));

        System.out.println("Обход дерева в ширину:");
        tree.traverseBreadthFirst(System.out::println);

        System.out.println("Обход дерева в глубину без рекурсии:");
        tree.traverseDeepFirst(System.out::println);

        System.out.println("Перефиксный обход дерева в глубину с рекурсией:");
        tree.traverseDeepFirstRecursive(System.out::println);
    }
}
