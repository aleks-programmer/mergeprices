# mergeprices

Рекомендации по развертыванию:
1. Создать БД prices
2. выполнить скрипты из файла script.sql для создания структуры БД и наполнения начальных данных
3. Перейти в директорию с приложением
4. Выполнить mvn clean install для сборки приложения
5. Выполнить java -jar target/merge-prices-1.0-SNAPSHOT-jar-with-dependencies.jar для запуска приложения
