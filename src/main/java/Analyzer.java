import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
* Actividades a desarrollar
 */

public class Analyzer {

    /*
     * Implementar el método readFile en la clase Analyzer
     */
    public static List<Sentence> readFile(String filename) {
        List<Sentence> listSentences = new ArrayList<Sentence>();

        // Fichero del que queremos leer
        File fichero = new File(filename);
        Scanner scanner = null;

        try {
            // Leemos el contenido del archivo de texto
            System.out.println("... Leemos el contenido del archivo ...");
            scanner = new Scanner(fichero);

            // Leemos linea a linea el archivo de texto
            while (scanner.hasNextLine()) {
                // Guardamos cada linea que saquemos en un string
                String line = scanner.nextLine();
                // Se imprime la linea para verificar
                System.out.println("Leido: " + line);

                String score = line.substring(0, line.indexOf(" "));
                String text = line.substring(line.indexOf(" ") + 1, line.length());

                /*
                * Se debería ignorar cualquier linea que no tenga el formato correcto.
                * Significa que la línea empiece con un número en el rango de -2 a 2,
                * tiene un espacio en blanco y luego una cadena.
                 */
                Pattern patternLine = Pattern.compile("^[+-]?[0-2]{1}\\s{1}[a-zA-ZÀ-ÿ\\u00f1\\u00d1’'´` ]*$");
                Matcher matcherLine = patternLine.matcher(line);

                if (matcherLine.find()) {
                    Sentence sentence = new Sentence(Integer.valueOf(score), text);
                    listSentences.add(sentence);
                    System.out.println("Introducido: " + line);
                }
            }
        } catch (Exception primaryException) {
            System.out.println("Mensaje: " + primaryException.getMessage());
        } finally {
            // Cerramos el archivo si la lectura ha sido correcta o no lo ha sido
            try {
                if (scanner != null)
                    scanner.close();
            } catch (Exception secondaryException) {
                System.out.println("Mensaje: " + secondaryException.getMessage());
            }
        }
        // Retornamos la lista
        return listSentences;
    }

    /*
     * Implementar el método allWords en la clase Analyzer
     */
    public static Set<Word> allWords(List<Sentence> sentences) {
        Set<Word> listWords = new TreeSet<Word>();
        /*
        * Leendo cada sentencia y colocando a la lista de palabras
        */
        System.out.println("... Leemos el contenido del archivo almacenado en ol objeto sentence.java ...");
        for (Sentence sentence : sentences) {
            String[] words = sentence.text.split(" ");
            for (String word : words) {
                Word readWord = new Word(word.toLowerCase().trim());
                listWords.add(readWord);
                System.out.println("Palabra leida " + readWord.getText());
            }
        }
        /*
        * Busca las palabras en las sentencias para asignarle el puntaje de la sentencia
        */
        for (Word word : listWords) {
            String findWord = word.getText();
            for (Sentence sentence : sentences) {
                String[] splitWords = sentence.text.split(" ");
                for (String splitWord : splitWords) {
                    if (findWord.equals(splitWord.toLowerCase().trim())) {
                        word.increaseTotal(sentence.getScore());
                        System.out.println("Palabra " + word.getText() + ", puntaje " + word.getTotal());
                    }
                }
            }
        }
        // Retornamos la lista de palabras
        return listWords;
    }

    /*
     * Implementar el método calculateScores en la clase Analyzer.
     */
    public static Map<String, Double> calculateScores(Set<Word> words) {
        Map<String, Double> map = new HashMap<String, Double>();
        for (Word word: words) {
            double score = word.calculateScore();
            map.put(word.getText(), score);
            System.out.println("Palabra: " + word.getText() + ", puntuación calculada: " + score);
        }
        // Retornamo el mapa
        return map;
    }

}
