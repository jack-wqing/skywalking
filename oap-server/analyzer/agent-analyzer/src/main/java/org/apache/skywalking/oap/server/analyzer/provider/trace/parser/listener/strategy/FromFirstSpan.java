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

package org.apache.skywalking.oap.server.analyzer.provider.trace.parser.listener.strategy;

import org.apache.skywalking.apm.network.language.agent.v3.SpanObject;

/**
 * FromFirstSpan means the status of the segment is the same as the status of the first span. Mostly, the first span is
 * an entry span. However, a tracing caused by a scheduled task, the first one should be a local span.
 */

// from first span

public class FromFirstSpan implements SegmentStatusAnalyzer {

    @Override
    public boolean isError(final SpanObject spanObject) {
        return spanObject.getSpanId() == 0 && spanObject.getIsError();
    }
}
