MotionDetection
===========
Android Studio version: 2.1.3
Gradle version: 2.1.3

The Latest Version
------------------
VersionCode 1
VersionName "1.1"

Project description
-------------------
This library implements fast recognition algorithm, for detect state device in space. An application must be an enabled gps connection.

Permissions
-----------
To use the library, add in your AndroidManifest.xml  the following permissions:
```
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
```
Installation
------------
1. Copy library jar file  to your project libs folder.
2. In Gradle add to dependencies library jar file:
```
dependencies {
    compile files('libs/motiondetection.jar')
    }
```
3. Library is ready for use!

How to use
----------
```
public class MainActivity extends AppCompatActivity implements DetectStatus {
    private DetectionManager mDetectionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDetectionManager = new DetectionManager(this, getApplicationContext());
        mDetectionManager.detect();
    }

    @Override
    public void onStopMotion() {
       // call when stop motion detected
    }

    @Override
    public void onMotion() {
       // call when motion detected
    }

    @Override
    protected void onDestroy() {
        mDetectionManager.removeUpdates();
        super.onDestroy();
    }
}
```

It is also possible to configure the algorithm parameters:

```
/** This value defines the distance traveled in meters for recognition **/
mDetectionManager.setMaxDistance(5); 
 
/**
This parameter depend on the accuracy and speed of recognition. The higher the value the 
more accurate the recognition and lower recognition rate.
**/
mDetectionManager.setMaxSize(3); 

```
