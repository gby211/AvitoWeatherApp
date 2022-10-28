# AvitoWeatherApp

### Требования к приложению:
1. Отображение прогноза погоды за текущий день
2. Отображение прогноза погоды за неделю
3. Возможность увидеть прогноз погоды в текущем городе
4. Возможность выбрать любой другой город, и узнать прогноз погоды в нем

#### API
В качестве API, для получения прогноза, можно использовать любой сервис; допускается использование бесплатных/ограниченных/пробных версий.\
Пример: [OpenWeatherMap API](https://openweathermap.org/api)

#### Дизайн
Решения о дизайне остаются полностью на ваше усмотрение.

#### Использованные технологии:
- `Retrofit2`
- `Clean Architecture`
- `MVVM`
- `Hilt`
- `kotlin.coroutines`
- `material 3`
- `navigation`
- `recyclerview/DiffUtil`
- `LiveData`
- `DataBinding` но использовалась не вся мощь :) 
- `Glide`
- `SharedPreferences`

#### Фишки:
- При выборе цветов для приложения воспользовался билдером Material 3 и использовал основные цвета логотипа avito
- Все размеры вынесены в dimen файл
- Все строки вынесены в strings файл
- AutoCompleteTextView для автозаполения города по названию города
- 2 RecyclerView расположенных горизонтально
- Настроено корректное отображение в тёмной теме и светлой
- Автопределение местоположения

#### Технологии которые не успел реализовать:
- `Room` для сохранения в кеш
- `junit` для тестов
- `espresso` для тестов

#### Реализованный функционал
- Выгрузка данных с api
- Обработка полученных данных
- Отображение температуры на данныхй момент
- Отображение информации о погоде на день : `температура`, `время`, `Скорость ветра`, `давление`, `влажность`, `картинка погоды`
- Отображение информации о погоде на неделю : `температура`, `дата`, `максимальная температура`, `минимальная температура`, `картинка погоды`
- Перемещение на вкладку с настройками
- Автозаполнение названия города с помощью "AutoCompleteTextView"
- Сохранение города в SharedPreferences
- Определение местоположения

#### Не реализованный функционал / что можно улучшить
- Показ progress bar для отображения того что данные загружаются, и скрытие эллементов для красоты
- Проработать интерфейс лучше для корректного отображения на всех экранах
- Внесети Header в recyclerView для отображения текста "НА ДЕНЬ"
- Доработать UseCase
- Воспользоваться DataBindingAdapter и использовать Databinding по своему назначению (использовать его в xml)
- Улучшить UI
- Улучшить UX

#### Презентация приложения

<img width="250" src="https://github.com/gby211/AvitoWeatherApp/blob/master/Screens/d1.jpg"> <img width="250" src="https://github.com/gby211/AvitoWeatherApp/blob/master/Screens/l1.jpg">
\
<img width="250" src="https://github.com/gby211/AvitoWeatherApp/blob/master/Screens/d2.jpg"> <img width="250" src="https://github.com/gby211/AvitoWeatherApp/blob/master/Screens/l2.jpg">
\
<img width="250" src="https://github.com/gby211/AvitoWeatherApp/blob/master/Screens/d3.jpg"> <img width="250" src="https://github.com/gby211/AvitoWeatherApp/blob/master/Screens/l3.jpg">
\
<img width="250" src="https://github.com/gby211/AvitoWeatherApp/blob/master/Screens/d4.jpg"> <img width="250" src="https://github.com/gby211/AvitoWeatherApp/blob/master/Screens/l4.jpg">









