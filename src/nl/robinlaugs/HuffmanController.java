package nl.robinlaugs;

import nl.robinlaugs.algorithm.decoding.Decoding;
import nl.robinlaugs.algorithm.decoding.DecodingController;
import nl.robinlaugs.algorithm.encoding.Encoding;
import nl.robinlaugs.algorithm.encoding.EncodingController;
import nl.robinlaugs.library.Message;
import nl.robinlaugs.statistics.Statistics;
import nl.robinlaugs.statistics.StatisticsController;
import nl.robinlaugs.statistics.StatisticsListener;

public class HuffmanController implements Huffman {

    private final Encoding encoding;
    private final Decoding decoding;

    HuffmanController(StatisticsListener listener) {
        Statistics statistics = new StatisticsController(listener);
        encoding = new EncodingController(statistics);
        decoding = new DecodingController(statistics);
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
