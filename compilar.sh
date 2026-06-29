#!/bin/bash

echo "Compilando VidaPlena"

mkdir -p bin

javac -encoding UTF-8 -d bin \
    src/exceptions/*.java \
    src/model/*.java \
    src/service/*.java \
    src/Main.java

if [ $? -eq 0 ]; then
    echo "Compilacao concluida com sucesso!"
    echo ""
    echo "Para executar: java -cp bin Main"
else
    echo "Erro na compilacao."
fi
