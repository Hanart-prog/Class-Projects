import static org.junit.Assert.assertEquals;
import org.junit.Test;
import components.map.Map;
import components.map.Map2;
import components.queue.Queue;
import components.set.Set;
import components.set.Set2;

/**
 * @author Hanat Sharif
 *
 */
public class GlossaryTest {

	/**
     * Verifies the function extracts a word before a comma separator
     */
    @Test
    public void extractWordBeforeComma() {
        String text = "Hello, World";
        int pos = 0;
        Set<Character> separators = new Set2<>();
        separators.add(' ');
        separators.add(',');
        separators.add('.');
        separators.add('!');
        separators.add('?');
        separators.add('/');
        separators.add('\\');
        Set<Character> separatorsExp = separators.newInstance();
        separatorsExp.add(' ');
        separatorsExp.add(',');
        separatorsExp.add('.');
        separatorsExp.add('!');
        separatorsExp.add('?');
        separatorsExp.add('/');
        separatorsExp.add('\\');
        String nextWordOrSep = Glossary.nextWordOrSeparator(text, pos, separators);
        String nextWordOrSepExp = "Hello";
        assertEquals(nextWordOrSepExp, nextWordOrSep);
        assertEquals(separatorsExp, separators);

    }

    /**
     * Tests the function for extracting a comma and a space as separators
     */
    @Test
    public void extracitngCommaAndSpace() {
        String text = "Hello, World";
        int pos = 5;
        Set<Character> separators = new Set2<>();
        separators.add(' ');
        separators.add(',');
        separators.add('.');
        separators.add('!');
        separators.add('?');
        separators.add('/');
        separators.add('\\');
        Set<Character> separatorsExp = separators.newInstance();
        separatorsExp.add(' ');
        separatorsExp.add(',');
        separatorsExp.add('.');
        separatorsExp.add('!');
        separatorsExp.add('?');
        separatorsExp.add('/');
        separatorsExp.add('\\');
        String nextWordOrSep = Glossary.nextWordOrSeparator(text, pos, separators);
        String nextWordOrSepExp = ", ";
        assertEquals(nextWordOrSepExp, nextWordOrSep);
        assertEquals(separatorsExp, separators);

       
    }

    /**
     * Checks the extraction of a word after a comma separator
     */
    @Test
    public void wordAfterComma() {
        String text = "Hello, World";
        int pos = 7;
        Set<Character> separators = new Set2<>();
        separators.add(' ');
        separators.add(',');
        separators.add('.');
        separators.add('!');
        separators.add('?');
        separators.add('/');
        separators.add('\\');
        Set<Character> separatorsExp = separators.newInstance();
        separatorsExp.add(' ');
        separatorsExp.add(',');
        separatorsExp.add('.');
        separatorsExp.add('!');
        separatorsExp.add('?');
        separatorsExp.add('/');
        separatorsExp.add('\\');
        String nextWordOrSep = Glossary.nextWordOrSeparator(text, pos, separators);
        String nextWordOrSepExp = "World";
        assertEquals(nextWordOrSepExp, nextWordOrSep);
        assertEquals(separatorsExp, separators);

    }

    /**
     * Ensures the function identifies a substring not starting from the first character
     */
    @Test
    public void midStringWord() {
        String text = "Hello, World";
        int pos = 2;
        Set<Character> separators = new Set2<>();
        separators.add(' ');
        separators.add(',');
        separators.add('.');
        separators.add('!');
        separators.add('?');
        separators.add('/');
        separators.add('\\');
        Set<Character> separatorsExp = separators.newInstance();
        separatorsExp.add(' ');
        separatorsExp.add(',');
        separatorsExp.add('.');
        separatorsExp.add('!');
        separatorsExp.add('?');
        separatorsExp.add('/');
        separatorsExp.add('\\');
        String nextWordOrSep = Glossary.nextWordOrSeparator(text, pos, separators);
        String nextWordOrSepExp = "llo";
        assertEquals(nextWordOrSepExp, nextWordOrSep);
        assertEquals(separatorsExp, separators);

    }

