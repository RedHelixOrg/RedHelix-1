/*
 * Copyright 2015 JBlade LLC
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License
 */
package org.redhelix.server.lib.reader.chassis;

import java.net.HttpURLConnection;
import java.util.HashSet;
import java.util.Set;
import org.apache.olingo.client.api.communication.response.ODataRetrieveResponse;
import org.apache.olingo.client.api.domain.ClientAnnotation;
import org.apache.olingo.client.api.domain.ClientComplexValue;
import org.apache.olingo.client.api.domain.ClientEntity;
import org.apache.olingo.client.api.domain.ClientProperty;
import org.apache.olingo.client.api.domain.ClientValue;
import org.redhelix.core.chassis.RedHxChassisParseException;
import org.redhelix.core.service.root.RedHxServiceRootIdEum;
import org.redhelix.core.util.RedHxHttpResponseException;
import org.redhelix.core.util.RedHxUriPath;
import org.redhelix.core.util.RedHxUriPathImpl;
import org.redhelix.server.lib.reader.util.RedHxServerConnectionContext;

/**
 * read zero or more links, each one pointing to a unique chassis. This class does not have a
 * constructor only a static method.
 *
 * @since RedHelix Version 0.1
 * @author Hank Bruning
 *
 */
public final class RedHxChassisPathCollectionReader {

  /**
   * The JSON property that identifies an array of chassis. This is derived from the Redfish file
   * ChassisCollection.json.
   */
  private static final String JSON_CHASSIS_COLLECTION_KEYWORD = "Members";

  /**
   * This identifies the URL to a single chassis.
   */
  private static final String ODATA_SINGLE_CHASSIS_KEYWORD = "odata.id";

  private RedHxChassisPathCollectionReader() {}

  /**
   * get a set of Paths used to identify unique chassis. Each Path can be used in a URL to identify
   * a chassis.
   *
   * @param ctx the communication context to single Redfish server.
   * @return A set of Paths. No element in the set is null.
   * @throws RedHxChassisParseException
   * @throws RedHxHttpResponseException
   */
  public static Set<RedHxUriPath> readChassisCollection(RedHxServerConnectionContext ctx)
      throws RedHxChassisParseException, RedHxHttpResponseException {
    final ODataRetrieveResponse<ClientEntity> response = ctx.getChassisEntityRequest().execute();
    final Set<RedHxUriPath> chassisPathSet = new HashSet<>();

    if (response.getStatusCode() == HttpURLConnection.HTTP_OK) {
      ClientEntity entity = response.getBody();

      /**
       * get the JSON entity
       */
      ClientProperty chassisProperty = entity.getProperty(JSON_CHASSIS_COLLECTION_KEYWORD);

      for (ClientValue chassisValue : chassisProperty.getCollectionValue()) {
        ClientComplexValue cplx = chassisValue.asComplex();

        if (cplx != null) {
          ClientAnnotation anno = cplx.getAnnotations().get(0);

          if (anno.getTerm().equals(ODATA_SINGLE_CHASSIS_KEYWORD)) {
            String chassisPath = anno.getValue().toString();

            if (chassisPath == null) {
              throw new RedHxChassisParseException(
                  "The JSON annotation pointing to a specific chassis was null.");
            }

            RedHxUriPath path = new RedHxUriPathImpl(chassisPath);

            chassisPathSet.add(path);
          } else {
            throw new RedHxChassisParseException("Unable to find keyword "
                + ODATA_SINGLE_CHASSIS_KEYWORD);
          }
        } else {
          throw new RedHxChassisParseException("The JSON message did not contains a class "
              + ClientComplexValue.class.getSimpleName());
        }
      }
    } else {
      throw new RedHxHttpResponseException(RedHxServiceRootIdEum.CHASSIS, response.getStatusCode(),
          "Can not read Chassis Collection.");
    }

    return chassisPathSet;
  }
}
