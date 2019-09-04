package ag;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Populacao {

    private final ArrayList<Individuo> individuos;

    public Populacao() {
        individuos = new ArrayList<>();
    }

    public void iniciarPopulacao(int tamPop) {
        for (int i = 0; i < tamPop; i++) {
            individuos.add(new Individuo());
        }
        ordenarPopulacao();
    }

    public void ordenarPopulacao() {
        //crescente para casos de minimização
        Collections.sort(individuos);
/*
        for (int i = 0; i < individuos.size(); i++) {

            for (int j = individuos.size() - 1; j > i; j--) {
                //if (individuos.get(i).getNome().compareToIgnoreCase(lista.get(j).getNome()) > 0) {
                if(individuos.get(i).getAptidao() > individuos.get(j).getAptidao()){
                    Individuo tmp = individuos.get(i);
                    individuos.set(i, individuos.get(j));
                    individuos.set(j, tmp);

                }
            }
        }*/

    }

    public Individuo getIndividuo(int pos) {
        return individuos.get(pos);
    }

    public void imprimePopulacao() {
        for (int i = 0; i < individuos.size(); i++) {
            System.out.println("Aptidão " + i + " = " + individuos.get(i).getAptidao());
        }
    }

    // coloca um individuo na proxima posicao disponivel da populacao
    public void setIndividuo(Individuo individuo) {
        individuos.add(individuo);
    }

    public void setIndividuos(ArrayList<Individuo> filhos) {
        individuos.addAll(filhos);
    }

    // numero de individuos existentes na populacao
    public int getNumIndividuos() {
        return individuos.size();
    }

}
