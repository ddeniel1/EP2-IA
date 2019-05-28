import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws java.io.FileNotFoundException, java.io.IOException{
        FileReader arq = new FileReader("./mailReader/emails.csv");
        BufferedReader buff = new BufferedReader(arq);
        ArrayList<Mail> emails = new ArrayList<Mail>();
        buff.readLine();
        Mail novo;
        while(buff.ready()){
            System.out.println("Aqui");
            novo = new Mail();
            String aux = buff.readLine();
            do {
                aux = aux+"\n"+buff.readLine();
            }while (aux.charAt(aux.length()-1)!='"');
            System.out.println(aux);
            novo.addMail(aux);
            emails.add(novo);
        }
        System.out.println(emails.get(0).toString());
    }
}
