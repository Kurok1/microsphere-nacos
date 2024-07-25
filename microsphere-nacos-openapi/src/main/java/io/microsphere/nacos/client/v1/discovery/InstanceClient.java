/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.microsphere.nacos.client.v1.discovery;

import io.microsphere.nacos.client.common.discovery.ConsistencyType;
import io.microsphere.nacos.client.common.discovery.model.BatchMetadataResult;
import io.microsphere.nacos.client.common.discovery.model.DeleteInstance;
import io.microsphere.nacos.client.common.discovery.model.Instance;
import io.microsphere.nacos.client.common.discovery.model.InstancesList;
import io.microsphere.nacos.client.common.discovery.model.NewInstance;
import io.microsphere.nacos.client.common.discovery.model.QueryInstance;
import io.microsphere.nacos.client.common.discovery.model.Service;
import io.microsphere.nacos.client.common.discovery.model.UpdateHealthInstance;
import io.microsphere.nacos.client.common.discovery.model.UpdateInstance;
import io.microsphere.nacos.client.v1.namespace.model.Namespace;

import java.util.Map;
import java.util.Set;

import static io.microsphere.nacos.client.common.discovery.ConsistencyType.EPHEMERAL;

/**
 * The Client for Nacos {@link Service} {@link Instance} Open API V1
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy<a/>
 * @see InstancesList
 * @see Instance
 * @see Service
 * @see ServiceClient
 * @since 1.0.0
 */
public interface InstanceClient {

    /**
     * Register {@link NewInstance a new instance} with parameters :
     * <table>
     * <thead>
     * <tr>
     * <th>Name</th>
     * <th>Type</th>
     * <th>Required</th>
     * <th>Description</th>
     * </tr>
     * </thead>
     * <tbody>
     * <tr>
     * <td>ip</td>
     * <td>String</td>
     * <td>yes</td>
     * <td>IP of instance</td>
     * </tr>
     * <tr>
     * <td>port</td>
     * <td>int</td>
     * <td>yes</td>
     * <td>Port of instance</td>
     * </tr>
     * <tr>
     * <td>namespaceId</td>
     * <td>String</td>
     * <td>no</td>
     * <td>ID of namespace</td>
     * </tr>
     * <tr>
     * <td>weight</td>
     * <td>double</td>
     * <td>no</td>
     * <td>Weight</td>
     * </tr>
     * <tr>
     * <td>enabled</td>
     * <td>boolean</td>
     * <td>no</td>
     * <td>enabled or not</td>
     * </tr>
     * <tr>
     * <td>healthy</td>
     * <td>boolean</td>
     * <td>no</td>
     * <td>healthy or not</td>
     * </tr>
     * <tr>
     * <td>metadata</td>
     * <td>String</td>
     * <td>no</td>
     * <td>extended information</td>
     * </tr>
     * <tr>
     * <td>clusterName</td>
     * <td>String</td>
     * <td>no</td>
     * <td>cluster name</td>
     * </tr>
     * <tr>
     * <td>serviceName</td>
     * <td>String</td>
     * <td>yes</td>
     * <td>service name</td>
     * </tr>
     * <tr>
     * <td>groupName</td>
     * <td>String</td>
     * <td>no</td>
     * <td>group name</td>
     * </tr>
     * <tr>
     * <td>ephemeral</td>
     * <td>boolean</td>
     * <td>no</td>
     * <td>if instance is ephemeral</td>
     * </tr>
     * </tbody>
     * </table>
     *
     * @param newInstance {@link NewInstance a new instance}
     * @return <code>true</code> if register successfully, otherwise <code>false</code>
     */
    boolean register(NewInstance newInstance);

