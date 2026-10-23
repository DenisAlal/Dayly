<a id="top"></a>
# 🗓️ Dayly — план разработки на 4 недели

> **Формат:** 3–4 часа в день, 6–7 дней в неделю
> **Итого:** ~84–112 часов практики за 4 недели
> **Распределение времени:** 20 % — изучение Kotlin и Android · 70 % — разработка · 10 % — тестирование и разбор ошибок

Этого достаточно, чтобы создать приличный MVP Dayly, изучить основные возможности Kotlin и попробовать настоящую многомодульную архитектуру. Не будем превращать проект в бесконечный учебник.

> ☁️ **Backend — только Firebase.** Аутентификация через Firebase Authentication, данные пользователя — в Cloud Firestore, push — через FCM. Собственного сервера нет.
>
> 🌐 **Retrofit + OkHttp тоже в деле** — но строго для внешнего публичного API. Данные пользователя через Retrofit не ходят.
>
> 🌤️ **Метрика самочувствия.** Из погоды считается индекс здоровья: головная боль, обезвоживание, суставы, дыхание, комфорт сна, УФ. Погода — через Retrofit (Open-Meteo), расчёт — чистая Kotlin-логика.

> 📌 Ниже по дням — не пошаговые инструкции, а **подсказки**: что здесь по-настоящему важно, где обычно застревают и как не тратить время впустую.

