#!/bin/bash
# Запуск юнит-тестов MatrixProcessor
# Использование: ./run_tests.sh

set -e

mkdir -p out

echo "Компиляция..."
javac -encoding UTF-8 -d out \
    src/main/java/MatrixProcessor.java \
    src/main/java/Main.java \
    src/test/java/MatrixProcessorTest.java

echo "Запуск тестов..."
echo ""
java -Dfile.encoding=UTF-8 -cp out MatrixProcessorTest
