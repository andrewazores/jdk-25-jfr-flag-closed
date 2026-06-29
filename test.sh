#!/bin/sh

set -e

DIR="$(dirname "$(readlink -f "$0")")"

IMG="${IMG:-jdk-25-jfr-flag}"

docker build -t "${IMG}" "${DIR}" -f "${DIR}/Dockerfile" --build-arg JAVA_VERSION="${JAVA_VERSION:-25}"
docker run --rm -it "${IMG}"
docker image prune -f
