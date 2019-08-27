package algoritmogenetico;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Individuo implements Comparable<Individuo> {

    private final Random random = new Random();
    private int peso;
    private Double aptidao;
    //private static int QTD_ITENS = 20;
    //atributos do problema especifico
    private double qtdMilho;
    private double qtdSoja;
    
    private ArrayList<Item> itens;

    //cria um individuo aleatorio da primeira geracao
    public Individuo() {
        itens = new ArrayList<>();
        do {
            this.setQtdItens();
        } while (!validar());
        avaliar();
    }

    // cria um individuo a partir de genes definidos
    public Individuo(double[] genes) {
        qtdMilho = genes[0];
        qtdSoja = genes[1];
        //testa se deve fazer mutacao
        if (random.nextDouble() <= Genetico.TAXADEMUTACAO) {
            int posAleatoria = random.nextInt(genes.length); //define gene que sera mutado
            mutacao(posAleatoria);
        }
        avaliar();
    }

    private boolean validar() {
        int peso = 0;
        for (Item item : Genetico.mochila) {
            if(item.getQtd() != 0){
                peso = peso + item.getPeso() * item.getQtd();
            }
        }
        
        if(peso < Genetico.PESO_MAXIMO){
        setPeso(peso);
        itens.addAll(Genetico.mochila);
        System.out.println("Atribuiu peso = "+ getPeso());
            return true;
        }else{
            return false;
        }
        //return peso < Genetico.PESO_MAXIMO;
    }

    private void mutacao(int posicao) {
        do {
            if (posicao == 0) {
                this.setQtdMilho();
            } else if (posicao == 1) {
                this.setQtdSoja();
            }
        } while (!validar());

    }

    
    private void setQtdItens(){
        Genetico.mochila.forEach((item) -> {
            item.setQtd(random.nextInt(5+1));
        });
        
        System.out.println("---------------------------------------------");
        Genetico.mochila.forEach((item) -> {    
            System.out.println("Item "+ item.getNome()+ " Qtd = "+ item.getQtd()
            + " || valor/unidade = "+ item.getValor() + "peso = "+ item.getPeso());
        });
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
        /*for(Item item : itens){
            System.out.println("Nome = " + item.getNome());
            System.out.println("Peso = " + item.getPeso());
            System.out.println("Quantidade ="+ item.getQtd());
            System.out.println("Valor = "+ item.getValor());
        }*/
        //return new double[]{}
        //return new double[]{qtdMilho, qtdSoja};
        int genes[] = new int[itens.size()];
        for(int i = 0; i < itens.size(); i++){
            genes[i] = itens.get(i).getQtd();
        }
        
        return genes;
    }

    private void avaliar() {
        Double valor = 0.0;
        for (Item item : Genetico.mochila) {
            if(item.getQtd() != 0){
            valor = valor + (item.getValor() * item.getQtd());
            }
             
        }     
        aptidao = valor;
        //System.out.println("Valor = "+ aptidao);
    }

    @Override
    public String toString() {
        return "Cromossomo " + Arrays.toString(getGenes()) + " Aptidao: " + aptidao + "\n";
    }

    @Override
    public int compareTo(Individuo i) {
        return this.aptidao.compareTo(i.aptidao);
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }
    
    
}
