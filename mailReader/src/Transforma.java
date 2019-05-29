import java.io.*;
import java.util.Scanner;

public class Transforma {
    public static void main(String[] args) throws java.io.FileNotFoundException, java.io.IOException{
        FileWriter criador = new FileWriter("./noSpamTeste.csv");
        BufferedWriter escritor = new BufferedWriter(criador);
        Scanner scan = new Scanner(System.in);
        String aux=scan.next();
        while(aux.equalsIgnoreCase("0")){
            escritor.write("\""+aux+"\",");
            aux = scan.next();
        }
        escritor.flush();
        escritor.close();
    }
}
