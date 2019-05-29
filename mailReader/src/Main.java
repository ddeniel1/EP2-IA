import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws java.io.FileNotFoundException, java.io.IOException{
        FileReader arqNoSpam = new FileReader("./mailReader/NoSpam.csv");
        //FileReader arqSpam = new FileReader("./mailReader/Spam.csv"); ainda nao tem
        BufferedReader buff = new BufferedReader(arqNoSpam);
        ArrayList<Mail> emailsTreino = new ArrayList<Mail>();
        buff.readLine();
        Mail novo;
        while(buff.ready()) {
            novo = new Mail(false);
            String aux = "";
            do {
                aux = aux + " " + buff.readLine();
            } while (aux.charAt(aux.length() - 1) != '"');
            // System.out.println(aux);
            novo.addMail(aux);
            emailsTreino.add(novo);
        }
        int aux = (int) (emailsTreino.size()*0.7);
        ArrayList<Mail> emailsTeste = new ArrayList<>();

        aux++;
        for (;aux<emailsTreino.size();){
            emailsTeste.add(emailsTreino.remove(aux));
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

//       Faz o Treino
        Probabilidade naoSpam = new Probabilidade(emailsTreino.size()); //alterar futuramente com a implementacao dos Spams
        for (int i=0;i<emailsTreino.size();i++) {
            naoSpam.addMail(emailsTreino.get(i).getPalavras());
        }

//      Resultado do treino
//        System.out.println(naoSpam.toString());

//      Faz o teste
        for (int i=0;i<emailsTeste.size();i++) {
            System.out.println("Email "+ i + " "+ (naoSpam.bayes(emailsTeste.get(i))<0.5?"Nao Spam":"Spam")+" prob = "+naoSpam.bayes(emailsTeste.get(i)));
        }

    }
}
