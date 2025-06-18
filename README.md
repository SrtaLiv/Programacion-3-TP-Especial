
# Estrategia de Resolución

## 📌 Descripción General

Este proyecto implementa un algoritmo de búsqueda para encontrar combinaciones de máquinas que produzcan una cantidad exacta de piezas `P`.

La solución se basa en generar un árbol de exploración mediante una lista enlazada (`LinkedList`), que facilita operaciones como `removeLast()` para manejar eficientemente el recorrido.

---

## 🌲 Generación del Árbol de Exploración

- Se utiliza una `LinkedList` para mantener un recorrido de máquinas en exploración.
- A partir de esta lista, se generan todas las combinaciones posibles de máquinas para producir piezas.
- Se analizan tanto soluciones válidas como no válidas.

---

### 🖼️ Visualización del Árbol de Exploración

![Árbol de exploración](src/main/resources/ARBOL%20DE%20EXPLORACION%20-%20BACKTRACKING.PNG)

---

## 🧩 Estados

### ✅ Estados Solución

Son aquellos recorridos que alcanzan exactamente la cantidad `P` de piezas.

### 🚧 Estados Finales

Son aquellos en los que la suma total de piezas es **menor o igual** a `P`. Estos incluyen:
- Soluciones óptimas.
- Soluciones que no alcanzan `P`.

---

## ✂️ Podas

Se implementan dos tipos de poda para optimizar el algoritmo:

1. **Restricción de Suma:**
   - Si la suma total de piezas del recorrido actual supera `P`, se descarta inmediatamente.

2. **Poda por Tamaño:**
   - Si ya existe una solución parcial y el recorrido actual tiene un tamaño mayor o igual al de esa solución, se descarta por no ser óptimo.

---

## 🛠️ Herramientas y Estructuras

- `LinkedList`: utilizada para construir y modificar fácilmente los recorridos de máquinas.
- Recursividad o backtracking (según implementación) para explorar combinaciones.

---

## 💡 Notas

Esta estrategia permite hallar todas las soluciones posibles, pero se optimiza mediante podas para buscar la solución más eficiente en términos de cantidad de máquinas utilizadas.


# Estrategia de Resolución: Greedy

## 📌 Descripción General

Esta estrategia greedy busca encontrar una combinación de máquinas que sumen exactamente `P` piezas utilizando **la menor cantidad de máquinas posibles**.

---

## ⚙️ Funcionamiento del Algoritmo

### 🎯 Candidatos

- Los **candidatos** son todos los números (máquinas) que:
   - Tienen una cantidad de piezas **menor o igual** a `P`.
   - No superan `P` cuando se suman al total actual.

- Se **repiten** tantas veces como entren en la suma sin superar `P`.

### 🧼 Filtro y descarte

- Cuando se selecciona un candidato y **la suma total supera `P`**, ese número se **descarta de la lista** de candidatos.
- Así, el algoritmo va limpiando automáticamente los elementos que no pueden aportar a una solución válida.

---

## 📊 Ordenamiento

Antes de iniciar la selección:

- Se **ordenan los candidatos de mayor a menor**.
- Esto se hace para:
   - Recorrer la menor cantidad de veces.
   - Obtener la **menor cantidad de máquinas posible** (priorizamos las más grandes primero).

---

## 🔄 Proceso

1. Se inicia con `P` como objetivo.
2. Se seleccionan las máquinas de mayor valor que **entren sin exceder `P`**.
3. Se suman hasta alcanzar exactamente `P` o hasta que no se pueda agregar ninguna sin superarlo.
4. Se devuelve la lista de máquinas elegidas como solución.

---

## ✅ Ventajas

- Muy rápido y eficiente para ciertos casos.
- Ideal cuando se busca una solución "buena" aunque no garantice ser la **óptima global**.

---

## 🖼️ Visualización del proceso

![Greedy proceso](src/main/resources/SELECCION%20DE%20CANDIDATOS%20-%20GREEDY.PNG)







