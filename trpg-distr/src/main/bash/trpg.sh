#!/bin/bash
JAVA_VER=$(java -version 2>&1 | sed 's/java version "\(.*\)\.\(.*\)\..*"/\1\2/; 1q')

if [[ "$JAVA_VER" -ge "18" ]]; then
        java -jar ./game-engine-@Version@-jar-with-dependencies.jar $@
else
        echo "Error: Found $JAVA_VER."
        echo "Java 1.8 is required or newer"
fi