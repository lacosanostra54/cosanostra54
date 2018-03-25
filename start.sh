#!/usr/bin/env bash
nohup mvn exec:java &
nohup python3 src/main/python/InstagramApiServer.py &
