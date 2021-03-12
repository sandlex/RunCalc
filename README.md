[![Build Status](https://travis-ci.org/sandlex/runcalc.svg?branch=master)](https://travis-ci.org/sandlex/runcalc)
[![Coverage Status](http://img.shields.io/coveralls/sandlex/runcalc/master.svg?style=flat-square)](https://coveralls.io/r/sandlex/runcalc?branch=master)

### Jack Daniels' Running Formula calculator
Input parameters:
 - comma-separated string with values of paces in min/km of following format: **E=4:45,L=4:45,M=4:14,T=4:00,I=3:41,H=3:41,R=3:25,jg=5:00,rest=5:20**
 - training session schema in following formats:
  - template: **L = lesser of 15 miles (24 km) & 100 min**
  - flexible format, e.g.: **2E + 3 x 1T w/2 min rest + 3 x 3 min H w/2 min jg + 4 x 200 R w/200 jg + 1E**. Numbers less than 100 are given in miles (2E, 1T, etc.), larger than 100 are given in meters (200R, 400 jg, etc.)
 
Output:
 - training session duration in format: hh:mm:ss
 - training session distance in kilometers
