#!/bin/bash

# O loop vai ler o arquivo linha por linha até chegar ao final (EOF)
while read -r linha || [ -n "$linha" ]; do
    
    # Etapa 3: tr -d ' \t\r' para remover espaços em branco e formatações
    linha_processada=$(echo "$linha" | tr -d ' \t\r')
    
    # Imprime a saída apenas se a linha não ficar vazia após a limpeza
    if [ -n "$linha_processada" ]; then
        echo "[SCANNER] Linha recebida (sem espaços): '$linha_processada'"
    fi

done
