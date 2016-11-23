#!/bin/sh

WEIBO4J_PATH=./lib/*:

java -cp ${WEIBO4J_PATH} org.ksoong.weibo4j.publisher.Publisher "$@" 
