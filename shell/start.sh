#!/bin/sh
JAR_FILE=shell-execute-demo-0.0.1-SNAPSHOT.jar

nohup java -jar $JAR_FILE >/dev/null 2>&1 &