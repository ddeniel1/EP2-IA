import java.io.BufferedReader;
import java.io.FileReader;

public class Main {
    public static void main(String[] args){
        FileReader arq = new FileReader("./mailReader/emails.csv");
        BufferedReader buff = new BufferedReader(arq);

    }
}
