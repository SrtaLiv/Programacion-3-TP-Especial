package org.jpa.trabajo_espeical;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Fabrica_greedy {
    private List<Maquina> recorridoActual;
    private List<Maquina> solucion;
    private int contadorGlobal;

    public Fabrica_greedy() {
        recorridoActual = new LinkedList<>();
        solucion = new LinkedList<>();
        contadorGlobal = 0;
    }

    public int getContadorGlobal() {
        return contadorGlobal;
    }

    public List<Maquina> secuenciarMaquinas(List<Maquina> maquinas, int piezasRequeridas) {
        solucion.clear();
        recorridoActual.clear();
        maquinas.sort(Comparator.comparingInt(Maquina::getPieza).reversed());
        secuenciarMaquinasGreedy(maquinas, piezasRequeridas, 0);
        return solucion;
    }

    private void secuenciarMaquinasGreedy(List<Maquina> maquinas, int piezasRequeridas, int contadorPiezasActuales) {
        while (!maquinas.isEmpty()) {
            if(piezasRequeridas==contadorPiezasActuales) {
                solucion = new LinkedList<>(recorridoActual);
                maquinas.clear();
            }
            else {
                if ((contadorPiezasActuales + maquinas.getFirst().getPieza()) <= piezasRequeridas){ // si es factible
                    recorridoActual.add(maquinas.getFirst());
                    contadorPiezasActuales+=maquinas.getFirst().getPieza();
                }
                else {
                    // si no es factible, es mayor a cant de piezas
                    maquinas.removeFirst();
                }
            }
            contadorGlobal++;
        }

    }

        public static void main(String[] args) throws IOException {
            Fabrica_greedy fabricaGreedy = new Fabrica_greedy();
            List<Maquina> maquinas = new LinkedList<>();
            // guardar las maquinas

            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/archivo.txt"));

            // Leer primera línea
            String primera = br.readLine();
            int piezasRequeridas = Integer.parseInt(primera.trim());

            // Despues leer el resto de linea
            System.out.println("Maquinas disponibles:");
            String linea;
            while((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                String nombre = partes[0].trim();
                int piezas = Integer.parseInt(partes[1].trim());
                maquinas.add(new Maquina(piezas, nombre));
                System.out.println(nombre + " -> " + piezas);
            }
            br.close();

            // Ejecutar algoritmo
            List<Maquina> resultado = fabricaGreedy.secuenciarMaquinas(maquinas, piezasRequeridas);

            // Mostrar resultado
            System.out.println(" ");
            System.out.println("Cantidad de maquinas en funcionamiento: " + resultado.size());
            System.out.println("Mejor secuencia de máquinas para " + piezasRequeridas + " piezas:");
            for (Maquina maq : resultado) {
                System.out.println(maq.getNombre() + " (" + maq.getPieza() + ")");
            }

            System.out.println("Estados generados: " + fabricaGreedy.getContadorGlobal());

    }
}
