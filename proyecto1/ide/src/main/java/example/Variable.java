package example;

/**
 * Representa una entrada en la tabla de símbolos.
 * Almacena el nombre, valor actual, tipo Java y ámbito de la variable.
 */
public class Variable {

    private final String name;
    private String value;
    private final String type;
    private final String scope;

    public Variable(String name, String value, String type, String scope) {
        this.name  = name;
        this.value = value;
        this.type  = type;
        this.scope = scope;
    }

    // ── Getters ───────────────────────────────────────────────────────────────
    public String getName()  { return name;  }
    public String getValue() { return value; }
    public String getType()  { return type;  }
    public String getScope() { return scope; }

    // ── Setter (solo el valor puede cambiar después de la declaración) ───────────
    public void setValue(String value) { this.value = value; }

    @Override
    public String toString() {
        return String.format("Variable{ Nombre='%s', Tipo='%s', Valor='%s', Ambito='%s' }",
                name, type, value, scope);
    }
}