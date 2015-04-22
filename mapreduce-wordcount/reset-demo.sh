#!/bin/bash

hdfs dfs -test -d /demo/in && hdfs dfs -rm -r /demo/in/
hdfs dfs -test -d /demo/out1/ && hdfs dfs -rm -r /demo/out1/
hdfs dfs -test -d /demo/out2/ &&  hdfs dfs -rm -r /demo/out2/
hdfs dfs -test -d /demo/out3/ && hdfs dfs -rm -r /demo/out3/
