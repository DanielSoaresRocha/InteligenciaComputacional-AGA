package ag;

import util.Time;



public class Principal {

    public static void main(String[] args) {
        Time tempo = new Time();

        Genetico AG = new Genetico();
        AG.Iniciar();
        
        System.out.println("\nTempo de execução: " +  tempo );
        
        Runtime rt = Runtime.getRuntime();
        System.out.println("Uso de memória  = " +(rt.totalMemory()-rt.freeMemory())/(1000*1000)+"M");
        
    }
}
