import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MiniScanner {
    public static void main(String[] args) {
        String codigo = "position = initial + rate * 60";
        
        // Expressão regular com Grupos Nomeados em Java: (?<NOME>regra)
        String regex = "(?<ID>[a-zA-Z_][a-zA-Z0-9_]*)|(?<NUM>\\d+)|(?<OP>[=+\\-*])";
        
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(codigo);
        
        // Simulando a Tabela de Símbolos exigida para a saída do Dragon Book
        Map<String, Integer> tabelaSimbolos = new LinkedHashMap<>();
        int idCounter = 1;
        
        List<String> saidaTokens = new ArrayList<>();

        // Iterando sobre os matches encontrados
        while (matcher.find()) {
            if (matcher.group("ID") != null) {
                String lexema = matcher.group("ID");
                
                // Se a variável ainda não está na Tabela de Símbolos, adiciona
                if (!tabelaSimbolos.containsKey(lexema)) {
                    tabelaSimbolos.put(lexema, idCounter++);
                }
                
                // Formato Dragon Book: <id, apontador_para_tabela>
                saidaTokens.add("<id, " + tabelaSimbolos.get(lexema) + ">");
                
            } else if (matcher.group("NUM") != null) {
                // Números literais: <60>
                saidaTokens.add("<" + matcher.group("NUM") + ">");
                
            } else if (matcher.group("OP") != null) {
                // Operadores: <=>, <+>, <*>
                saidaTokens.add("<" + matcher.group("OP") + ">");
            }
        }
        
        // Imprimindo exatamente como na Figura 1.7
        System.out.println("Saída do Scanner:");
        System.out.println(String.join(" ", saidaTokens));
        
        // Mostrando a tabela de símbolos para provar que os índices batem
        System.out.println("\nTabela de Símbolos (Lexema -> Índice):");
        for (Map.Entry<String, Integer> entry : tabelaSimbolos.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }
}