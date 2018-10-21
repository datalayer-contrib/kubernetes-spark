#
# Licensed to the Apache Software Foundation (ASF) under one or more
# contributor license agreements.  See the NOTICE file distributed with
# this work for additional information regarding copyright ownership.
# The ASF licenses this file to You under the Apache License, Version 2.0
# (the "License"); you may not use this file except in compliance with
# the License.  You may obtain a copy of the License at
#
#    http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#

FROM spark-base

# If this docker file is being used in the context of building your images from a Spark distribution, the docker build
# command should be invoked from the top level directory of the Spark distribution. E.g.:
# docker build -t spark-driver-py:latest -f dockerfiles/driver-py/Dockerfile .

ADD examples /opt/spark/examples
ADD python /opt/spark/python

RUN apk add --no-cache python3 && \
    python3 -m ensurepip && \
    rm -r /usr/lib/python*/ensurepip && \
    pip3 install --upgrade pip setuptools && \
    rm -r /root/.cache

RUN apk add --update alpine-sdk python3-dev

# RUN pip3 install dev
RUN pip3 install numpy
RUN pip3 install py4j
RUN apk add --no-cache freetype freetype-dev libjpeg-turbo-dev libpng-dev
RUN pip3 install matplotlib
RUN pip3 install bokeh
RUN pip3 install boto3
# RUN pip3 install ipython
RUN pip3 install pandas
RUN pip3 install pandasql

RUN apk add --no-cache libstdc++ lapack-dev && \
    apk add --no-cache \
        --virtual=.build-dependencies \
        g++ gfortran musl-dev \
        python3-dev && \
    ln -s locale.h /usr/include/xlocale.h && \
    pip3 install numpy && \
    pip3 install pandas && \
    pip3 install scipy && \
    pip3 install scikit-learn

# RUN apk add --no-cache gcc gfortran build-base wget freetype-dev libpng-dev openblas-dev
# RUN ln -s /usr/include/locale.h /usr/include/xlocale.h
RUN pip3 install scikit-learn
# ENV PATH /opt/rh/python34/root/usr/bin:$PATH
# RUN echo "/opt/rh/python34/root/usr/lib64" >> /etc/ld.so.conf
# RUN ldconfig

RUN find /usr/lib/python3.*/ -name 'tests' -exec rm -r '{}' + && \
    rm /usr/include/xlocale.h && \
    rm -r /root/.cache && \
    apk del .build-dependencies

ENV PYTHON_VERSION 3.6.1
ENV PYSPARK_PYTHON /usr/bin/python3
ENV PYSPARK_DRIVER_PYTHON /usr/bin/python3
ENV PYTHONPATH ${SPARK_HOME}/python/:${SPARK_HOME}/python/lib/py4j-0.10.4-src.zip:${PYTHONPATH}

CMD SPARK_CLASSPATH="${SPARK_HOME}/jars/*" && \
    env | grep SPARK_JAVA_OPT_ | sed 's/[^=]*=\(.*\)/\1/g' > /tmp/java_opts.txt && \
    readarray -t SPARK_DRIVER_JAVA_OPTS < /tmp/java_opts.txt && \
    if ! [ -z ${SPARK_MOUNTED_CLASSPATH+x} ]; then SPARK_CLASSPATH="$SPARK_MOUNTED_CLASSPATH:$SPARK_CLASSPATH"; fi && \
    if ! [ -z ${SPARK_SUBMIT_EXTRA_CLASSPATH+x} ]; then SPARK_CLASSPATH="$SPARK_SUBMIT_EXTRA_CLASSPATH:$SPARK_CLASSPATH"; fi && \
    if ! [ -z ${SPARK_EXTRA_CLASSPATH+x} ]; then SPARK_CLASSPATH="$SPARK_EXTRA_CLASSPATH:$SPARK_CLASSPATH"; fi && \
    if ! [ -z ${HADOOP_CONF_DIR+x} ]; then SPARK_CLASSPATH="$HADOOP_CONF_DIR:$SPARK_CLASSPATH"; fi && \
    if ! [ -z ${SPARK_MOUNTED_FILES_DIR+x} ]; then cp -R "$SPARK_MOUNTED_FILES_DIR/." .; fi && \
    if ! [ -z ${SPARK_MOUNTED_FILES_FROM_SECRET_DIR+x} ]; then cp -R "$SPARK_MOUNTED_FILES_FROM_SECRET_DIR/." .; fi && \
    ${JAVA_HOME}/bin/java "${SPARK_DRIVER_JAVA_OPTS[@]}" -cp "$SPARK_CLASSPATH" -Xms$SPARK_DRIVER_MEMORY -Xmx$SPARK_DRIVER_MEMORY -Dspark.driver.bindAddress=$SPARK_DRIVER_BIND_ADDRESS $SPARK_DRIVER_CLASS $PYSPARK_PRIMARY $PYSPARK_FILES $SPARK_DRIVER_ARGS
