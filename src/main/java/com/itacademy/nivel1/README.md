# Utilidades de Java para la gestión de archivos y directorios

## Descripción

En este nivel trabajamos con diferentes utilidades de Java para gestionar archivos y directorios, así como para leer, escribir y almacenar información en el sistema de archivos.

A través de cinco ejercicios progresivos, ponemos en práctica operaciones habituales en el desarrollo de software, como recorrer directorios, ordenar su contenido, manipular archivos de texto y realizar la serialización de objetos.

## 📌 Instrucciones del ejercicio

El objetivo de este nivel es familiarizarse con las herramientas que proporciona Java para trabajar con el sistema de archivos y desarrollar pequeñas funcionalidades reutilizables.

**Objetivos del nivel:**
- Utilizar la clase `File` para acceder y gestionar archivos y directorios.
- Trabajar con estructuras recursivas, como los árboles de directorios.
- Manipular flujos de entrada y salida (I/O).
- Leer y escribir información en archivos.
- Aplicar la persistencia de objetos mediante serialización.

## ✨ Ejercicios

### Ejercicio 1 – Listado alfabético del contenido de un directorio

**Enunciado:** Crear una clase que liste alfabéticamente el contenido de un directorio recibido como parámetro.

**Clase: `DirectoryContentLister`**

Se implementa una clase encargada de obtener y ordenar el contenido de un directorio.

**Características:**
- **`sortedListFilesByName(File file)`**: valida el directorio recibido, obtiene su contenido y devuelve un array de objetos `File` ordenado alfabéticamente por nombre, sin distinguir entre mayúsculas y minúsculas.
- **`validateDirectory(File file)`**: comprueba que el parámetro no sea `null`, que la ruta exista y que corresponda a un directorio. Si alguna condición no se cumple, lanza una `IllegalArgumentException`.
- **`getContentOfDirectory(File file)`**: obtiene el contenido mediante `listFiles()`. Si el método devuelve `null`, lanza una `IllegalStateException`.

**Pruebas unitarias:**

Se utilizan JUnit 5 y AssertJ para comprobar:
- La ordenación alfabética del contenido.
- El comportamiento ante rutas inexistentes.
- La validación de parámetros `null`.
- El rechazo de rutas que no corresponden a directorios.
- El comportamiento cuando el directorio está vacío.
