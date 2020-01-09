package nl.robinlaugs;

import nl.robinlaugs.library.Message;

import java.util.Scanner;

public class Main {

    private static final String PROMPT = "Enter a message to encode and decode: ";

    public static void main(String[] args) {
        Huffman huffman = new HuffmanController();

        System.out.print(PROMPT);

        Scanner scanner = new Scanner(System.in);
        String message = scanner.nextLine();

        Message encodedMessage = huffman.encode(message);

        huffman.decode(encodedMessage);
    }

}
