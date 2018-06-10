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
    public void testFileExist() {
        List<Sentence> list = new ArrayList<Sentence>();
        list = Analyzer.readFile("file.txt");
        assertTrue(!list.isEmpty());
    }

    // Test para verificar cuando no exista el archivo
    @Test
    public void testFileNotExist() {
        List<Sentence> list = new ArrayList<Sentence>();
        list = Analyzer.readFile("file_not_exist.txt");
        assertTrue(list.isEmpty());
    }

    // Test para verificar que cuando el archivo esta vacio nos devuelva una lista vacia
    @Test
    public void testReadFileEmpty() {
        List<Sentence> ListEmpty = Analyzer.readFile("empty_file.txt");
        assertEquals("", 0, ListEmpty.size());
    }

    /*
    * Test para verificar que cuando en nuestro archivo haya filas que no cumplan
    * el formato correcto no las lea del archivo
     */
    @Test
    public void testNotReadIncorrectFormat() {
        List<Sentence> listIncorrectFormat = Analyzer.readFile("file_fake.txt");
        assertEquals(3, listIncorrectFormat.size());
    }

    /*
     * Test para verificar que lea correctamente el numero de filas en el archivo y que cumplan
     * con el formato establecido
     */
    @Test
    public void testReadCorrectFormat() {
        List<Sentence> listCorrectFormat = Analyzer.readFile("file.txt");
        assertEquals(6, listCorrectFormat.size());
    }

    /* Test para verificar que incluso si esta en el formato correcto puede q las sentencias
    * uera de los limites de -2 a 2ç
    */
    @Test
    public void testFormatoIncorrectoLimites() {
        List<Sentence> lista = Analyzer.readFile("archivo_formato_limites.txt");
        assertEquals(2, lista.size());
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
    public void testNumberWords() {
        Set<Word> listWords = new TreeSet<Word>();
        List<Sentence> list = new ArrayList<Sentence>();
        list = Analyzer.readFile("file.txt");
        listWords = Analyzer.allWords(list);
        assertEquals(40, listWords.size());
    }

    /*
    * La cantidad de apariciones que tiene la palabra en las diferentes
    * sentencias dentro del archivo
    */
    @Test
    public void testDatosCorrectos() {
        Set<Word> listaPalabras = new TreeSet<Word>();
        List<Sentence> lista = new ArrayList<Sentence>();
        lista = Analyzer.readFile("file.txt");
        listaPalabras = Analyzer.allWords(lista);

        int count = 0;
        for (Word palabra : listaPalabras) {
            if("java".equals(palabra.getText())){
                count = palabra.getCount();
            }
        }

        assertEquals(2, count);
    }

    //acumulativo
    @Test
    public void testDatosCorrectosAcumulativo() { //FALLA
        Set<Word> listaPalabras = new TreeSet<Word>();
        List<Sentence> lista = new ArrayList<Sentence>();
        lista = Analyzer.readFile("archivo.txt");
        listaPalabras = Analyzer.allWords(lista);

        int total = 0;
        for (Word palabra : listaPalabras) {
            if("java".equals(palabra.getText())){
                total = palabra.getTotal();
            }
        }
        assertEquals(4, total);
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
