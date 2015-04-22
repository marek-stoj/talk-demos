#!/bin/bash

set -e

hdfs dfs -test -d /demo/out1 && hdfs dfs -rm -r /demo/out1/

hadoop jar bin/WordCount.jar imm.WordCount /demo/in /demo/out1
