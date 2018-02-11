# Android-Bootstrap
This is the launchpad for starting any android project which involves consuming rest based web services.
The bootstrap tries to remain testable by making use of constructor based dependency injection.

#### Getting started

#### 1. Clone

Run `git clone https://github.com/anuragarora/Android-Bootstrap.git` for https or `git clone git@github.com:anuragarora/Android-Bootstrap.git` for SSH

#### 2. Open the project in Android Studio

Select `Open Existing Project` from the options. To install please follow the instructions [here](https://developer.android.com/sdk/installing/studio.html)

Our autoformatting rules are as follows:
* Column width 140
* Field instance members are prefixed with 'm' e.g. mTextView
* Static members are prefixed with 's'
* Constants are private (unless needed elsewhere), static and all in uppercase, e.g. FADE_ANIMATION_OUT_MILLIS

### Assets

Assets should be added to the correct directory, e.g. drawable-hdpi or drawable-xxxhdpi based on their density. These directories can be found under `~/app/src/main/res`

[Here](https://www.youtube.com/watch?v=zhszwkcay2A) and [here](http://developer.android.com/guide/topics/resources/more-resources.html#Dimension) are short intros for understanding density independence


### Commit & PR Flow
* When committing to your feature branch, start your commit message with feature name.
* When you are ready to merge your feature branch to develop, raise a PR for it.


### Naming conventions

String ids:

`<activity/fragment/view/global>_<string name>` e.g. home_activity_welcome_message

If a string is used in multiple places, use the 'global' prefix rather than the screen name.

Layout ids:

`<activity/fragment/view>_<type>_<description>` e.g. home_activity_textview_welcome_message

### Third-party Libraries
* `Retrofit` - https://github.com/square/retrofit
* `Butterknife` - https://github.com/JakeWharton/butterknife
* `Eventbus` - https://github.com/greenrobot/EventBus
* `JodaTimeAndroid` - https://github.com/dlew/joda-time-android