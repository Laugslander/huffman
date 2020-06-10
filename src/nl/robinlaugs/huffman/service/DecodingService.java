package nl.robinlaugs.huffman.service;

import nl.robinlaugs.huffman.model.Message;
import nl.robinlaugs.huffman.model.Node;

import java.util.Iterator;
import java.util.List;

public class DecodingService extends HuffmanService {

    public static String decode(Message message) {
        StringBuilder stringBuilder = new StringBuilder();

        List<Character> characters = splitMessageIntoCharacters(message.message());
        Iterator<Character> characterIterator = characters.iterator();

        while (characterIterator.hasNext()) {
            Node node = message.trie();

            while (node.isInternal()) {
                node = node.getNode(characterIterator.next());
            }

            stringBuilder.append(node.getCharacter());
        }

        return stringBuilder.toString();
    }

}
