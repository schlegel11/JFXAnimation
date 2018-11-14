# JFXAnimation

[ ![Download](https://api.bintray.com/packages/schlegel11/JavaFX/jfxanimation/images/download.svg) ](https://bintray.com/schlegel11/JavaFX/jfxanimation/_latestVersion)
[![Build Status](https://travis-ci.org/schlegel11/JFXAnimation.svg?branch=master)](https://travis-ci.org/schlegel11/JFXAnimation)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/64afe731cf254cf5b36f725a125f0c5e)](https://www.codacy.com/app/marcel_4/JFXAnimation?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=schlegel11/JFXAnimation&amp;utm_campaign=Badge_Grade)

CSS keyframe animation for JavaFX.<br>
If you are using [JFoenix](https://github.com/jfoenixadmin/JFoenix) JFXAnimation is included *(currently not in release versions)*

## Requirements

- JDK 8 and up

## Documentation

##### Version 1.0.0 (Latest)

For details see: [JavaDoc](https://schlegel11.github.io/JFXAnimation/releases/1.0.0/api/docs/)

## Details

The JFXAnimation project provides the JFXAnimationTemplate classes, which acts as a builder for JavaFX animations.  
The building structure of a JFXAnimationTemplate is based on CSS keyframe animations, which have some advantages:

#### Features 

- Define the animation in a relative way, based on different percentage values, related to a total animation duration

- Multiple target observers per action

- Define the animation in a complete lazy evaluated way

- Finish events for every step/action

- Specific or global interpolator for all animation actions

- Transfer your current CSS animations easily to JavaFX

- Create animations simply for multiple animation objects

#### Code comparison:  

<br>

<table>
<tr>
<th colspan="3">

CSS [TADA](https://github.com/daneden/animate.css/blob/master/source/attention_seekers/tada.css) animation from [animate.css](https://daneden.github.io/animate.css/)
</th>
</tr>
<tr>
<td align="left">

[Implementation](https://github.com/fxexperience/code/blob/master/FXExperienceControls/src/com/fxexperience/javafx/animation/TadaTransition.java) with [Keyframe](https://docs.oracle.com/javafx/2/api/javafx/animation/KeyFrame.html) objects
</td>
<td align="left">

Implementation with [JFXAnimationTemplate](https://schlegel11.github.io/JFXAnimation/releases/1.0.0/api/docs/de/schlegel11/jfxanimation/JFXAnimationTemplate.html)
</td>
<td align="left">

[Implementation](https://github.com/daneden/animate.css/blob/master/source/attention_seekers/tada.css) with pure CSS
</td>
</tr>
<tr>
<td valign="top" align="left">
<pre lang="java">
TimelineBuilder.create()
                .keyFrames(
                    new KeyFrame(Duration.millis(0),    
                        new KeyValue(node.scaleXProperty(), 1, WEB_EASE),
                        new KeyValue(node.scaleYProperty(), 1, WEB_EASE),
                        new KeyValue(node.rotateProperty(), 0, WEB_EASE)
                    ),
                    new KeyFrame(Duration.millis(100),    
                        new KeyValue(node.scaleXProperty(), 0.9, WEB_EASE),
                        new KeyValue(node.scaleYProperty(), 0.9, WEB_EASE),
                        new KeyValue(node.rotateProperty(), -3, WEB_EASE)
                    ),
                    new KeyFrame(Duration.millis(200),    
                        new KeyValue(node.scaleXProperty(), 0.9, WEB_EASE),
                        new KeyValue(node.scaleYProperty(), 0.9, WEB_EASE),
                        new KeyValue(node.rotateProperty(), -3, WEB_EASE)
                    ),
                    new KeyFrame(Duration.millis(300),    
                        new KeyValue(node.scaleXProperty(), 1.1, WEB_EASE),
                        new KeyValue(node.scaleYProperty(), 1.1, WEB_EASE),
                        new KeyValue(node.rotateProperty(), 3, WEB_EASE)
                    ),
                    new KeyFrame(Duration.millis(400),    
                        new KeyValue(node.scaleXProperty(), 1.1, WEB_EASE),
                        new KeyValue(node.scaleYProperty(), 1.1, WEB_EASE),
                        new KeyValue(node.rotateProperty(), -3, WEB_EASE)
                    ),
                    new KeyFrame(Duration.millis(500),    
                        new KeyValue(node.scaleXProperty(), 1.1, WEB_EASE),
                        new KeyValue(node.scaleYProperty(), 1.1, WEB_EASE),
                        new KeyValue(node.rotateProperty(), 3, WEB_EASE)
                    ),
                    new KeyFrame(Duration.millis(600),    
                        new KeyValue(node.scaleXProperty(), 1.1, WEB_EASE),
                        new KeyValue(node.scaleYProperty(), 1.1, WEB_EASE),
                        new KeyValue(node.rotateProperty(), -3, WEB_EASE)
                    ),
                    new KeyFrame(Duration.millis(700),    
                        new KeyValue(node.scaleXProperty(), 1.1, WEB_EASE),
                        new KeyValue(node.scaleYProperty(), 1.1, WEB_EASE),
                        new KeyValue(node.rotateProperty(), 3, WEB_EASE)
                    ),
                    new KeyFrame(Duration.millis(800),    
                        new KeyValue(node.scaleXProperty(), 1.1, WEB_EASE),
                        new KeyValue(node.scaleYProperty(), 1.1, WEB_EASE),
                        new KeyValue(node.rotateProperty(), -3, WEB_EASE)
                    ),
                    new KeyFrame(Duration.millis(900),    
                        new KeyValue(node.scaleXProperty(), 1.1, WEB_EASE),
                        new KeyValue(node.scaleYProperty(), 1.1, WEB_EASE),
                        new KeyValue(node.rotateProperty(), 3, WEB_EASE)
                    ),
                    new KeyFrame(Duration.millis(1000),    
                        new KeyValue(node.scaleXProperty(), 1, WEB_EASE),
                        new KeyValue(node.scaleYProperty(), 1, WEB_EASE),
                        new KeyValue(node.rotateProperty(), 0, WEB_EASE)
                    )
)
</pre>
</td>
<td valign="top" align="left">
<pre lang="java">
         JFXAnimationTemplate.create()
             .from()
             .action(b -> b.target(Node::scaleXProperty, Node::scaleYProperty).endValue(1))
             .percent(10)
             .percent(20)
             .action(b -> b.target(Node::scaleXProperty, Node::scaleYProperty).endValue(0.9))
             .action(b -> b.target(Node::rotateProperty).endValue(-3))
             .percent(30, 50, 70, 90)
             .action(b -> b.target(Node::scaleXProperty, Node::scaleYProperty).endValue(1.1))
             .action(b -> b.target(Node::rotateProperty).endValue(3))
             .percent(40, 60, 80)
             .action(b -> b.target(Node::scaleXProperty, Node::scaleYProperty).endValue(1.1))
             .action(b -> b.target(Node::rotateProperty).endValue(-3))
             .to()
             .action(b -> b.target(Node::scaleXProperty, Node::scaleYProperty).endValue(1))
             .action(b -> b.target(Node::rotateProperty).endValue(0))
             .config(b -> b.duration(Duration.seconds(1)).interpolator(WEB_EASE));
</pre>
</td>
<td valign="top" align="left">
<pre lang="css">
     @keyframes tada {
       from {
         transform: scale3d(1, 1, 1);
       }
       10%,
       20% {
         transform: scale3d(0.9, 0.9, 0.9) rotate3d(0, 0, 1, -3deg);
       }
       30%,
       50%,
       70%,
       90% {
         transform: scale3d(1.1, 1.1, 1.1) rotate3d(0, 0, 1, 3deg);
       }
       40%,
       60%,
       80% {
         transform: scale3d(1.1, 1.1, 1.1) rotate3d(0, 0, 1, -3deg);
       }
       to {
         transform: scale3d(1, 1, 1);
       }
     }
</pre>
</td>
</tr>
</table>
  
  
<br>

As you can see, the default approach with KeyFrame objects has more lines of code.<br>
Furthermore, there is a repetitive number of KeyFrames (each for a specific animation section) which causes a lot of duplicated KeyValue objects.<br>
<br>
The JFXAnimationTemplate approach can handel one specified KeyFrame (action) on more animation sections due to different percentage values.<br>
Moreover, you can specify more target observers for one specific end value.     

#### How To

##### Create a JFXAnimationTemplate

The created type of JFXTemplate is use case specific. Mostly the JFXTemplateBuilder<T> is used as final type, where T is the default animation object type.<br>
A JFXTemplate can be created like that:

```java
...
      JFXAnimationTemplate.create()
...
```
in this case the default animation object type T is a Node type.<br>
It's also possible to set a own default animation object type:

```java
...
      JFXAnimationTemplate.create(MyType.class)
...
```

After the init creation you have to specify a animation interval like you do in CSS.<br>
You can use:

```java
...
      JFXAnimationTemplate.create()
          .from()
...
```
which means 0%,

```java
...
      JFXAnimationTemplate.create()
          .to()
...
```
which means 100% or

```java
...
      JFXAnimationTemplate.create()
          .percent(10)
...
```
which is the percentage value. Furthermore you can specify multiple percentage values:

```java
...
      JFXAnimationTemplate.create()
          .percent(10)
          .percent(20)
          .percent(30)
...
```

or via varargs:

```java
...
      JFXAnimationTemplate.create()
          .percent(10, 20, 30)
...
```

Now you have to implement the specific action or actions like:

```java
...
      JFXAnimationTemplate.create()
          .percent(10, 20, 30)
          .action(...)
          .action(...)
...
```

The action method can be called by value or in a lazy way.
If you use the non lazy method you have to create a JFXAnimationTemplateAction.Builder<?, ?> manually like:

```java
...
      JFXAnimationTemplateAction.builder()
...
```

The more comfortable possibility is to use the lazy approach where such a builder is ready to use like:

```java
...
      JFXAnimationTemplate.create()
          .percent(10, 20, 30)
          .action(builder -> builder...)
...
```

The next step is to define the specific animation values like:

```java
...
      JFXAnimationTemplate.create()
          .percent(10, 20, 30)
          .action(b -> b.target(Node::scaleXProperty).endValue(1))
...
```

For example you can use multiple target properties via varargs:

```java
...
      JFXAnimationTemplate.create()
          .percent(10, 20, 30)
          .action(b -> b.target(Node::scaleXProperty, Node::scaleYProperty).endValue(1))
...
```

There are a lot more functions and also lazy method representations, which also provides access to the actual animation object (like the target method in this example).
<br>
<br>

##### Configure a JFXAnimationTemplate

Lets assume we have our animation action defined like this:


```java
...
      JFXAnimationTemplate.create()
          .from()
          .action(b -> b.target(Node::scaleXProperty, Node::scaleYProperty).endValue(1))
          .percent(14)
          .action(b -> b.target(Node::scaleXProperty, Node::scaleYProperty).endValue(1.3))
          .percent(28)
          .action(b -> b.target(Node::scaleXProperty, Node::scaleYProperty).endValue(1))
          .percent(42)
          .action(b -> b.target(Node::scaleXProperty, Node::scaleYProperty).endValue(1.3))
          .percent(70)
          .action(b -> b.target(Node::scaleXProperty, Node::scaleYProperty).endValue(1))
...
```

We can finalize the animation by calling the config(...) method after the last action(...) method.<br>
Again there are the same possibilities as for the JFXAnimationTemplateAction.Builder<?, ?>.<br>
So we can use the non lazy version and create a JFXAnimationTemplateConfig.Builder manually like:

```java
...
      JFXAnimationTemplateConfig.builder()
...
```

or the more comfortable possibility with the lazy approach like:

```java
...
      JFXAnimationTemplate.create()
          .from()
          .action(b -> b.target(Node::scaleXProperty, Node::scaleYProperty).endValue(1))
          .percent(14)
          .action(b -> b.target(Node::scaleXProperty, Node::scaleYProperty).endValue(1.3))
          .percent(28)
          .action(b -> b.target(Node::scaleXProperty, Node::scaleYProperty).endValue(1))
          .percent(42)
          .action(b -> b.target(Node::scaleXProperty, Node::scaleYProperty).endValue(1.3))
          .percent(70)
          .action(b -> b.target(Node::scaleXProperty, Node::scaleYProperty).endValue(1))
          .config(builder -> builder...);

...
```
Now we have to define some config values like:
 
```java
...
      JFXAnimationTemplate.create()
          .from()
          .action(b -> b.target(Node::scaleXProperty, Node::scaleYProperty).endValue(1))
          .percent(14)
          .action(b -> b.target(Node::scaleXProperty, Node::scaleYProperty).endValue(1.3))
          .percent(28)
          .action(b -> b.target(Node::scaleXProperty, Node::scaleYProperty).endValue(1))
          .percent(42)
          .action(b -> b.target(Node::scaleXProperty, Node::scaleYProperty).endValue(1.3))
          .percent(70)
          .action(b -> b.target(Node::scaleXProperty, Node::scaleYProperty).endValue(1))
          .config(b -> b.duration(Duration.seconds(1.3)).interpolator(Interpolator.EASE_BOTH));

...
```

We have to define the total duration of the animation and we can also define a interpolator which is used by all actions.<br>
There are a lot more functions and also lazy method representations.<br>
For more see the JFXAnimationTemplateConfig class.
<br>
<br>

##### Build a JFXAnimationTemplate
 
 After defining the actions and the config method, the last step is to build the final animation.<br>
 There are different approaches to build an animation.<br>
 
###### Default animation objects

 Lets assume we have defined our animation for later use like this:
 
 ```java
 ...
  private static final JFXTemplateBuilder<Node> HEART_BEAT =
      JFXAnimationTemplate.create()
          .percent(0)
          .action(b -> b.target(Node::scaleXProperty, Node::scaleYProperty).endValue(1))
          .percent(14)
          .action(b -> b.target(Node::scaleXProperty, Node::scaleYProperty).endValue(1.3))
          .percent(28)
          .action(b -> b.target(Node::scaleXProperty, Node::scaleYProperty).endValue(1))
          .percent(42)
          .action(b -> b.target(Node::scaleXProperty, Node::scaleYProperty).endValue(1.3))
          .percent(70)
          .action(b -> b.target(Node::scaleXProperty, Node::scaleYProperty).endValue(1))
          .config(b -> b.duration(Duration.seconds(1.3)).interpolator(Interpolator.EASE_BOTH));
 
 ...
 ```
 
 Now we want e.g. a Button to animate. We would build the animation like:
 
  ```java
  ...
    Button button = new Button("Button");
    Timeline timeline = HEART_BEAT.build(button);
  ...
  ```
So the button is the default animation object and needs to be passed to the build(...) method.<br>
The default return value is the ready to use Timeline.<br>
There is also the possibility to use multiple default animation objects and also named animation objects.<br>
For this purpose we have to use again a Builder in our build(...) method.
And again we can do it in a non lazy:

  ```java
  ...
    JFXTemplateBuilder.JFXAnimationObjectMapBuilder.builder()
  ...
  ```
or lazy way:

  ```java
  ...
    Button button = new Button("Button");
    Timeline timeline = HEART_BEAT.build(builder -> builder...);
  ...
  ``` 
So to use multiple default animation objects via varargs we have to do the following:

  ```java
  ...
    Button button = new Button("Button");
    Button button2 = new Button("Button2");
  ...  
    Timeline timeline = HEART_BEAT.build(b -> b.defaultObject(button, button2));
  ...
  ```   
 
 Now the animation for both buttons are contained in the timeline object.<br>
 
 ###### Named animation objects
 
 Lets assume we have defined our animation for later use like this:
 
 ```java
 ...
  private static final JFXTemplateBuilder<Node> HEART_BEAT =
      JFXAnimationTemplate.create()
          .percent(0)
          .action(b -> b.target(Node::scaleXProperty, Node::scaleYProperty).endValue(1))
          .percent(14)
          .action(b -> b.target(Node::scaleXProperty, Node::scaleYProperty).endValue(1.3))
          .percent(28)
          .action(b -> b.target(Node::scaleXProperty, Node::scaleYProperty).endValue(1))
          .action(b -> b.withAnimationObject("SpecialNode").target(Node::translateYProperty).endValue(20))
          .percent(42)
          .action(b -> b.target(Node::scaleXProperty, Node::scaleYProperty).endValue(1.3))
          .percent(70)
          .action(b -> b.target(Node::scaleXProperty, Node::scaleYProperty).endValue(1))
          .config(b -> b.duration(Duration.seconds(1.3)).interpolator(Interpolator.EASE_BOTH));
 
 ...
 ``` 
Here we are using a named animation object "SpecialNode".<br>
So this animation object will be used in exactly this action method.<br>
There is also the possibility to define another type for the named animation object like:

  ```java
  ...
          .action(b -> b.withAnimationObject(Button.class, "SpecialNode")...)
  ...
  ```  
So a type of Button can be used in the defined animation values.<br>
If we don't define a type a Node type will be used.<br>
It's also possible to define multiple named animation objects via varargs for a specific type:

  ```java
  ...
          .action(b -> b.withAnimationObject("SpecialNode", "SpecialNode2", "SpecialNode3")...)
  ...
  ```  
  
To build our animation for e.g. a Button and our special Node we have to do the following:

  ```java
  ...
    Button button = new Button("Button");
    Label specialNode = new Label("Label");
    
    Timeline timeline = HEART_BEAT.build(b -> b.defaultObject(button).namedObject("SpecialNode", specialNode));
  ...
  ```   
  
We can also use multiple named animation objects via varargs for one name like:

  ```java
  ...
    Button button = new Button("Button");
    Label specialNode = new Label("Label");
    Label specialNode2 = new Label("Label2");
    
    Timeline timeline = HEART_BEAT.build(b -> b.defaultObject(button).namedObject("SpecialNode", specialNode, specialNode2));
  ...
  ```   
<br>  
<br>  

##### Build a specific animation container

The default animation container for a JFXAnimation is Timeline.<br>
It can explicitly set at build time like:

  ```java
  ...
    Button button = new Button("Button");
    
    Timeline timeline = myAnimation.build(JFXAnimationTemplates::buildTimeline, button);
  ...
  ```       
  
 Where buildTimeline is just a method which accepts a type of JFXAnimationTemplate, which contains all animation values and configs.<br>
 To use another animation container except Timeline, just write your own implementation, which handles the JFXAnimationTemplate instance.<br>
 For orientation the implementation of buildTimeline in JFXAnimationTemplates class can be used and copied.
 
 ## Demo
 
 A full blown example of animations can be found in the demo project/package.<br>
 The demo uses JFoenix and is also included in the JFoenix demo package.<br>
 It also requires java 8 or 9.
 
 Run the demo with:
 
 ```shell
 ./gradlew demo:animationDemo
 ```
 
 ![Alt text](https://raw.githubusercontent.com/schlegel11/JFXAnimation/assets/jfx_animation_demo.gif "Animation Demo")
 
 ## Download
 
If you are using JFoenix, you don't have to use this dependency (it's already included in JFoenix).<br>
If you use this dependency and switch later to JFoenix you can remove this dependency.<br>
Furthermore, you have to change the package names from *de.schlegel11.jfxanimation.** to *com.jfoenix.transitions.template.**
 
 #### Gradle
 
 ```groovy
 compile 'de.schlegel11:jfxanimation:1.0.0'
 ```
 
 #### Maven
 
 ```xml
<dependency>
  <groupId>de.schlegel11</groupId>
  <artifactId>jfxanimation</artifactId>
  <version>1.0.0</version>
</dependency>
 ```
