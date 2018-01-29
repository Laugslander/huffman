package nl.robinlaugs.library;

public class Message {

    private final String message;
    private final Node trie;

    public Message(String message, Node trie) {
        this.message = message;
        this.trie = trie;
    }

    public String getMessage() {
        return message;
    }

    public Node getTrie() {
        return trie;
    }

}
