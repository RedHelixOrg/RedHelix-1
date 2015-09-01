/*
 * Copyright 2015 JBlade LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License
 *
 */



package org.redhelix.server.main;

import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.util.Iterator;
import org.apache.olingo.client.api.communication.request.retrieve.ODataEntityRequest;
import org.apache.olingo.client.api.communication.response.ODataRetrieveResponse;
import org.apache.olingo.client.api.domain.ClientAnnotation;
import org.apache.olingo.client.api.domain.ClientComplexValue;
import org.apache.olingo.client.api.domain.ClientEntity;
import org.apache.olingo.client.api.domain.ClientProperty;
import org.redhelix.core.chassis.RedHxChassis;
import org.redhelix.core.chassis.RedHxChassisBuilder;
import org.redhelix.core.chassis.id.RedHxChassisManufacturerName;
import org.redhelix.core.chassis.id.RedHxChassisSerialNumber;
import org.redhelix.core.service.root.RedHxServiceRootIdEum;
import org.redhelix.core.util.RedHxHttpResponseException;

/**
 *
 * <br><br>
 * Git SHA: $Id$
 *
 * @since RedHelix Version HELIX_VERSION_TAG // Do not change this line.
 * @author Hank Bruning
 *
 */
final class ChassisReader
        extends AbstractRedfishJsonReader
{
    private final static String JSON_KEY_ACTIONS              = "Actions";
    private final static String JSON_KEY_ASSET_TAG            = "AssetTag";
    private final static String JSON_KEY_CHASSIS_TYPE         = "ChassisType";    // required by the JSON schema
    private final static String JSON_KEY_DESCRIPTION          = "Description";
    private final static String JSON_KEY_ID                   = "Id";
    private final static String JSON_KEY_INDICATOR_LED        = "IndicatorLED";
    private final static String JSON_KEY_LOG_SERVICES         = "LogServices";
    private final static String JSON_KEY_MANUFACTURER         = "Manufacturer";
    private final static String JSON_KEY_MODEL                = "Model";
    private final static String JSON_KEY_NAME                 = "Name";
    private final static String JSON_KEY_PART_NUMBER          = "PartNumber";
    private final static String JSON_KEY_POWER                = "Power";
    private final static String JSON_KEY_SERIALNUMBER         = "SerialNumber";
    private final static String JSON_KEY_SKU                  = "SKU";
    private final static String JSON_KEY_STATUS               = "Status";
    private final static String JSON_ANOTATION_KEY_THERMAL    = "Thermal";
    private final static String JSON_ANOTATION_KEY_POWER      = "Power";
    private final static String JSON_SUB_KEY_STATUS_STATE     = "State";
    private final static String JSON_SUB_KEY_STATUS_HEALTH    = "Health";
    private final static String JSON_LINK_KEY_COMPUTER_SYSTEM = "ComputerSystems";
    private final static String JSON_LINK_KEY_CONTAINED_BY    = "ContainedBy";
    private final static String JSON_LINK_KEY_MANAGED_BY      = "ManagedBy";

    ChassisReader( RedHxServerConnectionContext ctx,
                   String                       chassisLink )
            throws URISyntaxException,
                   RedHxHttpResponseException
    {
        super(ctx,
              RedHxServiceRootIdEum.CHASSIS,
              chassisLink);
    }

    /*
     * HFB5: property name Id, 1
     * HFB5: property name Name, Computer System Chassis
     * HFB5: property name ChassisType, RackMount
     * HFB5: property name Manufacturer, ManufacturerName
     * HFB5: property name Model, ProductModelName
     * HFB5: property name SKU,
     * HFB5: property name SerialNumber, 2M220100SL
     * HFB5: property name PartNumber,
     * HFB5: property name AssetTag, CustomerWritableThingy
     * HFB5: property name IndicatorLED, Lit
     * HFB5:     prop2 primitive=State, Enabled
     * HFB5:     prop2 primitive=Health, OK
     * HFB5: anno-0=odata.id, /redfish/v1/Chassis/1/Thermal
     * HFB5: anno-0=odata.id, /redfish/v1/Chassis/1/Power
     * HFB5:     prop2 Unknown=ComputerSystems, []
     * HFB5:     prop2 cplx2=odata.id, /redfish/v1/Chassis/Rack1
     * HFB5:     prop2 Unknown=ManagedBy, []
     */

    /**
     *
     * @param ctx
     * @param chassisLink
     * @return
     * @throws RedHxHttpResponseException
     * @throws URISyntaxException
     */
    RedHxChassis readChassis( )
            throws RedHxHttpResponseException,
                   URISyntaxException
    {
        final RedHxChassis chassis;

        System.out.println("HFB5: HHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH");

        RedHxChassisManufacturerName manufacturerName = null;
        RedHxChassisSerialNumber     serialNumber     = null;
        RedHxChassisBuilder          builder          = new RedHxChassisBuilder(manufacturerName,
                serialNumber);
        String                       tmpStr;

        tmpStr = getOptionalProperty(JSON_KEY_ID);
        System.out.println("HFB5: id=" + tmpStr);
        tmpStr = getOptionalProperty(JSON_KEY_NAME);
        System.out.println("HFB5: name=" + tmpStr);
        tmpStr = getOptionalProperty(JSON_KEY_CHASSIS_TYPE);
        System.out.println("HFB5: chassisType=" + tmpStr);
        tmpStr = getComplexValue(JSON_KEY_STATUS, JSON_SUB_KEY_STATUS_HEALTH);
        System.out.println("HFB5: status health=" + tmpStr);
        tmpStr = getComplexValue(JSON_KEY_STATUS, JSON_SUB_KEY_STATUS_STATE);
        System.out.println("HFB5: status state=" + tmpStr);
        tmpStr = getAnnotation(JSON_ANOTATION_KEY_POWER);
        System.out.println("HFB5: power=" + tmpStr);
        tmpStr = getAnnotation(JSON_ANOTATION_KEY_THERMAL);
        System.out.println("HFB5: thermal=" + tmpStr);
        tmpStr = getOptionalProperty(JSON_KEY_INDICATOR_LED);
        System.out.println("HFB5: indicator led=" + tmpStr);

        //
        tmpStr = getLinkArray(JSON_LINK_KEY_COMPUTER_SYSTEM);
        System.out.println("HFB5: computer systems=" + tmpStr);
        tmpStr = getLinkSingle(JSON_LINK_KEY_CONTAINED_BY);
        System.out.println("HFB5: contained by=" + tmpStr);
        tmpStr = getLinkArray(JSON_LINK_KEY_MANAGED_BY);
        System.out.println("HFB5: managed by=" + tmpStr);

        //
        chassis = builder.getInstance();

        return chassis;
    }

    static RedHxChassis readChassis( RedHxServerConnectionContext ctx,
                                     String                       chassisLink )
            throws RedHxHttpResponseException,
                   URISyntaxException
    {
        final ODataEntityRequest<ClientEntity>    req      = ctx.getEntityRequest(chassisLink);
        final ODataRetrieveResponse<ClientEntity> response = req.execute();
        final RedHxChassis                        chassis;

        System.out.println("HFB5: HHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH");

        if (response.getStatusCode() == HttpURLConnection.HTTP_OK)
        {
            ClientEntity                 entity           = response.getBody();
            RedHxChassisManufacturerName manufacturerName = null;
            RedHxChassisSerialNumber     serialNumber     = null;

            for (ClientProperty prop : entity.getProperties())
            {
                if (prop.hasComplexValue())
                {
                    final ClientComplexValue val = prop.getComplexValue();

                    if (!val.getAnnotations().isEmpty())
                    {
                        for (int i = 0; i < val.getAnnotations().size(); ++i)
                        {
                            ClientAnnotation anno = val.getAnnotations().get(i);

                            System.out.println("HFB5: anno-" + i + "=" + anno.getTerm() + ", " + anno.getValue());
                        }
                    }
                    else if (val.isPrimitive())
                    {
                        System.out.println("HFB5: primitive " + val.asPrimitive());
                    }
                    else if (val.isCollection())
                    {
                        System.out.println("HFB5: collection " + val.asCollection());
                    }
                    else if (val.isComplex())
                    {
                        final ClientComplexValue cplx = val.asComplex();
                        Iterator<ClientProperty> iter = cplx.iterator();

                        while (iter.hasNext())
                        {
                            ClientProperty prop2 = iter.next();

                            if (prop2.hasPrimitiveValue())
                            {
                                System.out.println("HFB5:     prop2 primitive=" + prop2.getName() + ", " + prop2.getValue());
                            }
                            else if (prop2.hasComplexValue())
                            {
                                final ClientComplexValue cplx2 = prop2.getComplexValue();

                                if (!cplx2.getAnnotations().isEmpty())
                                {
                                    for (int i = 0; i < cplx2.getAnnotations().size(); ++i)
                                    {
                                        ClientAnnotation anno = cplx2.getAnnotations().get(i);

                                        System.out.println("HFB5:     prop2 cplx2=" + anno.getTerm() + ", " + anno.getValue());
                                    }
                                }
                            }
                            else
                            {
                                System.out.println("HFB5:     prop2 Unknown=" + prop2.getName() + ", " + prop2.getAnnotations());
                            }
                        }

                        // System.out.println("HFB5: complex " + cplx.get(chassisLink));
                    }
                    else if (!val.getAnnotations().isEmpty())
                    {
                        System.out.println("HFB5:       annontaions2=" + val.getAnnotations());
                    }
                }
                else if (prop.getName() != null)
                {
                    System.out.println("HFB5: property name " + prop.getName() + ", " + prop.getValue());
                }
                else
                {
                    System.out.println("HFB5: unknown property=" + prop);

                    if (prop.hasCollectionValue())
                    {
                        System.out.println("HFB5: collection val=" + prop.getCollectionValue());
                    }
                }
            }

            RedHxChassisBuilder builder = new RedHxChassisBuilder(manufacturerName,
                    serialNumber);

            chassis = builder.getInstance();
        }
        else
        {
            throw new RedHxHttpResponseException(RedHxServiceRootIdEum.CHASSIS,
                    response.getStatusCode(),
                    "Can not read Chassis Collection.");
        }

        return chassis;
    }
}
