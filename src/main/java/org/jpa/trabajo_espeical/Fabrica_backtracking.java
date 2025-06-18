package org.jpa.trabajo_espeical;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Fabrica_backtracking {
    private List<Maquina> recorridoActual;
    private List<Maquina> solucion; // solo necesitamos 1
    private int contadorGlobal;

    public Fabrica_backtracking() {
        recorridoActual = new LinkedList<>();
        solucion = new LinkedList<>();
        contadorGlobal = 0;
    }

    public int getContadorGlobal() {
        return contadorGlobal;
    }

    public List<Maquina> secuenciarMaquinas(List<Maquina> maquinas, int piezasRequeridas) {
        recorridoActual.clear();
        secuenciarMaquinasBacktraking(maquinas, piezasRequeridas, 0);
        return solucion;
    }

    private void secuenciarMaquinasBacktraking(List<Maquina> maquinas, int piezasRequeridas, int contadorPiezasActuales) {
        // contador global + 1
        contadorGlobal++;
        if(contadorPiezasActuales==piezasRequeridas) {
            // tengo que comparar contra la solucion actual si el size es mas chico es mejor
            // o el size actual es vacio
            if (solucion.isEmpty() || solucion.size() > recorridoActual.size()){
                solucion.clear();
                solucion.addAll(recorridoActual); // preguntar
            }

        } else {
            // PODA por solución actual
            if (!solucion.isEmpty() && recorridoActual.size() >= solucion.size()) {
                return;
            }

            for (Maquina maq : maquinas){
                contadorPiezasActuales += maq.getPieza();

                // hay que podar los que ya usamos y se pasan de piezasRequeridas...

                // RESTRICCION
                // si el tamanio.size es mayor, si ya tengo solucion y sigue sinedo mas chica e la q voy avanzando avanzo, SIZE + 1
                // O q la solucion sea cero
                if (contadorPiezasActuales <= piezasRequeridas) {

                    recorridoActual.add(maq);
                    secuenciarMaquinasBacktraking(maquinas, piezasRequeridas, contadorPiezasActuales);
                    recorridoActual.removeLast(); // para linkedlist
                }
                contadorPiezasActuales -= maq.getPieza();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Fabrica_backtracking fabricaBacktracking = new Fabrica_backtracking();
        List<Maquina> maquinas = new LinkedList<>();
        // Guardar las maquinas

        BufferedReader br = new BufferedReader(new FileReader("src/main/resources/archivo.txt"));

        // Leer primera línea
        System.out.println("Maquinas disponibles:");
        String primera = br.readLine();
        int piezasRequeridas = Integer.parseInt(primera.trim());

        // Después leer el resto de líneas
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
        List<Maquina> resultado = fabricaBacktracking.secuenciarMaquinas(maquinas, piezasRequeridas);

        // Mostrar resultado
        System.out.println(" ");
        System.out.println("Cantidad de maquinas en funcionamiento: " + resultado.size());
        System.out.println("Mejor secuencia de máquinas para " + piezasRequeridas + " piezas:");
        for (Maquina maq : resultado) {
            System.out.println(maq.getNombre() + " (" + maq.getPieza() + ")");
        }

        System.out.println("Total de llamados recursivos: " + fabricaBacktracking.getContadorGlobal());

    }
}
