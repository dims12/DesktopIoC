Library to use JavaFX with Spring.

Features:

1) Reprsesent JavaFX nodes as beans 

2) Autowiring for JavaFX controllers

3) Creating isolating with child contexts

Main idea: you have special bean of class SpringFX or SpringFXML in your
parent context and in this bean you configure child context. Then you create
child context for new window, dialof or pane. When child context is not needed,
you dispose it. 

Spring child contexts have access to all parent beans, so you can autowire then,
also you can autowire in FXML controllers.