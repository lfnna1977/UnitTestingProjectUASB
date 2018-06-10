import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;

public class AnalyzerTest {

    /*
    * Método readFile de la clase Analyzer
     */

    // Test para verificar si existe el archivo
    @Test
    public void test_readFile_FileExist() {
        List<Sentence> list = new ArrayList<Sentence>();
        list = Analyzer.readFile("file.txt");
        assertTrue(!list.isEmpty());
    }

    // Test para verificar cuando no exista el archivo
    @Test
    public void test_readFile_FileNotExist() {
        List<Sentence> list = new ArrayList<Sentence>();
        list = Analyzer.readFile("file_not_exist.txt");
        assertTrue(list.isEmpty());
    }

    // Test para verificar que cuando el archivo esta vacio nos devuelva una lista vacia
    @Test
    public void test_readFile_ReadFileEmpty() {
        List<Sentence> ListEmpty = Analyzer.readFile("empty_file.txt");
        assertEquals("", 0, ListEmpty.size());
    }

    /*
    * Test para verificar que cuando en nuestro archivo haya filas que no cumplan
    * el formato correcto no las lea del archivo
     */
    @Test
    public void test_readFile_NotReadIncorrectFormat() {
        List<Sentence> listIncorrectFormat = Analyzer.readFile("file_fake.txt");
        assertEquals(3, listIncorrectFormat.size());
    }

    /*
     * Test para verificar que lea correctamente el numero de filas en el archivo y que cumplan
     * con el formato establecido
     */
    @Test
    public void test_readFile_ReadCorrectFormat() {
        List<Sentence> listCorrectFormat = Analyzer.readFile("file.txt");
        assertEquals(6, listCorrectFormat.size());
    }

    /* Test para verificar que incluso si esta en el formato correcto puede q las sentencias
    * esten fuera de los limites de -2 a 2 en la primera parte de la lectura
    */
    @Test
    public void test_readFile_OutLimits() {
        List<Sentence> list = Analyzer.readFile("file_out_limits.txt");
        assertEquals(4, list.size());
    }

    /*
     * Método allWords en la clase Analyzer
     */

    /*
    * La cantidad de palabras dentro del archivo sea el correcto
    * se considera que el no existen duplicados en las palabras
    * y que todos estan en minusculas
    */
    @Test
    public void test_allWords_NumberWords() {
        Set<Word> listWords = new TreeSet<Word>();
        List<Sentence> list = new ArrayList<Sentence>();
        list = Analyzer.readFile("file.txt");
        listWords = Analyzer.allWords(list);
        assertEquals(40, listWords.size());
    }

    /*
    * La cantidad de apariciones que tiene la palabra en las diferentes
    * sentencias dentro del archivo
    * Ejemplo la palabra more que parece 3 veces en el archivo
    */
    @Test
    public void test_allWords_NumberAppearances() {
        Set<Word> listWords = new TreeSet<Word>();
        List<Sentence> list = new ArrayList<Sentence>();
        list = Analyzer.readFile("file.txt");
        listWords = Analyzer.allWords(list);

        int count = 0;
        for (Word word : listWords) {
            if("more".equals(word.getText())){
                count = word.getCount();
            }
        }
        assertEquals(3, count);
    }

    /*
    * Se testea el acumulativo de la palabra comparado con sus apariciones dentro del archivo
    * Ejemplo la palabra more su acumulativo es 0 = (-1) + (-1) + (2)
    */

    @Test
    public void test_allWords_TotalCumulative() {
        Set<Word> listWords = new TreeSet<Word>();
        List<Sentence> list = new ArrayList<Sentence>();
        list = Analyzer.readFile("file.txt");
        listWords = Analyzer.allWords(list);
        int total = 0;
        for (Word word : listWords) {
            if("more".equals(word.getText())){
                total = word.getTotal();
            }
        }
        assertEquals(0, total);
    }


    /*
     * Implement this method in Part 3
     */
    @Test
    public void testScore() {
        Set<Word> listaPalabras = new TreeSet<Word>();
        List<Sentence> lista = new ArrayList<Sentence>();
        lista = Analyzer.readFile("archivo.txt");
        listaPalabras = Analyzer.allWords(lista);

        Map<String, Double> mapa = new HashMap<String, Double>();
        mapa = Analyzer.calculateScores(listaPalabras);

        double score = 0;
        for (Map.Entry<String, Double> entry : mapa.entrySet()) {
            if ("fun".equals(entry.getKey())) {
                score = entry.getValue();
            }
        }
        assertEquals("0.8", String.valueOf(score));
    }

    @Test
    public void testMapaVacio() {
        Set<Word> listaPalabras = new TreeSet<Word>();
        List<Sentence> lista = new ArrayList<Sentence>();
        lista = Analyzer.readFile("archivo_vacio.txt");
        listaPalabras = Analyzer.allWords(lista);

        Map<String, Double> mapa = new HashMap<String, Double>();
        mapa = Analyzer.calculateScores(listaPalabras);


        assertEquals(0, mapa.size());
    }

/*


    @Test
    public void testSomeLibraryMethod() {
    	//Sentence sentence = new Sentence();
    	List<Sentence> lista = new ArrayList<Sentence>();
    	lista = Analyzer.readFile("archivo.txt");

        //assertTrue("someLibraryMethod should return 'true'", classUnderTest.someLibraryMethod());
    }

    @Test
    public void testSomeLibraryMethod1() {
    	Set<Word> listaPalabras = new TreeSet<Word>();
    	List<Sentence> lista = new ArrayList<Sentence>();
    	lista = Analyzer.readFile("archivo.txt");
    	listaPalabras = Analyzer.allWords(lista);

        //assertTrue("someLibraryMethod should return 'true'", classUnderTest.someLibraryMethod());
    }

    @Test
    public void testSomeLibraryMethod2() {
    	Set<Word> listaPalabras = new TreeSet<Word>();
    	List<Sentence> lista = new ArrayList<Sentence>();
    	lista = Analyzer.readFile("archivo.txt");
    	listaPalabras = Analyzer.allWords(lista);

    	Map<String, Double> mapa = new HashMap<String, Double>();
    	mapa = Analyzer.calculateScores(listaPalabras);

    	for (Map.Entry<String, Double> entry : mapa.entrySet()) {
    		System.out.println(entry.getKey() + ": " + entry.getValue());
    	}
        //assertTrue("someLibraryMethod should return 'true'", classUnderTest.someLibraryMethod());
    }
    */


}
