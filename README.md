[![Build Status](https://travis-ci.org/sandlex/runcalc.svg?branch=master)](https://travis-ci.org/sandlex/runcalc)
[![Coverage Status](http://img.shields.io/coveralls/sandlex/runcalc/master.svg?style=flat-square)](https://coveralls.io/r/sandlex/runcalc?branch=master)

### Running workout calculator
Calculates estimated distance and completion time of pace based workouts.

Imagine you have to do a training according to the following schema:
**15 minutes warming up, then 3 kilometers in 10 km pace, then 5 times of 400 meters in 5 km pace followed by 30 seconds rest, after 5 repetitions do 1.5 km in easy pace and then 1.5 hours in marathon pace.**

To calculate this program should get two input parameters:
1. pace block: _WU=5:00,T10=3:40,E=4:30,T5=3:30,Rest=10:00,M=4:00_
2. workout schema: _15:00WU + 3T10 + 1.5E + 5 * (0.4T5 + 00:30Rest) + 1.5E + 1:30:00M_

After calculation result can be used in following format:
1. distance in kilometers (or miles): _33.00_
2. time as number of seconds: _8340_
3. time as formatted string: _02:19:00_
4. everything together as a string in the format: _Estimated distance - 33.00, time - 02:19:00_

Notes:
* Pace name can't start with a number: T10 - ok, 10T - not ok
* Nested repetitions are not supported
* Time format - hh:mm:ss or mm:ss
* If distance is given in miles then pace is assumed to be in min/mile. If distance is in kilometers then pace is in min/km
* Mind the difference between imperial and metric systems when working with short intervals (fraction of kilometers/miles): 0.4 is 400 meters in metric system. In imperial system 0.4 will have a different meaning

### Usage
#### As a library
* add jar file to the classpath

```java
Estimation estimation = Calculator.getEstimation("WU=5:00,T10=3:40,E=4:30,T5=3:30,Rest=10:00,M=4:00", "15:00WU + 3T10 + 1.5E + 5 * (0.4T5 + 00:30Rest) + 1.5E + 1:30:00M");

double distance = estimation.getDistance();
long seconds = estimation.getSeconds();
String time = estimation.getFormattedTime();
String result = estimation.toString();
```

#### As a java app
`java -jar runcalc-xxx.jar`
with two arguments:
1. _WU=5:00,T10=3:40,E=4:30,T5=3:30,Rest=10:00,M=4:00_
2. _15:00WU + 3T10 + 1.5E + 5 * (0.4T5 + 00:30Rest) + 1.5E + 1:30:00M_