> ⬆️ [К содержанию](#top)

---

## 📑 Содержание

**План по неделям**

- [Обзор четырёх недель](#overview)
- [Неделя 1. Kotlin и Compose](#week-1)
- [Неделя 2. Room, архитектура и Gradle](#week-2)
- [Неделя 3. Firebase, Firestore и Retrofit](#week-3)
- [Неделя 4. Фоновые уведомления и завершение MVP](#week-4)
- [Как организовать каждый учебный день](#day-format)
- [Что должно получиться через 4 недели](#results)

**Справочник**

- [Архитектурные решения (ADR)](#adr)
- [1. Технологический стек](#stack)
- [2. Архитектура проекта](#architecture)
- [3. Как разобраться с фоновыми уведомлениями](#background)
- [4. Что ты изучишь в Kotlin и Compose](#kotlin-compose)
- [5. Firebase, внешний REST и самочувствие](#firebase)
- [6. Как подключать зависимости без `libs.versions.toml`](#dependencies)
- [7. Дизайн-система и переиспользуемый UI](#design-system)
- [8. Навигация: типизированные маршруты](#navigation)

**Дни**

- [1](#day-1) · [2](#day-2) · [3](#day-3) · [4](#day-4) · [5](#day-5) · [6](#day-6) · [7](#day-7)
- [8](#day-8) · [9](#day-9) · [10](#day-10) · [11](#day-11) · [12](#day-12) · [13](#day-13) · [14](#day-14)
- [15](#day-15) · [16](#day-16) · [17](#day-17) · [18](#day-18) · [19](#day-19) · [20](#day-20) · [21](#day-21)
- [22](#day-22) · [23](#day-23) · [24](#day-24) · [25](#day-25) · [26](#day-26) · [27](#day-27) · [28](#day-28)

**ADR**

- [1](#adr-1) · [2](#adr-2) · [3](#adr-3) · [4](#adr-4) · [5](#adr-5) · [6](#adr-6) · [7](#adr-7) · [8](#adr-8) · [9](#adr-9) · [10](#adr-10) · [11](#adr-11) · [12](#adr-12)

---

<a id="overview"></a>
## Обзор четырёх недель

| # | Неделя | Тема | Нагрузка | Результат |
| :-: | --- | --- | :-: | --- |
| **01** | 1 | **Kotlin + Jetpack Compose** | 21–28 ч | Освоить синтаксис Kotlin и собрать UI приложения |
| **02** | 2 | **Room + Clean Architecture + Gradle** | 21–28 ч | Реализовать локальное хранилище, слои приложения и первые модули |
| **03** | 3 | **Firebase + Firestore + Retrofit + DI** | 21–28 ч | Подключить облако, внешний REST, аутентификацию и метрику самочувствия |
| **04** | 4 | **Уведомления + тесты + полировка** | 21–28 ч | Реализовать фоновые напоминания и подготовить проект к демонстрации |

---

<a id="week-1"></a>
## Неделя 1. Kotlin и Compose

> 🎯 **Цель:** приложение уже выглядит как настоящее, а не как набор учебных примеров.

| День | Тема |
| :-: | --- |
| [1](#day-1) | Настройка окружения и базовый Kotlin |
| [2](#day-2) | Gradle, модули и первые модели |
| [3](#day-3) | Коллекции, generics, расширения |
| [4](#day-4) | Корутины и Flow |
| [5](#day-5) | Основы Compose и дизайн-система |
| [6](#day-6) | Состояние и ViewModel |
| [7](#day-7) | Навигация: типизированные маршруты |

<a id="day-1"></a>
### День 1 — Настройка окружения и базовый Kotlin

- Не застревай на настройке: стандартный Compose-проект уже рабочий, а день уйдёт на синтаксис. Цикл «написал → запустил» учит быстрее чтения.
- `minSdk = 26` (Android 8) закрывает почти всё нужное для уведомлений; `compileSdk` — только для сборки.
- Ошибки Gradle чаще про версии плагинов и интернет, а не про код: читай **первую** строку.

**Ориентир:** приложение запускается на эмуляторе, проект под Git, есть первый коммит.

**📚 Куда смотреть:** [METANIT: Kotlin](https://metanit.com/kotlin/) · [kotlinlang.ru](https://kotlinlang.ru/)

<a id="day-2"></a>
### День 2 — Gradle, модули и первые модели

- Модуль появляется только после `include(":core:ui")` в `settings.gradle.kts`: папка и `build.gradle.kts` без строки в settings для Gradle не существуют.
- Двоеточие — это вложенность папок: `domain/api` → `:domain:api`. Промежуточные папки модулями не становятся, build-файл в них не нужен.
- Держи каркас минимальным: `:core:ui` (тема и токены) и `:domain:api` (чистый Kotlin). Полный скелет сразу — только мешает.
- Модели пиши сразу в `domain`, а не в `app`: `Medication`, `Dose`, `MedicationSchedule`. Тогда их не придётся переносить на второй неделе.
- `data class` бесплатно даёт `equals`/`hashCode`/`copy` — на этом держится перерисовка Compose и `distinctUntilChanged` в `Flow`. Модели — неизменяемые.
- `MedicationUiState` пока не нужен: это состояние экрана, а не доменная модель. Оно появится на дне 6 вместе с ViewModel.

**Ориентир:** модули `:core:ui` и `:domain:api` зарегистрированы в `settings.gradle.kts` и собираются; тема переехала в `:core:ui`; в `:domain:api` лежат `Medication`, `Dose`, `DoseUnit`, `MedicationSchedule`, `MedicationTime`, а `:app` зависит от обоих модулей.

**📚 Куда смотреть:** [Как подключить новый модуль](#architecture) · [Gradle: java-library и api](https://docs.gradle.org/current/userguide/java_library_plugin.html) · [METANIT: Kotlin](https://metanit.com/kotlin/)

<a id="day-3"></a>
### День 3 — Коллекции, generics, расширения

- `map`/`filter`/`groupBy`/`associateBy`/`sortedBy` — это 80 % работы с данными; после них циклы почти не нужны.
- Scope-функции: достаточно `let`, `apply`, `also`; остальные — по мере надобности.
- Extension-функции — поведение без наследования: вынеси туда форматирование дозы и времени.

**Ориентир:** список лекарств группируется по дню и сортируется по времени, а формат дозы и времени живёт в extension-функциях.

**📚 Куда смотреть:** [METANIT: коллекции](https://metanit.com/kotlin/) · [kotlinlang.ru](https://kotlinlang.ru/)

<a id="day-4"></a>
### День 4 — Корутины и Flow

- `suspend` — это «можно приостановить», а не «фон». Scope — `viewModelScope`, не `GlobalScope`.
- Для состояния UI — `StateFlow`; `SharedFlow`/`Channel` — для одноразовых событий (навигация, snackbar).
- `Flow` ленивый: без `collect` не выполняется ничего.

**Ориентир:** in-memory репозиторий отдаёт `StateFlow<List<Medication>>`, и добавление лекарства обновляет список без ручного перезапуска.

**📚 Куда смотреть:** [kotlinlang.ru: руководство по корутинам](https://kotlinlang.ru/docs/coroutines-guide.html)

<a id="day-5"></a>
### День 5 — Основы Compose и дизайн-система

- Compose-экран — функция от состояния: порядок `Modifier` влияет на результат, а в `LazyColumn.item` нужен `key`.
- Дополняй `:core:ui` компонентами: токены и `DaylyTheme` уже там с дня 2, теперь очередь `DaylyCard` и `DaylyButton`. Material не даёт отступов — определи их в токенах.
- В компоненте первым идёт `modifier` и ставится на корень: размещением владеет вызывающий. Подробности — в [разделе 7](#design-system).

**Ориентир:** экран списка на моковых данных; в `:core:ui` появились первые переиспользуемые компоненты со своими `@Preview`.

**📚 Куда смотреть:** [METANIT: Jetpack Compose](https://metanit.com/kotlin/jetpack/) · [Compose docs](https://developer.android.com/develop/ui/compose/documentation?hl=ru)

<a id="day-6"></a>
### День 6 — Состояние и ViewModel

- `remember` живёт в композиции, `rememberSaveable` переживает смерть процесса — для полей ввода нужен второй.
- Состояние экрана — в ViewModel; в Compose предпочитай `collectAsStateWithLifecycle`.
- Одно `UiState` на экран вместо набора флагов.

**Ориентир:** `MedicationViewModel` отдаёт `StateFlow<MedicationUiState>`, и экран показывает загрузку, «пусто» и список; состояние переживает поворот.

**📚 Куда смотреть:** [METANIT: ViewModel](https://metanit.com/kotlin/jetpack/) · [Architecture](https://developer.android.com/topic/architecture?hl=ru)

<a id="day-7"></a>
### День 7 — Навигация: типизированные маршруты

- Маршрут — это `@Serializable` тип, а не строка: `composable<Home>` и `navigate(MedicationDetails(id))`. Опечатка становится ошибкой компиляции, а рефакторинг безопасен.
- Через маршрут передавай только id, а не объект: экран сам подтянет данные из репозитория, и back stack не будет таскать состояние.
- Простые переходы — колбэками; навигация «после действия» — одноразовым событием из ViewModel через `Channel`, которое выполняет `LaunchedEffect`.
- Граф живёт в `:app`: фичи не знают друг о друге, а переходы между ними связываются лямбдами. Подробности — в [разделе 8](#navigation).

**Ориентир:** `NavHost` на маршрутах `Home`/`Medications`/`AddMedication`/`SleepSettings`; экран деталей принимает `id` как аргумент, а после добавления записи приложение возвращается к списку и он обновляется.

**📚 Куда смотреть:** [Navigation](https://developer.android.com/guide/navigation?hl=ru) · [METANIT: навигация](https://metanit.com/kotlin/jetpack/)

> 💡 Не пытайся идеально изучить весь Kotlin. На каждый новый концепт делай небольшой пример прямо в проекте.

**Итог недели 1:** приложение с четырьмя экранами (главный, список лекарств, форма добавления, настройки сна), данные живут в памяти, навигация работает; с первого дня подняты модули `:core:ui` и `:domain:api`, тема и модели живут там, а не в `app`.

---

<a id="week-2"></a>
## Неделя 2. Room, архитектура и Gradle

> 🎯 **Цель:** полноценная работа с локальными данными и правильные границы модулей.

| День | Тема |
| :-: | --- |
| [8](#day-8) | Room: Entity, DAO, Database |
| [9](#day-9) | CRUD и Flow из Room |
| [10](#day-10) | MVVM, Repository, Use Case |
| [11](#day-11) | Модуль реализации `:data:impl` |
| [12](#day-12) | Gradle Kotlin DSL: `api` vs `implementation` |
| [13](#day-13) | Hilt и DI |
| [14](#day-14) | Миграции и тесты DAO |

<a id="day-8"></a>
### День 8 — Room: Entity, DAO, Database

- Room — «аннотации + генератор кода»: важно, что DAO порождает `*_Impl`, а не сам синтаксис.
- `Flow` в DAO — то, ради чего Room берут: база сама сообщает об изменениях.
- KSP-плагин и `room-compiler` — в том модуле, где объявлены `@Entity`/`@Database`; схему экспортируй сразу.

**Ориентир:** есть `MedicationEntity` и `MedicationDao`, после сборки — `MedicationDao_Impl`, схема в `app/schemas/`.

**📚 Куда смотреть:** [Room](https://developer.android.com/training/data-storage/room?hl=ru) · [METANIT: Room](https://metanit.com/kotlin/jetpack/)

<a id="day-9"></a>
### День 9 — CRUD и Flow из Room

- В DAO операции с изменениями — `suspend`, чтение — `Flow`.
- Ошибки базы обрабатывай рядом с репозиторием, а не в UI; entity не возвращай в UI.

**Ориентир:** добавленное лекарство остаётся после перезапуска, а список на экране — `Flow` из DAO.

**📚 Куда смотреть:** [Room: DAO и запросы](https://developer.android.com/training/data-storage/room?hl=ru)

<a id="day-10"></a>
### День 10 — MVVM, Repository, Use Case

- Repository — интерфейс в domain, реализация в data: это и есть точка замены хранилища.
- Контракт `MedicationRepository` клади сразу в `:domain:api` — модуль у тебя есть с недели 1. Реализация пока может жить рядом с Room в `app`.
- UseCase — когда логика переиспользуется или тестируется отдельно; зависимости — только через конструктор.
- Мапперы entity ↔ domain не дают базе протечь в UI.

**Ориентир:** `MedicationRepository` — интерфейс в `:domain:api`, реализация — рядом с Room; ViewModel не знает про `MedicationDao`.

**📚 Куда смотреть:** [Architecture](https://developer.android.com/topic/architecture?hl=ru) · [Start Android: Architecture Components](https://startandroid.ru/)

<a id="day-11"></a>
### День 11 — Модуль реализации `:data:impl`

- `api`-модуль уже создан на неделе 1, поэтому здесь появляется второй слой — модуль реализации.
- Зарегистрируй его в `settings.gradle.kts` и подключи в `app`; реализация зависит от `:domain:api` и `:core:database`.
- Выноси постепенно, проверяя сборку после каждого шага: большой перенос в один заход почти всегда ломается.

**Ориентир:** `:data:impl` в `settings.gradle.kts`; `MedicationRepositoryImpl` живёт там, `feature:medications` по-прежнему зависит только от `:domain:api`.

**📚 Куда смотреть:** [Модуляризация](https://developer.android.com/topic/architecture?hl=ru) · [Start Android](https://startandroid.ru/)

<a id="day-12"></a>
### День 12 — Gradle Kotlin DSL: `api` vs `implementation`

- `implementation` по умолчанию; `api` — только если тип из зависимости виден в публичных сигнатурах.
- `implementation(project(":x"))` даёт классы `:x`, но **не** его зависимости — это главная путаница.
- `./gradlew :data:impl:dependencies` показывает граф; версии Kotlin/Compose/AGP — одни на проект.

Подробный разбор с примерами — в [ADR-3](#adr-3).

**Ориентир:** `api(coroutines)` в domain и `implementation(retrofit)` в `:core:network`; в дереве зависимостей Retrofit не виден у UI.

**📚 Куда смотреть:** [Gradle: java-library и api](https://docs.gradle.org/current/userguide/java_library_plugin.html) · [METANIT: Gradle](https://metanit.com/kotlin/jetpack/)

<a id="day-13"></a>
### День 13 — Hilt и DI

- `@Binds` — интерфейс → реализация; `@Provides` — внешние типы (Retrofit, Firestore).
- Классу с `@Inject constructor` `@Provides` не нужен; связывание — в модуле реализации, а не в `app`.

**Ориентир:** `MedicationRepositoryImpl` приходит в ViewModel через `@Inject`, в коде нет ни `new`, ни `getInstance`.

**📚 Куда смотреть:** [Hilt](https://developer.android.com/training/dependency-injection/hilt-android?hl=ru)

<a id="day-14"></a>
### День 14 — Миграции и тесты DAO

- Смена схемы без миграции — падение у пользователя; одной настоящей `Migration` для старта достаточно.
- Тесты DAO на in-memory базе — самая быстрая точка входа в тесты.

**Ориентир:** БД версии 2 с реальной `Migration`, тест DAO зелёный, миграция покрыта `MigrationTestHelper`.

**📚 Куда смотреть:** [Room](https://developer.android.com/training/data-storage/room?hl=ru) · [Start Android: тестирование](https://startandroid.ru/)

> 💡 Не создавай все возможные модули сразу. `:core:ui` и `:domain:api` уже есть с недели 1 — на второй неделе добавь к ним `:core:database` и `:data:impl`, то есть один полноценный вертикальный срез.

> 💡 **Роль Room после подключения Firebase.** Firestore умеет офлайн-кэш из коробки, но Room остаётся удобнее для двух задач: быстрых запросов под расписание напоминаний и явной модели данных в domain-слое. Поэтому Room проектируем как локальный кэш впереди Firestore, а не как единственный источник правды.

**Итог недели 2:** лекарства сохраняются в Room между запусками; добавились `:core:database` и `:data:impl`, контракт остаётся в `:domain:api`; Hilt связывает репозиторий, миграция и тест DAO зелёные.

---

<a id="week-3"></a>
## Неделя 3. Firebase, Firestore и Retrofit

> 🎯 **Цель:** подключить облако Firebase (аутентификация + хранение) и внешний REST через Retrofit, связав всё с локальным кэшем.

| День | Тема |
| :-: | --- |
| [15](#day-15) | Firebase setup и Authentication |
| [16](#day-16) | Firestore CRUD и мапперы |
| [17](#day-17) | Security rules и структура данных |
| [18](#day-18) | Offline persistence и Room |
| [19](#day-19) | Retrofit и OkHttp |
| [20](#day-20) | Погода и качество воздуха |
| [21](#day-21) | Индекс самочувствия |

<a id="day-15"></a>
### День 15 — Firebase setup и Authentication

- `google-services.json` — только в модуле `app`; версии Firebase — через BoM.
- `FirebaseAuth.currentUser` достаточно для навигации «вход → приложение»; состояние — поток, а не одна проверка.

**Ориентир:** вход по email работает, сессия переживает перезапуск, навигацией управляет `AuthRepository.currentUser`.

**📚 Куда смотреть:** [Firebase Authentication](https://firebase.google.com/docs/auth?hl=ru)

<a id="day-16"></a>
### День 16 — Firestore CRUD и мапперы

- Документ Firestore ≈ `Map`: маппинг прост, но версионирование схемы — на тебе.
- `snapshots()` — реактивно, `get()` — одноразово; `@DocumentId` заполняет SDK, время — через `FieldValue.serverTimestamp()`.

**Ориентир:** в консоли Firestore видно `users/{uid}/medications/{id}`, чтение идёт через `snapshots()`, id приходит в `@DocumentId`.

**📚 Куда смотреть:** [Cloud Firestore](https://firebase.google.com/docs/firestore?hl=ru)

<a id="day-17"></a>
### День 17 — Security rules и структура данных

- Security rules — единственная защита, раз backend-кода нет; проверяй их в эмуляторе правил.
- Структура под `users/{uid}` делает правила короткими; составные запросы требуют индексов.

**Ориентир:** со второго аккаунта данные первого недоступны, чужой запрос даёт `PERMISSION_DENIED`.

**📚 Куда смотреть:** [Security rules](https://firebase.google.com/docs/rules?hl=ru)

<a id="day-18"></a>
### День 18 — Offline persistence и Room

- Один источник правды (Firestore) и один путь чтения (Room): два источника — генератор багов.
- Пиши локально, затем в облако; продумай состояние «нет ни в облаке, ни в кэше».

**Ориентир:** в авиарежиме добавленное лекарство видно сразу и уходит в Firestore после включения сети.

**📚 Куда смотреть:** [Firestore: офлайн и кэш](https://firebase.google.com/docs/firestore?hl=ru) · [Emulator Suite](https://firebase.google.com/docs/emulator-suite?hl=ru)

<a id="day-19"></a>
### День 19 — Retrofit и OkHttp

- Логирующий интерсептор — включай сразу; таймауты по умолчанию велики, задай их явно.
- DTO ≠ domain-модель; используй `suspend`-функции, а не `Call.enqueue`.

**Ориентир:** `GET v1/forecast` возвращает `WeatherDto`, в Logcat видны строка запроса и ответ, таймауты заданы явно.

**📚 Куда смотреть:** [Retrofit](https://square.github.io/retrofit/) · [OkHttp](https://square.github.io/okhttp/)

<a id="day-20"></a>
### День 20 — Погода и качество воздуха

- Два сервиса (прогноз + качество воздуха) — тренировка агрегации нескольких источников.
- Кэшируй последний успешный ответ и различай «нет данных» и «запрос упал».

**Ориентир:** на главном экране карточка с температурой, влажностью и PM2.5; без сети — последний кэш, а не пустой экран.

**📚 Куда смотреть:** [Open-Meteo: документация](https://open-meteo.com/en/docs)

<a id="day-21"></a>
### День 21 — Индекс самочувствия

- Расчёт — чистая функция без Android: иначе её нельзя тестировать быстро.
- Тестируй границы (6 гПа, 28 °C, `uv_index = 6`); формулировки подсказок держи в одном месте.

**Ориентир:** `HealthIndexCalculator` возвращает score и список факторов, есть тесты на границы, обновление идёт через WorkManager.

**📚 Куда смотреть:** [kotlinlang.ru: корутины и Flow](https://kotlinlang.ru/docs/coroutines-guide.html)

> 💡 **Две разные сети, не смешивай.** Данные пользователя идут через Firestore SDK, Retrofit — только за внешней погодой. Поэтому DTO, сериализация и интерсепторы нужны лишь для внешнего API.
>
> 💡 **Прямые запросы vs имитация.** В продакшене `data:impl` вызывает Firestore и Retrofit напрямую. «Имитация» нужна только в тестах: ViewModel мокается через `MockK`, Firebase проверяется на **Emulator Suite**, внешний REST — на **MockWebServer**, а расчёт индекса тестируется без сети вообще.

**Итог недели 3:** пользователь входит в аккаунт, лекарства лежат в Firestore и читаются через Room-кэш, а на главном экране показывается индекс самочувствия из актуальной погоды.

> ⚠️ Не трать много времени на сложную синхронизацию конфликтующих изменений. Для MVP достаточно: Firestore — источник правды, Room — кэш, а UI всегда читает из Room.

---

<a id="week-4"></a>
## Неделя 4. Фоновые уведомления и завершение MVP

> 🎯 **Цель:** реализовать главную особенность приложения — напоминания, которые работают при закрытом UI.

| День | Тема |
| :-: | --- |
| [22](#day-22) | Каналы и базовые уведомления |
| [23](#day-23) | AlarmManager и точные будильники |
| [24](#day-24) | Расписания и действия из уведомления |
| [25](#day-25) | Перезагрузка и системные ограничения |
| [26](#day-26) | Firebase Cloud Messaging |
| [27](#day-27) | Тесты |
| [28](#day-28) | Полировка и README |

<a id="day-22"></a>
### День 22 — Каналы и базовые уведомления

- Канал обязателен с Android 8, иначе уведомление просто не покажется; на 13+ нужен `POST_NOTIFICATIONS` в рантайме.
- Начни с уведомления по кнопке: убедись, что канал и разрешение работают, до всяких расписаний.

**Ориентир:** канал `reminders` создан, уведомление показывается, а на Android 13+ приложение корректно запрашивает разрешение.

**📚 Куда смотреть:** [Notifications](https://developer.android.com/develop/ui/views/notifications?hl=ru)

<a id="day-23"></a>
### День 23 — AlarmManager и точные будильники

- `AlarmManager` — единственный способ разбудить приложение в точное время; `WorkManager` не про точность до минуты.
- Флаги `PendingIntent` важны: неверный — уведомление не сработает или перезапишет чужое.

**Ориентир:** будильник на +1 минуту срабатывает при закрытом приложении точно в назначенное время.

**📚 Куда смотреть:** [Schedule alarms](https://developer.android.com/develop/background-work/services/alarms/schedule?hl=ru)

<a id="day-24"></a>
### День 24 — Расписания и действия из уведомления

- При редактировании отменяй старый будильник; для повторов надёжнее перепланировать следующий, чем верить `setRepeating`.
- Действие «Принял» — через `BroadcastReceiver`; привязка «расписание ↔ будильник» — по стабильному id.

**Ориентир:** создание, изменение и отмена дают ровно один будильник, а кнопка «Принял» пишет отметку в базу.

**📚 Куда смотреть:** [Start Android: уведомления](https://startandroid.ru/)

<a id="day-25"></a>
### День 25 — Перезагрузка и системные ограничения

- После перезагрузки будильники теряются — восстанавливай их на `BOOT_COMPLETED`.
- Doze и энергосбережение режут фон; точное время гарантирует `setExactAndAllowWhileIdle`; проверяй на реальном устройстве.

**Ориентир:** после перезагрузки устройства запланированное напоминание срабатывает снова.

**📚 Куда смотреть:** [WorkManager](https://developer.android.com/topic/libraries/architecture/workmanager?hl=ru) · [Schedule alarms](https://developer.android.com/develop/background-work/services/alarms/schedule?hl=ru)

<a id="day-26"></a>
### День 26 — Firebase Cloud Messaging

- FCM — про сообщения от сервера, не про будильники: не подменяй им `AlarmManager`.
- Различай notification- и data-сообщения: в фоне ведут себя по-разному; работай через тему, а не по токену.

**Ориентир:** тестовое сообщение из консоли Firebase приходит и отображается, токен виден в логах.

**📚 Куда смотреть:** [Cloud Messaging](https://firebase.google.com/docs/cloud-messaging?hl=ru)

<a id="day-27"></a>
### День 27 — Тесты

- Пирамида: много JVM-тестов на логику, меньше на data, совсем немного UI.
- `MockWebServer` проверяет таймауты и ошибки без реальной сети; тестируй результат, а не число вызовов.

**Ориентир:** `./gradlew test` зелёный: есть тесты ViewModel (MockK), сервиса (MockWebServer) и расчёта индекса, плюс один Compose UI test.

**📚 Куда смотреть:** [Test your app](https://developer.android.com/training/testing?hl=ru)

<a id="day-28"></a>
### День 28 — Полировка и README

- Прогоняй сценарий как пользователь: без сети, без разрешений, с пустой базой.
- README со схемой модулей и разделом «что вне MVP» экономит время и производит впечатление.

**Ориентир:** сценарий пройден на устройстве целиком, README со схемой модулей и скриншотами, зафиксировано, что осталось вне MVP.

**📚 Куда смотреть:** [Habr: хаб Android](https://habr.com/ru/hubs/android/)

> ⚠️ Помни, что `AlarmManager` и FCM решают разные задачи. Для локального расписания нужен первый, для сообщений от сервера — второй. `WorkManager` предназначен для фоновых задач, которым не нужна точность до минуты.
>
> 🔗 FCM и Firestore — части одного проекта Firebase, поэтому `google-services.json` и инициализация общие для обоих.

**Итог недели 4:** приложение показывает локальное напоминание при закрытом UI, восстанавливает расписания после перезагрузки, принимает тестовый push и проходит прогон по сценарию без падений.

---

<a id="day-format"></a>
## Как организовать каждый учебный день

Режим на 3,5 часа:

| Время | Блок | Что делать |
| :-: | --- | --- |
| **30 мин** | 📚 Изучение | Одна новая тема Kotlin или Android |
| **2 ч** | 💻 Разработка | Реализация функциональности в Dayly |
| **30 мин** | 🧪 Самостоятельная практика | Изменить решение, проверить понимание, разобрать ошибки |
| **30 мин** | ✅ Тесты и заметки | Зафиксировать выводы и проверить работоспособность |

> 💡 Если какая-то тема окажется сложной, лучше потратить на неё дополнительный день, чем механически переходить дальше. Особенно это касается корутин, Flow, границ модулей и Android-уведомлений.

---

<a id="results"></a>
## Что должно получиться через 4 недели

- [ ] Рабочее Android-приложение на Kotlin и Jetpack Compose.
- [ ] Аутентификация через Firebase Authentication (email и/или Google).
- [ ] Firestore и Room в связке через Repository: облако как источник правды, Room как кэш.
- [ ] Внешний REST через Retrofit + OkHttp с кэшем и тестами на MockWebServer.
- [ ] Метрика самочувствия по погоде — с unit-тестами на расчёт.
- [ ] Несколько Gradle-модулей с разделением `api` и `implementation`.
- [ ] Дизайн-система в `:core:ui`: токены, тема и переиспользуемые компоненты.
- [ ] Навигация на типизированных маршрутах, граф собран в `:app`.
- [ ] Внедрение зависимостей через Hilt.
- [ ] Локальные уведомления и базовая интеграция FCM.
- [ ] Первые unit-тесты и UI-тесты (MockK, MockWebServer, Emulator Suite).
- [ ] Понимание основных паттернов и причин их применения.

> 🏆 Я бы считал успехом не идеальную архитектуру, а способность **объяснить каждое решение** и самостоятельно добавить новую функцию, не копируя готовый код.

> ⚠️ **Важный приоритет:** если к концу третьей недели не будет закончена работа с Firestore и Retrofit, лучше перенести FCM на будущее. Работающие локальные напоминания гораздо важнее для первой версии Dayly, чем интеграция push ради самой интеграции.

---

<a id="adr"></a>
## Архитектурные решения

Здесь собраны решения, которые стоит принять **до** того, как проект разрастётся. Каждое можно пересмотреть, но лучше делать это осознанно.

<a id="adr-1"></a>
### ADR-1. Один доменный слой и один слой реализации

- На MVP — два общих слоя: `:domain:api` (модели, контракты, use cases, чистая логика) и `:data:impl` (реализации: Room, Firestore, Retrofit). Это не «модуль на каждую фичу», а один срез на всё приложение.
- `:feature:<name>` — по модулю на экранную область: `home`, `medications`, `auth`.
- `:core:*` — общие технические модули без бизнес-логики: `ui`, `database`, `firebase`, `network`.
- Срез на фичу (`:domain:wellness:api` + `:data:wellness:impl`) появляется, когда у неё есть своя граница: отдельный источник данных, отдельная команда или тяжёлые зависимости.
- Не выноси каждый класс в модуль: модуль нужен там, где есть реальная граница.

**Почему:** 10 модулей читаются и собираются быстрее, чем 23, а направление зависимостей остаётся тем же, что в «большой» Clean Architecture. Переход к срезам по фичам потом не ломает UI — контракты уже отделены от реализаций.

<a id="adr-2"></a>
### ADR-2. Направление зависимостей

Стрелка `A → B` означает «A зависит от B».

```text
:app → :feature:*                  (собирает граф)
:feature:* → :domain:api           (UI знает только контракты)
:data:impl → :domain:api + :core:* (реализация знает контракт и инфраструктуру)
:app → :data:impl                  (единственное место, где impl попадает в сборку)
```

**Запрещено:**
- `:domain:api` не зависит от `:data:impl`, Android, Room, Firebase и Retrofit.
- `:feature:*` не зависит от `:data:impl` напрямую.
- `:core:*` не зависит от `:feature:*`.
- Никто не зависит от `:app`.

**Почему:** пока UI не знает про Room и Firestore, хранилище можно заменить, не переписывая экраны.

<a id="adr-3"></a>
### ADR-3. `api` vs `implementation` — главное Gradle-решение

Это ключевая настройка многомодульного проекта. Правила простые:

1. **По умолчанию — `implementation`.** Зависимость видна только внутри модуля.
2. **`api` — только если тип из зависимости попадает в публичную сигнатуру твоего модуля** (публичную функцию, свойство, тип-параметр, базовый класс).
3. `api` **протекает транзитивно** и замедляет сборку, поэтому им не пользуются «на всякий случай».
4. `implementation(project(":x"))` **даёт доступ к собственным классам модуля** `:x`, но не к его зависимостям. Это главная путаница: `api`/`implementation` управляют только транзитивными зависимостями, а не самим модулем.

**Тест на `api`:** если удалить зависимость и публичный код *потребителя* перестанет компилироваться — нужен `api`. Если ломается только внутренний код модуля — хватит `implementation`.

**Типичные случаи в Dayly:**

| Модуль | Что в публичной сигнатуре | `api(...)` | `implementation(...)` |
| --- | --- | --- | --- |
| `:domain:api` | `Flow<List<Medication>>`, `Flow<HealthIndex>` в контрактах | `kotlinx-coroutines-core` | — |
| `:data:impl` | ничего (скрыто за интерфейсами) | — | `:domain:api`, `:core:database`, `:core:firebase`, `:core:network`, `firebase-firestore`, `room-*`, `hilt-android` |
| `:core:network` | свои `WeatherApi` и DTO | — | `retrofit`, `okhttp`, `logging-interceptor`, конвертер, `kotlinx-serialization-json` |
| `:core:firebase` | — | — | `firebase-firestore`, `firebase-auth` |
| `:feature:home` | Composable без domain-типов | — | `:domain:api`, Compose, Navigation |
| `:app` | — | — | все `:feature:*`, `:data:impl`, `:domain:api` |

**Пример: доменный модуль.** Контракт возвращает `Flow`, значит `Flow` должен быть доступен потребителю — используем `api`:

```kotlin
// :domain:api/build.gradle.kts
plugins {
    id("org.jetbrains.kotlin.jvm")
    id("java-library")   // именно java-library добавляет конфигурацию api
}

dependencies {
    // Flow виден в публичных сигнатурах → api
    api("org.jetbrains.kotlinx:kotlinx-coroutines-core:COROUTINES_VERSION")
    // Ничего лишнего: domain не знает про Android, Room, Firebase и Retrofit.
}
```

**Пример: модуль реализации.** Наружу торчит только интерфейс, реализация скрыта — везде `implementation`:

```kotlin
// :data:impl/build.gradle.kts
plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
}

android { namespace = "com.denalal.dayly.data.impl" }

dependencies {
    implementation(project(":domain:api"))
    implementation(project(":core:database"))
    implementation(project(":core:firebase"))

    implementation(platform("com.google.firebase:firebase-bom:FIREBASE_BOM_VERSION"))
    implementation("com.google.firebase:firebase-firestore")

    implementation("androidx.room:room-runtime:ROOM_VERSION")
    ksp("androidx.room:room-compiler:ROOM_VERSION")

    implementation("com.google.dagger:hilt-android:HILT_VERSION")
    ksp("com.google.dagger:hilt-compiler:HILT_VERSION")
}
```

**Пример: транспортный модуль.** Retrofit и OkHttp спрятаны внутри — наружу отдаём свои `WeatherApi` и DTO:

```kotlin
// :core:network/build.gradle.kts
plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.serialization")
}

android { namespace = "com.denalal.dayly.core.network" }

dependencies {
    // Retrofit не появляется в наших публичных сигнатурах → implementation
    implementation("com.squareup.retrofit2:retrofit:RETROFIT_VERSION")
    implementation("com.squareup.okhttp3:okhttp:OKHTTP_VERSION")
    implementation("com.squareup.okhttp3:logging-interceptor:OKHTTP_VERSION")
    implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:RETROFIT_SERIALIZATION_VERSION")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:KOTLINX_SERIALIZATION_VERSION")
}
```

> ⚠️ **Частая ошибка.** Сделать `api(project(":domain:x:api"))` в модуле `impl`, чтобы `:app` «случайно» увидел domain-модели. Правильнее добавить `:domain:x:api` в зависимости `:app` напрямую: так граф честный, а каждый модуль объявляет то, чем пользуется.

> 💡 **Проверка графа.** Запусти `./gradlew :app:dependencies` и `./gradlew projects` — сразу видно, кто что тянет и где нет лишних транзитивных зависимостей.

<a id="adr-4"></a>
### ADR-4. Доменные модули — чистый Kotlin без Android

- `:domain:api` собираются плагином `org.jetbrains.kotlin.jvm`, а не Android-плагином.
- Никаких `Context`, `R`, `androidx` внутри domain.

**Почему:** тесты domain идут на JVM за миллисекунды, а архитектура остаётся честной. Нужно ли это на старте — вопрос, но переезжать потом больнее. Если хочется проще, можно сделать все модули `com.android.library`; `api` доступен и там.

<a id="adr-5"></a>
### ADR-5. Offline-first: Firestore — источник правды, Room — кэш

- **Чтение:** UI всегда читает из Room (`Flow`), Room наполняется из Firestore.
- **Запись:** сначала локально, затем в Firestore; при отсутствии сети — отложенная отправка.
- **Конфликты:** для MVP — last-write-wins по `updatedAt`.

**Почему:** приложение остаётся быстрым и работоспособным без сети, а облако не блокирует UI.

<a id="adr-6"></a>
### ADR-6. Внешние данные изолированы

- `:core:network` — только транспорт: Retrofit, OkHttp, конвертеры. Никакой бизнес-логики.
- `:data:impl` — маппинг, оркестрация запросов, кэш.
- `:domain:api` — модели и **чистая** логика (`HealthIndexCalculator`).

**Почему:** замена Open-Meteo на другой источник — правка одного модуля; UI и расчёт не меняются.

<a id="adr-7"></a>
### ADR-7. DI-связывание живёт в модуле реализации

- `@Binds` для «интерфейс → реализация» объявляй в `:data:impl`, а не в `:app` и не в `:domain`.
- `@Provides` — для внешних типов: `Retrofit`, `OkHttpClient`, `FirebaseFirestore`, `FirebaseAuth`.
- `:app` про реализации ничего не знает, кроме строки в Gradle.

**Почему:** модуль сам отвечает за своё внедрение, `:app` остаётся сборщиком графа.

<a id="adr-8"></a>
### ADR-8. Версии без Version Catalog

- Версии живут в `build.gradle.kts` тех модулей, где нужны.
- Повторяющиеся версии (`coroutines`, `compose`, `room`) держи одинаковыми: либо константой в каждом файле, либо — когда надоест — заведите `libs.versions.toml` осознанно.
- Правило: **одна версия Kotlin, Compose и AGP на весь проект.**

**Почему:** для обучения это прозрачнее, но требует дисциплины, иначе версии разъедутся.

<a id="adr-9"></a>
### ADR-9. Тестирование по слоям

| Слой | Что тестируем | Чем |
| --- | --- | --- |
| `:domain:api` | расчёты, use cases | чистые JVM-тесты |
| `:data:impl` | мапперы, репозиторий | MockK, Firebase Emulator Suite |
| `:core:network` | сервисы, ошибки | MockWebServer |
| `:feature:*` | ViewModel и UI | JUnit + Compose UI Test |
| `:app` | сценарий целиком | ручной прогон |

**Почему:** чем ниже слой, тем дешевле и быстрее тест; пирамида тестов остаётся пирамидой.

<a id="adr-10"></a>
### ADR-10. Однонаправленный поток данных (UDF)

- У экрана одно состояние: `UiState` (`Loading` / `Empty` / `Content` / `Error`).
- UI шлёт события в ViewModel, ViewModel меняет состояние, UI перерисовывается.
- Compose не хранит бизнес-состояние, только локальное UI-состояние.

**Почему:** предсказуемые экраны без «двух источников правды».

<a id="adr-11"></a>
### ADR-11. Дизайн-система в `:core:ui`

- Токены, тема и переиспользуемые компоненты живут в `:core:ui`, который зависит только от Compose — не от domain, data и Firebase.
- Экраны `:feature:*` собираются из компонентов и токенов: ни «сырых» цветов, ни магических `dp`, ни собственных кнопок.
- API компонентов: корневой `modifier` (размещением владеет вызывающий), слоты для переменного контента, примитивы — только для смысловых контрактов.

**Почему:** дизайн описывается один раз; смена темы или кнопки — правка в одном модуле, а не по всем экранам. Подробности — в [разделе 7](#design-system).

<a id="adr-12"></a>
### ADR-12. Навигация на типизированных маршрутах

- Маршрут — это `@Serializable` тип, а не строка; аргументы — поля этого типа.
- Через маршрут передаются только идентификаторы; сами данные экран получает из репозитория.
- `NavHost` и связывание фич живут в `:app`: фичи не знают друг о друге.
- Навигация «после действия» — одноразовое событие из ViewModel через `Channel`, а не флаг в состоянии.

**Почему:** переходы проверяются компилятором, аргументы нельзя рассинхронизировать, а граф остаётся в одном месте. Подробности — в [разделе 8](#navigation).

---

<a id="stack"></a>
## 1. Технологический стек

| Область | Технология |
| --- | --- |
| Язык | Kotlin |
| UI | Jetpack Compose + Material 3 |
| Архитектура | Clean Architecture + MVVM + UDF |
| Асинхронность | Coroutines, Flow, StateFlow |
| Навигация | Navigation Compose (типизированные маршруты) |
| DI | Hilt |
| Аутентификация | Firebase Authentication (email, Google) |
| Хранение данных пользователя | Cloud Firestore |
| Локальный кэш | Room + KSP |
| Внешние данные | Retrofit + OkHttp + kotlinx.serialization (Open-Meteo) |
| Метрика самочувствия | Чистая domain-логика на Kotlin |
| Локальные напоминания | AlarmManager |
| Фоновая синхронизация | Firestore offline persistence + WorkManager |
| Push-уведомления | Firebase Cloud Messaging |
| Тестирование | JUnit, MockK, MockWebServer, Compose UI Test, Firebase Emulator Suite |
| Сборка | Gradle Kotlin DSL, `build.gradle.kts`, без Version Catalog |

> ℹ️ **Разделение ответственности.** Firestore — хранилище данных пользователя. Retrofit/OkHttp — внешний публичный API (погода). Расчёт метрики — обычная Kotlin-функция без Android и сети.

---

<a id="architecture"></a>
## 2. Архитектура проекта

Разделение на `api` и `impl` — но не каждый класс в отдельный модуль. Начни с такой структуры:

```text
Dayly/
├── app/                    # граф, тема, навигация, уведомления
│
├── core/
│   ├── ui/                # дизайн-система: токены, тема, компоненты
│   ├── database/          # Room: локальный кэш и расписания напоминаний
│   ├── firebase/          # Firestore и Firebase Authentication
│   └── network/           # Retrofit + OkHttp: внешний REST API
│
├── domain/
│   └── api/               # модели, контракты, use cases, HealthIndexCalculator
│
├── data/
│   └── impl/              # реализации репозиториев: Room, Firestore, Retrofit
│
└── feature/
    ├── home/              # главный экран, сон, индекс самочувствия
    ├── medications/       # список и форма добавления
    └── auth/              # вход и регистрация
```

> 💡 Это **10 модулей**, а не 23. Каждая папка с `build.gradle.kts` — отдельный Gradle-модуль; группы `core`, `domain`, `data`, `feature` модулями не являются.
>
> 💡 `habits` и `sleep` на MVP живут внутри `:feature:home`: отдельный модуль появляется, когда у фичи возникает своя граница (свой data-слой, своя команда, тяжёлые зависимости). Вынести позже — дешевле, чем тащить 15 пустых модулей с первого дня.

### Как будут связаны модули

```text
                        :app
         +---------------+--------------+
         |               |              |
  :feature:auth   :feature:home  :feature:medications
         |               |              |
         +---------------+--------------+
                         |
                    :domain:api
                         ^
                         |
                     :data:impl
                    /     |      \
          :core:database :core:firebase :core:network
```

Подробнее о направлении стрелок и запретах — в [ADR-2](#adr-2), о том, что писать в `api`, а что в `implementation`, — в [ADR-3](#adr-3).

### Структура до файлов

Полный скелет. Подробно расписан срез лекарств, остальные фичи — на уровне пакетов: принцип тот же.

```text
Dayly/
├── app/
│   ├── build.gradle.kts
│   └── src/main/
│       ├── AndroidManifest.xml
│       ├── java/com/denalal/dayly/
│       │   ├── DaylyApplication.kt          # @HiltAndroidApp
│       │   ├── MainActivity.kt              # DaylyTheme { DaylyApp() }
│       │   ├── navigation/
│       │   │   ├── DaylyApp.kt              # Scaffold + NavHost
│       │   │   └── DaylyNavHost.kt          # собирает граф из фич
│       │   └── notifications/
│       │       ├── ReminderScheduler.kt     # AlarmManager: plan/cancel
│       │       ├── ReminderReceiver.kt      # показывает уведомление
│       │       ├── BootReceiver.kt          # BOOT_COMPLETED
│       │       └── NotificationChannels.kt
│       └── res/values/strings.xml
│
├── core/
│   ├── ui/
│   │   └── src/main/java/com/denalal/dayly/core/ui/
│   │       ├── theme/
│   │       │   ├── DaylyTheme.kt
│   │       │   ├── Color.kt
│   │       │   ├── Type.kt
│   │       │   └── Spacing.kt
│   │       └── component/
│   │           ├── DaylyCard.kt
│   │           ├── DaylyButton.kt
│   │           ├── DaylyTextField.kt
│   │           ├── DaylyTopBar.kt
│   │           └── StateViews.kt            # Loading / Empty / Error
│   │
│   ├── database/
│   │   └── src/main/java/com/denalal/dayly/core/database/
│   │       ├── DaylyDatabase.kt             # @Database
│   │       ├── Converters.kt
│   │       ├── medication/
│   │       │   ├── MedicationEntity.kt
│   │       │   └── MedicationDao.kt
│   │       └── wellness/
│   │           ├── HealthIndexEntity.kt
│   │           └── WellnessDao.kt
│   │
│   ├── firebase/
│   │   └── src/main/java/com/denalal/dayly/core/firebase/
│   │       ├── FirestorePaths.kt            # users/{uid}/medications/...
│   │       └── FirebaseModule.kt            # @Provides Firestore / Auth
│   │
│   └── network/
│       └── src/main/java/com/denalal/dayly/core/network/
│           ├── HttpClientFactory.kt         # OkHttp + Retrofit
│           ├── WeatherApi.kt
│           ├── AirQualityApi.kt
│           └── dto/
│               ├── WeatherDto.kt
│               └── AirQualityDto.kt
│
├── domain/
│   └── api/
│       ├── src/main/java/com/denalal/dayly/domain/
│       │   ├── medication/
│       │   │   ├── model/
│       │   │   │   ├── Medication.kt
│       │   │   │   ├── Dose.kt              # Dose + DoseUnit
│       │   │   │   ├── MedicationSchedule.kt
│       │   │   │   ├── MedicationTime.kt
│       │   │   │   ├── Weekday.kt
│       │   │   │   └── MedicationListExtensions.kt
│       │   │   ├── repository/
│       │   │   │   └── MedicationRepository.kt
│       │   │   └── usecase/                 # появится на неделе 2
│       │   ├── auth/
│       │   │   ├── model/
│       │   │   │   └── User.kt
│       │   │   └── repository/
│       │   │       └── AuthRepository.kt
│       │   └── wellness/
│       │       ├── model/
│       │       │   ├── HealthIndex.kt
│       │       │   ├── HealthFactor.kt
│       │       │   ├── WeatherSnapshot.kt
│       │       │   └── AirQualitySnapshot.kt
│       │       ├── repository/
│       │       │   └── WellnessRepository.kt
│       │       └── usecase/
│       │           └── CalculateHealthIndex.kt
│       └── src/test/java/com/denalal/dayly/domain/
│           └── medication/
│               ├── model/
│               │   ├── DoseTest.kt
│               │   ├── MedicationScheduleTest.kt
│               │   ├── MedicationTimeTest.kt
│               │   ├── MedicationListExtensionsTest.kt
│               │   └── MedicationExtensionsTest.kt
│               └── repository/
│                   ├── FakeMedicationRepository.kt
│                   └── MedicationRepositoryTest.kt
│
├── data/
│   └── impl/
│       └── src/main/java/com/denalal/dayly/data/
│           ├── medication/
│           │   ├── MedicationRepositoryImpl.kt
│           │   ├── local/
│           │   │   └── MedicationMapper.kt
│           │   └── remote/
│           │       ├── MedicationDocument.kt
│           │       └── FirestoreMedicationSource.kt
│           ├── auth/
│           │   ├── AuthRepositoryImpl.kt
│           │   └── FirebaseAuthSource.kt
│           ├── wellness/
│           │   ├── WellnessRepositoryImpl.kt
│           │   ├── HealthIndexCalculatorImpl.kt
│           │   └── WeatherMapper.kt
│           └── di/
│               ├── DatabaseModule.kt
│               └── RepositoryModule.kt
│
└── feature/
    ├── medications/
    │   └── src/main/java/com/denalal/dayly/feature/medications/
    │       ├── navigation/
    │       │   ├── MedicationsRoutes.kt     # @Serializable маршруты
    │       │   └── MedicationsGraph.kt      # NavGraphBuilder-расширение
    │       ├── list/
    │       │   ├── MedicationsScreen.kt
    │       │   ├── MedicationsViewModel.kt
    │       │   └── MedicationsUiState.kt
    │       └── add/
    │           ├── AddMedicationScreen.kt
    │           ├── AddMedicationViewModel.kt
    │           └── AddMedicationUiState.kt
    │
    ├── home/
    │   └── src/main/java/com/denalal/dayly/feature/home/
    │       ├── navigation/HomeRoutes.kt
    │       ├── HomeScreen.kt
    │       ├── HomeViewModel.kt
    │       ├── HomeUiState.kt
    │       └── HealthIndexCard.kt
    │
    └── auth/
        └── src/main/java/com/denalal/dayly/feature/auth/
            ├── navigation/AuthRoutes.kt
            ├── login/
            │   ├── LoginScreen.kt
            │   └── LoginViewModel.kt
            └── register/
                ├── RegisterScreen.kt
                └── RegisterViewModel.kt
```

**Кто за что отвечает в срезе лекарств:**

| Файл | Роль |
| --- | --- |
| `domain/.../medication/model/Medication.kt` | доменная модель: `id`, `name`, `dose`, `schedule` |
| `domain/.../medication/repository/MedicationRepository.kt` | контракт: `observeAll`, `add`, `markAsTaken` |
| `domain/.../medication/usecase/*` | сценарии: один файл — одно действие |
| `core/database/.../MedicationEntity.kt` | таблица Room |
| `core/database/.../MedicationDao.kt` | SQL и `Flow`-запросы |
| `data/impl/.../local/MedicationMapper.kt` | `Entity` ↔ `Medication` |
| `data/impl/.../remote/FirestoreMedicationSource.kt` | Firestore: `snapshots()`, `set`, `update` |
| `data/impl/.../MedicationRepositoryImpl.kt` | оркестрация: Room как кэш + Firestore как источник правды |
| `data/impl/.../di/RepositoryModule.kt` | `@Binds` интерфейс → реализация |
| `feature/.../navigation/MedicationsRoutes.kt` | типизированные маршруты: `Medications`, `MedicationDetails(id)` |
| `feature/.../list/MedicationsViewModel.kt` | `StateFlow<MedicationsUiState>` и вызов use cases |
| `feature/.../list/MedicationsScreen.kt` | только UI: собирает компоненты из `:core:ui` |
| `app/.../navigation/DaylyNavHost.kt` | связывает все фичи и передаёт лямбды переходов |

**Правила именования и размещения**

- `*Screen` — Composable-экран, `*ViewModel` — состояние и логика, `*UiState` — `sealed interface` состояния.
- Внутри фичи домена — три пакета по роли: `model/` (данные и правила), `repository/` (контракт), `usecase/` (сценарии). Пакет появляется, когда в нём есть что держать, а не заранее.
- `*Entity` / `*Dao` — только в `:core:database`; `*Dto` — только в `:core:network`; `*Document` — только в `:data:impl` (Firestore-документ).
- `*Repository` (интерфейс) — в `:domain:api`, `*RepositoryImpl` — в `:data:impl`; `*UseCase` — в `:domain:api`.
- `*Source` — тонкая обёртка над внешним API (Firestore, Auth), живёт в `:data:impl`.
- Тесты повторяют пакеты: `src/test/java/com/denalal/dayly/...`, UI-тесты — `src/androidTest/...`.
- Модули у нас используют `src/main/java`; `src/main/kotlin` тоже работает. Важно не имя папки, а совпадение `package` с путём.

> 💡 **Куда положить новый файл?** Если это модель или контракт — `:domain:api`. Если работа с базой, сетью или Firebase — `:data:impl` (или соответствующий `:core:*`, если это инфраструктура). Если это экран или ViewModel — в `:feature:<name>`. Если переиспользуемая кнопка или цвет — `:core:ui`.

**Например, публичные контракты:**

```kotlin
interface MedicationRepository {
    fun observeAll(): Flow<List<Medication>>

    suspend fun add(medication: Medication)

    suspend fun markAsTaken(id: String)
}

interface AuthRepository {
    val currentUser: Flow<User?>

    suspend fun signInWithEmail(email: String, password: String)

    suspend fun signOut()
}

interface WellnessRepository {
    fun observeHealthIndex(): Flow<HealthIndex>

    suspend fun refresh(location: Coordinates)
}
```

**Расчёт метрики — чистая функция в domain-слое:**

```kotlin
interface HealthIndexCalculator {
    fun compute(weather: WeatherSnapshot, air: AirQualitySnapshot): HealthIndex
}
```

**Реализации:**

```kotlin
class MedicationRepositoryImpl(
    private val dao: MedicationDao,             // Room: локальный кэш
    private val remote: MedicationRemoteSource, // Firestore
) : MedicationRepository {
    // Читаем из Room, пишем в Firestore и обновляем кэш.
}

class WellnessRepositoryImpl(
    private val weatherApi: WeatherApi,             // Retrofit
    private val airQualityApi: AirQualityApi,       // Retrofit
    private val calculator: HealthIndexCalculator,  // таблица расчёта
    private val dao: WellnessDao,                   // кэш последнего расчёта
) : WellnessRepository {
    override suspend fun refresh(location: Coordinates) {
        val weather = weatherApi.forecast(location).toDomain()
        val air = airQualityApi.current(location).toDomain()
        dao.upsert(calculator.compute(weather, air).toEntity())
    }
}
```

**Интерфейсы Retrofit-сервисов:**

```kotlin
interface WeatherApi {
    @GET("v1/forecast")
    suspend fun forecast(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("current") current: String = "temperature_2m,apparent_temperature,"
            + "relative_humidity_2m,surface_pressure,uv_index,wind_speed_10m",
    ): WeatherDto
}

interface AirQualityApi {
    @GET("v1/air-quality")
    suspend fun current(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("current") current: String = "pm2_5,pm10,birch_pollen,grass_pollen",
    ): AirQualityDto
}
```

**В приложении подключаешь нужные реализации:**

```kotlin
dependencies {
    implementation(project(":feature:medications"))
    implementation(project(":feature:auth"))
    implementation(project(":feature:home"))

    implementation(project(":data:impl"))

    // Доменные контракты — явно, если :app их использует напрямую.
    implementation(project(":domain:api"))
}
```

> 💡 `feature:home` зависит от `domain:api`, а не от Retrofit-сервиса. Заменишь Open-Meteo на другой источник — UI не изменится, потому что индекс самочувствия останется той же моделью.

### Как подключить новый модуль

Модуль — это **папка + свой `build.gradle.kts` + строка в `settings.gradle.kts`**. Пока модуля нет в `settings.gradle.kts`, для Gradle его не существует, даже если папка и файл на месте.

**Три шага**

1. Создать папку и `build.gradle.kts` (в Android Studio: **File → New → Module → Android Library** — он сделает и папку, и файл, и `include`).
2. Зарегистрировать в `settings.gradle.kts`: `include(":core:database")`.
3. Подключить как зависимость там, где он нужен: `implementation(project(":core:database"))`.

**Правила путей**

- Двоеточие в `include` — это вложенность папок: `domain/api` → `:domain:api`.
- Промежуточные папки (`core`, `domain`, `data`, `feature`) модулями не становятся: `build.gradle.kts` в них не нужен.
- Имя папки должно совпадать с последним сегментом пути (иначе `projectDir` придётся задавать вручную).

```kotlin
// settings.gradle.kts — только leaf-модули
rootProject.name = "Dayly"

include(":app")

include(":core:ui")
include(":core:database")

include(":domain:api")
include(":data:impl")
include(":feature:medications")
```

**Android-библиотека** — например `core/ui/build.gradle.kts`:

```kotlin
plugins {
    alias(libs.plugins.android.library)   // Android Library, а не Application
}

android {
    namespace = "com.denalal.dayly.core.ui"   // уникальный, продолжение app-namespace
    compileSdk { version = release(37) }
    defaultConfig { minSdk = 24 }
    buildFeatures { compose = true }
}

dependencies {
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.ui)
}
```

**Чистый Kotlin-модуль** — например `domain/api/build.gradle.kts`. Здесь нет `android { }`, зато доступна конфигурация `api` (см. [ADR-3](#adr-3)):

```kotlin
plugins {
    alias(libs.plugins.kotlin.jvm)
    id("java-library")   // именно он добавляет конфигурацию api
}

dependencies {
    api("org.jetbrains.kotlinx:kotlinx-coroutines-core:COROUTINES_VERSION")
}
```

**Подключение в `app/build.gradle.kts`:**

```kotlin
dependencies {
    implementation(project(":core:ui"))
    implementation(project(":feature:medications"))
    implementation(project(":data:impl"))
}
```

> 💡 Плагины `android.library` и `kotlin.jvm` должны быть объявлены в `gradle/libs.versions.toml` (или указаны версией прямо в модуле) — в примерах выше они взяты через `alias(libs.plugins.*)`, как в самом проекте.

> 💡 `namespace` у каждого модуля — свой и уникальный: `com.denalal.dayly.core.ui`, `com.denalal.dayly.feature.medications`. Два модуля с одинаковым namespace Gradle не соберёт.

> 💡 `compileSdk`, `minSdk` и версию Kotlin держи одинаковыми во всех Android-модулях — иначе получаешь странные ошибки сборки.

> 💡 Начни с одного-двух модулей: `:core:ui` (тема и компоненты) и один вертикальный срез `domain` → `data` → `feature`. Остальное — только когда появится реальная граница.

---

<a id="background"></a>
## 3. Как разобраться с фоновыми уведомлениями

Здесь принципиально важно **не смешивать три механизма**:

- **`AlarmManager`** — для напоминаний, которые должны сработать в определённое время, с учётом ограничений Android и разрешений на точные будильники.
- **`WorkManager`** — для отложенной синхронизации и задач, которые не требуют выполнения в точную минуту.
- **Firebase Cloud Messaging (FCM)** — для удалённых push-сообщений от сервера.

### Пример сценария

1. Пользователь создаёт расписание лекарства на 21:00.
2. Расписание сохраняется в Firestore и в Room.
3. Приложение планирует локальное уведомление.
4. Android показывает уведомление, даже когда Activity закрыта.
5. После нажатия «Принял» результат сохраняется в Room.
6. При появлении сети Firestore синхронизирует изменение с облаком (offline persistence).

Локальные уведомления не требуют постоянно работающего процесса приложения. Но нужно учитывать перезагрузку устройства, разрешения на уведомления, точные будильники и ограничения энергосбережения.

> 💡 FCM нужен, если сервер должен присылать новые события, например синхронизацию расписания с другого устройства или сообщение от Cloud Functions. Для ежедневных напоминаний локальные уведомления остаются основным механизмом.

> ⚠️ Не полагайся на FCM для точных напоминаний: push может прийти с задержкой или не прийти вовсе. Точное время — это всегда `AlarmManager`.

---

<a id="kotlin-compose"></a>
## 4. Что ты изучишь в Kotlin и Compose

Предлагаю изучать темы по мере появления конкретных задач.

| Тема | Практическая задача |
| --- | --- |
| `data class`, `enum`, `sealed interface` | Модели самочувствия и состояния UI |
| Null safety, extension functions | Обработка данных погоды |
| Generics, interfaces, delegation | Контракты и переиспользование |
| Coroutines, `suspend` | Запросы к Firestore, Firebase Auth и Retrofit |
| `Flow`, `StateFlow` | Наблюдение за Room, snapshot listeners Firestore, состояние UI |
| `remember`, `rememberSaveable` | Локальное состояние Compose |
| `LaunchedEffect` | Эффекты UI |
| ViewModel, lifecycle | Управление состоянием экрана |
| Navigation Compose, типизированные маршруты | Граф экранов и аргументы без строк |
| Hilt | Внедрение зависимостей |
| Room, SQL, миграции | Локальный кэш данных |
| Firebase SDK: Firestore, Auth | Облачное хранилище и вход пользователя |
| Retrofit, OkHttp | Внешний REST, интерсепторы и логирование |
| kotlinx.serialization | Парсинг JSON погоды и качества воздуха |
| MockWebServer | Тестирование сетевого слоя без реальной сети |
| Gradle `api` / `implementation` | Границы модулей и скорость сборки |
| Design tokens, тема, slot-API | `:core:ui` и переиспользуемые компоненты |
| Gradle Kotlin DSL | Настройка модулей и зависимостей |

> 💡 Из паттернов я бы обязательно применил **Repository, MVVM, Use Case, Dependency Injection, Observer, Adapter/Mapper и Strategy**. Factory и Facade добавлял бы только там, где есть реальная необходимость.

---

<a id="firebase"></a>
## 5. Firebase, внешний REST и самочувствие

Backend у проекта — Firebase, а Retrofit тянет внешнюю погоду. Ключевые решения:

- **Аутентификация** — только Firebase Authentication. Экран входа показывает состояние из `FirebaseAuth` через `AuthRepository`, навигация реагирует на `currentUser`.
- **Данные пользователя** — сгруппированы под его `uid`, чтобы security rules ограничивали доступ автоматически.
- **Внешние данные** — Retrofit + OkHttp ходит в публичный API **Open-Meteo** за погодой и качеством воздуха. К своим пользовательским данным через Retrofit не обращаемся.

**Структура Firestore:**

```text
users/{uid}/
├── medications/{medicationId}
├── habits/{habitId}
└── sleep/{date}
```

**Security rules (каждый видит только свои данные):**

```javascript
rules_version = '2';
service cloud.firestore {
  match /databases/{database}/documents {
    match /users/{userId}/{document=**} {
      allow read, write: if request.auth != null
                         && request.auth.uid == userId;
    }
  }
}
```

- **Чтение данных** — `snapshots()` для реактивного UI и `get()` для одноразовых запросов.
- **Никакого REST-слоя для своих данных** — репозиторий вызывает Firebase SDK напрямую. Fake-реализация `Repository` живёт только в тестах.
- **Модель данных** — один `data class` на документ, `@DocumentId` для идентификатора, поле `updatedAt` для разрешения конфликтов.
- **Офлайн** — Firestore offline persistence для данных пользователя и кэш последнего индекса в Room для внешнего API.

### Индекс самочувствия

Идея: погода влияет на самочувствие, поэтому из внешних данных считается одна метрика **0–100** плюс список факторов с уровнем риска и подсказкой. Это чистый Kotlin — без сети, Android и Firebase, поэтому идеально тестируется.

**Входные данные:** температура и ощущаемая температура, влажность, атмосферное давление и его тренд, УФ-индекс, ветер, вероятность осадков, PM2.5/PM10 и пыльца.

**Факторы и правила (пример):**

| Фактор | Триггер | Что показывает |
| --- | --- | --- |
| 🧠 Головная боль | резкое падение давления (> 6 гПа за 6 ч) | «Возможна головная боль» |
| 💧 Обезвоживание | жара ≥ 28 °C и влажность < 40 % | «Пей больше воды» |
| 🦴 Суставы | низкое давление + холод + высокая влажность | «Дискомфорт в суставах» |
| 🫁 Дыхание и аллергия | PM2.5 выше нормы, пыльца, сухой воздух | «Риск для дыхания» |
| 😴 Комфорт сна | ночная температура вне 16–19 °C, влажность вне 40–60 % | «Условия для сна» |
| ☀️ УФ-нагрузка | `uv_index` ≥ 6 | «Защита от солнца» |

**Модель результата:**

```kotlin
data class HealthIndex(
    val score: Int,                    // 0..100
    val headline: String,              // «Умеренный риск головной боли»
    val factors: List<HealthFactor>,
    val updatedAt: Instant,
)

data class HealthFactor(
    val kind: HealthFactorKind,        // HEADACHE, HYDRATION, JOINTS, RESPIRATORY, SLEEP, UV
    val level: Level,                  // LOW, MODERATE, HIGH
    val advice: String,
)
```

**Где это видно в приложении:**

- **Главный экран** — карточка «Самочувствие сегодня»: индекс, главный фактор и совет.
- **Экран сна** — фактор `SLEEP`, чтобы было понятно, как погода влияет на качество сна.
- **Напоминания о лекарствах** — контекстная подсказка для аллергии или астмы, если поднялся фактор `RESPIRATORY`.

> 💡 Open-Meteo бесплатный и **не требует API-ключа**, а прогноз и качество воздуха — это два отдельных эндпоинта, то есть два Retrofit-сервиса и хорошая практика с несколькими источниками.

> 💡 Координаты бери из настроек (город пользователя) как самый простой вариант. Подключать геолокацию (`FusedLocationProvider` + разрешения) можно позже — это отдельная тема.

> ⚠️ Публичный API — чужой и нестабильный: он может тормозить, вернуть ошибку или измениться. Поэтому кэшируй последний индекс в Room и не показывай пустой экран при сбое сети.

> ⚠️ Индекс самочувствия — это не медицинская рекомендация. Формулируй подсказки мягко и не обещай диагнозов.

> ⚠️ Никогда не храни в приложении ключи и не отключай security rules «для отладки». Правила — единственная защита данных, раз backend-кода у тебя нет.

---

<a id="dependencies"></a>
## 6. Как подключать зависимости без `libs.versions.toml`

**В корневом `build.gradle.kts` можно объявить версии плагинов:**

```kotlin
plugins {
    id("com.android.application") version "AGP_VERSION" apply false
    id("com.android.library") version "AGP_VERSION" apply false
    id("org.jetbrains.kotlin.android") version "KOTLIN_VERSION" apply false
    id("org.jetbrains.kotlin.jvm") version "KOTLIN_VERSION" apply false
    id("org.jetbrains.kotlin.plugin.compose") version "KOTLIN_VERSION" apply false
    id("org.jetbrains.kotlin.plugin.serialization") version "KOTLIN_VERSION" apply false
    id("com.google.devtools.ksp") version "KSP_VERSION" apply false
    id("com.google.dagger.hilt.android") version "HILT_VERSION" apply false
    id("com.google.gms.google-services") version "GOOGLE_SERVICES_VERSION" apply false
}
```

**В модуле приложения:**

```kotlin
plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.compose")
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.Dayly"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.Dayly"
        minSdk = 26
        targetSdk = 36
    }

    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation("androidx.compose.ui:ui:UI_VERSION")
    implementation("androidx.compose.material3:material3:MATERIAL3_VERSION")

    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:LIFECYCLE_VERSION")
    implementation("androidx.navigation:navigation-compose:NAVIGATION_VERSION")

    // Firebase: версии управляются через BoM, поэтому у артефактов их нет.
    implementation(platform("com.google.firebase:firebase-bom:FIREBASE_BOM_VERSION"))
    implementation("com.google.firebase:firebase-auth")
    implementation("com.google.firebase:firebase-firestore")
    implementation("com.google.firebase:firebase-messaging")

    // Hilt
    implementation("com.google.dagger:hilt-android:HILT_VERSION")
    ksp("com.google.dagger:hilt-compiler:HILT_VERSION")

    implementation(project(":feature:medications"))
    implementation(project(":feature:auth"))
    implementation(project(":feature:home"))

    implementation(project(":data:impl"))
    implementation(project(":data:impl"))
    implementation(project(":data:impl"))

    implementation(project(":domain:api"))
}
```

> ⚠️ Обозначения `*_VERSION` нужно заменить конкретными совместимыми версиями. Это **шаблон, а не готовый к сборке файл**: понадобится ещё `google-services.json` в модуле `app`, KSP для Room в том модуле, где объявлена база, и, при желании, Firebase Emulator Suite.
>
> 💡 Артефакты `firebase-*` указывай без суффикса `-ktx`: расширения для Kotlin уже входят в основные модули, а отдельные KTX-пакеты устарели.
>
> 💡 `retrofit2-kotlinx-serialization-converter` — не единственный вариант: вместо него можно взять `converter-gson` или `converter-moshi`, если они тебе привычнее.
>
> 💡 Порядок `api`/`implementation` для каждого модуля разобран в [ADR-3](#adr-3).

---

<a id="design-system"></a>
## 7. Дизайн-система и переиспользуемый UI

Цель — описать дизайн **один раз** и дальше только переиспользовать. Экран не должен знать ни цветов, ни отступов, ни «своей» кнопки: всё это живёт в `:core:ui`.

### Что где лежит

- **`:core:ui`** — токены, тема и базовые компоненты. Зависит только от Compose: ни domain-моделей, ни бизнес-логики, ни Firebase.
- **`:feature:*`** — собирает экран из компонентов `:core:ui` и, при необходимости, добавляет свои экранные блоки.

### Токены

Дизайн начинается не с компонентов, а с токенов — иначе значения разъедутся по файлам.

- **Цвета** — через `ColorScheme` Material 3: в экране `MaterialTheme.colorScheme.primary`, а не `Color(0xFF3F51B5)`.
- **Типографика** — `Typography` плюс пара своих стилей (заголовок, тело, подпись).
- **Отступы** — Material их не даёт, поэтому заведи `LocalSpacing` или объект с шагом `4/8/12/16/24 dp`. Без этого по экранам поползут магические числа.
- **Формы и тень** — `Shapes` и `Elevation`; светлая и тёмная тема — одна тема с двумя наборами значений.

```kotlin
@Composable
fun DaylyTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(
        LocalSpacing provides if (darkTheme) DarkSpacing else LightSpacing,
    ) {
        MaterialTheme(
            colorScheme = if (darkTheme) DarkColors else LightColors,
            typography = DaylyTypography,
            shapes = DaylyShapes,
            content = content,
        )
    }
}
```

### Правила API компонентов

- **Корневой `modifier: Modifier = Modifier`** — обязателен и ставится на корень композиции. Размещение (`fillMaxWidth`, экранные отступы, выравнивание) — забота вызывающего, а не компонента.
- **Порядок модификаторов:** сначала пришедший извне, затем собственные «личные» (`clip`, размер по умолчанию).
- **Слоты вместо флагов:** переменный контент — это `@Composable () -> Unit`, а не набор `showLeading`/`showIcon`.
- **Необязательный слот — nullable с `null` по умолчанию**, чтобы вместе с ним исчезали контейнер и его отступы.
- **Примитивы остаются там, где есть смысловой контракт** (например, уровень риска), а не «на всякий случай».
- **Повторяющиеся значения и дефолты — в `XxxDefaults`.**

```kotlin
@Composable
fun DaylyCard(
    content: @Composable ColumnScope.() -> Unit,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    leadingContent: (@Composable () -> Unit)? = null,
    trailingContent: (@Composable () -> Unit)? = null,
)
```

### Минимальный набор на старте

`DaylyScaffold`, `DaylyTopBar`, `DaylyCard`, `DaylyButton`, `DaylyTextField`, `DaylyListItem`. Отдельно — `EmptyState`, `ErrorState`, `LoadingState`: они окупятся на каждом экране и заодно снимут вопрос «что рисовать, пока грузится».

### Превью как каталог

Каждый компонент — с `@Preview` внутри `DaylyTheme`. Это живой каталог без отдельного приложения: меняешь токен — видишь последствия во всех состояниях. Позже сюда же ложатся screenshot-тесты, поэтому превью — не украшение, а точка опоры.

### Чего не делать

- **Не заводить компонент под каждый экран.** Компонент появляется на втором-третьем использовании; до этого он живёт в фиче.
- **Не тащить в `:core:ui` доменные модели** (`Medication`, `HealthIndex`): иначе UI-модуль начнёт зависеть от domain и перестанет быть переиспользуемым.
- **Не хардкодить цвета и `dp` в экранах** — это первый признак, что дизайн-система не прижилась.
- **Не строить флаг-матрицу** (`isPrimary`, `isLarge`, `hasIcon`…) для одного компонента: обычно это два разных компонента или один слот.

### Порядок зависимости

```text
:feature:* → :core:ui            (экраны используют компоненты и тему)
:app       → :core:ui            (тема на самом верху, вокруг NavHost)
:core:ui   → только Compose      (никаких domain, data, Firebase)
```

> 💡 Тема оборачивает приложение один раз, в `:app`, вокруг `NavHost` — тогда ни один экран не занимается настройкой цветов и типографики.

---

<a id="navigation"></a>
## 8. Навигация: типизированные маршруты

Навигация — это не «переходы между экранами», а **состояние**: `NavController` хранит back stack, а Compose перерисовывает то, что наверху. Отсюда всё остальное.

### Почему не строки

- Строку не проверяет компилятор: опечатка вылезает в рантайме как «destination not found».
- Аргументы (`"medications/{id}"` + `arguments = listOf(...)`) легко рассинхронизировать с кодом.
- Рефакторинг имени экрана не находит все использования.

С type-safe API маршрут — это **тип**. Переход `navigate(MedicationDetails(id))` не скомпилируется, если аргумент забыт или имеет не тот тип. Типизированные маршруты используют kotlinx.serialization, плагин которого в проекте уже стоит.

### Основы

```kotlin
@Serializable data object Home
@Serializable data object Medications
@Serializable data class MedicationDetails(val id: String)
@Serializable data object SleepSettings

NavHost(navController = navController, startDestination = Home) {
    composable<Home> {
        HomeScreen(onOpenMedications = { navController.navigate(Medications) })
    }
    composable<Medications> {
        MedicationsScreen(
            onItemClick = { id -> navController.navigate(MedicationDetails(id)) },
        )
    }
    composable<MedicationDetails> { entry ->
        val route = entry.toRoute<MedicationDetails>()
        MedicationDetailsScreen(id = route.id)
    }
}
```

### Аргументы

- Поддерживаются примитивы, `String`, nullable и значения по умолчанию.
- Значение по умолчанию делает аргумент необязательным: `data class Search(val query: String = "")`.
- `enum` работает как аргумент, если он `@Serializable`; для своих типов нужен кастомный `NavType`.
- **Не передавай объекты и entity.** Только id — иначе в маршруте окажется копия состояния.

### Где читать аргументы

- В Composable: `entry.toRoute<MedicationDetails>()`.
- В ViewModel: `SavedStateHandle.toRoute<MedicationDetails>()` — тогда экран не разбирает аргументы вручную, а ViewModel сама подгружает данные по id.

### Как вернуть результат назад

Объекты через маршрут не возвращают. Классический приём: экран-источник кладёт результат в `previousBackStackEntry.savedStateHandle`, а экран-приёмник подписывается на `currentBackStackEntry.savedStateHandle.getStateFlow(...)`. Альтернатива для смежных экранов — общий навигационный граф с одним ViewModel, но это реже бывает оправдано.

### Навигация «после действия» и UDF

- **Простые переходы** (клик по элементу) — колбэк `onClick: () -> Unit`, переданный в Composable. Минимум связей, легко тестировать.
- **Навигация как следствие действия** (форма отправлена, пользователь вышел из аккаунта) — ViewModel отдаёт одноразовое событие через `Channel<NavEvent>`, а экран собирает его в `LaunchedEffect` и вызывает `navController`.
- `Channel` предпочтительнее флага в `UiState`: событие не «переигрывается» при повороте экрана.

### Вложенные графы

Поток лучше делить по смыслу, а не условиями в каждом экране:

```kotlin
navigation<AuthGraph>(startDestination = Login) {
    composable<Login> { … }
    composable<Register> { … }
}
```

Тогда «вход или основное приложение» — это выбор графа, а не `if (isLoggedIn)` в `NavHost` и внутри экранов.

### Back stack: где чаще всего ломается

- `launchSingleTop = true` — не плодить дубликат текущего экрана.
- `popUpTo<Home> { saveState = true }` + `restoreState = true` — сохранять состояние вкладок нижней навигации.
- `popUpTo(0) { inclusive = true }` после выхода из аккаунта — чтобы системная кнопка «назад» не вернула в логин.

### Deep links

Типизированный маршрут умеет быть и ссылкой: `composable<MedicationDetails>(deepLinks = listOf(navDeepLink<MedicationDetails>(basePath = "dayly://medications")))`. FCM-сообщение тогда открывает нужный экран тем же маршрутом, что и обычный переход.

### Мультимодульность и граф

- **`NavHost` живёт в `:app`** — только этот модуль видит все фичи.
- Фича отдаёт расширение графа, а не маршруты соседей: `fun NavGraphBuilder.medicationsScreen(onOpen: (String) -> Unit)`.
- `:app` связывает лямбды: открыть детали — это `navigate(MedicationDetails(id))`.
- Если тип-маршрут нужен нескольким фичам, вынеси его в небольшой `:core:navigation`. Не создавай этот модуль заранее — он появляется, когда маршруты реально стали общими.

### Тестирование навигации

`TestNavHostController` + `createComposeRule()`: проверяешь, что после клика текущий маршрут — ожидаемый (`navController.currentBackStackEntry?.toRoute<MedicationDetails>()`), а аргумент совпадает. Это самые ценные UI-тесты: они ловят именно те баги, которые дороже всего искать руками.

### Частые ошибки

- Передать в маршрут весь объект вместо id.
- Вызывать `navigate` прямо в теле Composable — получишь переход на каждом пересоздании; для этого есть `LaunchedEffect` и колбэки.
- Забыть `popUpTo` при выходе/логине — «назад» уводит туда, где пользователь уже не должен быть.
- Смешать навигацию и бизнес-состояние: экран не должен сам решать, куда идти после успешной операции.
