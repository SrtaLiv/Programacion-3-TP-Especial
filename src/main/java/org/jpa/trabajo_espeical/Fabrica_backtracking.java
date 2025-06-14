package org.jpa.trabajo_espeical;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Fabrica_backtracking {
    private List<Maquina> recorridoActual;
    private List<Maquina> soluciones; // solo necesitamos 1
    private int contadorGlobal;

    public Fabrica_backtracking() {
        recorridoActual = new LinkedList<>();
        soluciones = new LinkedList<>();
        contadorGlobal = 0;
    }

    public int getContadorGlobal() {
        return contadorGlobal;
    }

    public List<Maquina> secuenciarMaquinas(List<Maquina> maquinas, int piezasRequeridas) {
        recorridoActual.clear();
        secuenciarMaquinasBacktraking(maquinas, piezasRequeridas, 0);
        return soluciones;
    }

    private void secuenciarMaquinasBacktraking(List<Maquina> maquinas, int piezasRequeridas, int contadorPiezasActuales) {
        // contador global + 1
        // consutlar
        contadorGlobal++;
        if(contadorPiezasActuales==piezasRequeridas) {
            // tengo que comparar contra la solucion actual si el size es mas chico es mejor
            // o el size actual es vacio
            if (soluciones.isEmpty() || soluciones.size() > recorridoActual.size()){
                soluciones.clear();
                soluciones.addAll(recorridoActual); // preguntar
            }

        } else {
            // PODA por solución actual
            if (!soluciones.isEmpty() && recorridoActual.size() >= soluciones.size()) {
                return;
            }

            for (Maquina maq : maquinas){
                contadorPiezasActuales += maq.getPieza();

                // hay que podar los que ya usamos y se pasan de piezasRequeridas...

                // RESTRICCION
                if (contadorPiezasActuales <= piezasRequeridas) {

                    // PODA
                    // si el tamanio.size es mayor, si ya tengo solucion y sigue sinedo mas chcia e la q voy avanzando avanzo, SIZE + 1
                    // O q la solucion sea cero
                    recorridoActual.add(maq);
                    secuenciarMaquinasBacktraking(maquinas, piezasRequeridas, contadorPiezasActuales);
                    recorridoActual.removeLast(); // para linkedlist
                }
                contadorPiezasActuales -= maq.getPieza();
                //recorridoActual.remove(maquinas.size()-1); // este para arraylist
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Fabrica_backtracking fabricaBacktracking = new Fabrica_backtracking();
        List<Maquina> maquinas = new LinkedList<>();
        // guardar las maquinas

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

        /*
        PRUEBAS DESDE JAVA
        List<Maquina> maquinas = new ArrayList<>();
        maquinas.add(new Maquina(7, "Torno"));
        maquinas.add(new Maquina(3, "Fresadora"));
        maquinas.add(new Maquina(4, "Taladro"));
        maquinas.add(new Maquina(1, "Cortadora"));

        // Crear instancia de Fabrica
        Fabrica fabrica = new Fabrica();

        // Probar con un requerimiento de 5 piezas
        int piezasRequeridas = 10;
        List<Maquina> resultado = fabrica.secuenciarMaquinas(maquinas, piezasRequeridas);

        // Mostrar resultado
        System.out.println("Mejor secuencia de máquinas para " + piezasRequeridas + " piezas:");
        for (Maquina maq : resultado) {
            System.out.println(maq.getNombre() + " (" + maq.getPieza() + ")");
        }

        System.out.println(fabrica.getContadorGlobal());*/
    }
}
