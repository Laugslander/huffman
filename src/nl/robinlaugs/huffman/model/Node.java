package nl.robinlaugs.huffman.model;

import static java.util.Comparator.comparing;

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
        return comparing(Node::getFrequency)
                .thenComparing(Node::getCharacter)
                .compare(this, node);
    }

    public boolean isInternal() {
        return character == '\u0000';
    }

    public Node getNode(char character) {
        return character == '1' ? right : left;
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
