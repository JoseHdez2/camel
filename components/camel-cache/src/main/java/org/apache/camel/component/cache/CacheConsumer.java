/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.camel.component.cache;

import org.apache.camel.Endpoint;
import org.apache.camel.Processor;
import org.apache.camel.impl.DefaultConsumer;

public class CacheConsumer extends DefaultConsumer {

    private CacheConfiguration config;

    public CacheConsumer(Endpoint endpoint, Processor processor, CacheConfiguration config) {
        super(endpoint, processor);
        this.config = config;
    }

    @Override
    protected void doStart() throws Exception {
        super.doStart();
        createConsumerCacheConnection();
    }

    @Override
    public CacheEndpoint getEndpoint() {
        return (CacheEndpoint) super.getEndpoint();
    }

    protected void createConsumerCacheConnection() {
        CacheEventListener cacheEventListener = new CacheEventListener();
        cacheEventListener.setCacheConsumer(this);

        config.getEventListenerRegistry().addCacheEventListener(cacheEventListener);

        getEndpoint().initializeCache();
    }

}
