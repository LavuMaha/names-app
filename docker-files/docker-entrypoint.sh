#!/bin/bash

# Available variables from Dockerfile
# COMPONENT_NAME

# Available variables from Conducktor
# EXTRA_JAVA_OPTIONS, SPRING_PROFILES_ACTIVE

JAVA_OPTIONS="-server"
JAVA_OPTIONS="${JAVA_OPTIONS} -XX:+HeapDumpOnOutOfMemoryError"
#JAVA_OPTIONS="${JAVA_OPTIONS} -XX:HeapDumpPath=${MESOS_SANDBOX}"
JAVA_OPTIONS="${JAVA_OPTIONS} -Djava.net.preferIPv4Stack=true"
# Add some fields for better logs aggregation
JAVA_OPTIONS="${JAVA_OPTIONS} -Dmarathon.appIdx=${COMPONENT_NAME}"

SPRING_OPS=""
if [ ! -z "${SPRING_PROFILES_ACTIVE}" ]; then
  SPRING_OPS="${SPRING_OPS} -Dspring.profiles.active=${SPRING_PROFILES_ACTIVE}"
fi

# Not used at the moment
# It doesn't cooperate as nice as spring.profiles.active
if [ ! -z "${SPRING_PROFILES_INCLUDE}" ]; then
  SPRING_OPS="${SPRING_OPS} -Dspring.profiles.include=${SPRING_PROFILES_INCLUDE}"
fi

if [ "${DEBUG}" == "true" ]; then
  JAVA_OPTIONS="$JAVA_OPTIONS -Dlog4j.configurationFile=./log4j-debug.xml"
else
  JAVA_OPTIONS="$JAVA_OPTIONS -Dlog4j.configurationFile=./log4j-info.xml"
fi

echo ""
echo "- Starting ${COMPONENT_NAME}"
echo "  - JAVA Options: ${JAVA_OPTIONS}"
echo "  - JAVA Extra Options: ${EXTRA_JAVA_OPTIONS}"
echo "  - SPRING Options: ${SPRING_OPS}"

java ${JAVA_OPTIONS}${SPRING_OPS} ${EXTRA_JAVA_OPTIONS} -jar tmobile-${COMPONENT_NAME}.jar
