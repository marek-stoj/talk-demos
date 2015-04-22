#!/bin/bash

set -e

hdfs dfs -test -d /demo/out2 && hdfs dfs -rm -r /demo/out2/

hadoop jar bin/WordCount.jar imm.SortWordCount /demo/out1 /demo/out2