    /**
     * Tests for correct extraction of a sequence of separators at the beginning of a string
     */
    @Test
    public void leadingSeparators() {
        String text = " ,.Hello,";
        int pos = 0;
        Set<Character> separators = new Set2<>();
        separators.add(' ');
        separators.add(',');
        separators.add('.');
        separators.add('!');
        separators.add('?');
        separators.add('/');
        separators.add('\\');
        Set<Character> separatorsExp = separators.newInstance();
        separatorsExp.add(' ');
        separatorsExp.add(',');
        separatorsExp.add('.');
        separatorsExp.add('!');
        separatorsExp.add('?');
        separatorsExp.add('/');
        separatorsExp.add('\\');
        String nextWordOrSep = Glossary.nextWordOrSeparator(text, pos, separators);
        String nextWordOrSepExp = " ,.";
        assertEquals(nextWordOrSepExp, nextWordOrSep);
        assertEquals(separatorsExp, separators);

    }
    
    /**
     * Tests nextWordOrSeparator with an empty string to ensure it returns an empty result
     */
    @Test
    public void emptyStringChecks() {
        String text = " ";
        int pos = 0;
        Set<Character> separators = new Set2<>();
        separators.add(' ');
        separators.add(',');
        separators.add('.');
        separators.add('!');
        separators.add('?');
        separators.add('/');
        separators.add('\\');
        Set<Character> separatorsExp = separators.newInstance();
        separatorsExp.add(' ');
        separatorsExp.add(',');
        separatorsExp.add('.');
        separatorsExp.add('!');
        separatorsExp.add('?');
        separatorsExp.add('/');
        separatorsExp.add('\\');
        String nextWordOrSep = Glossary.nextWordOrSeparator(text, pos, separators);
        String nextWordOrSepExp = " ";
        assertEquals(nextWordOrSepExp, nextWordOrSep);
        assertEquals(separatorsExp, separators);
    }

    /**
     * Verifies that a single dot is extracted among other separators
     */
    @Test
    public void singleDotSeparator() {
        String text = " ,.Hello,";
        int pos = 2;
        Set<Character> separators = new Set2<>();
        separators.add(' ');
        separators.add(',');
        separators.add('.');
        separators.add('!');
        separators.add('?');
        separators.add('/');
        separators.add('\\');
        Set<Character> separatorsExp = separators.newInstance();
        separatorsExp.add(' ');
        separatorsExp.add(',');
        separatorsExp.add('.');
        separatorsExp.add('!');
        separatorsExp.add('?');
        separatorsExp.add('/');
        separatorsExp.add('\\');
        String nextWordOrSep = Glossary.nextWordOrSeparator(text, pos,
                separators);
        String nextWordOrSepExp = ".";
        assertEquals(nextWordOrSepExp, nextWordOrSep);
        assertEquals(separatorsExp, separators);

    }

    /**
     * Confirms that the last character of a word is correctly extracted if it is not a separator
     */
    @Test
    public void finalCharacterAsWord() {
        String text = "Hello";
        int pos = 4;
        Set<Character> separators = new Set2<>();
        separators.add(' ');
        separators.add(',');
        separators.add('.');
        separators.add('!');
        separators.add('?');
        separators.add('/');
        separators.add('\\');
        Set<Character> separatorsExp = separators.newInstance();
        separatorsExp.add(' ');
        separatorsExp.add(',');
        separatorsExp.add('.');
        separatorsExp.add('!');
        separatorsExp.add('?');
        separatorsExp.add('/');
        separatorsExp.add('\\');
        String nextWordOrSep = Glossary.nextWordOrSeparator(text, pos,
                separators);
        String nextWordOrSepExp = "o";
        assertEquals(nextWordOrSepExp, nextWordOrSep);
        assertEquals(separatorsExp, separators);

    }

    /**
     * Tests the creation of a glossary map from a file input
     */
    @Test
    public void glossaryFromInputFile() {
        String inputFile = "data/test1.txt";
        Map<String, String> map = Glossary.createGlossary(inputFile);
        Map<String, String> mapExp = map.newInstance();
        mapExp.add("Bicycle",
                "a two-wheeled vehicle that is propelled by pedaling");
        mapExp.add("house", "a place to eat, sleep, and relax");
        mapExp.add("Garden", "an outdoor area where flowers, fruits, and vegetables are cultivated");
        mapExp.add("Raincoat",
                "a waterproof coat worn to protect the body from rain");
        assertEquals(mapExp, map);
    }
    
