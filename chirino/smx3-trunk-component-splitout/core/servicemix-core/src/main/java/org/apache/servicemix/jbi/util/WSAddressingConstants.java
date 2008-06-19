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
package org.apache.servicemix.jbi.util;

public interface WSAddressingConstants {

    String WSA_NAMESPACE_200303 = "http://schemas.xmlsoap.org/ws/2003/03/addressing";
    String WSA_NAMESPACE_200403 = "http://schemas.xmlsoap.org/ws/2004/03/addressing";
    String WSA_NAMESPACE_200408 = "http://schemas.xmlsoap.org/ws/2004/08/addressing";
    String WSA_NAMESPACE_200508 = "http://www.w3.org/2005/08/addressing";
    
    String WSA_PREFIX = "wsa";
    
    String EL_ACTION = "Action";
    String EL_ADDRESS = "Address";
    String EL_FAULT_TO = "FaultTo";
    String EL_FROM = "From";
    String EL_MESSAGE_ID = "MessageID";
    String EL_METADATA = "Metadata";
    String EL_REFERENCE_PARAMETERS = "ReferenceParameters";
    String EL_RELATES_TO = "RelatesTo";
    String EL_REPLY_TO = "ReplyTo";
    String EL_TO = "To";

}
