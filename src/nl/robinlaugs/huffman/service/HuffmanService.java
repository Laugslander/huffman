package nl.robinlaugs.huffman.service;

import java.util.List;

import static java.util.stream.Collectors.toList;

public abstract class HuffmanService {

    protected static List<Character> splitMessageIntoCharacters(String message) {
        return message.chars()
                .mapToObj(i -> (char) i)
                .collect(toList());
    }

}
