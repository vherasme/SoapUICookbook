/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.cxf.io;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import org.apache.cxf.endpoint.Endpoint;
import org.apache.cxf.service.model.ServiceInfo;

/**
 * This interface defines a bus service for Stax validation.
 * Initially, this is purely with Woodstox4 and msv.
 */
public interface StaxValidationManager {
    /**
     * Install the schemas onto the reader for validation.
     * @param reader - the reader to enable validation
     * @param serviceInfo - the Service to pull the schema from
     * @throws XMLStreamException 
     */
    boolean setupValidation(XMLStreamReader reader,
                            Endpoint endpoint,
                            ServiceInfo serviceInfo) throws XMLStreamException;
    
    /**
     * Install the schemas onto the writer for validation.
     * @param writer - the writer to enable validation
     * @param serviceInfo - the Service to pull the schema from
     * @throws XMLStreamException 
     */
    boolean setupValidation(XMLStreamWriter writer,
                            Endpoint endpoint,
                            ServiceInfo serviceInfo) throws XMLStreamException;

}
