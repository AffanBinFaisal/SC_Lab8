/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package poet;

import static org.junit.Assert.*;
import org.junit.Test;
import java.io.File;
import java.io.IOException;

public class GraphPoetTest {

    @Test(expected = AssertionError.class)
    public void testAssertionsEnabled() {
        assert false;
    }

    @Test
    public void testInitializationWithEmptyCorpus() throws IOException {
        File emptyCorpus = new File("testfiles/empty.txt");
        GraphPoet poet = new GraphPoet(emptyCorpus);

    }

    @Test
    public void testCorpusReading() throws IOException {
        File corpusFile = new File("testfiles/sample_corpus.txt");
        GraphPoet poet = new GraphPoet(corpusFile);

    }

    @Test
    public void testPoemGenerationWithEmptyInput() throws IOException {
        File corpusFile = new File("testfiles/sample_corpus.txt");
        GraphPoet poet = new GraphPoet(corpusFile);
        String poem = poet.poem("");
        assertEquals("Expected an empty input to result in an empty poem", "", poem);
    }

    @Test
    public void testPoemGenerationWithSingleWordInput() throws IOException {
        File corpusFile = new File("testfiles/sample_corpus.txt");
        GraphPoet poet = new GraphPoet(corpusFile);
        String poem = poet.poem("word");
        assertEquals("Expected the same word in the poem for a single-word input", "word", poem);
    }

}

