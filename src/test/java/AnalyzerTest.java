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
    * Método calculateScores en la clase Analyzer
    */

    // Se testea que salga el score correcto de una palabra en concreto
    @Test
    public void test_calculateScores_Score() {
        Set<Word> listWords = new TreeSet<Word>();
        List<Sentence> list = new ArrayList<Sentence>();
        list = Analyzer.readFile("file.txt");
        listWords = Analyzer.allWords(list);

        Map<String, Double> map = new HashMap<String, Double>();
        map = Analyzer.calculateScores(listWords);

        double score = 0;
        for (Map.Entry<String, Double> entry : map.entrySet()) {
            if ("fun".equals(entry.getKey())) {
                score = entry.getValue();
            }
        }
        assertEquals("0.8", String.valueOf(score));
    }

    /*
    * Si el conjunto de palabras de entrada es nulo o está vacío, el método calculateScores
    * debe devolver un mapa vacío.
    */
    @Test
    public void test_calculateScores_emptyMap() {
        Set<Word> listWords = new TreeSet<Word>();
        List<Sentence> list = new ArrayList<Sentence>();
        list = Analyzer.readFile("file_empty.txt");
        listWords = Analyzer.allWords(list);

        Map<String, Double> map = new HashMap<String, Double>();
        map = Analyzer.calculateScores(listWords);

        assertEquals(0, map.size());
    }

}
