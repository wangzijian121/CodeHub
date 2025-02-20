#!/bin/bash

PID=$(ps -ef | grep 'com.zjyun.springboot.Application' | grep -v grep | awk '{print $2}')

if [ -z "$PID" ]; then
    echo "No Application process found to stop"
    exit 1
fi

echo "Stopping Application process $PID"
kill $PID

exit 0
