
# Conversor de Monedas

Proyecto desarrollado como parte del curso de Back-end con Java de **Alura LATAM** en colaboración con **Oracle**. Este conversor de monedas utiliza la API de **ExchangeRate** para realizar conversiones precisas y actualizadas entre diferentes monedas.

---

## **Índice**
1. [Características](#características)
2. [Requisitos](#requisitos)
3. [Ejecución](#ejecución)
4. [Estructura del proyecto](#estructura-del-proyecto)
5. [Uso del programa](#uso-del-programa)
6. [Tecnologías utilizadas](#tecnologías-utilizadas)
7. [Detalles de Maven (pom.xml)](#detalles-de-maven)
8. [Autor](#autor)
9. [Contribuciones](#contribuciones)

---

## **Características**
- **Conexión a una API:** Obtiene tasas de cambio en tiempo real utilizando ExchangeRate.
- **Conversión entre monedas disponibles:**
    - ARS (Peso argentino)
    - USD (Dólar estadounidense)
    - BRL (Real brasileño)
    - COP (Peso colombiano)
    - CLP (Peso chileno)
    - BOB (Boliviano boliviano)
- **Interfaz en consola:** Menú interactivo con opciones claras.
- **Validaciones robustas:**
    - Verificación de entradas válidas.
    - Rechazo de montos negativos.
    - Manejo de errores en caso de fallos con la API.

---

## **Requisitos**
- **Java:** JDK 17 o superior.
- **Conexión a Internet:** Para acceder a la API de ExchangeRate.
- **Herramienta de desarrollo:** IntelliJ IDEA, Eclipse o cualquier editor de texto con soporte para Java.

---

## **Ejecución**
1. Clona este repositorio:
   ```bash
   git clone https://github.com/Qleoz12/Java-conversor-de-monedas-Oracle-Alura
   ```
2. Abre el proyecto en tu editor o IDE preferido.
3. Asegúrate de que las dependencias estén instaladas correctamente.
4. Ejecuta el archivo `Main.java`.

---

## **Estructura del proyecto**
```plaintext
src/
├── conversor/
│   ├── Main.java             # Clase principal con el menú interactivo
│   ├── api/
│   │   ├── ServicioAPI.java  # Lógica para conectarse a la API
│   ├── modelos/
│   │   ├── Monedas.java      # Record para las tasas de cambio
│   │   ├── RespuestasAPI.java # Record para mapear respuestas JSON
```

---

## **Uso del programa**
1. **Inicio:** Se muestra un menú con las opciones de conversión disponibles.
2. **Selección:**
    - Elige la moneda de partida y la moneda de destino.
    - Ingresa el monto a convertir (debe ser positivo).
3. **Resultado:** El programa mostrará el monto convertido.
4. **Repetición o salida:** Decide si deseas realizar otra conversión o salir del programa.

---

## **Tecnologías utilizadas**
- **Java 17**
- **Gson:** Procesamiento de datos JSON.
- **Java HTTP Client:** Solicitudes a la API.
- **API ExchangeRate:** Fuente de tasas de cambio.

---

## **Detalles de Maven**
El proyecto utiliza **Spring Boot** y varias dependencias administradas con Maven. Aquí un resumen:

### **Información del proyecto**
- **Group ID:** `com.java.dev.qleoz12`
- **Artifact ID:** `alura-conversor-monedas`
- **Versión:** `0.0.1-SNAPSHOT`
- **Java Version:** `16`

### **Dependencias clave**
- **Spring Boot Starter Web:** Para la estructura del proyecto.
- **Spring Boot Starter Test:** Para pruebas automatizadas.
- **Gson:** Procesamiento de datos JSON (`v2.11.0`).
- **Commons Codec:** Codificación y decodificación de datos (`v1.15`).

### **Plugins importantes**
- **Spring Boot Maven Plugin:** Manejo de empaquetado.
- **Maven Compiler Plugin:** Configuración del compilador para Java 16.
- **Jacoco Maven Plugin:** Generación de reportes de cobertura de pruebas.

---

## **Autor**
Este proyecto fue desarrollado por [**Dann Brown**](https://qleoz12.github.io/about/) como parte del curso de **Back-end con Java** ofrecido por **Alura LATAM** en colaboración con **Oracle**.

---

## **Contribuciones**
¡Las contribuciones son bienvenidas! Haz un **fork** del repositorio, realiza tus mejoras y envía un **pull request**. 💡

---
