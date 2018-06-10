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
    @Test
    public void testReadFileEmpty() {
        List<Sentence> ListaVaciaResult=Analyzer.readFile("archivo.txt");
        assertEquals(0, ListaVaciaResult.size());
    }

    @Test
    public void testReadFileStringEntry() {
        String nombreArchivo="archivo.txt";
        String extencionArchivo=nombreArchivo.substring((nombreArchivo.length())-3, nombreArchivo.length());
        assertTrue(extencionArchivo.equals("txt"));
    }

    @Test
    public void testReadReturnList6Elements() {
        List<Sentence> ListaVaciaResult=Analyzer.readFile("archivo.txt");
        assertEquals(6, ListaVaciaResult.size());
    }

    @Test
    public void testAllWords() {
        List<Sentence> Lista= new ArrayList<Sentence>();
        Sentence sentence= new Sentence(5, "Web develop");
        Lista.add(sentence);
        Set<Word> ListaPalabrasResult=Analyzer.allWords(Lista);
        assertEquals(2, ListaPalabrasResult.size());
    }
    */

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

    @Test
    public void testFormatoIncorrecto() {//FALLA, REVISAR
        List<Sentence> lista = Analyzer.readFile("file_fake.txt");
        assertEquals(3, lista.size());
    }

    @Test
    public void testFormatoIncorrectoLimites() {
        List<Sentence> lista = Analyzer.readFile("archivo_formato_limites.txt");
        assertEquals(2, lista.size());
    }

}
