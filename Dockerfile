ARG JAVA_VERSION

FROM registry.access.redhat.com/ubi9/openjdk-$JAVA_VERSION:latest AS builder
WORKDIR /tmp
COPY Reproducer.java .
RUN javac Reproducer.java

FROM registry.access.redhat.com/ubi9/openjdk-$JAVA_VERSION-runtime:latest
WORKDIR /deployments/app
ENTRYPOINT ["/usr/bin/java"]
CMD ["Reproducer"]
ENV JAVA_TOOL_OPTIONS="-XX:StartFlightRecording=name=startup,settings=profile,duration=5s,filename=/tmp/,disk=true"
COPY --from=builder /tmp/Reproducer.class .
