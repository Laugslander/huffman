package nl.robinlaugs.huffman.service;

import nl.robinlaugs.huffman.model.Message;
import nl.robinlaugs.huffman.model.Node;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toCollection;

public class EncodingService extends HuffmanService {

    public static Message encode(String message) {
        List<Character> characters = splitMessageIntoCharacters(message);

        Node trie = createTrie(characters);
        Map<Character, String> codeMap = createCodeMap(trie, new HashMap<>(), new StringBuilder());

        String encodedMessage = encodeCharacters(characters, codeMap);

        return new Message(encodedMessage, trie);
    }

    private static Node createTrie(List<Character> characters) {
        Map<Character, Long> characterFrequency = characters.stream()
                .collect(groupingBy(identity(), counting()));

        Queue<Node> nodes = characterFrequency.entrySet().stream()
                .map(e -> new Node(e.getValue(), e.getKey()))
                .collect(toCollection(PriorityQueue::new));

        return nodes.stream()
                .reduce((n1, n2) -> new Node(n1.getFrequency() + n2.getFrequency(), n1, n2))
                .orElseThrow(() -> new RuntimeException("An error occurred while building the trie"));
    }

    private static Map<Character, String> createCodeMap(Node trie, Map<Character, String> codeMap, StringBuilder builder) {
        if (trie.isInternal()) {
            createCodeMap(trie.getLeft(), codeMap, new StringBuilder(builder).append('0'));
            createCodeMap(trie.getRight(), codeMap, new StringBuilder(builder).append('1'));
        } else {
            codeMap.put(trie.getCharacter(), builder.toString());
        }

        return codeMap;
    }

    private static String encodeCharacters(List<Character> characters, Map<Character, String> codeMap) {
        return characters.stream()
                .map(codeMap::get)
                .collect(joining());
    }

}
