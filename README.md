Recruitment App for Moatable with invited user @Moatable-Internship-Android.

App fetches a list of characters and episodes asynchronously mapping them togeher.
In case of an error app shows a message and a retry button.
During loading it shows progress indicator.
App presents presents a list of characters with each row consisting of image, name and status.
Upon clicking an item (character) app navigates to characte's details screen, including episodes in which it appears.
App theme, colors and typography are made with Material 3 Theme Builder and then modified to fit Rick and Morty style.
App has light mode and dark mode depending on the phone settings.
Character has color indicator regarding his status in both screens.
App uses Jetpack Compose, coroutines, Retrofit, viewmodel, navigation-compose, coil and google fonts.
App has couple unit tests in JUnit.
App has classes and functions explained in comments.
App was tested on my real device.
App loads everything it needs at the start, because fetching and mapping takes very little time and navigating between screens is smooth.

Ideas to expand the app in the future:
- More unit test with JUnit and Mockito
- Add search bar for characters
- Add filter and sortBy for example alphabetically, episodically, by status, by gender etc. Can add sticky headers for this
- Check out new type-safe compose navigation in 2.8.0-alpha08
- Change app icon
- Check out Shared Element Transition to animate information that is on both screens when navigation (like image or name)
- Add dialog to change lightTheme/darkTheme/phoneSettingsTheme

Android Studio version used:
Android Studio Hedgehog | 2023.1.1 Patch 2
Build #AI-231.9392.1.2311.11330709, built on January 19, 2024
Runtime version: 17.0.7+0-b2043.56-10550314 amd64
VM: OpenJDK 64-Bit Server VM by JetBrains s.r.o.
Windows 10.0
GC: G1 Young Generation, G1 Old Generation
Memory: 2048M
Cores: 16
Registry:
    external.system.auto.import.disabled=true
    debugger.new.tool.window.layout=true
    ide.text.editor.with.preview.show.floating.toolbar=false
    ide.experimental.ui=true
