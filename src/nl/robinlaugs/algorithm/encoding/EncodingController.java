package nl.robinlaugs.algorithm.encoding;

import nl.robinlaugs.library.Message;
import nl.robinlaugs.library.Node;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.Long.sum;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.*;

public class EncodingController implements Encoding {

    private static final Logger logger = Logger.getLogger(EncodingController.class.getName());

    private static final char BINARY_ONE = '1';
    private static final char BINARY_ZERO = '0';

    @Override
    public Message encode(String message) {
        Map<Character, Long> characterFrequency = countCharacterFrequency(message);
        Queue<Node> nodes = buildNodeQueue(characterFrequency);
        Node trie = buildTrie(nodes);
        Map<Character, String> codeMap = buildCodeMap(trie, new HashMap<>(), new StringBuilder());
        String encodedMessage = encodeMessage(codeMap, message);

        logger.log(Level.INFO, "Message: {0}", message);
        logger.log(Level.INFO, "Encoded: {0}", encodedMessage);

        return new Message(encodedMessage, trie);
    }

    private Map<Character, Long> countCharacterFrequency(String message) {
        return message.chars()
                .mapToObj(i -> (char) i)
                .collect(groupingBy(identity(), counting()));
    }

    private Queue<Node> buildNodeQueue(Map<Character, Long> characterFrequency) {
        return characterFrequency.entrySet().stream()
                .map(e -> new Node(e.getValue(), e.getKey()))
                .collect(toCollection(PriorityQueue::new));
    }

    private Node buildTrie(Queue<Node> nodes) {
        while (nodes.size() > 1) {
            Node left = nodes.poll();
            Node right = nodes.poll();
            Long sum = sum(left.getFrequency(), right.getFrequency());

            nodes.offer(new Node(sum, left, right));
        }

        return nodes.peek();
    }

    private Map<Character, String> buildCodeMap(Node trie, Map<Character, String> codeMap, StringBuilder builder) {
        if (trie.isInternal()) {
            buildCodeMap(trie.getLeft(), codeMap, new StringBuilder(builder).append(BINARY_ZERO));
            buildCodeMap(trie.getRight(), codeMap, new StringBuilder(builder).append(BINARY_ONE));
        } else {
            codeMap.put(trie.getCharacter(), builder.toString());
        }

        return codeMap;
    }

    private String encodeMessage(Map<Character, String> codeMap, String message) {
        return message.chars()
                .mapToObj(i -> (char) i)
                .map(codeMap::get)
                .collect(joining());
    }

}
