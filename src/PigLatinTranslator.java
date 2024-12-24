import java.lang.*;
public class PigLatinTranslator {

    private static boolean isVowel(String s) {
        return "aeiouAEIOU".contains(s);
    }


    public static String translateWord(String currentWord) {
        String translation = "";
        int indexFound = 0;
        for (int i=0; i < currentWord.length(); i++){
            String currentLetter = currentWord.substring(i, i+1);
            if (isVowel(currentLetter)) {
                indexFound = i;
                break;
            }
        }
        if (indexFound == 0) {
            translation = currentWord.replace(".", "") + "ay";
        } else {
            translation = currentWord.substring(indexFound, currentWord.length()) + currentWord.substring(0, indexFound).toLowerCase() + "ay";
            translation = translation.replace(".", "");
        }

        if (!(currentWord.trim().isEmpty()) && Character.isUpperCase(currentWord.charAt(0))) {
            String temp = translation.substring(0,1).toUpperCase() + translation.substring(1, translation.length());
            translation = temp;
        } 

        if (!(currentWord.trim().isEmpty()) && currentWord.contains(".")) {
            String temp = translation + ".";
            translation = temp; 
        }
        return translation;
    }


    public static String translate (String input){

        if (input.trim().isEmpty()) {
            return input;
        } 

        String[] words = input.split("[,\\s]");
        String[] translatedWords = new String[words.length];


        for (int j = 0; j < words.length; j++) {
            String currentWord = words[j];

            translatedWords[j] = translateWord(currentWord);
            /* */
            if (!(currentWord.contains("-"))) {
               
            } else {
                String[] splitWord = currentWord.split("-");
                String[] wordParts = new String[splitWord.length];
                for (int i = 0; i < splitWord.length; i++) {
                    String translatedWord = translateWord(splitWord[i]);
                    wordParts[i] = translatedWord;
                }
                translatedWords[j] = String.join("-", wordParts);
            }
        }
        String newLine = String.join(" ", translatedWords);
        return newLine;
    }




    public static Book translate(Book input) {
        Book pigLatinBook = new Book();
   
        for (int i = 0; i < input.getLineCount(); i++) {
            String currentLine = input.getLine(i);
            pigLatinBook.appendLine(translate(currentLine));
        }

        return pigLatinBook;
    }
}
