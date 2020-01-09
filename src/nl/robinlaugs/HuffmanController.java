package nl.robinlaugs;

import nl.robinlaugs.algorithm.decoding.Decoding;
import nl.robinlaugs.algorithm.decoding.DecodingController;
import nl.robinlaugs.algorithm.encoding.Encoding;
import nl.robinlaugs.algorithm.encoding.EncodingController;
import nl.robinlaugs.library.Message;

public class HuffmanController implements Huffman {

    private final Encoding encoding;
    private final Decoding decoding;

    HuffmanController() {
        encoding = new EncodingController();
        decoding = new DecodingController();
    }

    @Override
    public Message encode(String message) {
        return encoding.encode(message);
    }

    @Override
    public String decode(Message message) {
        return decoding.decode(message);
    }

}
