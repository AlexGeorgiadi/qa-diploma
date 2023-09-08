## Необходимое ПО.
IntelliJ IDEA
OpenJDK11
Docker

## Предварительные шаги для запуска автотестов.
1. В корневой папке проекта запустить терминал и выполнить команду docker-compose up.
1. Открыть проект в IntelliJ IDEA.

## Запуск SUT.
В терминале IntelliJ IDEA выполнить команду <br>
**для работы с MySQL**: java -Dspring.datasource.url=jdbc:mysql://localhost:3306/app -jar artifacts/aqa-shop.jar <br>
**для работы с Postgres**: java -Dspring.datasource.url=jdbc:postgresql://localhost:5432/app -jar artifacts/aqa-shop.jar

## Запуск автотестов.
В терминале IntelliJ IDEA выполнить команду <br>
**для работы с MySQL**: ./gradlew test -Ddb.url=jdbc:mysql://localhost:3306/app <br>
**для работы с Postgres**: ./gradlew test -Ddb.url=jdbc:postgresql://localhost:5432/app

## Просмотр репорта Allure.
В терминале IntelliJ IDEA выполнить команду ./gradlew allureServe

## Остановка контейнера Docker.
В корневой папке проекта запустить терминал и выполнить команду docker-compose down.
