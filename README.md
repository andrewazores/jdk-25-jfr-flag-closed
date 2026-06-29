# jdk-25-jfr-flag-closed

Test behaviour of `-XX:StartFlightRecording` with `filename=/path/to/dir/` and `duration=n`.

Prior to JDK 24 this would result in the recording transitioning to `STOPPED` and remaining visible to `jdk.jfr.FlightRecorder` and `FlightRecorderMXBean`. In JDK 24+, including JDK 25 LTS, the recording is instead `CLOSED` and becomes inaccessible except for at the dump file within `/path/to/dir/`.

`./test.sh`
`JAVA_VERSION=21 ./test.sh`
