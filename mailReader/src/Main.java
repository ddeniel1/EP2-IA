import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws java.io.FileNotFoundException, java.io.IOException{
        FileReader arqNoSpam = new FileReader("./mailReader/noSpam2.csv");
        FileReader arqSpam = new FileReader("./mailReader/Spam.csv");
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
        int tamanho1 = emailsTreino.size();
        buff = new BufferedReader(arqSpam);
        buff.readLine();
        while(buff.ready()){
            novo = new Mail(true);
            String aux = buff.readLine();
            do {
                aux = aux+"\n"+buff.readLine();
            }while (aux.charAt(aux.length()-1)!='"');
//            System.out.println(aux);
            novo.addMail(aux);
            emailsTreino.add(novo);
        }
        int tamanho2 = emailsTreino.size()-tamanho1;
//        System.out.println("tamanho1: "+tamanho1+" tamanho2: "+tamanho2);
//       Seleciona os emails para treino e para teste
        int aux = 1;//(int) (emailsTreino.size()*0.1);
        ArrayList<Mail> emailsTeste = new ArrayList<>();
        Random gerador = new Random();
        for (int i=0;i<aux;i++){
            emailsTeste.add(emailsTreino.remove(gerador.nextInt(emailsTreino.size())));
        }
//       Faz o Treino
        Probabilidade naoSpam = new Probabilidade(tamanho1);
        for (int i=0;i<tamanho1;i++) {
            naoSpam.addMail(emailsTreino.get(i).getPalavras());
//            System.out.println(i);
        }
        Probabilidade spam = new Probabilidade(tamanho2);
        for (int i=tamanho1;i<emailsTreino.size();i++) {
            spam.addMail(emailsTreino.get(i).getPalavras());
//            System.out.println(i);
        }

//      Resultado do treino
//

//      Faz o teste
        for (int i=0;i<emailsTeste.size();i++) {
            if(naoSpam.bayes(emailsTeste.get(i),spam.getTotalEmails(),spam.getKeys())>spam.bayes(emailsTeste.get(i),naoSpam.getTotalEmails(),naoSpam.getKeys())){
                System.out.println("----------->\tEmail Spam\t<-----------\n\n"+emailsTeste.get(i).toString());
            }else{
                System.out.println("----------->\tEmail nao-Spam\t<-----------\n\n"+emailsTeste.get(i).toString());
            }
//            System.out.println("Email "+ i + " "+ (naoSpam.bayes(emailsTeste.get(i),spam.getTotalEmails(),spam.getKeys())>spam.bayes(emailsTeste.get(i),naoSpam.getTotalEmails(),naoSpam.getKeys())?"Nao Spam":"Spam"));
        }
//        System.out.println(naoSpam.toString(spam.getKeys()));
    }
}
