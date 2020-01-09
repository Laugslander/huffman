package nl.robinlaugs.algorithm.decoding;

import nl.robinlaugs.library.Message;
import nl.robinlaugs.library.Node;

import java.util.logging.Level;
import java.util.logging.Logger;

public class DecodingController implements Decoding {

    private static final Logger logger = Logger.getLogger(DecodingController.class.getName());

    @Override
    public String decode(Message message) {
        String decodedMessage = decodeMessage(message.getMessage(), message.getTrie());

        logger.log(Level.INFO, "Decoded: {0}", decodedMessage);

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
