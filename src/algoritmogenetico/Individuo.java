package algoritmogenetico;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Individuo implements Comparable<Individuo> {

    private final Random random = new Random();
    private int pesoTotal;
    private Double aptidao;
    //private static int QTD_ITENS = 20;
    //atributos do problema especifico
    private double qtdMilho;
    private double qtdSoja;
    
    private ArrayList<Item> itens;

    //cria um individuo aleatorio da primeira geracao
    public Individuo() {
        itens = new ArrayList<>();
        itens.addAll(Genetico.mochila);
        do {
            this.setQtdItens();
        } while (!validar());
        avaliar();
    }

    // cria um individuo a partir de genes definidos
    public Individuo(int[] genes) {
        itens = new ArrayList<>();
        itens.addAll(Genetico.mochila);
        for(int i = 0; i < itens.size(); i++){
            itens.get(i).setQtd(genes[i]);
        }

        //testa se deve fazer mutacao
        if (random.nextDouble() <= Genetico.TAXADEMUTACAO) {
            int posAleatoria = random.nextInt(genes.length); //define gene que sera mutado
            mutacao(posAleatoria);
        }
        avaliar2();
    }

    private boolean validar() {
        int peso = 0;
        for (Item item : itens) {
            if(item.getQtd() != 0){
                peso = peso + item.getPeso() * item.getQtd();
            }
        }
        
        if(peso < Genetico.PESO_MAXIMO){
        setPeso(peso);
        //itens.clear();
        //itens.addAll(Genetico.mochila);
        //System.out.println("Atribuiu peso = "+ getPeso());
            return true;
        }else{
            return false;
        }
        //return peso < Genetico.PESO_MAXIMO;
    }

    private void mutacao(int posicao) {
        do {
            Random r = new Random();
            
            itens.get(posicao).setQtd(r.nextInt(5+1));
            
            
        } while (!validar());
            avaliar();
    }

    
    private void setQtdItens(){
        itens.forEach((item) -> {
            item.setQtd(random.nextInt(5+1));
        });
        /*
        System.out.println("---------------------------------------------");
        Genetico.mochila.forEach((item) -> {    
            System.out.println("Item "+ item.getNome()+ " Qtd = "+ item.getQtd()
            + " || valor/unidade = "+ item.getValor() + " peso = "+ item.getPeso());
        });*/
    }
    
    
    
    private void setQtdMilho() {
        this.qtdMilho = random.nextInt(10);
    }

    private void setQtdSoja() {
        this.qtdSoja = random.nextDouble();
    }

    public double getAptidao() {
        return aptidao;
    }

    public int[] getGenes() { 
        int genes[] = new int[itens.size()];
        for(int i = 0; i < itens.size(); i++){
            genes[i] = itens.get(i).getQtd();
        }
        
        return genes;
    }

    private void avaliar() {
        Double valor = 0.0;
        for (Item item : itens) {
            if(item.getQtd() != 0){
            valor = valor + (item.getValor() * item.getQtd());
            }
             
        }     
        aptidao = valor;
        //System.out.println("Valor = "+ aptidao);
    }
    
    private void avaliar2() {
        Double valor = 0.0;
        for (Item item : itens) {
            if(item.getQtd() != 0){
            valor = valor + (item.getValor() * item.getQtd());
            }
             
        }   
        aptidao = valor;
        //aptidao = valor; System.out.println("Aptidao print = " + aptidao);
        //System.out.println("Valor = "+ aptidao);
    }

    @Override
    public int compareTo(Individuo i) {
        return this.aptidao.compareTo(i.aptidao);
        /*
        if (this.aptidao <= i.aptidao) {
            return -1;
        }
        if (this.aptidao >= i.aptidao) {
            return 1;
        }
        return 0;*/
    }

    public int getPeso() {
        int pesoTotal = 0;
        for (Item item : itens) {
            if(item.getQtd() != 0){
                pesoTotal = pesoTotal + item.getPeso() * item.getQtd();
            }
        }
        
        return pesoTotal;
    }

    public void setPeso(int peso) {
        this.pesoTotal = peso;
    }
    
    @Override
    public String toString() {
        return "Cromossomo " + Arrays.toString(getGenes()) + " Aptidao: " + aptidao + "\n"
                + "peso ocupado na mochila = " + getPeso();
    }
}
