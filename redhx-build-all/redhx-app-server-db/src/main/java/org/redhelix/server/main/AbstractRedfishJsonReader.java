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
package org.redhelix.server.main;

import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import org.apache.olingo.client.api.communication.request.retrieve.ODataEntityRequest;
import org.apache.olingo.client.api.communication.response.ODataRetrieveResponse;
import org.apache.olingo.client.api.domain.ClientAnnotation;
import org.apache.olingo.client.api.domain.ClientCollectionValue;
import org.apache.olingo.client.api.domain.ClientComplexValue;
import org.apache.olingo.client.api.domain.ClientEntity;
import org.apache.olingo.client.api.domain.ClientProperty;
import org.apache.olingo.client.api.domain.ClientValue;
import org.redhelix.core.action.RedHxActionName;
import org.redhelix.core.action.RedHxActionNameImpl;
import org.redhelix.core.action.RedHxActionProperties;
import org.redhelix.core.action.RedHxActionPropertiesImpl;
import org.redhelix.core.service.root.RedHxServiceRootIdEum;
import org.redhelix.core.util.RedHxHttpResponseException;
import org.redhelix.core.util.RedHxOperatingHealthEnum;
import org.redhelix.core.util.RedHxOperatingHealthRollupEnum;
import org.redhelix.core.util.RedHxOperatingStateEnum;
import org.redhelix.core.util.RedHxOperatingStatus;
import org.redhelix.core.util.RedHxOperatingStatusImpl;
import org.redhelix.core.util.RedHxUriPath;
import org.redhelix.core.util.RedHxUriPathImpl;

/**
 * execute olingo commands to read the Redfish resources and links. This class executes the HTTP request to read data from the server and
 * subclasses do not have to execute any HTTP commands. They do have to parse the JSON response.
 *
 *
 *
 * @since RedHelix Version 0.1
 * @author Hank Bruning
 *
 */
public abstract class AbstractRedfishJsonReader
{

    private static final String ODATA_ID_FLAG = "odata.id";
    private final static String JSON_SUB_KEY_STATUS_HEALTH = "Health";
    private final static String JSON_SUB_KEY_STATUS_STATE = "State";
    private final static String JSON_SUB_KEY_STATUS_HEALTH_ROLLUP = "HealthRollUp";    // bug in redfish. the Resource.json file says the U is lower case but the mockups have upper case.
    private final ClientEntity entity;
    private final boolean isResponseValid;
    private final RedHxUriPath uriPath;

    /**
     * create a reader and exeucte an HTTP get for the data in Redfish link. If the response has an HTTP error an exception is throw an no
     * other methods in the class can be called. As a result all methods in subclass of this do not perform error checking for the HTTP
     * response.
     *
     * @param ctx
     * @param serviceRootId
     * @param pathToResource
     * @throws URISyntaxException
     * @throws RedHxHttpResponseException
     */
    protected AbstractRedfishJsonReader(final RedHxServerConnectionContext ctx,
                                        final RedHxServiceRootIdEum serviceRootId,
                                        final RedHxUriPath pathToResource)
            throws URISyntaxException,
                   RedHxHttpResponseException
    {
        this.uriPath = pathToResource;

        final ODataEntityRequest<ClientEntity> req = ctx.getEntityRequest(pathToResource);
        final ODataRetrieveResponse<ClientEntity> response = req.execute();

        this.isResponseValid = response.getStatusCode() == HttpURLConnection.HTTP_OK;

        if (isResponseValid)
        {
            entity = response.getBody();
        }
        else
        {
            throw new RedHxHttpResponseException(serviceRootId,
                                                 response.getStatusCode(),
                                                 "Can not read " + serviceRootId + " " + pathToResource);
        }
    }

    private AbstractRedfishJsonReader()
    {
        this.entity = null;
        this.isResponseValid = false;
        this.uriPath = null;
    }

    public RedHxUriPath getUriPath()
    {
        return uriPath;
    }

    protected String getActionPath(String propName)
    {

        // use a tree set so the actions are in alpa order with the key being the action name.
        final ClientProperty clientProp = entity.getProperty(propName);
        String str;

        if (clientProp != null)
        {
            str = clientProp.getValue().toString();
        }
        else
        {
            str = null;
        }

        return str;
    }

