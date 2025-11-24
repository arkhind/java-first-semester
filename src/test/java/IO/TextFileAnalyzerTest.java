package IO;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class TextFileAnalyzerTest {

    @Test
    void testAnalyzeFile() throws IOException {
        TextFileAnalyzer analyzer = new TextFileAnalyzer();

        Path testFile = Files.createTempFile("test", ".txt");
        Files.write(testFile, Arrays.asList("Hello world!", "This is test."));

        TextFileAnalyzer.AnalysisResult result = analyzer.analyzeFile(testFile.toString());

        assertEquals(2, result.getLineCount());
        assertEquals(5, result.getWordCount());
        assertEquals(25, result.getCharCount());
        assertTrue(result.getCharFrequency().get('H') > 0);
        assertTrue(result.getCharFrequency().get('e') > 0);
    }

    @Test
    void testSaveAnalysisResult() throws IOException {
        TextFileAnalyzer analyzer = new TextFileAnalyzer();

        TextFileAnalyzer.AnalysisResult result = new TextFileAnalyzer.AnalysisResult(2, 5, 20, java.util.Map.of('a', 5L, 'b', 3L));

        Path outputFile = Files.createTempFile("analysis", ".txt");
        analyzer.saveAnalysisResult(result, outputFile.toString());

        assertTrue(Files.size(outputFile) > 0);

        String content = Files.readString(outputFile);
        assertTrue(content.contains("Line count: 2"));
        assertTrue(content.contains("Word count: 5"));
        assertTrue(content.contains("Character count: 20"));
    }
}