    /**
     * Verifies glossary creation with multiple entries including complex terms and definitions
     */
    @Test
    public void glossaryFromComplexEntries() {
        String inputFile = "data/test2.txt";
        Map<String, String> map = Glossary.createGlossary(inputFile);
        Map<String, String> mapExp = map.newInstance();
        mapExp.add("history",
                "the study of past events, often recorded in written documents");
        mapExp.add("music", "art of arranging sounds that produce melody, rhythm, and vibes");
        mapExp.add("word",
                "a string of characters in a language, which has at least one character");
        mapExp.add("definition",
                "a sequence of words that gives meaning to a term");
        mapExp.add("glossary",
                "a list of difficult or specialized terms, with their definitions, usually near the end of a book");
        mapExp.add("language",
                "a set of strings of characters, each of which has meaning");
        mapExp.add("novel", "a long narrative describing fictional characters and events");
        assertEquals(mapExp, map);
    }

    /**
     * Tests glossary creation from a file with straightforward, simple mappings
     */
    @Test
    public void createSimpleGlossary() {
        String inputFile = "data/test3.txt";
        Map<String, String> map = Glossary.createGlossary(inputFile);
        Map<String, String> mapExp = map.newInstance();
        mapExp.add("Mothers", "are always right");
        mapExp.add("Dreese", "is my next class on Neil Avenue.");
        mapExp.add("Ohio", "a state in the United  States");
        assertEquals(mapExp, map);

    }
    
    /**
     * Checks that terms are ordered alphabetically in a mixed category scenario
     */
    @Test
    public void fromMixedCategories() {
        Map<String, String> map = new Map2<>();
        map.add("car", "vehicle");
        map.add("bus", "vehicle");
        map.add("truck", "vehicle");
        map.add("house", "building");
        map.add("apartment", "building");
        map.add("mall", "building");
        Map<String, String> mapExp = new Map2<>();
        mapExp.add("car", "vehicle");
        mapExp.add("bus", "vehicle");
        mapExp.add("truck", "vehicle");
        mapExp.add("house", "building");
        mapExp.add("apartment", "building");
        mapExp.add("mall", "building");

        Queue<String> q = Glossary.putOrder(map);
        Queue<String> qExp = q.newInstance();
        qExp.enqueue("apartment");
        qExp.enqueue("bus");
        qExp.enqueue("car");
        qExp.enqueue("house");
        qExp.enqueue("mall");
        qExp.enqueue("truck");

        assertEquals(mapExp, map);
        assertEquals(qExp, q);

    } 

    /**
     * Ensures terms are alphabetically ordered from a glossary containing single-letter terms
     */
    @Test
    public void SingleLetterTerms() {
        Map<String, String> map = new Map2<>();
        map.add("alpha", "1");
        map.add("artic", "1");
        map.add("alot", "1");
        map.add("acclaim", "1");
        map.add("are", "1");
        map.add("animal", "1");
        Map<String, String> mapExp = new Map2<>();
        mapExp.add("alpha", "1");
        mapExp.add("artic", "1");
        mapExp.add("alot", "1");
        mapExp.add("acclaim", "1");
        mapExp.add("are", "1");
        mapExp.add("animal", "1");

        Queue<String> q = Glossary.putOrder(map);
        Queue<String> qExp = q.newInstance();
        qExp.enqueue("acclaim");
        qExp.enqueue("alot");
        qExp.enqueue("alpha");
        qExp.enqueue("animal");
        qExp.enqueue("are");
        qExp.enqueue("artic");

        assertEquals(mapExp, map);
        assertEquals(qExp, q);

    }
    
    /**
     * Tests that an empty glossary remains unchanged after attempting to order terms
     */
    @Test
    public void handleEmptyGlossary() {
        Map<String, String> map = new Map2<>();
        Map<String, String> mapExp = new Map2<>();

        Queue<String> q = Glossary.putOrder(map);
        Queue<String> qExp = q.newInstance();

        assertEquals(mapExp, map);
        assertEquals(qExp, q);

    }
}