import java.util.*;


public class Main {
    public static void main(String[] args) {
        String s = "/first/second/attrib1\n" +
                "/first/second/attrib2\n" +
                "/first/second/attrib3/a\n" +
                "/first/second/attrib3/b\n" +
                "/first/second/attrib3/c\n" +
                "/third/forth/a\n" +
                "/third/forth/b/c\n" +
                "/third/forth/b/d\n" +
                "/fifth/sixth/b/d/e\n" ;
        String[] inputStrings = s.split("\\n");
        butify(inputStrings);
    }

    public static void butify(String[] inputStrings){
        ArrayDeque<String> lastString = new ArrayDeque<>();
        for (int i = 0; i < inputStrings.length; i++) {
            int lineIndent = 0;
            String[] currentLineWords = inputStrings[i].substring(1).split("/");
            ArrayDeque<String> currentString = new ArrayDeque<>(Arrays.asList(currentLineWords));
            int originalCurrentStringSize = currentString.size();
            for (String currentWord : currentString) {
                if (lastString.peekFirst() != null && lastString.peekFirst().equals(currentWord)) {
                    lastString.pollFirst();
                    currentString.pollFirst();
                    lineIndent += currentWord.length() + 1;
                } else if (lineIndent > 0 && originalCurrentStringSize > lastString.size()) {
                    System.out.println();
                    printArrayStringsAsString(currentString, lineIndent);
                    break;
                } else if (originalCurrentStringSize == currentString.size()) {
                    if(lastString.size()!=0) {
                        System.out.println();
                    }
                    printArrayStringsAsString(currentString, lineIndent);
                    break;
                } else {
                    if (lineIndent > 0) {
                        System.out.println();
                    }
                    printWord("/" + currentWord, lineIndent);// напечатать с отступом
                }
            }
            lastString.clear();
            lastString.addAll(Arrays.asList(currentLineWords));
        }
        System.out.println();
    }

    public static void printWord(String lastWord, int lineIndent){
        StringBuilder output = new StringBuilder();
        for (int i = 0 ;  i <lineIndent; i ++){
            output.append(" ");
        }
        output.append(lastWord);
        System.out.print(output);
    }

    public static void printArrayStringsAsString(ArrayDeque<String> strings, int lineIndent){
        StringBuilder output = new StringBuilder();
        for (int i = 0 ;  i <lineIndent; i ++){
            output.append(" ");
        }
        for (String str: strings){
            output.append("/").append(str);
        }
        System.out.print(output);
    }
}

