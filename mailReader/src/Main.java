import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws java.io.FileNotFoundException, java.io.IOException{
        FileReader arqNoSpam = new FileReader("./mailReader/NoSpam.csv");
        //FileReader arqSpam = new FileReader("./mailReader/Spam.csv"); ainda nao tem
        BufferedReader buff = new BufferedReader(arqNoSpam);
        ArrayList<Mail> emails = new ArrayList<Mail>();
        buff.readLine();
        Mail novo;
        while(buff.ready()){
            novo = new Mail(false);
            String aux = buff.readLine();
            do {
                aux = aux+"\n"+buff.readLine();
            }while (aux.charAt(aux.length()-1)!='"');
           // System.out.println(aux);
            novo.addMail(aux);
            emails.add(novo);
        }
       /* buff = new BufferedReader(arqSpam);
        buff.readLine();
        while(buff.ready()){
            novo = new Mail(true);
            String aux = buff.readLine();
            do {
                aux = aux+"\n"+buff.readLine();
            }while (aux.charAt(aux.length()-1)!='"');
            System.out.println(aux);
            novo.addMail(aux);
            emails.add(novo);
        }*/
        Probabilidade naoSpam = new Probabilidade(emails.size()); //alterar futuramente com a implementacao dos Spams
        for (int i=0;i<emails.size();i++) {
            naoSpam.addMail(emails.get(i).getPalavras());
        }


        System.out.println(naoSpam.toString());
    }
}
