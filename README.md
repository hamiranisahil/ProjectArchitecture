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
```
    // retrofit, gson
    implementation 'com.google.code.gson:gson:2.8.5'
    implementation 'com.squareup.retrofit2:retrofit:2.4.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.4.0'
``` 
Step-1 Create Application Class and add it to Manifest file.
```
class AppClass : Application() {

    override fun onCreate() {
        super.onCreate()
        AppConfig().projectSetUp(applicationContext)
    }

}
```

Now add this class in AndroidManifest.xml file.
```
android:name=".AppClass"
```

### jsonschema2pojo Plugin

Step-1 Open build.gradle(project-level) file.

```
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
```

Step-2 Open build.gradle(project-level) file.
```
apply plugin: 'jsonschema2pojo'


// Each configuration is set to the default value
jsonSchema2Pojo {

    // Whether to allow 'additional' properties to be supported in classes by adding a map to
    // hold these. This is true by default, meaning that the schema rule 'additionalProperties'
    // controls whether the map is added. Set this to false to globabally disable additional properties.
    includeAdditionalProperties = false

    // Whether to generate builder-style methods of the form withXxx(value) (that return this),
    // alongside the standard, void-return setters.
    generateBuilders = false

    // Whether to use primitives (long, double, boolean) instead of wrapper types where possible
    // when generating bean properties (has the side-effect of making those properties non-null).
    usePrimitives = false

    // Location of the JSON Schema file(s). This may refer to a single file or a directory of files.
    source = files("${project.rootDir}/schema")

    // Target directory for generated Java source files. The plugin will add this directory to the
    // java source set so the compiler will find and compile the newly generated source files.
    targetDirectory = file("${project.buildDir}/generated-sources/js2p")

    // Package name used for generated Java classes (for types where a fully qualified name has not
    // been supplied in the schema using the 'javaType' property).
    targetPackage = 'com.modal'

    // The characters that should be considered as word delimiters when creating Java Bean property
    // names from JSON property names. If blank or not set, JSON properties will be considered to
    // contain a single word when creating Java Bean property names.
    propertyWordDelimiters = [] as char[]

    // Whether to use the java type long (or Long) instead of int (or Integer) when representing the
    // JSON Schema type 'integer'.
    useLongIntegers = false

    // Whether to use the java type BigInteger when representing the JSON Schema type 'integer'. Note
    // that this configuration overrides useLongIntegers
    useBigIntegers = false

    // Whether to use the java type double (or Double) instead of float (or Float) when representing
    // the JSON Schema type 'number'.
    useDoubleNumbers = true

    // Whether to use the java type BigDecimal when representing the JSON Schema type 'number'. Note
    // that this configuration overrides useDoubleNumbers
    useBigDecimals = false

    // Whether to include hashCode and equals methods in generated Java types.
    includeHashcodeAndEquals = true

    // Whether to include a toString method in generated Java types.
    includeToString = true

    // The style of annotations to use in the generated Java types. Supported values:
    //  - jackson (alias of jackson2)
    //  - jackson2 (apply annotations from the Jackson 2.x library)
    //  - jackson1 (apply annotations from the Jackson 1.x library)
    //  - gson (apply annotations from the Gson library)
    //  - moshi1 (apply annotations from the Moshi 1.x library)
    //  - none (apply no annotations at all)
    annotationStyle = 'gson'

    // A fully qualified class name, referring to a custom annotator class that implements
    // org.jsonschema2pojo.Annotator and will be used in addition to the one chosen
    // by annotationStyle. If you want to use the custom annotator alone, set annotationStyle to none.
    customAnnotator = 'org.jsonschema2pojo.NoopAnnotator'

    // Whether to include JSR-303/349 annotations (for schema rules like minimum, maximum, etc) in
    // generated Java types. Schema rules and the annotation they produce:
    //  - maximum = @DecimalMax
    //  - minimum = @DecimalMin
    //  - minItems,maxItems = @Size
    //  - minLength,maxLength = @Size
    //  - pattern = @Pattern
    //  - required = @NotNull
    // Any Java fields which are an object or array of objects will be annotated with @Valid to
    // support validation of an entire document tree.
    includeJsr303Annotations = false

    // The type of input documents that will be read. Supported values:
    //  - jsonschema (schema documents, containing formal rules that describe the structure of JSON data)
    //  - json (documents that represent an example of the kind of JSON data that the generated Java types
    //          will be mapped to)
    //  - yamlschema (JSON schema documents, represented as YAML)
    //  - yaml (documents that represent an example of the kind of YAML (or JSON) data that the generated Java types
    //          will be mapped to)
    sourceType = 'json'

    // Whether to empty the target directory before generation occurs, to clear out all source files
    // that have been generated previously. <strong>Be warned</strong>, when activated this option
    // will cause jsonschema2pojo to <strong>indiscriminately delete the entire contents of the target
    // directory (all files and folders)</strong> before it begins generating sources.
    removeOldOutput = false

    // The character encoding that should be used when writing the generated Java source files
    outputEncoding = 'UTF-8'

    // Whether to use {@link org.joda.time.DateTime} instead of {@link java.util.Date} when adding
    // date type fields to generated Java types.
    useJodaDates = false

    // Whether to add JsonFormat annotations when using Jackson 2 that cause format "date", "time", and "date-time"
    // fields to be formatted as yyyy-MM-dd, HH:mm:ss.SSS and yyyy-MM-dd'T'HH:mm:ss.SSSZ respectively. To customize these
    // patterns, use customDatePattern, customTimePattern, and customDateTimePattern config options or add these inside a
    // schema to affect an individual field
    formatDateTimes = true
    formatDates = true
    formatTimes = true

    // Whether to initialize Set and List fields as empty collections, or leave them as null.
    initializeCollections = true

    // Whether to add a prefix to generated classes.
    classNamePrefix = ""

    // Whether to add a suffix to generated classes.
    classNameSuffix = ""

    // An array of strings that should be considered as file extensions and therefore not included in class names.
    fileExtensions = [] as String[]

    // Whether to generate constructors or not.
    includeConstructors = false

    // **EXPERIMENTAL** Whether to make the generated types Parcelable for Android
    parcelable = false

    // Whether to make the generated types Serializable
    serializable = false

    // Whether to include getters or to omit these accessor methods and create public fields instead.
    includeGetters = false

    // Whether to include setters or to omit these accessor methods and create public fields instead.
    includeSetters = false

    // Whether to include dynamic getters, setters, and builders or to omit these methods.
    includeDynamicAccessors = false

    // Whether to include dynamic getters or to omit these methods.
    includeDynamicGetters = false

    // Whether to include dynamic setters or to omit these methods.
    includeDynamicSetters = false

    // Whether to include dynamic builders or to omit these methods.
    includeDynamicBuilders = false

    // What type to use instead of string when adding string properties of format "date" to Java types
    dateType = "java.time.LocalDate"

    // What type to use instead of string when adding string properties of format "date-time" to Java types
    dateTimeType = "java.time.LocalDateTime"
}
```
