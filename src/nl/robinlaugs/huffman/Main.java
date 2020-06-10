package nl.robinlaugs.huffman;

import nl.robinlaugs.huffman.model.Message;
import nl.robinlaugs.huffman.service.DecodingService;
import nl.robinlaugs.huffman.service.EncodingService;

import java.util.Scanner;
import java.util.logging.Logger;

import static java.util.logging.Level.INFO;

public class Main {

    private static final String ENTER_MESSAGE_PROMPT = "Enter a message to encode and decode: ";

    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        System.out.print(ENTER_MESSAGE_PROMPT);

        String inputMessage = new Scanner(System.in).nextLine();
        logger.log(INFO, "Input message: {0}", inputMessage);

        Message encodedMessage = EncodingService.encode(inputMessage);
        logger.log(INFO, "Encoded message: {0}", encodedMessage.message());

        String decodedMessage = DecodingService.decode(encodedMessage);
        logger.log(INFO, "Decoded message: {0}", decodedMessage);
    }

}
