package IO;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class TextFileAnalyzer {

    public static class AnalysisResult {
        private final long lineCount;
        private final long wordCount;
        private final long charCount;
        private final Map<Character, Long> charFrequency;

        public AnalysisResult(long lineCount, long wordCount, long charCount, Map<Character, Long> charFrequency) {
            this.lineCount = lineCount;
            this.wordCount = wordCount;
            this.charCount = charCount;
            this.charFrequency = charFrequency;
        }

        public long getLineCount() { return lineCount; }
        public long getWordCount() { return wordCount; }
        public long getCharCount() { return charCount; }
        public Map<Character, Long> getCharFrequency() { return charFrequency; }

        @Override
        public String toString() {
            return "AnalysisResult{" +
                    "lineCount=" + lineCount +
                    ", wordCount=" + wordCount +
                    ", charCount=" + charCount +
                    ", charFrequency=" + charFrequency +
                    '}';
        }
    }

    public AnalysisResult analyzeFile(String filePath) throws IOException {
        long lineCount = 0;
        long wordCount = 0;
        long charCount = 0;
        Map<Character, Long> charFrequency = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lineCount++;
                charCount += line.length();

                String[] words = line.split("\\s+");
                if (!line.trim().isEmpty()) {
                    wordCount += words.length;
                }

                for (char c : line.toCharArray()) {
                    charFrequency.put(c, charFrequency.getOrDefault(c, 0L) + 1);
                }
            }
        }

        return new AnalysisResult(lineCount, wordCount, charCount, charFrequency);
    }

    public void saveAnalysisResult(AnalysisResult result, String outputPath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath))) {
            writer.write("File Analysis Results:\n");
            writer.write("Line count: " + result.getLineCount() + "\n");
            writer.write("Word count: " + result.getWordCount() + "\n");
            writer.write("Character count: " + result.getCharCount() + "\n");
            writer.write("Character frequency:\n");

            for (Map.Entry<Character, Long> entry : result.getCharFrequency().entrySet()) {
                writer.write("'" + entry.getKey() + "': " + entry.getValue() + "\n");
            }
        }
    }
}