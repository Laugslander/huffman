package nl.robinlaugs;

import nl.robinlaugs.library.Message;

public interface Huffman {

    Message encode(String message);

    String decode(Message message);

}
