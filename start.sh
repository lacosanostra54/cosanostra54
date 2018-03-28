#!/usr/bin/env bash
killall python3
nohup python3 src/main/python/InstagramApiServer.py &
killall java
mvn install
nohup mvn exec:java &
