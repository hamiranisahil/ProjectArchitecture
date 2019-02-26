# ProjectArchitecture

# Usage

- [Api Call](https://github.com/hamiranisahil/ProjectArchitecture#api-call)
- Recyclerview
- App Permission
- Database
- SharedPreference
- Sqlite Database
- MyLog
- ViewPagerAdapter
- ApplicationOperations
- BannerAdInListUtility
- BannerAdUtility
- CustomAlertDialogWithBannerAd
- ListDialog
- CircleImageView
- RateThisApp (Rate Dialog)
- TextDrawable (Text to Image)
- MySnackbar
- CustomAlertDialog (One-Two Button)
- DatePickerDialogUtility
- DateUtility
- FileUtility
- FirebasePhoneAuthentication
- ImagePickerUtility
- ImageUtility
- IntentUtility
- KeyboardUtility
- LocaleManager
- NetworkUtility
- NotificationUtility
- StringUtility
- ValidatorUtility
- [jsonschema2pojo Plugin](https://github.com/hamiranisahil/ProjectArchitecture#jsonschema2pojo-plugin)

### Api Call

Add this Dependencies. 

    // retrofit, gson
    
    implementation 'com.google.code.gson:gson:2.8.5'
    implementation 'com.squareup.retrofit2:retrofit:2.4.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.4.0'
    
    
### jsonschema2pojo Plugin

Step-1 Open build.gradle(project-level) file.

'''
buildscript {
  repositories {
    mavenCentral()
  }

  dependencies {
    // this plugin
    classpath 'org.jsonschema2pojo:jsonschema2pojo-gradle-plugin:${js2p.version}'
    // add additional dependencies here if you wish to reference instead of generate them (see example directory)
  }
}
'''
