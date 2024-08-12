import java.util.Comparator;
import components.map.Map;
import components.map.Map1L;
import components.queue.Queue;
import components.queue.Queue2;
import components.set.Set;
import components.set.Set2;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Program that constructs a glossary from a text input file
 * The glossary is formatted as a series of HTML pages, each defining a term
 * found in the text. The main page lists all terms, each linked to its definition page.
 *
 * @author Hanat Sharif
 *
 */
public final class Glossary {
 
    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Glossary() {
    }

    /**
     * Compare {@code String}s in lexicographic order.
     */
    private static class StringLT implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            return o1.compareTo(o2);
        } 
    }

    /**
     * Returns the first "word" (maximal length string of characters not in
     * {@code separators}) or "separator string" (maximal length string of
     * characters in {@code separators}) in the given {@code text} starting at
     * the given {@code position}.
     *
     * @param text
     *            the {@code String} from which to get the word or separator
     *            string
     * @param position
     *            the starting index
     * @param separators
     *            the {@code Set} of separator characters
     * @return the first word or separator string found in {@code text} starting
     *         at index {@code position}
     * @requires 0 <= position < |text|
     * @ensures <pre>
     * nextWordOrSeparator =
     *   text[position, position + |nextWordOrSeparator|)  and
     * if entries(text[position, position + 1)) intersection separators = {}
     * then
     *   entries(nextWordOrSeparator) intersection separators = {}  and
     *   (position + |nextWordOrSeparator| = |text|  or
     *    entries(text[position, position + |nextWordOrSeparator| + 1))
     *      intersection separators /= {})
     * else
     *   entries(nextWordOrSeparator) is subset of separators  and
     *   (position + |nextWordOrSeparator| = |text|  or
     *    entries(text[position, position + |nextWordOrSeparator| + 1))
     *      is not subset of separators)
     * </pre>
     *
     */
    public static String nextWordOrSeparator(String text, int position,
            Set<Character> separators) {
        assert text != null : "Violation of: text is not null";
        assert separators != null : "Violation of: separators is not null";
        assert 0 <= position : "Violation of: 0 <= position";
        assert position < text.length() : "Violation of: position < |text|";
        String nextWordOrSep = "";
        
        //Checking if the character at the current position is separator 
        if (separators.contains(text.charAt(position))) {
            nextWordOrSep = nextWordOrSep
                    + text.substring(position, position + 1);
            int i = position + 1;
            boolean isSep = true;
            //Continue adding separators to the result until a non separator is found
            while (i < text.length() && isSep) {
                if (separators.contains(text.charAt(i))) {
                    nextWordOrSep = nextWordOrSep + text.substring(i, i + 1);
                } else {
                	//Flag it once it encounters a non separator
                    isSep = false;
                }
                i++;
            }

        } else {
        	//Start with the current character if not a separator
            nextWordOrSep = nextWordOrSep
                    .concat(text.substring(position, position + 1));
            int i = position + 1;
            boolean isWord = true;
            while (i < text.length() && isWord) {
                if (!separators.contains(text.charAt(i))) {
                    nextWordOrSep = nextWordOrSep + text.substring(i, i + 1);
                } else {
                    isWord = false;
                }
                i++;
            }

        }

        return nextWordOrSep;
    }
    
    /**
     * Reads terms and their definition from a file and stores them in a map.
     * 
     * @param inputFile the path to the input file containing the glossary terms and definitions
     * @return a map where each key is a term and each value is its definition
     * @requires inputFile is a valid, accessible file path
     * @ensures the returned map contains all parsed terms from the inputFile with their corresponding definitions
     */
    public static Map<String, String> createGlossary(String inputFile) {

        SimpleReader in = new SimpleReader1L(inputFile);
        Map<String, String> map = new Map1L<>();
        //Continue reading until the end
        while (!in.atEOS()) {
            String term = in.nextLine();
            String definition = in.nextLine();
            String tempLine = definition;
            //read all continuation lines of the definition until a blank line or EOS
            while (tempLine.length() != 0 && !in.atEOS()) {
                tempLine = in.nextLine();
                //If the line is not empty append it to the current definition
                if (tempLine.length() != 0) {
                    definition = definition + " " + tempLine;
                }
            }
            //Ensuring the term is not already in the map and is not empty before adding
            if (!map.hasKey(term) && term.length() > 0) {
                map.add(term, definition);
            }
        }

        in.close();
        return map;
    }

    /**
     * Orders the terms from the glossary alphabetically and returns them in queue
     * 
     * @param glossary the map containing glossary terms and their definitions
     * @return an order queue of glossary terms
     * @requires glossary is not empty
     * @ensures terms in returned queue are in lexicographic order
     */
    public static Queue<String> putOrder(Map<String, String> glossary) {
        Queue<String> termsOrder = new Queue2<>();
        Comparator<String> order = new StringLT();
        // Iterate over each pair in the map
        for (Map.Pair<String, String> pair : glossary) {
            termsOrder.enqueue(pair.key());
        }
        termsOrder.sort(order);
        return termsOrder;
    }

    /**
     * Generates an HTML index page listing all the terms in the glossary linking each term
     * to its respective definition page
     * 
     * @param folder the directory path where the HTML files will be saved
     * @param terms the queue containing the ordered terms from the glossary
     * @requires folder is a valid directory path and terms is not empty and contains valid strings
     * @ensures creates an HTML file at the specified folder path that lists all terms
     */
    public static void createIndexPage(String folder, Queue<String> terms) {
    	//Concatenate the folder path with the filename to create the full file
        String filePath = folder + "/index.html";
        SimpleWriter indexOut = new SimpleWriter1L(filePath);
        //Start of the HTML page
        indexOut.println("<!DOCTYPE html>");
        indexOut.println("<html>");
        indexOut.println("<head>");
        indexOut.println("<title>Glossary Index</title>");
        indexOut.println("</head>");
        indexOut.println("<body>");
        indexOut.println("<h2>Glossary</h2>");
        indexOut.println("<hr>");
        indexOut.println("<h3>Index</h3>");

        indexOut.println("<ul>");
        for (String term : terms) {
            String termPath = term + ".html";
            indexOut.println("<li><a href=\"" + termPath + "\">" + term + "</a></li>");
        }
        indexOut.println("</ul>");
        indexOut.println("</body>");
        indexOut.println("</html>");
        indexOut.close();
    }

    /**
     * Creates individual HTML pages for each term in the glossary, including the definitions
     * and links back to the index page.
     * 
     * @param folder 
     * 				the directory where the HTML files will be saved
     * @param terms
     *  			the queue of terms to be processed
     * @param glossary
     *  			the map containing terms and their definitions
     * @requires folder is a valid directory path; terms and glossary are not empty
     * @ensures creates individual HTML files for each term with definitions at the specified folder path
     */
    public static void createTermPages(String folder, Queue<String> terms,
            Map<String, String> glossary) {
        for (String term : terms) {
            String definition = glossary.value(term);
            String filePath = folder + "/" + term + ".html";
            SimpleWriter fileOut = new SimpleWriter1L(filePath);
            
            //HTML document structure
            fileOut.println("<!DOCTYPE html>");
            fileOut.println("<html>");
            fileOut.println("<head>");
            fileOut.println("<title>" + term + "</title>");
            fileOut.println("</head>");
            fileOut.println("<body>");
            fileOut.println(
                    "<h1 style=\"color:red;\"><i>" + term + "</i></h1>");
            fileOut.println("<p><strong>Definition:</strong></p>");

            printDefinitionWithLinks(definition, fileOut, glossary);
            fileOut.println("<hr>");
            fileOut.println(
                    "<p>Back to <a href=\"index.html\">Index Page</a></p>");
            fileOut.println("</body>");
            fileOut.println("</html>");
            fileOut.close();
        }

    }
    
    /**
     * Generates a set of characters that are considered separators for text parsing
     * Separators are used to distinguish between words and non words in the text
     * 
     * @return a {@code Set<Character>} containing characters used as separators			
     */
    public static Set<Character> genSeparator() {
        Set<Character> x = new Set2<>();
        x.add(' ');
        x.add(',');
        x.add('.');
        x.add('!');
        x.add('?');
        x.add('/');
        x.add('\\');
        return x;
    }

    /**
     * Outputs the definition text as HTML, turning any term found within the definition
     * that exists in the glossary into a hyperlink 
     * 
     * @param definition 
     * 					the definition to process
     * @param out
     * 			the output stream
     * @param glossary
     * 				the map of glossary terms and their definitions
     * @requires needs definition for each term
     * @ensures all recognized terms in the definition that are present in the glossary are linked to their definition pages
     */
    public static void printDefinitionWithLinks(String definition,
            SimpleWriter out, Map<String, String> glossary) {

        Set<Character> separators = genSeparator();

        out.print("<blockquote>");
        int i = 0;
        //Continue processing the definition until all characters are looked at
        while (i < definition.length()) {
            String nextString = nextWordOrSeparator(definition, i, separators);
            //Check if the first character of the string is not a separator
            if (!separators.contains(nextString.charAt(0))) {
            	//If the string is a term and exist then convert it into a hyperlink to its definition page
                if (glossary.hasKey(nextString)) {
                    out.print("<a href=\"" + nextString + ".html\">");
                    out.print(nextString);
                    out.print("</a>");
                } else {
                    out.print(nextString);
                }
            } else {
                out.print(nextString);
            }
            //Advance the index by the length of the string that was just processed
            i += nextString.length();
        }
        out.println("</blockquote>");

    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        out.print("Enter input file: ");
        String inputFile = in.nextLine();
        out.print("Enter output folder: ");
        String folderName = in.nextLine();
        
        Map<String, String> glossary = createGlossary(inputFile);
        Queue<String> alphabeticTerms = putOrder(glossary);

        //Create the HTML page with the list of the terms with links to individual pages
        createIndexPage(folderName, alphabeticTerms);
        //Creating individual HTML pages for each term with their definition and links back to index
        createTermPages(folderName, alphabeticTerms, glossary);

        in.close();
        out.close();
    }

}
    	
    
    
    

   
   

    