package algoritmogenetico;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Genetico {
    //mochila
    public static ArrayList<Item> mochila = new ArrayList<>();
    private static int QTD_ITENS;
    public static int PESO_MAXIMO = 50;
    //configuracao dos parametros do algoritmo genetico
    public static final double TAXADEMUTACAO = 0.05;
    static final double TAXADECRUZAMENTO = 0.9;

    static final boolean ELITISMO = true;
    static final boolean ESTAGNA = true; //se controla ou nao a estagnacao
    static final double VALORESTAGNA = 200; //valor da estagnacao maxima

    static final int TAMANHODAPOPULACAO = 400;//400
    static final int MAXIMODEGERACOES = 1000;//1000
    //-------------------------------------------------

    private Populacao populacao = new Populacao();
    private final Random r = new Random();
    private int contEstagnar;
    private double melhorAptidaoAnterior = -1;

    //Fluxo principal do algoritmo
    public void Iniciar() {
        declararItens();
        int geracao = 0;
        populacao.iniciarPopulacao(TAMANHODAPOPULACAO);
        do {
            populacao = gerarPopulacao();

            System.out.println("Geracao " + geracao + "| Melhor " + populacao.getIndividuo(TAMANHODAPOPULACAO-1)
            +"\n peso ocupado na mochila = " + populacao.getIndividuo(TAMANHODAPOPULACAO-1).getPeso());

            if (ESTAGNA) {
                contaEstagnacao();
                if (contEstagnar >= VALORESTAGNA) {
                    break;
                }
            }

        } while (++geracao < MAXIMODEGERACOES);
    }

    //Gera nova populacao de solucoes
    private Populacao gerarPopulacao() {
        Populacao novaPopulacao = new Populacao();

        if (ELITISMO) {
            novaPopulacao.setIndividuo(populacao.getIndividuo(0));
        }

        // insere novos individuos na nova populacao, ate atingir o tamanho maximo
        while (novaPopulacao.getNumIndividuos() <= TAMANHODAPOPULACAO) {
            ArrayList<Individuo> filhos = cruzamento(selecaoTorneioBinario());
            novaPopulacao.setIndividuos(filhos);
        }

        novaPopulacao.ordenarPopulacao();
        return novaPopulacao;
    }

    private ArrayList<Individuo> selecaoTorneioBinario() {
        ArrayList<Individuo> pais = new ArrayList<>();
        int a, b;
        //repete esse laco 2 vezes para pegar 2 pais
        for (int i = 0; i < 2; i++) {
            a = r.nextInt(TAMANHODAPOPULACAO);
            b = r.nextInt(TAMANHODAPOPULACAO);
            //considerando que a populacao esta ordenada, o individuo na posicao menor eh melhor
            if (a < b) {
                pais.add(populacao.getIndividuo(a));
            } else {
                pais.add(populacao.getIndividuo(b));
            }
        }

        return pais;
    }

    //selecao por roleta para problema de MINIMIZACAO
    private ArrayList<Individuo> selecaoRoleta() {
        ArrayList<Individuo> pais = new ArrayList<>();

        double totalAptidoes = 0;
        double[] percentual = new double[populacao.getNumIndividuos()];
        double[] fatias = new double[populacao.getNumIndividuos()];

        //soma todas as aptidoes
        for (int i = 0; i < populacao.getNumIndividuos(); i++) {
            totalAptidoes += 1 / populacao.getIndividuo(i).getAptidao();
        }

        //calcula o percentual de cada individuo no total das aptidoes
        for (int i = 0; i < populacao.getNumIndividuos(); i++) {
            percentual[i] = (1 / populacao.getIndividuo(i).getAptidao()) / totalAptidoes;
        }

        //calcula a fatia da roleta para cada individuo de acordo com seu percentual
        for (int i = 0; i < populacao.getNumIndividuos(); i++) {
            if (i == 0) {
                fatias[i] = percentual[i];
            } else {
                fatias[i] = fatias[i - 1] + percentual[i];
            }
        }

        //roda a roleta 2 vezes, para selecionar 2 pais
        for (int i = 0; i < 2; i++) {
            pais.add(populacao.getIndividuo(rodaRoleta(fatias)));
        }
        return pais;
    }

    private int rodaRoleta(double[] fatias) {
        double random = new Random().nextDouble();
        for (int i = 0; i < fatias.length; i++) {
            if (random < fatias[i]) {
                return i;
            }
        }
        return 0;
    }

    private ArrayList<Individuo> cruzamento(ArrayList<Individuo> pais) {
        double[] pai0 = pais.get(0).getGenes();
        double[] pai1 = pais.get(1).getGenes();

        double[] filho0 = new double[pai0.length];
        double[] filho1 = new double[pai1.length];

        if (r.nextDouble() <= TAXADECRUZAMENTO) {
            // se tiver mais genes, adapta os pontos de corte
            System.arraycopy(pai0, 0, filho0, 0, 1);
            System.arraycopy(pai0, 1, filho1, 1, 1);

            System.arraycopy(pai1, 0, filho1, 0, 1);
            System.arraycopy(pai1, 1, filho0, 1, 1);
        } else {
            filho0 = pai0;
            filho1 = pai1;
        }

        ArrayList<Individuo> filhos = new ArrayList<>();
        filhos.add(new Individuo(filho0));
        filhos.add(new Individuo(filho1));

        return filhos;
    }

    private void contaEstagnacao() {
        if (melhorAptidaoAnterior == -1 || populacao.getIndividuo(0).getAptidao() != melhorAptidaoAnterior) {
            melhorAptidaoAnterior = populacao.getIndividuo(0).getAptidao();
            contEstagnar = 1;
        } else {
            contEstagnar++;
        }
    }
    
    public void declararItens(){
        Scanner leitor = new Scanner(System.in);
        System.out.println("----------------------------------------------");
        System.out.println("Digite a quantidade de itens na mochila: ");
        QTD_ITENS = leitor.nextInt();
        
        for(int i = 0; i < QTD_ITENS; i++){
            Item item = new Item("",0,0,0);
            System.out.println("Digite o nome do item: "+(i+1));
            item.setNome(leitor.next());
            System.out.println("Digite o peso deste item: ");
            item.setPeso(leitor.nextInt());
            System.out.println("Digite o valor do item:");
            item.setValor(leitor.nextInt());
            
            mochila.add(item);
            System.out.println("----------------------------------------------");
        }
    }
}
