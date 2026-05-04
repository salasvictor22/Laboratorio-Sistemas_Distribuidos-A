# Laboratorio 01: Hilos (Threads) en Java

## Descripción

Este repositorio contiene el desarrollo del **Laboratorio 01** del curso
de *Sistemas Distribuidos*, enfocado en el estudio y aplicación de hilos
(*threads*) en Java.

El objetivo principal de la práctica es comprender el comportamiento de
la ejecución secuencial y concurrente, así como la importancia de la
sincronización en entornos multihilo mediante la implementación de
distintos casos de estudio.

------------------------------------------------------------------------

## Contenido del Laboratorio

### 1. Simulación de atención en un supermercado

Se implementa un sistema que simula el proceso de cobro de clientes en
un supermercado, analizando tres enfoques:

#### Ejecución secuencial (sin hilos)

-   Las tareas se ejecutan de forma ordenada.
-   No existe concurrencia.
-   El tiempo total es la suma de los tiempos individuales.

#### Uso de hilos con la clase `Thread`

-   Cada cajera se ejecuta como un hilo independiente.
-   Se permite la atención simultánea de clientes.
-   Se reduce el tiempo total de ejecución.

#### Uso de hilos con la interfaz `Runnable`

-   Se separa la lógica de la tarea de la gestión del hilo.
-   Se logra un diseño más flexible.
-   Se mantiene la ejecución concurrente.

**Resultado principal:** \> El uso de hilos reduce significativamente el
tiempo de procesamiento, permitiendo una ejecución concurrente
eficiente.

------------------------------------------------------------------------

### 2. Problema Productor--Consumidor

Se implementa el problema clásico de sincronización entre hilos
utilizando un recurso compartido.

#### Versión básica

-   Uso de métodos sincronizados (`synchronized`).
-   Coordinación mediante `wait()` y `notifyAll()`.
-   Control de acceso al recurso compartido.

#### Versión con monitores

-   Uso del monitor implícito de Java.
-   Exclusión mutua garantizada.
-   Coordinación eficiente entre productor y consumidor.

**Resultado principal:** \> Se garantiza que cada dato producido sea
consumido una sola vez, evitando duplicación o pérdida de información.

------------------------------------------------------------------------

## Tecnologías utilizadas

-   Java\
-   Programación concurrente (Threads)\
-   Sincronización de hilos:
    -   `synchronized`
    -   `wait()`
    -   `notify()`
    -   `notifyAll()`\
-   Entorno de desarrollo en Windows 11

------------------------------------------------------------------------

## Objetivos alcanzados

-   Comprender la diferencia entre ejecución secuencial y concurrente.\
-   Implementar hilos utilizando `Thread` y `Runnable`.\
-   Analizar el impacto de la concurrencia en el rendimiento.\
-   Aplicar mecanismos de sincronización para evitar condiciones de
    carrera.\
-   Entender el uso de monitores en Java para la gestión de recursos
    compartidos.

------------------------------------------------------------------------

## Conclusiones

El uso de hilos permite mejorar significativamente el rendimiento de las
aplicaciones al posibilitar la ejecución concurrente de tareas.

La correcta sincronización entre hilos es fundamental para evitar
inconsistencias en los datos compartidos. Mecanismos como
`synchronized`, `wait()` y `notify()` permiten coordinar la ejecución de
manera segura.

Finalmente, el uso de la interfaz `Runnable` y de monitores en Java
proporciona una forma más flexible y estructurada de desarrollar
aplicaciones concurrentes, lo cual es esencial en el contexto de
sistemas distribuidos.

------------------------------------------------------------------------

## Autores

-   Quispe Huaman, Rodrigo Ferdinand
-   Salas Aguilar, Juan Victor
-   Huarca Pallani, Jael Emerson
-   Chambi Orosco, Luis Fernando

