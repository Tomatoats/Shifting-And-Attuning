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
                //if you're doing affines, make sure only AffineAndPrint is up
                //if you're doing shifts, make sure only ShiftAndPrint is up
                ShiftAndPrint(str, total);
                //AffineAndPrint(str);

            }
        } catch (IOException | NoSuchElementException | IllegalStateException e) {
            e.printStackTrace();
        }
        //if you're only doing shift ciphers, comment out printAffine
        // and make sure printShift is up.
        printShift(total, lines);
        //if you're doing affine ciphers, only do printAffine

    }
    static void ShiftAndPrint(String str, ArrayList totalSt) throws FileNotFoundException {
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
            totalSt.add(newString);
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
}
