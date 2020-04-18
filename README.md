## News API coding challenge

Using MVVM design pattern with manual Dependency Injection.
the Manual DI creates the model dependency (M) in the Application implementation, and exposes it to the Activity (V) to get.
The Activity then creates the ViewModel (VM) using a factory object that takes the model as a constructor argument.

### The control flow of the app is fully reactive.
- LiveData is used inside the model and the view model
- The activity observes the "selectedUrl" property.
  If it's null - then it opens the main list page.
  If it's not null - then it opens the selected article.
  It also uses the same event to set the toolbar back button.
- pressing "back" (arrow or button) just sets the url to null, causing a reactive effect that opens the main list page.
  If it's already null - the app is allowed to exit.
- data binding is used to set view content directly from the model, no logic code is needed
- Two biding adapters were used - one used to asynchronously load images into views,
  and another to format dates from the API format to human readable format (as shown in the concept page).


### Comments
- 3rd party libraries used
-- retrofit+gson to access and parse json from the REST API
-- picasso for async load of images
-- didn't use Dagger for the DI because the scope was so small that manual DI was much easier.
- The news is fetched every time the main page is resumed (a requirement)

### Testing
-- Unit Testing
  The ViewModel is constructed in a way to allow easy mocking of the injected model.
  Used MockK as the mocking library.

-- Instrumented Testing
  Tested the date conversion and assignment to android TextView.

### Release
here's a non obfuscated [compiled apk](./apk/newsapp.apk)