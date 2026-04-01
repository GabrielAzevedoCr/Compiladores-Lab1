import re

def tokenizar_livro(caminho_arquivo):
    # Usando a variável caminho_arquivo que será passada na chamada da função
    with open(caminho_arquivo, 'r', encoding='utf-8') as file:
        texto = file.read()

    # EXPRESSÃO REGULAR:
    regex = r'[a-zA-ZÀ-ÿ\-]+|[.,!?;:"\'()\[\]]'
    
    # re.findall retorna exatamente uma lista de strings com todos os matches
    tokens = re.findall(regex, texto)
    return tokens

# Execução do programa
if __name__ == "__main__":
    # Passando o nome do arquivo como string (entre aspas!)
    caminho = 'Livro.txt'
    tokens_extraidos = tokenizar_livro(caminho)
    
    # Imprime os 50 primeiros tokens no terminal para validar
    print(tokens_extraidos[:50])
    
    # BÔNUS: Salva o resultado em um arquivo .txt para facilitar o envio pro GitHub
    with open('output_python.txt', 'w', encoding='utf-8') as f:
        f.write(str(tokens_extraidos))
        print("\nTokens salvos em 'output_python.txt'")