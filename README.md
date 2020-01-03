# AppAutomation

#### Prerequisites

We use a number of open source library to work properly:

- [Oh My Zsh](https://github.com/robbyrussell/oh-my-zsh) - Framework for managing your zsh configuration _(Optional)_
- [Node.js](https://nodejs.org/dist/latest-v10.x/) - JavaScript runtime built **(v10.16.x)**
- [JDK](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) - Java SE Development Kit
- [Appium](http://appium.io) - Automation framework for use with native, hybrid and mobile web apps
- [Appium Doctor](https://github.com/appium/appium-doctor) - Attempts to diagnose and fix common Node, iOS and Android configuration issues before starting Appium
- [IntelliJ IDEA](https://www.jetbrains.com/idea/) - Capable and Ergonomic IDE for JVM
- [Android Studio](https://developer.android.com/studio/) - Tools for building apps on every type of Android device
- Android Emulator or Real Device
- Setup Environment Variable on `.zshrc` or `.bashrc`

#### Node & Npm
This automation tools requires [Node.js](https://nodejs.org/) v10+ to run.
Please check node & npm version on your terminal

```sh
$ node -v
$ npm -v
```
After you installed node.js, you can optionally upgrade npm to latest version:

```sh
$ npm install -g npm
```

#### JDK (Java SE Development Kit)

Please install Java SE Development Kit by going to [this](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) website.

Verify java installation

```sh
$ java -version
```

It should show something like this
```
java version "1.8.0_221"
Java(TM) SE Runtime Environment (build 1.8.0_211-b12)
Java HotSpot(TM) 64-Bit Server VM (build 25.211-b12, mixed mode)
```

#### Appium & Appium Doctor

We will use latest appium version

```sh
$ npm install -g appium
$ appium -v
```

After that, we need to `appium-doctor` to check whether appium setup is correct or not.

```sh
$ npm install -g appium-doctor
$ appium-doctor
```

You will find several error, but don't worry we will fix that later.

#### IntelliJ IDEA

This is recommended IDE to write automation test.
You can open [IntelliJ](https://www.jetbrains.com/idea/) website to download community version of this IDE and install it on your machine.

#### Android Studio

I recommend you to download android studio including Android SDK [here](https://developer.android.com/studio/#downloads).

If you don't need Android Studio, you can download the basic Android command line tools [here](https://developer.android.com/studio/#command-tools). You can use the included sdkmanager to download other SDK packages. 

#### Android Emulator or Real Device

To run automation test, you can run it on android emulator or on real device.
You can follow instruction [here](https://developer.android.com/studio/run/managing-avds).

When you plugged your device to your machine, you can check device id by using this command:

```sh
$ adb devices
```

Make sure you've already set **JAVA_HOME** on your terminal profile

#### Environment Variable

To complete our setup you can define PATH for several libraries on our terminal profile e.g: `.bashrc` or `.zshrc`

Example:
```
export JAVA_HOME=$(/usr/libexec/java_home)
export ANDROID_HOME=/Users/bukalapak/Library/Android/sdk
export PATH=$JAVA_HOME/bin:$PATH
export PATH=$ANDROID_HOME/platform-tools:$PATH
export PATH=$ANDROID_HOME/tools:$PATH
export PATH="/usr/local/bin:$PATH"
```

Don't forget to run
```sh
$ source ~/.zshrc
or
$ source ~/.bashrc
```
to apply changes on terminal profile.

### Importing Project to IntelliJ

To start making android automation test, you can simply import this project using IntelliJ.
It will automatically downloading libraries using gradle task.

* First of all, clone this repo
* Open Intellij IDEA and select Import Project
* Locate project folder and open
* Choose Import Project from external model and select gradle
![image](https://user-images.githubusercontent.com/7087414/44521273-b432d600-a6fc-11e8-88ec-6eeddfffd523.png)
* Check on auto-import, and leave the other setting as it is
* Click finish and wait for IDE to synchronise the libraries
* Install Gherkin and Cucumber for Java Plugin from Preferences, select Plugins, Browse Repositories
* You are ready to rock ! 🤘
