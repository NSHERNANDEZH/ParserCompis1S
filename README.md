# AvengerScript IDE

IDE web para el lenguaje **AvengerScript** — un lenguaje de programación con temática Marvel desarrollado para el curso de Compiladores en la Universidad Rafael Landívar.

---

## Requisitos

- **Java 11 o mayor** instalado y en el PATH

```bash
java -version   # debe mostrar 11.x o superior
```

Si no lo tenés: [https://adoptium.net](https://adoptium.net)

---

## Cómo correr

1. Cloná el repositorio
2. Doble clic en **`run-ide.bat`**
3. El navegador se abre automáticamente en `http://localhost:8080`
4. Para apagar el servidor, cerrá la ventana de la terminal

> La primera vez descarga dependencias automáticamente — necesitás internet y puede tardar unos minutos. Las siguientes veces arranca en segundos.

---

## El lenguaje AvengerScript

AvengerScript traduce a Java. Cada keyword tiene su héroe:

| Categoría | AvengerScript | Equivalente |
|-----------|--------------|-------------|
| Tipo entero | `stark` | `int` |
| Tipo decimal | `banner` | `double` |
| Tipo texto | `rogers` | `String` |
| Tipo booleano | `thor` | `boolean` |
| Función void | `bob` | `void` |
| Asignación | `jarvis` | `=` |
| If | `vision` | `if` |
| Else | `wanda` | `else` |
| While | `loki` | `while` |
| For | `fury` | `for` |
| Print | `nebula(...)` | `System.out.println(...)` |
| Read input | `gamora(var)` | `scanner.next(var)` |
| Igual | `==` | `==` |
| Diferente | `!=` | `!=` |
| Retorno | `return` | `return` |

### Ejemplo

```
// Factorial en AvengerScript
stark factorial(stark n) {
    stark resultado jarvis 1;
    fury (stark i jarvis 1; i < n + 1; i jarvis i + 1) {
        resultado jarvis resultado * i;
    }
    return resultado;
}

nebula("Ingresa un numero:");
stark n jarvis 0;
gamora(n);
nebula(factorial(n));
```

---

## Funcionalidades del IDE

- **Editor** con resaltado de sintaxis para AvengerScript
- **Compilación** con reporte de errores léxicos, sintácticos y semánticos
- **Ejecución interactiva** — el output aparece en tiempo real y podés escribir entrada (`gamora`) directamente en el panel de salida
- **Tabla de tokens** del análisis léxico
- **Código Java generado** visible en el panel derecho, descargable como `Traduccion.java`
- **Ejemplos** precargados desde el menú desplegable
- **Paneles redimensionables** — arrastrá los divisores entre editores y consola

---

## Estructura del proyecto

```
ParserCompis1S/
├── run-ide.bat              → Script para compilar y correr el IDE
├── proyecto1/
│   ├── ide/                 → Módulo principal (compilador + servidor web)
│   │   ├── pom.xml
│   │   └── src/main/
│   │       ├── java/
│   │       │   ├── antlr/   → Gramática Avenger.g4 y archivos ANTLR generados
│   │       │   ├── example/ → EvalVisitor, SemanticVisitor, Variable
│   │       │   └── ide/     → Spring Boot: controladores, servicios, modelos, WebSocket
│   │       └── resources/
│   │           └── static/  → Frontend: index.html, app.js, style.css
│   ├── docs/                → Documentación técnica (PDF)
│   └── testing/             → Archivos de prueba (.txt, .avng)
└── lab01/                   → Laboratorio 01 (parser de expresiones)
```

---

## Para desarrolladores

### Regenerar archivos ANTLR

Si modificás la gramática (`Avenger.g4`):

1. Instalá el plugin **ANTLR v4** en IntelliJ (autor: Terence Parr)
2. Click derecho en `Avenger.g4` → *Configure ANTLR* → output dir: `src/main/java/antlr`
3. Click derecho en `Avenger.g4` → *Generate ANTLR Recognizer*

### Compilar manualmente

```bash
./mvnw -f proyecto1/ide/pom.xml clean package -DskipTests
java -jar proyecto1/ide/target/avenger-ide-1.0-SNAPSHOT.jar
```

---

## Integrantes

| Nombre | Carné |
|--------|-------|
| Nery Hernández | 1098824 |
| Gabriel Toyom | 1051524 |
| Marc Schaub | 1243424 |

**Universidad Rafael Landívar — Compiladores**
