# AvengerScript IDE

IDE web para el lenguaje **AvengerScript** — un lenguaje de programación con temática Marvel desarrollado para el curso de Compiladores en la Universidad Rafael Landívar.

---

## Requisitos

- **Java 11 o mayor** instalado y en el PATH

```bash
java -version   # debe mostrar 11.x o superior
```

Si no lo tenés: [https://oracle.com](https://www.oracle.com/java/technologies/downloads/#jdk26-windows)

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
- **Ejecución interactiva** — el output aparece en tiempo real directamente en el panel de salida
- **Tabla de tokens** del análisis léxico
- **Código Java generado** visible en el panel derecho, descargable como `Traduccion.java`
- **Ejemplos** precargados desde el menú desplegable

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
├── vscode-extension/        → Extensión de VS Code
│   ├── install-extension.bat → Construye e instala la extensión
│   ├── src/extension.ts     → Lógica principal (compilar, ejecutar, diagnósticos)
│   ├── syntaxes/            → Gramática TextMate para syntax highlighting
│   ├── snippets/            → Snippets de código
│   └── images/              → Ícono de la extensión
└── lab01/                   → Laboratorio 01 (parser de expresiones)
```

---

## Extensión de VS Code

Además del IDE web, el proyecto incluye una extensión de VS Code que permite compilar y ejecutar archivos `.avng` directamente desde el editor, usando el mismo servidor como backend.

### Instalación (una sola vez)

**Requisito previo:** tener [Node.js](https://nodejs.org) instalado.

1. Iniciá el servidor con **`run-ide.bat`**
2. Doble clic en **`vscode-extension/install-extension.bat`**
   - Descarga dependencias npm
   - Compila el TypeScript
   - Genera el archivo `avengerscript-1.0.0.vsix`
   - Lo instala automáticamente en VS Code
3. Recargá VS Code (`Ctrl+Shift+P` → *Reload Window*)

### Funcionalidades

| Función | Cómo activarla |
|---|---|
| Syntax highlighting en `.avng` | Automático al abrir el archivo |
| Compilar y ver errores subrayados | `Ctrl+Shift+B` o al guardar |
| Ejecutar con entrada estándar | `F5` |
| Ver el código Java generado | `Ctrl+Shift+P` → *AvengerScript: Ver código Java generado* |
| Iniciar el servidor IDE desde VS Code | `Ctrl+Shift+P` → *AvengerScript: Iniciar servidor IDE* |
| Snippets (`stark`, `nebula`, `fury`…) | Tipear el prefix y `Tab` |

> El IDE web en `localhost:8080` sigue funcionando con normalidad. La extensión es un cliente que le habla al mismo servidor.

### Estructura de la extensión

```
vscode-extension/
├── install-extension.bat        → Construye e instala la extensión
├── package.json                 → Manifiesto (comandos, keybindings, config)
├── src/extension.ts             → Lógica principal
├── syntaxes/avenger.tmLanguage.json  → Syntax highlighting
├── snippets/avenger.json        → 14 snippets de código
└── language-configuration.json → Brackets, comentarios
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
**Más información en `docs/AvengerScript_Documentacion.pdf`**

---

## Integrantes

| Nombre | Carné |
|--------|-------|
| Nery Hernández | 1098824 |
| Gabriel Toyom | 1051524 |
| Marc Schaub | 1243424 |

**Universidad Rafael Landívar — Compiladores**