    protected Set<RedHxActionProperties> getActions(String propName)
    {

        // use a tree set so the actions are in alpa order with the key being the action name.
        Set<RedHxActionProperties> set = new TreeSet<>();
        final ClientProperty clientProp = entity.getProperty(propName);

        if (clientProp != null)
        {
            /*
             * HFB5: the action is , XXXXXXXXX anno=org.apache.olingo.client.core.domain.ClientComplexValueImpl@7f3b84b8[
             * navigationLinks=[]
             * associationLinks=[]
             * annotations=[]
             * fields={#ComputerSystem.Reset=ODataPropertyImpl{name=#ComputerSystem.Reset,valuable=org.apache.olingo.client.core.domain.ClientValuableImpl@6069db50[
             * value=org.apache.olingo.client.core.domain.ClientComplexValueImpl@5c33f1a9[
             *
             * navigationLinks=[]
             * associationLinks=[]
             * annotations=[]
             * fields={target=ODataPropertyImpl{name=target,valuable=org.apache.olingo.client.core.domain.ClientValuableImpl@49139829[
             * value=/redfish/v1/Systems/1/Actions/ComputerSystem.Reset
             * ], annotations=[]}}
             * typeName=<null>
             * ]
             * ], annotations=[]}, Oem=ODataPropertyImpl{name=Oem,valuable=org.apache.olingo.client.core.domain.ClientValuableImpl@2f9f7dcf[
             * value=org.apache.olingo.client.core.domain.ClientComplexValueImpl@1b7cc17c[
             * navigationLinks=[]
             * associationLinks=[]
             * annotations=[]
             * fields={#Contoso.Reset=ODataPropertyImpl{name=#Contoso.Reset,valuable=org.apache.olingo.client.core.domain.ClientValuableImpl@5b8dfcc1[
             * value=org.apache.olingo.client.core.domain.ClientComplexValueImpl@77fbd92c[
             * navigationLinks=[]
             * associationLinks=[]
             * annotations=[]
             * fields={target=ODataPropertyImpl{name=target,valuable=org.apache.olingo.client.core.domain.ClientValuableImpl@72967906[
             * value=/redfish/v1/Systems/1/OEM/Contoso/Actions/Contoso.Reset
             * ], annotations=[]}}
             * typeName=<null>
             * ]
             * ], annotations=[]}}
             * typeName=<null>
             * ]
             * ], annotations=[]}}
             * typeName=<null>
             * ]
             */
            ClientComplexValue cplx = clientProp.getComplexValue();
            Iterator<ClientProperty> iter = cplx.iterator();

            while (iter.hasNext())
            {
                ClientProperty cp = iter.next();

                if (cp.getName().equals("Oem"))
                {
                    Iterator<ClientProperty> iter2 = cp.getComplexValue().iterator();

                    while (iter2.hasNext())
                    {
                        ClientProperty cp2 = iter2.next();
                        RedHxActionName actionName = new RedHxActionNameImpl(cp2.getName());
                        ClientValue cValue = cp2.getComplexValue().get("target").getValue();
                        RedHxUriPath actionsPath = new RedHxUriPathImpl(cValue.toString());
                        RedHxActionProperties action = new RedHxActionPropertiesImpl(actionName,
                                                                                     actionsPath);

                        set.add(action);
                    }
                }
                else
                {
                    ClientComplexValue cplx2 = cp.getComplexValue();
                    Iterator<ClientProperty> iter2 = cplx2.iterator();

                    /**
                     * todo: this does not read the ResetType@Redfish.AllowableValues for the Reset action. I can't find them using olingo
                     * beta3.
                     */
                    while (iter2.hasNext())
                    {
                        ClientProperty cp2 = iter2.next();
                        RedHxActionName actionName = new RedHxActionNameImpl(cp.getName());
                        ClientValue cValue = cplx2.get("target").getValue();
                        RedHxUriPath actionsPath = new RedHxUriPathImpl(cValue.toString());
                        RedHxActionProperties action = new RedHxActionPropertiesImpl(actionName,
                                                                                     actionsPath);

                        set.add(action);
                    }
                }
            }
        }

        return set;
    }

    /**
     * from the JSON response get the value of an anotation.
     *
     * @param propName
     * @return
     */
    protected String getAnnotation(String propName)
    {
        final ClientProperty clientProp = entity.getProperty(propName);
        String retVal;

        if (clientProp != null)
        {
            retVal = null;

            ClientComplexValue cplx = clientProp.getComplexValue();

            for (int i = 0; i < cplx.getAnnotations().size(); ++i)
            {
                ClientAnnotation anno = cplx.getAnnotations().get(i);

                if (anno.getTerm().equals(ODATA_ID_FLAG))
                {
                    retVal = anno.getValue().toString();

                    break;
                }
            }
        }
        else
        {
            retVal = null;
        }

        return retVal;
    }

