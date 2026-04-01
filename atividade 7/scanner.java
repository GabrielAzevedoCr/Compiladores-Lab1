import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.FileWriter;

public class scanner{
    public static void main(String[] args) {
        String caminhoArquivo = "Livro.txt";
        
        try {
            // 1. Lê todo o conteúdo do arquivo em uma única String
            String texto = Files.readString(Paths.get(caminhoArquivo));
            
            // 2. A mesma Regex usada no Python (com escapes extras para o Java)
            String regex = "[a-zA-ZÀ-ÿ\\-]+|[.,!?;:\"'()\\[\\]]";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(texto);
            
            List<String> tokens = new ArrayList<>();
            
            // 3. Extrai os tokens
            while (matcher.find()) {
                tokens.add(matcher.group()); // Adiciona o texto casado na lista
            }
            
            // 4. Imprime os 50 primeiros para validação
            System.out.println(tokens.subList(0, Math.min(50, tokens.size())));
            
            // 5. Salva a lista num arquivo de output (Requisito 6)
            FileWriter writer = new FileWriter("output_java.txt");
            writer.write(tokens.toString());
            writer.close();
            System.out.println("\nTokens salvos em 'output_java.txt'");
            
        } catch (IOException e) {
            System.err.println("Erro ao ler/escrever o arquivo: " + e.getMessage());
        }
    }
}