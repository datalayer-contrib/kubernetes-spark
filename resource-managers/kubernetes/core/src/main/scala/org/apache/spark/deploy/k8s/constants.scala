/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.spark.deploy.k8s

package object constants {
  // Labels
  private[spark] val SPARK_DRIVER_LABEL = "spark-driver"
  private[spark] val SPARK_APP_ID_LABEL = "spark-app-selector"
  private[spark] val SPARK_EXECUTOR_ID_LABEL = "spark-exec-id"
  private[spark] val SPARK_ROLE_LABEL = "spark-role"
  private[spark] val SPARK_POD_DRIVER_ROLE = "driver"
  private[spark] val SPARK_POD_EXECUTOR_ROLE = "executor"
  private[spark] val SPARK_APP_NAME_ANNOTATION = "spark-app-name"

  // Credentials secrets
  private[spark] val DRIVER_CREDENTIALS_SECRETS_BASE_DIR =
    "/mnt/secrets/spark-kubernetes-credentials"
  private[spark] val DRIVER_CREDENTIALS_CA_CERT_SECRET_NAME = "ca-cert"
  private[spark] val DRIVER_CREDENTIALS_CA_CERT_PATH =
    s"$DRIVER_CREDENTIALS_SECRETS_BASE_DIR/$DRIVER_CREDENTIALS_CA_CERT_SECRET_NAME"
  private[spark] val DRIVER_CREDENTIALS_CLIENT_KEY_SECRET_NAME = "client-key"
  private[spark] val DRIVER_CREDENTIALS_CLIENT_KEY_PATH =
    s"$DRIVER_CREDENTIALS_SECRETS_BASE_DIR/$DRIVER_CREDENTIALS_CLIENT_KEY_SECRET_NAME"
  private[spark] val DRIVER_CREDENTIALS_CLIENT_CERT_SECRET_NAME = "client-cert"
  private[spark] val DRIVER_CREDENTIALS_CLIENT_CERT_PATH =
    s"$DRIVER_CREDENTIALS_SECRETS_BASE_DIR/$DRIVER_CREDENTIALS_CLIENT_CERT_SECRET_NAME"
  private[spark] val DRIVER_CREDENTIALS_OAUTH_TOKEN_SECRET_NAME = "oauth-token"
  private[spark] val DRIVER_CREDENTIALS_OAUTH_TOKEN_PATH =
    s"$DRIVER_CREDENTIALS_SECRETS_BASE_DIR/$DRIVER_CREDENTIALS_OAUTH_TOKEN_SECRET_NAME"
  private[spark] val DRIVER_CREDENTIALS_SECRET_VOLUME_NAME = "kubernetes-credentials"

  // Hadoop credentials secrets for the Spark app.
  private[spark] val SPARK_APP_HADOOP_CREDENTIALS_BASE_DIR = "/mnt/secrets/hadoop-credentials"
  private[spark] val SPARK_APP_HADOOP_SECRET_VOLUME_NAME = "hadoop-secret"

  // Default and fixed ports
  private[spark] val DEFAULT_DRIVER_PORT = 7078
  private[spark] val DEFAULT_BLOCKMANAGER_PORT = 7079
  private[spark] val DEFAULT_UI_PORT = 4040
  private[spark] val BLOCK_MANAGER_PORT_NAME = "blockmanager"
  private[spark] val DRIVER_PORT_NAME = "driver-rpc-port"
  private[spark] val EXECUTOR_PORT_NAME = "executor"

  // Environment Variables
  private[spark] val ENV_EXECUTOR_PORT = "SPARK_EXECUTOR_PORT"
  private[spark] val ENV_DRIVER_URL = "SPARK_DRIVER_URL"
  private[spark] val ENV_DRIVER_BIND_ADDRESS = "SPARK_DRIVER_BIND_ADDRESS"
  private[spark] val ENV_EXECUTOR_CORES = "SPARK_EXECUTOR_CORES"
  private[spark] val ENV_EXECUTOR_MEMORY = "SPARK_EXECUTOR_MEMORY"
  private[spark] val ENV_APPLICATION_ID = "SPARK_APPLICATION_ID"
  private[spark] val ENV_EXECUTOR_ID = "SPARK_EXECUTOR_ID"
  private[spark] val ENV_EXECUTOR_POD_IP = "SPARK_EXECUTOR_POD_IP"
  private[spark] val ENV_DRIVER_MEMORY = "SPARK_DRIVER_MEMORY"
  private[spark] val ENV_SUBMIT_EXTRA_CLASSPATH = "SPARK_SUBMIT_EXTRA_CLASSPATH"
  private[spark] val ENV_EXECUTOR_EXTRA_CLASSPATH = "SPARK_EXECUTOR_EXTRA_CLASSPATH"
  private[spark] val ENV_MOUNTED_CLASSPATH = "SPARK_MOUNTED_CLASSPATH"
  private[spark] val ENV_DRIVER_MAIN_CLASS = "SPARK_DRIVER_CLASS"
  private[spark] val ENV_DRIVER_ARGS = "SPARK_DRIVER_ARGS"
  private[spark] val ENV_DRIVER_JAVA_OPTS = "SPARK_DRIVER_JAVA_OPTS"
  private[spark] val ENV_MOUNTED_FILES_DIR = "SPARK_MOUNTED_FILES_DIR"
  private[spark] val ENV_PYSPARK_FILES = "PYSPARK_FILES"
  private[spark] val ENV_PYSPARK_PRIMARY = "PYSPARK_PRIMARY"
  private[spark] val ENV_R_FILE = "R_FILE"
  private[spark] val ENV_JAVA_OPT_PREFIX = "SPARK_JAVA_OPT_"
  private[spark] val ENV_MOUNTED_FILES_FROM_SECRET_DIR = "SPARK_MOUNTED_FILES_FROM_SECRET_DIR"
  private[spark] val ENV_HADOOP_TOKEN_FILE_LOCATION = "HADOOP_TOKEN_FILE_LOCATION"
  private[spark] val ENV_SPARK_USER = "SPARK_USER"

  // Bootstrapping dependencies with the init-container
  private[spark] val INIT_CONTAINER_SECRET_VOLUME_MOUNT_PATH =
    "/mnt/secrets/spark-init"
  private[spark] val INIT_CONTAINER_SUBMITTED_JARS_SECRET_KEY =
    "downloadSubmittedJarsSecret"
  private[spark] val INIT_CONTAINER_SUBMITTED_FILES_SECRET_KEY =
    "downloadSubmittedFilesSecret"
  private[spark] val INIT_CONTAINER_STAGING_SERVER_TRUSTSTORE_SECRET_KEY = "trustStore"
  private[spark] val INIT_CONTAINER_STAGING_SERVER_CLIENT_CERT_SECRET_KEY = "ssl-certificate"
  private[spark] val INIT_CONTAINER_CONFIG_MAP_KEY = "download-submitted-files"
  private[spark] val INIT_CONTAINER_DOWNLOAD_JARS_VOLUME_NAME = "download-jars-volume"
  private[spark] val INIT_CONTAINER_DOWNLOAD_FILES_VOLUME_NAME = "download-files"
  private[spark] val INIT_CONTAINER_PROPERTIES_FILE_VOLUME = "spark-init-properties"
  private[spark] val INIT_CONTAINER_PROPERTIES_FILE_DIR = "/etc/spark-init"
  private[spark] val INIT_CONTAINER_PROPERTIES_FILE_NAME = "spark-init.properties"
  private[spark] val INIT_CONTAINER_PROPERTIES_FILE_PATH =
    s"$INIT_CONTAINER_PROPERTIES_FILE_DIR/$INIT_CONTAINER_PROPERTIES_FILE_NAME"
  private[spark] val DEFAULT_SHUFFLE_MOUNT_NAME = "shuffle"
  private[spark] val INIT_CONTAINER_SECRET_VOLUME_NAME = "spark-init-secret"

  // Hadoop Configuration
  private[spark] val HADOOP_FILE_VOLUME = "hadoop-properties"
  private[spark] val HADOOP_CONF_DIR_PATH = "/etc/hadoop/conf"
  private[spark] val ENV_HADOOP_CONF_DIR = "HADOOP_CONF_DIR"
  private[spark] val HADOOP_CONF_DIR_LOC = "spark.kubernetes.hadoop.conf.dir"
  private[spark] val HADOOP_CONFIG_MAP_SPARK_CONF_NAME =
    "spark.kubernetes.hadoop.executor.hadoopConfigMapName"

  // Kerberos Configuration
  private[spark] val KERBEROS_DELEGEGATION_TOKEN_SECRET_NAME =
    "spark.kubernetes.kerberos.delegationTokenSecretName"
  private[spark] val KERBEROS_KEYTAB_SECRET_NAME =
    "spark.kubernetes.kerberos.keyTabSecretName"
  private[spark] val KERBEROS_KEYTAB_SECRET_KEY =
    "spark.kubernetes.kerberos.keyTabSecretKey"
  private[spark] val KERBEROS_SECRET_LABEL_PREFIX =
    "hadoop-tokens"
  private[spark] val SPARK_HADOOP_PREFIX = "spark.hadoop."
  private[spark] val HADOOP_SECURITY_AUTHENTICATION =
    SPARK_HADOOP_PREFIX + "hadoop.security.authentication"

  // Kerberos Token-Refresh Server
  private[spark] val KERBEROS_REFRESH_LABEL_KEY = "refresh-hadoop-tokens"
  private[spark] val KERBEROS_REFRESH_LABEL_VALUE = "yes"

  // Bootstrapping dependencies via a secret
  private[spark] val MOUNTED_SMALL_FILES_SECRET_MOUNT_PATH = "/etc/spark-submitted-files"

  // Miscellaneous
  private[spark] val ANNOTATION_EXECUTOR_NODE_AFFINITY = "scheduler.alpha.kubernetes.io/affinity"
  private[spark] val DRIVER_CONTAINER_NAME = "spark-kubernetes-driver"
  private[spark] val KUBERNETES_MASTER_INTERNAL_URL = "https://kubernetes.default.svc"
  private[spark] val MEMORY_OVERHEAD_FACTOR = 0.10
  private[spark] val MEMORY_OVERHEAD_MIN_MIB = 384L
  private[spark] val GENERATED_LOCAL_DIR_MOUNT_ROOT = "/mnt/tmp/spark-local"
}
