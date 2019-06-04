import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws java.io.FileNotFoundException, java.io.IOException{
        Scanner scan = new Scanner(System.in);

        int vezes = scan.nextInt();
        scan.close();
        double porcentagem = 0;
        for (int i=0;i<vezes;i++){
            double corrida = run();
            porcentagem+=corrida;
            System.out.println("Run ("+(i+1)+"): "+ corrida+"% de acerto");
        }
        porcentagem /= vezes;
        System.out.println("Taxa de acerto: "+porcentagem+"% resultado da media de "+vezes+" runs");

    }
    private static double run()throws java.io.FileNotFoundException, java.io.IOException{
        FileReader arqNoSpam = new FileReader("./mailReader/noSpam.csv");
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
        arqNoSpam.close();
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
        buff.close();
        arqSpam.close();
        int tamanho2 = emailsTreino.size()-tamanho1;
//        System.out.println("tamanho1: "+tamanho1+" tamanho2: "+tamanho2);
//       Seleciona os emails para treino e para teste
        int aux =(int) (emailsTreino.size()*0.2);
        ArrayList<Mail> emailsTeste = new ArrayList<>();
        Random gerador = new Random();
        for (int i=0;i<aux;i++){
            emailsTeste.add(emailsTreino.remove(gerador.nextInt(emailsTreino.size())));
        }
//       Faz o Treino
        Probabilidade naoSpam = new Probabilidade(tamanho1);

        while(!emailsTreino.get(0).isSpam()){
            naoSpam.addMail(emailsTreino.remove(0).getPalavras());
        }

        Probabilidade spam = new Probabilidade(emailsTreino.size());
        for (int i=0;i<emailsTreino.size();i++) {
            spam.addMail(emailsTreino.get(i).getPalavras());
//            System.out.println(i);
        }

//      Resultado do treino
//

//      Faz o teste
        int acertou = 0;
        for (int i=0;i<emailsTeste.size();i++) {
            if (naoSpam.bayes(emailsTeste.get(i), spam.getTotalEmails(), spam.getKeys()) >
                    spam.bayes(emailsTeste.get(i), naoSpam.getTotalEmails(), naoSpam.getKeys())) {
                if (emailsTeste.get(i).isSpam()) acertou++;
//                System.out.println("----------->\tEmail Spam\t<-----------\n\n"+emailsTeste.get(i).toString());
            } else {
                if (!emailsTeste.get(i).isSpam()) acertou++;
//                System.out.println("----------->\tEmail nao-Spam\t<-----------\n\n"+emailsTeste.get(i).toString());
            }
//            System.out.println("Email "+ i + " "+ (naoSpam.bayes(emailsTeste.get(i),spam.getTotalEmails(),spam.getKeys())>spam.bayes(emailsTeste.get(i),naoSpam.getTotalEmails(),naoSpam.getKeys())?"Nao Spam":"Spam"));
        }
        double taxaAcerto = (double) acertou/emailsTeste.size();
//        System.out.println((taxaAcerto*100)+"% de taxa de acerto");
        return taxaAcerto*100;
    }
}
