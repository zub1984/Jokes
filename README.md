# Build It Bigger - P4:Jokes App 
This project is part of Udacity Project,In this project you will create an app with multiple flavors that uses multiple libraries 
and Google Cloud Endpoints. The finished app will consist of four modules:

1.	A Java library that provides jokes
2.	A Google Cloud Endpoints (GCE) project that serves those jokes
3.	An Android Library containing an activity for displaying jokes
4.	An Android app that fetches jokes from the GCE module and passes them to the Android Library for display


# Used libraries
   compile fileTree(dir: 'libs', include: ['*.jar'])
    testCompile 'junit:junit:4.12'
    compile 'com.android.support:appcompat-v7:23.1.1'
    compile 'com.android.support:design:23.1.1'
    compile 'com.google.android.gms:play-services-ads:8.3.0'
    compile project(':jokesProvider')

# Reference



# License

    Copyright (C) 2015  Mohammad Jubair Khan (zub1984.kn@gmail.com) - Build It Bigger of Udacity Nanodegree course.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.


