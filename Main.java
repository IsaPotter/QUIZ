
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    List<String> perguntas = new ArrayList<>();
    List<String> respostas = new ArrayList<>();
    int pontuacao = 0;
    
    System.out.println("=== BEM-VINDO AO QUIZ ===");
    System.out.println("Responda as perguntas da melhor forma possível.");
    System.out.println("Cada resposta correta vale 10 pontos!\n");
    
    try (Scanner leitorArquivo = new Scanner(new File("perguntas.txt"));
         Scanner leitorUsuario = new Scanner(System.in)) {
         
      while (leitorArquivo.hasNextLine()) {
        String linha = leitorArquivo.nextLine();
        String[] dados = linha.split("\\|");
        if (dados.length != 2) {
          throw new IllegalArgumentException("Formato inválido no arquivo!");
        }
        perguntas.add(dados[0]);
        respostas.add(dados[1].trim().toLowerCase());
      }
      
      for (int i = 0; i < perguntas.size(); i++) {
        System.out.println("\nPergunta " + (i + 1) + ": " + perguntas.get(i));
        String respostaUsuario;
        
        do {
          System.out.print("Sua resposta: ");
          respostaUsuario = leitorUsuario.nextLine().trim().toLowerCase();
          if (respostaUsuario.isEmpty()) {
            System.out.println("Por favor, digite uma resposta!");
          }
        } while (respostaUsuario.isEmpty());
        
        if (respostaUsuario.equals(respostas.get(i))) {
          pontuacao += 10;
          System.out.println("✓ Correto! +10 pontos!");
        } else {
          System.out.println("✗ Errado! Resposta correta: " + respostas.get(i));
        }
      }
      
      System.out.println("\n=== RESULTADO FINAL ===");
      System.out.println("Pontuação: " + pontuacao + " pontos!");
      System.out.println("Acertos: " + (pontuacao/10) + "/" + perguntas.size());
      
    } catch (FileNotFoundException e) {
      System.out.println("Erro ao ler o arquivo: " + e.getMessage());
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
    }
  }
}