    /**
     * from the JSON response parse a key composed of a primary and secondary key to single value.
     *
     * @param primaryKeyName
     * @param subKeyName
     * @return
     */
    protected String getComplexValue(String primaryKeyName,
                                     String subKeyName)
    {
        final ClientProperty clientProp = entity.getProperty(primaryKeyName);
        String retVal;

        if (clientProp != null)
        {
            ClientComplexValue cplx = clientProp.getComplexValue();

            /*
             * I would expect this code that is commented out should work but does not. It only gets the
             * first element(the propName2 im the complex type final ClientProperty subProp =
             * cplx.get(propName2); retVal = subProp.getValue().toString();
             */
            retVal = (String) cplx.asJavaMap().get(subKeyName);
        }
        else
        {
            retVal = null;
        }

        return retVal;
    }

    /**
     * from the JSON response parse an array of links.
     *
     * @param propName the top level JSON property name.
     * @return
     */
    protected List<String> getLinkArray(String propName)
    {
        final ClientProperty clientProp = entity.getProperty("Links");
        List<String> list = new ArrayList<>();

        if (clientProp != null)
        {
            ClientComplexValue cplx = clientProp.getComplexValue();
            ClientProperty prop2 = cplx.get(propName);

            if ((prop2 != null) && prop2.hasCollectionValue())
            {
                /*
                 * this may not be the best way to get the link but it works
                 */
                ClientCollectionValue<ClientValue> collection2 = prop2.getCollectionValue();

                for (ClientValue cv : collection2)
                {
                    List<ClientAnnotation> annotationList = cv.asComplex().getAnnotations();

                    if (annotationList.size() == 1)
                    {
                        ClientAnnotation anno = annotationList.get(0);

                        if (anno.getTerm().equals(ODATA_ID_FLAG))
                        {
                            String link = anno.getValue().toString();

                            list.add(link);
                        }
                    }
                }
            }
        }

        return list;
    }

    /**
     * from the JSON response get a single link for a resource on the Redfish server.
     *
     * @param propName
     * @return
     */
    protected String getLinkSingle(String propName)
    {
        final ClientProperty clientProp = entity.getProperty("Links");
        String retVal;

        if (clientProp != null)
        {
            retVal = null;

            ClientComplexValue cplx = clientProp.getComplexValue();
            ClientProperty prop2 = cplx.get(propName);

            if ((prop2 != null) && !prop2.hasCollectionValue())
            {
                List<ClientAnnotation> annotationList = prop2.getComplexValue().asComplex().getAnnotations();

                if (annotationList.size() == 1)
                {
                    ClientAnnotation anno = annotationList.get(0);

                    if (anno.getTerm().equals(ODATA_ID_FLAG))
                    {
                        retVal = anno.getValue().toString();
                    }
                }
            }
        }
        else
        {
            retVal = null;
        }

        return retVal;
    }

    protected RedHxOperatingStatus getOperatingStatus(String primaryKeyName)
    {
        String tmpStr = getComplexValue(primaryKeyName,
                                        JSON_SUB_KEY_STATUS_HEALTH);
        final RedHxOperatingHealthEnum opHealth;

        if ((tmpStr != null) && !tmpStr.isEmpty())
        {
            opHealth = RedHxOperatingHealthEnum.getInstance(tmpStr);
        }
        else
        {
            opHealth = null;
        }

        tmpStr = getComplexValue(primaryKeyName, JSON_SUB_KEY_STATUS_STATE);

        final RedHxOperatingStateEnum opState;

        if ((tmpStr != null) && !tmpStr.isEmpty())
        {
            opState = RedHxOperatingStateEnum.getInstance(tmpStr);
        }
        else
        {
            opState = null;
        }

        tmpStr = getComplexValue(primaryKeyName, JSON_SUB_KEY_STATUS_HEALTH_ROLLUP);

        RedHxOperatingHealthRollupEnum opRollup;

        if ((tmpStr != null) && !tmpStr.isEmpty())
        {
            opRollup = RedHxOperatingHealthRollupEnum.getInstance(tmpStr);
        }
        else
        {
            opRollup = null;
        }

        /**
         * memory operating status
         */
        final RedHxOperatingStatus computerOperatingStatus;

        if ((opHealth != null) || (opRollup != null) || (opState != null))
        {
            computerOperatingStatus = new RedHxOperatingStatusImpl(opHealth,
                                                                   opRollup,
                                                                   opState);
        }
        else
        {
            computerOperatingStatus = null;
        }

        return computerOperatingStatus;
    }

    /**
     * from the JSON response read a property they may be present. If it is not a null is returned.
     *
     * @param propName
     * @return
     */
    protected String getOptionalProperty(String propName)
    {
        final ClientProperty clientProp = entity.getProperty(propName);
        final String retVal;

        if (clientProp != null)
        {
            retVal = clientProp.getValue().toString();
        }
        else
        {
            retVal = null;
        }

        return retVal;
    }
}
