#!/bin/bash

hdfs dfs -test -d /demo/out3 && hdfs dfs -rm -r /demo/out3/

pig word-count.pig
