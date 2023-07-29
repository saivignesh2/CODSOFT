import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class WordCounter {

    
    private static final String[] STOP_WORDS = {
        "a", "an", "the", "and", "or", "in", "on", "of", "to", "for", "with", "is", "are", "it", "that", "this"
        
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Word Counter!");
        System.out.print("Enter the symbol '@' to input text or Enter '#' to provide a file path: ");
        String inputType = scanner.nextLine();

        String text = " ";

        if (inputType.equalsIgnoreCase("@")) {
            System.out.println("Enter your text (press Enter twice to finish input): ");
            String line;
            while (!(line = scanner.nextLine()).isEmpty()) {
                text += line + " ";
            }
        } else if (inputType.equalsIgnoreCase("#")) {
            System.out.print("Enter the file path: ");
            String filePath = scanner.nextLine();

            try {
                File file = new File(filePath);
                Scanner fileScanner = new Scanner(file);

                while (fileScanner.hasNextLine()) {
                    text += fileScanner.nextLine() + " ";
                }

                fileScanner.close();
            } catch (FileNotFoundException e) {
                System.out.println("File not found. Please check the file path and try again.");
                return;
            }
        } else {
            System.out.println("Invalid input type. Please try again with '@' or '#'.");
            return;
        }

        String[] words = text.split("[\\s.,!?\":;()\\[\\]{}]+");
        int totalCount = words.length;

        // Remove stop words and count unique words
        Map<String, Integer> wordFrequency = new HashMap<>();
        for (String word : words) {
            word = word.toLowerCase();
            if (!isStopWord(word)) {
                wordFrequency.put(word, wordFrequency.getOrDefault(word, 0) + 1);
            }
        }

        int uniqueWordCount = wordFrequency.size();

        System.out.println("Word Count: " + totalCount);
        System.out.println("Unique Word Count: " + uniqueWordCount);

        // Print word frequency statistics
        System.out.println("Word Frequency:");
        for (Map.Entry<String, Integer> entry : wordFrequency.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    private static boolean isStopWord(String word) {
        for (String stopWord : STOP_WORDS) {
            if (stopWord.equalsIgnoreCase(word)) {
                return true;
            }
        }
        return false;
    }
}
