package pso;

import java.util.ArrayList;
import featureselectionwraper.BaseDeDados;
import featureselectionwraper.FeatureSelection;
import java.sql.Time;
import java.util.Scanner;

/**
 *
 * @author Laura Emmanuella <lauraemmanuella at eaj.ufrn.br>
 */

public class Principal {
    static final double ALFA = 0.5; //inercia
    static final double BETA = 2.05; //memoria
    static final double GAMA = 2.05; //cooperacao
    static final double VMAX = 6; //velocidade maxima
    static final boolean VELCONTROL = true; //se controla ou nao a velocidade
    
    static final int QTDPARTICULAS = 200;
    static final int QTDITERACOES = 50;
    
    static double PESO[];
    static double VALOR[];
    static int CAPACIDADE;
    static int QUANTIDADE;
    
  /*
    FAZ PARTE DA SOLUÇÃO DA PROFESSORA

    static final BaseDeDados BASE = BaseDeDados.KEYSTROKE;
    static final String MODELO = "naive";

    */

    public static void main(String[] args) {
        /*Nuvem nuvemParticulas = new Nuvem(QTDPARTICULAS);
        nuvemParticulas.executarPSO();
        ArrayList<Integer> selecionados = nuvemParticulas.getMelhorSolucaoNuvem();
        FeatureSelection fs = new FeatureSelection(BASE.NOMEBASE, MODELO);
        fs.removerAtributos(selecionados, true, "PSO");
        */
        
        Scanner scanner = new Scanner(System.in);
        System.out.println("Quantidade de itens: ");
        QUANTIDADE = scanner.nextInt();
        
        PESO = new double[QUANTIDADE];
        VALOR = new double[QUANTIDADE];
        
        for(int i = 0; i < QUANTIDADE; i++){
            System.out.println("Digite o Objeto " + (i+1));
            VALOR[i] = scanner.nextDouble();
            
            System.out.println("Digite o peso " + (i+1));
            PESO[i] = scanner.nextDouble();
        }
        
        System.out.println("Digite a capacidade da Mochila");
        CAPACIDADE = scanner.nextInt();
        
        Nuvem nuvem = new Nuvem(QUANTIDADE);
        nuvem.executarPSO();
        

        System.out.println("------PROCESSO CONCLUIDO------");
    }

}
