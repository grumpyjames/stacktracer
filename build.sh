mkdir build
javac -d build -source 1.6 src/main/java/net/digihippo/*.java src/main/java/net/digihippo/predicate/*.java src/main/java/net/digihippo/xform/*.java
cd build
jar cf stacktracer-0.0.1.jar *