package nl.robinlaugs.algorithm.decoding;

import nl.robinlaugs.library.Message;
import nl.robinlaugs.library.Node;
import nl.robinlaugs.statistics.Statistics;

public class DecodingController implements Decoding {

    private final Statistics statistics;

    public DecodingController(Statistics statistics) {
        this.statistics = statistics;
    }

    @Override
    public String decode(Message message) {
        String decodedMessage = decodeMessage(message.getMessage(), message.getTrie());

        statistics.addStatistic(String.format("Decoded: %s", decodedMessage));

        return decodedMessage;
    }

    private String decodeMessage(String message, Node trie) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < (message.length() - 1); ) {
            Node node = trie;

            while (node.isInternal()) {
                node = message.charAt(i) == '1' ? node.getRight() : node.getLeft();

                i++;
            }

            builder.append(node.getCharacter());
        }

        return builder.toString();
    }

}
