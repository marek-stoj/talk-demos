#!/bin/bash

hdfs dfs -test -d /demo/in || hdfs dfs -mkdir /demo/in
hdfs dfs -put in/* /demo/in/
