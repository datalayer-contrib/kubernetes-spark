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

import java.io.File

import scala.collection.JavaConverters._

import io.fabric8.kubernetes.api.model.{ContainerBuilder, KeyToPathBuilder, PodBuilder}

import org.apache.spark.deploy.k8s.constants._
import org.apache.spark.internal.Logging

/**
 * This is separated out from the HadoopConf steps API because this component can be reused to
 * set up the Hadoop Configuration for executors as well.
 */
private[spark] trait HadoopConfBootstrap {
 /**
  * Bootstraps a main container with the ConfigMaps containing Hadoop config files
  * mounted as volumes and an ENV variable pointing to the mounted file.
  */
  def bootstrapMainContainerAndVolumes(originalPodWithMainContainer: PodWithMainContainer)
    : PodWithMainContainer
}

private[spark] class HadoopConfBootstrapImpl(
  hadoopConfConfigMapName: String,
  hadoopConfigFiles: Seq[File]) extends HadoopConfBootstrap with Logging {

  override def bootstrapMainContainerAndVolumes(originalPodWithMainContainer: PodWithMainContainer)
    : PodWithMainContainer = {
    logInfo("HADOOP_CONF_DIR defined. Mounting Hadoop specific files")
    val keyPaths = hadoopConfigFiles.map { file =>
      val fileStringPath = file.toPath.getFileName.toString
      new KeyToPathBuilder()
        .withKey(fileStringPath)
        .withPath(fileStringPath)
      .build() }
    val hadoopSupportedPod = new PodBuilder(originalPodWithMainContainer.pod)
      .editSpec()
        .addNewVolume()
          .withName(HADOOP_FILE_VOLUME)
          .withNewConfigMap()
            .withName(hadoopConfConfigMapName)
            .withItems(keyPaths.asJava)
            .endConfigMap()
          .endVolume()
        .endSpec()
      .build()
    val hadoopSupportedContainer = new ContainerBuilder(
      originalPodWithMainContainer.mainContainer)
      .addNewVolumeMount()
        .withName(HADOOP_FILE_VOLUME)
        .withMountPath(HADOOP_CONF_DIR_PATH)
        .endVolumeMount()
      .addNewEnv()
        .withName(ENV_HADOOP_CONF_DIR)
        .withValue(HADOOP_CONF_DIR_PATH)
        .endEnv()
      .build()

    originalPodWithMainContainer.copy(
      pod = hadoopSupportedPod,
      mainContainer = hadoopSupportedContainer)
  }
}
