/*
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
package org.apache.servicemix.jbi.deployer.events;

import java.util.EventObject;

import javax.jbi.management.LifeCycleMBean;

/**
 */
public class LifeCycleEvent extends EventObject {

    public enum LifeCycleEventType {
        Starting,
        Started,
        Stopping,
        Stopped,
        ShuttingDown,
        ShutDown
    }

    private final LifeCycleEventType type;
    private final boolean forced;

    public LifeCycleEvent(LifeCycleEventType type, LifeCycleMBean source, boolean forced) {
        super(source);
        this.type = type;
        this.forced = forced;
    }

    public LifeCycleEventType getType() {
        return type;
    }

    public LifeCycleMBean getLifeCycleMBean() {
        return (LifeCycleMBean) getSource();
    }

    public boolean isForced() {
        return forced;
    }

    public String toString() {
        String clz = getSource().getClass().getSimpleName();
        return (clz.endsWith("Impl") 
                ? clz.substring(0, clz.length() - "Impl".length())
                : clz) + " " +
               getSource() + " " +
               type + " " +
               (isForced() ? "(forced)" : "");
    }
}
