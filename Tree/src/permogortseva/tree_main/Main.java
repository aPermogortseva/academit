package permogortseva.tree_main;

import permogortseva.tree.Tree;

public class Main {
    public static void main(String[] args) {
        Tree<Integer> tree = new Tree<>(Integer::compareTo, 8);

        tree.add(0);
        tree.add(10);

        tree.add(-1);
        tree.add(2);
        tree.add(14);

        tree.add(1);
        tree.add(7);
        tree.add(13);

        tree.add(5);
        tree.add(7);

        tree.add(4);
        tree.add(6);

        tree.add(3);

        System.out.println("Количество элементов в дереве: " + tree.size());

        System.out.println("Заданное число найдено: " + tree.find(-1));

        System.out.println("Число найдено и удалёно: " + tree.remove(2));

        System.out.println("Обход дерева в ширину:");
        tree.traverseBreadthFirst(System.out::println);

        System.out.println("Обход дерева в глубину без рекурсии:");
        tree.traverseDeepFirst(System.out::println);

        System.out.println("Перефиксный обход дерева в глубину с рекурсией:");
        tree.traverseDeepFirstRecursive(System.out::println);
    }
}
