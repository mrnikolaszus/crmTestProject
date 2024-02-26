*** Итоговый проект курса JavaRush ***


## [REST API](http://localhost:8080/doc)

## Концепция:

- Spring Modulith
    - [Spring Modulith: достигли ли мы зрелости модульности](https://habr.com/ru/post/701984/)
    - [Introducing Spring Modulith](https://spring.io/blog/2022/10/21/introducing-spring-modulith)
    - [Spring Modulith - Reference documentation](https://docs.spring.io/spring-modulith/docs/current-SNAPSHOT/reference/html/)

```
  url: jdbc:postgresql://localhost:5432/jira
  username: jira
  password: JiraRush
```

- Есть 2 общие таблицы, на которых не fk
    - _Reference_ - справочник. Связь делаем по _code_ (по id нельзя, тк id привязано к окружению-конкретной базе)
    - _UserBelong_ - привязка юзеров с типом (owner, lead, ...) к объекту (таска, проект, спринт, ...). FK вручную будем
      проверять

## Аналоги

- https://java-source.net/open-source/issue-trackers

## Тестирование

- https://habr.com/ru/articles/259055/

Список выполненных задач:

Изучена структура

Удалены соц сети (вместе с классами социальных сетей и исправлен SpringSecturityConfig)

чувствительная информация в проперти, несколько yaml файлов (application, application-secret, application-test)

Использована библиотека TestContainers, что бы динамически поднимать postgres контейнер для тестов

Сделаны тесты для ProgileRestController

Переписан IO на NIO

Написаны методы для task_tag (удаление и добавление) без фронта

9 и 10 задание выполнены: Написать Dockerfile и docker-compose ...
