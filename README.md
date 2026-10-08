# Android Auto-Scroll Carousel

A Jetpack Compose app with a horizontal carousel of color cards and an **Auto Scroll** toggle. When it's on, the carousel moves one card every 500 ms, reverses at the end and moves back toward the start.

## Project structure

| File | Responsibility |
| --- | --- |
| [`view/MainActivity.kt`](app/src/main/java/com/haidershah/myapplication/view/MainActivity.kt) | Compose UI: the carousel, the toggle and the auto-scroll loop. |
| [`viewmodel/MainViewModel.kt`](app/src/main/java/com/haidershah/myapplication/viewmodel/MainViewModel.kt) | Exposes a `StateFlow<UiState>` and handles the toggle. |
| [`model/UiState.kt`](app/src/main/java/com/haidershah/myapplication/model/UiState.kt) | Screen state: `colors` and `isAutoScrollEnabled`. |
| [`model/ColorInfo.kt`](app/src/main/java/com/haidershah/myapplication/model/ColorInfo.kt) | One card: name, hex value and color resource. |

## How auto-scroll works

The loop runs in a `LaunchedEffect` keyed on `isAutoScrollEnabled`, so turning the toggle off cancels it. Each tick uses `LazyListState.canScrollForward` and `lastScrolledBackward` to decide whether to move forward, turn around at the end, or move backward.

## Tech stack

Kotlin, Jetpack Compose, Material 3, ViewModel, Coroutines/Flow. `minSdk` 24, `targetSdk` 37.

## Run it

```bash
./gradlew installDebug
```

Or open the project in Android Studio and run the `app` configuration.

## Future improvements

- Move the scroll direction logic into a testable function, and make the interval and card width parameters.
- Pause auto-scroll while the user is dragging the carousel.
