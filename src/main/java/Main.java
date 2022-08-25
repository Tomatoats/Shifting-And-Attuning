import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.*;
import java.nio.file.Paths;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<String> total = new ArrayList<>();
        int lines = 0;

        try (Scanner in = new Scanner(Paths.get("in.txt"))) {
            //take in the inputs
            while (in.hasNext()) {
                lines++;
                String str = in.nextLine();
                System.out.println(str);
                //if you're doing affines, make sure only Affine is up
                //if you're doing shifts, make sure only Shift is up
                //Shift(str, total);
                Affine(str, total);

            }
        } catch (IOException | NoSuchElementException | IllegalStateException e) {
            e.printStackTrace();
        }
        //if you're only doing shift ciphers, comment out printAffine
        // and make sure printShift is up.
        //printShift(total, lines);
        //if you're doing affine ciphers, only do printAffine
        printAffine(total);

    }


    private static void Affine(String str, ArrayList total) {
        int counter = 1;
        char[] strar = str.toCharArray();
        int len = strar.length;
        char[] newstrar = new char[len];
        int[] slope = {1, 3, 5, 7, 9, 11, 25, 23, 21, 19, 17, 15};
        for (int k = 0; k < 12; k++)
            for (int i = 0; i < 26; i++) {
                for (int j = 0; j < strar.length; j++) {
                    int toBe = (int) (strar[j]) -97;
                    int currentChain = (toBe *slope[k]) + (i+1);
                    int finalMove = (currentChain %26) +97;
                    //int toBe = ((((((int) (strar[j])) - 97) + add) % 26) + 97);
                    newstrar[j] = (char) finalMove;
                }
                //IF you want to find a specific line to encrypt, uncomment the print line
                //and also put the plaintext into in.txt
                //System.out.printf("slope %d , add %d, it will be line %d\n", slope[k], i+1,counter);
                String newString = String.valueOf(newstrar);
                total.add(newString);
                counter++;
        }
    }

    static void Shift(String str, ArrayList total) throws FileNotFoundException {
        char[] strar = str.toCharArray();
        int len = strar.length;
        char[] newstrar = new char[len];
        for (int i = 0; i < 26; i++){
            for (int j = 0; j < strar.length; j++){
                int add = i+1;
                int toBe = ((((((int) (strar[j])) -97)+add)%26)+97);
                //System.out.printf("hey, numeric value of %c is %d\n", strar[j], (int) (strar[j]));
                //System.out.printf("hey the number equivalent of %c is %d\n", strar[j], toBe);
                newstrar[j] = (char) toBe;
                //System.out.printf("so now %c is %c\n", strar[j], newstrar[j]);
                //newstrar[j] = 'h';
                //System.out.printf("secondloop %d times\n", j);
            }
            String newString = String.valueOf(newstrar);
            //System.out.printf("i is %d\n newstrar is %s\n", i, newString);
            total.add(newString);
            //System.out.printf("and now total[%d] should also be %s\n", i, Total[i]);
        }

    }
    static void printShift(ArrayList totalSt, int lines) throws FileNotFoundException {
            PrintStream out = new PrintStream(new FileOutputStream("out.txt"));
            System.setOut(out);

            for (int i = 0; i < lines*26; i++) {
                System.out.println(totalSt.get(i));
            }
        System.out.printf("\n");

    }
    private static void printAffine(ArrayList<String> total) throws FileNotFoundException {
        PrintStream out = new PrintStream(new FileOutputStream("out.txt"));
        System.setOut(out);

        for (int i = 0; i < 12*26; i++) {
            System.out.println(total.get(i));
        }
        System.out.printf("\n");

    }
}