    /**
     * Deregister a {@link DeleteInstance} with parameters :
     * <table>
     * <thead>
     * <tr>
     * <th>Name</th>
     * <th>Type</th>
     * <th>Required</th>
     * <th>Description</th>
     * </tr>
     * </thead>
     * <tbody>
     * <tr>
     * <td>serviceName</td>
     * <td>String</td>
     * <td>yes</td>
     * <td>Service name</td>
     * </tr>
     * <tr>
     * <td>groupName</td>
     * <td>String</td>
     * <td>no</td>
     * <td>group name</td>
     * </tr>
     * <tr>
     * <td>ephemeral</td>
     * <td>boolean</td>
     * <td>no</td>
     * <td>if instance is ephemeral</td>
     * </tr>
     * <tr>
     * <td>ip</td>
     * <td>String</td>
     * <td>yes</td>
     * <td>IP of instance</td>
     * </tr>
     * <tr>
     * <td>port</td>
     * <td>int</td>
     * <td>yes</td>
     * <td>Port of instance</td>
     * </tr>
     * <tr>
     * <td>clusterName</td>
     * <td>String</td>
     * <td>no</td>
     * <td>Cluster name</td>
     * </tr>
     * <tr>
     * <td>namespaceId</td>
     * <td>String</td>
     * <td>no</td>
     * <td>ID of namespace</td>
     * </tr>
     * </tbody>
     * </table>
     *
     * @param deleteInstance {@link DeleteInstance an instance to be deregistered}
     * @return <code>true</code> if deregister successfully, otherwise <code>false</code>
     */
    boolean deregister(DeleteInstance deleteInstance);

    /**
     * Refresh the registered {@link UpdateInstance} with parameters:
     * <table>
     * <thead>
     * <tr>
     * <th>Name</th>
     * <th>Type</th>
     * <th>Required</th>
     * <th>Description</th>
     * </tr>
     * </thead>
     * <tbody>
     * <tr>
     * <td>serviceName</td>
     * <td>String</td>
     * <td>yes</td>
     * <td>Service name</td>
     * </tr>
     * <tr>
     * <td>groupName</td>
     * <td>String</td>
     * <td>no</td>
     * <td>group name</td>
     * </tr>
     * <tr>
     * <td>ephemeral</td>
     * <td>boolean</td>
     * <td>no</td>
     * <td>if instance is ephemeral</td>
     * </tr>
     * <tr>
     * <td>ip</td>
     * <td>String</td>
     * <td>yes</td>
     * <td>IP of instance</td>
     * </tr>
     * <tr>
     * <td>port</td>
     * <td>int</td>
     * <td>yes</td>
     * <td>Port of instance</td>
     * </tr>
     * <tr>
     * <td>clusterName</td>
     * <td>String</td>
     * <td>no</td>
     * <td>Cluster name</td>
     * </tr>
     * <tr>
     * <td>namespaceId</td>
     * <td>String</td>
     * <td>no</td>
     * <td>ID of namespace</td>
     * </tr>
     * <tr>
     * <td>weight</td>
     * <td>double</td>
     * <td>no</td>
     * <td>Weight</td>
     * </tr>
     * <tr>
     * <td>enabled</td>
     * <td>boolean</td>
     * <td>no</td>
     * <td>If enabled</td>
     * </tr>
     * <tr>
     * <td>metadata</td>
     * <td>JSON</td>
     * <td>no</td>
     * <td>Extended information</td>
     * </tr>
     * </tbody>
     * </table>
     *
     * @param updateInstance {@link UpdateInstance an instance to be refreshed}
     * @return <code>true</code> if refresh successfully, otherwise <code>false</code>
     */
    boolean refresh(UpdateInstance updateInstance);

    /**
     * Get the {@link InstancesList} of the specified namespaceId and serviceName
     *
     * @param namespaceId the id of {@link Namespace}
     * @param serviceName the name of {@link Service}
     * @return non-null {@link InstancesList}
     */
    default InstancesList getInstancesList(String namespaceId, String serviceName) {
        return getInstancesList(namespaceId, null, serviceName);
    }

    /**
     * Get the {@link InstancesList} of the specified namespaceId, groupName and serviceName
     *
     * @param namespaceId the id of {@link Namespace}
     * @param groupName   the name of group (optional)
     * @param serviceName the name of {@link Service}
     * @return non-null {@link InstancesList}
     */
    default InstancesList getInstancesList(String namespaceId, String groupName, String serviceName) {
        return getInstancesList(namespaceId, groupName, serviceName, null);
    }

    /**
     * Get the {@link InstancesList} of the specified namespaceId, groupName, serviceName and clusters
     *
     * @param namespaceId the id of {@link Namespace}
     * @param groupName   the name of group (optional)
     * @param serviceName the name of {@link Service}
     * @param clusters    the clusters (optional)
     * @return non-null {@link InstancesList}
     */
    default InstancesList getInstancesList(String namespaceId, String groupName, String serviceName, Set<String> clusters) {
        return getInstancesList(namespaceId, groupName, serviceName, clusters, false);
    }

