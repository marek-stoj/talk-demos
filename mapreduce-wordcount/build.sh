#!/bin/bash

set -e

rm -f bin/WordCount.jar

javac src/imm/*.java -cp "/usr/hdp/2.2.0.0-2041/hadoop/*:/usr/hdp/2.2.0.0-2041/hadoop-mapreduce/*"

cd src
jar cvf ../bin/WordCount.jar imm/*.class
cd ..
