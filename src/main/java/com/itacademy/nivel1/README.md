# Utilidades de Java para la gestión de archivos y directorios

## Descripción

En este nivel trabajamos con diferentes utilidades y bibliotecas de Java para gestionar archivos y directorios, así como para leer, escribir y almacenar información en el sistema de archivos.

A través de cinco ejercicios, ponemos en práctica operaciones habituales en el desarrollo de software, como recorrer directorios, ordenar su contenido, manipular archivos de texto y trabajar con la persistencia de información.

## 📌 Instrucciones del ejercicio

El objetivo de este nivel es familiarizarse con las herramientas que proporciona Java para trabajar con el sistema de archivos y desarrollar pequeñas funcionalidades reutilizables.

**Objetivos del nivel:**
- Utilizar las bibliotecas de Java para acceder y gestionar archivos y directorios.
- Trabajar con estructuras de directorios y sus contenidos.
- Manipular flujos de entrada y salida (I/O).
- Leer y escribir información en archivos.
- Aplicar mecanismos de persistencia de objetos mediante serialización.

## ✨ Ejercicios

### Ejercicio 1 – Listado alfabético del contenido de un directorio

**Enunciado:** Crear una clase que liste alfabéticamente el contenido de un directorio recibido como parámetro.

**Clase: `DirectoryContentLister`**

Se implementa una clase encargada de validar un directorio, obtener su contenido y devolverlo ordenado alfabéticamente por nombre.

**Características:**
- **`sortedListFilesByName(Path directory)`**: valida el directorio recibido, obtiene su contenido y devuelve un array de objetos `Path` ordenado alfabéticamente por nombre, sin distinguir entre mayúsculas y minúsculas.
- **`validateDirectory(Path directory)`**: comprueba que el parámetro no sea `null`, que la ruta exista y que corresponda a un directorio. Si alguna condición no se cumple, lanza una `IllegalArgumentException`.
- **`getContentOfDirectory(Path directory)`**: obtiene el contenido del directorio mediante `Files.list()` y devuelve un array de objetos `Path`. Las posibles excepciones de entrada/salida se propagan mediante `IOException`.

**Pruebas unitarias:**

Se utilizan JUnit 5 y AssertJ para comprobar:
- La ordenación alfabética del contenido.
- El comportamiento ante rutas inexistentes.
- La validación de parámetros `null`.
- El rechazo de rutas que no corresponden a directorios.
- El comportamiento cuando el directorio está vacío.

Las pruebas utilizan `@TempDir` para crear directorios y archivos temporales, evitando depender de rutas específicas del equipo.

### Ejercicio 2 - Listado recursivo de un árbol de directorios

## 🧪 Ejecución de las pruebas desde consola

Los tests se pueden ejecutar desde la terminal, situándose previamente en el directorio raíz del proyecto, donde se encuentra el archivo `pom.xml`.

**Ejecutar todos los tests:**

```bash
mvn test
```

**Limpiar los archivos generados y ejecutar todos los tests:**

```bash
mvn clean test
```

**Ejecutar los tests de una clase concreta:**

```bash
mvn -Dtest=NombreDeLaClaseTest test
```

El comando `mvn clean test` elimina primero el directorio `target` y, a continuación, compila el proyecto y ejecuta las pruebas.

Esta sección recoge los comandos generales que se utilizarán para comprobar los ejercicios de este nivel.