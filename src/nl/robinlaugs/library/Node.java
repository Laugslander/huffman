package nl.robinlaugs.library;

import static java.lang.Character.compare;
import static java.lang.Long.compare;

public class Node implements Comparable<Node> {

    private final Long frequency;
    private char character;
    private Node left, right;

    public Node(Long frequency, char character) {
        this.frequency = frequency;
        this.character = character;
    }

    public Node(Long frequency, Node left, Node right) {
        this.frequency = frequency;
        this.left = left;
        this.right = right;
    }

    @Override
    public int compareTo(Node node) {
        int frequencyResult = compare(frequency, node.getFrequency());
        int characterResult = compare(character, node.getCharacter());

        return frequencyResult == 0 ? characterResult : frequencyResult;
    }

    public boolean isInternal() {
        return character == Character.MIN_VALUE;
    }

    public Long getFrequency() {
        return frequency;
    }

    public char getCharacter() {
        return character;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

}
