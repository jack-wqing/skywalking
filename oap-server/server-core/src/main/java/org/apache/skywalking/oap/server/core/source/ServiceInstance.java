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
 *
 */

package org.apache.skywalking.oap.server.core.source;

import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import org.apache.skywalking.oap.server.core.analysis.IDManager;
import org.apache.skywalking.oap.server.core.analysis.Layer;

import static org.apache.skywalking.oap.server.core.source.DefaultScopeDefine.SERVICE_INSTANCE;
import static org.apache.skywalking.oap.server.core.source.DefaultScopeDefine.SERVICE_INSTANCE_CATALOG_NAME;

//

@ScopeDeclaration(id = SERVICE_INSTANCE, name = "ServiceInstance", catalog = SERVICE_INSTANCE_CATALOG_NAME)
@ScopeDefaultColumn.VirtualColumnDefinition(fieldName = "entityId", columnName = "entity_id", isID = true, type = String.class)
public class ServiceInstance extends Source {
    private volatile String entityId;

    @Override
    public int scope() {
        return DefaultScopeDefine.SERVICE_INSTANCE;
    }

    @Override
    public String getEntityId() {
        if (entityId == null) {
            entityId = IDManager.ServiceInstanceID.buildId(serviceId, name);
        }
        return entityId;
    }

    @Getter
    @ScopeDefaultColumn.DefinedByField(columnName = "service_id")
    private String serviceId;
    @Getter
    @Setter
    @ScopeDefaultColumn.DefinedByField(columnName = "name", requireDynamicActive = true)
    private String name;
    @Getter
    @Setter
    @ScopeDefaultColumn.DefinedByField(columnName = "service_name", requireDynamicActive = true)
    private String serviceName;
    @Getter
    @Setter
    private Layer serviceLayer;
    @Getter
    @Setter
    private String endpointName;
    @Getter
    @Setter
    private int latency;
    @Getter
    @Setter
    private boolean status;
    @Getter
    @Setter
    private int httpResponseStatusCode;
    @Getter
    @Setter
    private String rpcStatusCode;
    @Getter
    @Setter
    private RequestType type;
    @Getter
    @Setter
    private List<String> tags;
    @Setter
    private Map<String, String> originalTags;
    @Getter
    @Setter
    private SideCar sideCar = new SideCar();

    @Override
    public void prepare() {
        serviceId = IDManager.ServiceID.buildId(serviceName, serviceLayer.isNormal());
    }

    public String getTag(String key) {
        return originalTags.get(key);
    }
}
