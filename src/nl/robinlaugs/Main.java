package nl.robinlaugs;

import nl.robinlaugs.library.Message;
import nl.robinlaugs.statistics.StatisticsListener;

public class Main implements StatisticsListener {

    private static final String MESSAGE = "huffman coding implementation by robin laugs";

    public static void main(String[] args) {
        Huffman huffman = new HuffmanController(new Main());

        Message encodedMessage = huffman.encode(MESSAGE);

        huffman.decode(encodedMessage);
    }

    @Override
    public void onStatisticReceived(String statistic) {
        System.out.println(statistic);
    }

}
