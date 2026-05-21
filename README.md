# AvengerScript IDE

IDE web para el lenguaje **AvengerScript**, desarrollado para el curso de Compiladores.

---

## Requisitos

- **Java 11 o mayor** instalado y en el PATH  
  Verificá con: `java -version`  
  Descargá desde: https://adoptium.net

---

## Cómo correr

1. Cloná el repositorio
2. Doble clic en **`run-ide.bat`**
3. El navegador se abre automáticamente en `http://localhost:8080`
4. Para apagar el servidor, cerrá la ventana de la terminal

> La primera vez descarga dependencias automáticamente (requiere internet). Las siguientes veces arranca más rápido.

---

## Estructura del proyecto

```
ParserCompis1S/
├── proyecto1/
│   ├── ide/          → Servidor web + compilador AvengerScript
│   │   └── src/main/java/
│   │       ├── antlr/    → Gramática y lexer/parser generados por ANTLR
│   │       ├── example/  → EvalVisitor, SemanticVisitor, Variable
│   │       └── ide/      → Spring Boot (controladores, servicios, modelos)
│   ├── testing/      → Archivos de prueba (.txt, .avng)
│   └── docs/         → Documentación técnica
└── lab01/            → Laboratorio 01 (parser de expresiones)
```

---

## Para desarrolladores

Si modificás la gramática (`Avenger.g4`), regenerá los archivos ANTLR:

1. Instalá el plugin **ANTLR v4** en IntelliJ (autor: Terence Parr)
2. Click derecho en `Avenger.g4` → *Generate ANTLR Recognizer*
3. Asegurate que los archivos se generen en `src/main/java/antlr/`
