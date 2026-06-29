# jdk-25-jfr-flag-closed

Test behaviour of `-XX:StartFlightRecording` with `filename=/path/to/dir/` and `duration=n`.

Prior to JDK 24 this would result in the recording transitioning to `STOPPED` and remaining visible to `jdk.jfr.FlightRecorder` and `FlightRecorderMXBean`. In JDK 24+, including JDK 25 LTS, the recording is instead `CLOSED` and becomes inaccessible except for at the dump file within `/path/to/dir/`.

`./test.sh`
```
JVM version 25.0.3+9-LTS
Checking recording states:

	startup (1): RUNNING

.jfr files within /tmp:
	2026_06_29_13_56_00.jfr
========================

	startup (1): RUNNING

.jfr files within /tmp:
	2026_06_29_13_56_00.jfr
========================

	NONE
.jfr files within /tmp:
	hotspot-pid-1-id-1-2026_06_29_13_56_05.jfr
========================
```

----

`JAVA_VERSION=21 ./test.sh`
```
JVM version 21.0.11+10-LTS
Checking recording states:

	startup (1): RUNNING

.jfr files within /tmp:
	2026_06_29_13_56_36.jfr
========================

	startup (1): RUNNING

.jfr files within /tmp:
	2026_06_29_13_56_36.jfr
========================

	startup (1): STOPPED

.jfr files within /tmp:
	2026_06_29_13_56_36.jfr
========================
```