    /**
     * Get the {@link InstancesList} of the specified namespaceId, groupName, serviceName, clusters and healthyOnly
     *
     * @param namespaceId the id of {@link Namespace}
     * @param groupName   the name of group (optional)
     * @param serviceName the name of {@link Service}
     * @param clusters    the clusters (optional)
     * @param healthyOnly the healthy only
     * @return non-null {@link InstancesList}
     */
    InstancesList getInstancesList(String namespaceId, String groupName, String serviceName, Set<String> clusters, boolean healthyOnly);

    /**
     * Get the {@link Instance} of the specified {@link QueryInstance} with parameters :
     * <table>
     * <thead>
     * <tr>
     * <th>Name</th>
     * <th>Type</th>
     * <th>Required</th>
     * <th>Description</th>
     * </tr>
     * </thead>
     * <tbody>
     * <tr>
     * <td>namespaceId</td>
     * <td>String</td>
     * <td>no</td>
     * <td>ID of namespace</td>
     * </tr>
     * <tr>
     * <td>serviceName</td>
     * <td>String</td>
     * <td>yes</td>
     * <td>Service name</td>
     * </tr>
     * <tr>
     * <td>groupName</td>
     * <td>String</td>
     * <td>no</td>
     * <td>group name</td>
     * </tr>
     * <tr>
     * <td>ephemeral</td>
     * <td>boolean</td>
     * <td>no</td>
     * <td>if instance is ephemeral</td>
     * </tr>
     * <tr>
     * <td>ip</td>
     * <td>String</td>
     * <td>yes</td>
     * <td>IP of instance</td>
     * </tr>
     * <tr>
     * <td>port</td>
     * <td>String</td>
     * <td>yes</td>
     * <td>Port of instance</td>
     * </tr>
     * <tr>
     * <td>cluster</td>
     * <td>String</td>
     * <td>no</td>
     * <td>Cluster name</td>
     * </tr>
     * </tbody>
     * </table>
     *
     * @param queryInstance {@link QueryInstance}
     * @return non-null {@link Instance}
     */
    Instance getInstance(QueryInstance queryInstance);

    /**
     * Send {@link Instance Instance's} Heartbeat to Nacos Server
     *
     * @param instance {@link Instance}
     * @return <code>true</code> if send successfully, otherwise <code>false</code>
     */
    boolean sendHeartbeat(Instance instance);

    /**
     * {@link UpdateHealthInstance Update Instances' Health}
     *
     * @param updateHealthInstance {@link UpdateHealthInstance}
     * @return <code>true</code> if update successfully, otherwise <code>false</code>
     */
    boolean updateHealth(UpdateHealthInstance updateHealthInstance);

    /**
     * Batch Update Service Instances' Metadata
     *
     * @param instances one or more {@link Instance instances}
     * @param metadata  Service Instances' Metadata
     * @return non-null {@link BatchMetadataResult}
     * @since Nacos 1.4.x (Beta)
     */
    default BatchMetadataResult batchUpdateMetadata(Iterable<Instance> instances, Map<String, String> metadata) {
        return batchUpdateMetadata(instances, metadata, EPHEMERAL);
    }

    /**
     * Batch Update Service Instances' Metadata
     *
     * @param instances       one or more {@link Instance instances}
     * @param metadata        Service Instances' Metadata
     * @param consistencyType {@link ConsistencyType}
     * @return non-null  {@link BatchMetadataResult}
     * @since Nacos 1.4.x (Beta)
     */
    BatchMetadataResult batchUpdateMetadata(Iterable<Instance> instances, Map<String, String> metadata, ConsistencyType consistencyType);

    /**
     * Batch Delete Service Instances' Metadata
     *
     * @param instances one or more {@link Instance instances}
     * @param metadata  Service Instances' Metadata
     * @return non-null {@link BatchMetadataResult}
     * @since Nacos 1.4.x (Beta)
     */
    default BatchMetadataResult batchDeleteMetadata(Iterable<Instance> instances, Map<String, String> metadata) {
        return batchDeleteMetadata(instances, metadata, EPHEMERAL);
    }

    /**
     * Batch Delete Service Instances' Metadata
     *
     * @param instances       one or more {@link Instance instances}
     * @param metadata        Service Instances' Metadata
     * @param consistencyType {@link ConsistencyType}
     * @return non-null {@link BatchMetadataResult}
     * @since Nacos 1.4.x (Beta)
     */
    BatchMetadataResult batchDeleteMetadata(Iterable<Instance> instances, Map<String, String> metadata, ConsistencyType consistencyType);
}
