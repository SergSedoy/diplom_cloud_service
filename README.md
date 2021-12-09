# Дипломная работа "Облачное хранилище"
Дипломная работа представляет собой REST-сервис.
REST интерфейс предоставляет возможность загрузки, скачивания, удаления файлов и вывода списка уже загруженных файлов пользователя.
Все запросы к сервису возможны только через авторизацию.
Сервис реализовывает все методы описанные в протоколе https://github.com/SergSedoy/netology-homeworks/blob/master/diploma/CloudServiceSpecification.yaml.

При реализации данной работы некоторую трудность вызвали темы, которые пришлось изучать "на ходу", в том числе конвертация файла в тип содержимого ответа - *multipart/form-data*.
Также разобрался с аутентификацией в Spring Security с помощью JWT токена!

### В проекте используются технологии:
- Фреймворк *Spring Boot*;
- Фреймворк *Spring Security*;
- JSON Web Tokens (*JWT*)
- Сборщик проектов *Gradle*;
- Система управления реляционными базами данных *MySQL*;
- Фреймворки *Mockito* и *JUnit*;
- Платформа для разработки и запуска контейнеров *Docker, docker-compose*;
- Интеграционные тесты с использованием *@Testcontainers*.

### Интеграция с FRONT:
Инструкция по подключению веб-приложения (FRONT) к разработанному REST-сервису находится по адресу https://github.com/SergSedoy/netology-homeworks/blob/master/diploma/cloudservice.md.

### Docker: команды запуска и порты:
|Команда | Описание |
|--------|----------|
|'docker build -t transferservice .'|*создаём Docker-образ*
|'docker run -p 5500:5500 -d transferservice'|*запускаем Docker-образ*|
|'docker stop $(docker ps -aq)'|*$(docker ps -aq) - выводим список id контейнеров*|
|'docker rm $(docker ps -aq)'|*удалить все контейнеры*|
|'docker-compose up'|*запуск через docker-compose.yml*