/*
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
package org.redhelix.server.data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import org.apache.olingo.commons.api.data.ComplexValue;
import org.apache.olingo.commons.api.data.Entity;
import org.apache.olingo.commons.api.data.EntityCollection;
import org.apache.olingo.commons.api.data.Parameter;
import org.apache.olingo.commons.api.data.Property;
import org.apache.olingo.commons.api.data.ValueType;
import org.apache.olingo.commons.api.edm.EdmPrimitiveType;
import org.apache.olingo.commons.api.edm.EdmPrimitiveTypeException;
import org.apache.olingo.commons.api.edm.EdmPrimitiveTypeKind;
import org.apache.olingo.commons.api.http.HttpStatusCode;
import org.apache.olingo.server.api.OData;
import org.redhelix.server.data.DataProvider.DataProviderException;

public class ActionData
{

    /**
     * Performs the named action (i.e., does nothing, currently) and returns the primitive-type result.
     *
     * @param name name of the action
     * @param parameters parameters of the action
     */
    protected static Property primitiveAction(final String name, final Map<String, Parameter> parameters)
            throws DataProviderException
    {
        if ("UARTString".equals(name))
        {
            return DataCreator.createPrimitive(null, "UARTString string value");
        }
        throw new DataProviderException("Action " + name + " is not yet implemented.");
    }

    protected static Property primitiveCollectionAction(final String name, final Map<String, Parameter> parameters,
                                                        final OData oData) throws DataProviderException
    {
        if ("UARTCollStringTwoParam".equals(name))
        {
            Parameter paramInt16 = parameters.get("ParameterInt16");
            Parameter paramDuration = parameters.get("ParameterDuration");
            if (paramInt16 == null || paramInt16.isNull() || paramDuration == null || paramDuration.isNull())
            {
                try
                {
                    String param16String = valueAsString(paramInt16, EdmPrimitiveTypeKind.Int16, oData);
                    String paramDurationString = valueAsString(paramDuration, EdmPrimitiveTypeKind.Duration, oData);

                    return new Property(null, name, ValueType.COLLECTION_PRIMITIVE, Arrays.asList(new String[]
                                {
                                    name + " int16 value: " + param16String,
                                    name + " duration value: " + paramDurationString
                    }));
                }
                catch (EdmPrimitiveTypeException e)
                {
                    throw new DataProviderException("EdmPrimitiveTypeException", e);
                }
            }
            short loopCount = (Short) paramInt16.asPrimitive();
            BigDecimal duration = (BigDecimal) paramDuration.asPrimitive();
            EdmPrimitiveType primDuration = oData.createPrimitiveTypeInstance(EdmPrimitiveTypeKind.Duration);
            BigDecimal addValue = new BigDecimal(1);
            List<Object> collectionValues = new ArrayList<Object>();
            for (int i = 0; i < loopCount; i++)
            {
                try
                {
                    String value = primDuration.valueToString(duration, false, null, null, null, null);
                    collectionValues.add(name + " duration value: " + value);
                }
                catch (EdmPrimitiveTypeException e)
                {
                    throw new DataProviderException("EdmPrimitiveTypeException", e);
                }
                duration = duration.add(addValue);
            }
            return new Property(null, name, ValueType.COLLECTION_PRIMITIVE, collectionValues);
        }
        throw new DataProviderException("Action " + name + " is not yet implemented.");
    }

    private static String valueAsString(final Parameter parameter, final EdmPrimitiveTypeKind kind, final OData oData)
            throws EdmPrimitiveTypeException
    {
        return parameter == null ? "null"
                : oData.createPrimitiveTypeInstance(kind)
                .valueToString(parameter.asPrimitive(), null, null, null, null, null);
    }

    protected static Property complexAction(final String name, final Map<String, Parameter> parameters)
            throws DataProviderException
    {
        if ("UARTCTTwoPrimParam".equals(name))
        {
            Parameter paramInt16 = parameters.get("ParameterInt16");
            final Short number = paramInt16 == null ? (short) 32767 : (Short) paramInt16.asPrimitive();
            return createCTTwoPrimComplexProperty(number, "UARTCTTwoPrimParam string value");
        }
        throw new DataProviderException("Action " + name + " is not yet implemented.");
    }

    private static Property createCTTwoPrimComplexProperty(final Short number, final String text)
    {
        ComplexValue compValue = new ComplexValue();
        Property propInt = new Property();
        propInt.setName("PropertyInt16");
        propInt.setValue(ValueType.PRIMITIVE, number);
        compValue.getValue().add(propInt);
        Property propString = new Property();
        propString.setName("PropertyString");
        propString.setValue(ValueType.PRIMITIVE, text);
        compValue.getValue().add(propString);

        Property complexProp = new Property();
        complexProp.setValue(ValueType.COMPLEX, compValue);
        return complexProp;
    }

    protected static Property complexCollectionAction(final String name, final Map<String, Parameter> parameters)
            throws DataProviderException
    {
        if ("UARTCollCTTwoPrimParam".equals(name))
        {
            List<ComplexValue> complexCollection = new ArrayList<ComplexValue>();
            complexCollection.add(createCTTwoPrimComplexProperty((short) 16, "Test123").asComplex());
            complexCollection.add(createCTTwoPrimComplexProperty((short) 17, "Test456").asComplex());
            complexCollection.add(createCTTwoPrimComplexProperty((short) 18, "Test678").asComplex());

            Parameter paramInt16 = parameters.get("ParameterInt16");
            if (paramInt16 != null)
            {
                Short number = (Short) paramInt16.asPrimitive();
                if (number < 0)
                {
                    complexCollection.clear();
                }
                else if (number >= 0 && number < complexCollection.size())
                {
                    complexCollection = complexCollection.subList(0, number);
                }
                Property complexCollProperty = new Property();
                complexCollProperty.setValue(ValueType.COLLECTION_COMPLEX, complexCollection);
                return complexCollProperty;
            }
        }
        throw new DataProviderException("Action " + name + " is not yet implemented.");
    }

    protected static EntityActionResult entityAction(final String name, final Map<String, Parameter> parameters,
                                                     final Map<String, EntityCollection> data) throws DataProviderException
    {
        if ("UARTETTwoKeyTwoPrimParam".equals(name))
        {
            Parameter parameter = parameters.get("ParameterInt16");
            final Short number = parameter == null ? 0 : (Short) parameter.asPrimitive();

            EntityCollection entityCollection = data.get("ESTwoKeyTwoPrim");
            for (Entity entity : entityCollection.getEntities())
            {
                Object asPrimitive = entity.getProperty("PropertyInt16").asPrimitive();
                if (number.equals(asPrimitive))
                {
                    return new EntityActionResult().setEntity(entity);
                }
            }
            // Entity Not found
            throw new DataProviderException("Entity not found with key: " + number, HttpStatusCode.NOT_FOUND);
        }
        else if ("UARTETAllPrimParam".equals(name))
        {
            Parameter paramDate = parameters.get("ParameterDate");
            EntityCollection entityCollection = data.get("ESAllPrim");
            if (paramDate != null)
            {
                Calendar date = (Calendar) paramDate.asPrimitive();
                boolean freeKey;
                Short key = -1;
                do
                {
                    freeKey = true;
                    key++;
                    for (Entity entity : entityCollection.getEntities())
                    {
                        Short entityKey = (Short) entity.getProperty("PropertyInt16").asPrimitive();
                        if (key.equals(entityKey))
                        {
                            freeKey = false;
                            break;
                        }
                    }
                }
                while (!freeKey);
                return new EntityActionResult().setEntity(createAllPrimEntity(key, "UARTETAllPrimParam string value", date))
                        .setCreated(true);
            }
            else
            {
                return new EntityActionResult().setEntity(entityCollection.getEntities().get(0));
            }
        }
        throw new DataProviderException("Action " + name + " is not yet implemented.");
    }

    private static Entity createAllPrimEntity(final Short key, final String val, final Calendar date)
    {
        return new Entity().addProperty(DataCreator.createPrimitive("PropertyInt16", key))
                .addProperty(DataCreator.createPrimitive("PropertyString", val))
                .addProperty(DataCreator.createPrimitive("PropertyBoolean", false))
                .addProperty(DataCreator.createPrimitive("PropertyByte", null))
                .addProperty(DataCreator.createPrimitive("PropertySByte", null))
                .addProperty(DataCreator.createPrimitive("PropertyInt32", null))
                .addProperty(DataCreator.createPrimitive("PropertyInt64", null))
                .addProperty(DataCreator.createPrimitive("PropertySingle", null))
                .addProperty(DataCreator.createPrimitive("PropertyDouble", null))
                .addProperty(DataCreator.createPrimitive("PropertyDecimal", null))
                .addProperty(DataCreator.createPrimitive("PropertyBinary", null))
                .addProperty(DataCreator.createPrimitive("PropertyDate", date))
                .addProperty(DataCreator.createPrimitive("PropertyDateTimeOffset", null))
                .addProperty(DataCreator.createPrimitive("PropertyDuration", null))
                .addProperty(DataCreator.createPrimitive("PropertyGuid", null))
                .addProperty(DataCreator.createPrimitive("PropertyTimeOfDay", null));
    }

    protected static EntityCollection entityCollectionAction(final String name, final Map<String, Parameter> parameters)
            throws DataProviderException
    {
        if ("UARTCollETKeyNavParam".equals(name))
        {
            Parameter paramInt16 = parameters.get("ParameterInt16");
            final Short number = paramInt16 == null ? 0 : (Short) paramInt16.asPrimitive();
            EntityCollection collection = new EntityCollection();
            if (number > 0)
            {
                for (short i = 1; i <= number; i++)
                {
                    collection.getEntities().add(createETKeyNavEntity(i));
                }
            }
            return collection;
        }
        else if ("UARTCollETAllPrimParam".equals(name))
        {
            Parameter paramTimeOfDay = parameters.get("ParameterTimeOfDay");
            EntityCollection collection = new EntityCollection();
            if (paramTimeOfDay != null)
            {
                Calendar timeOfDay = (Calendar) paramTimeOfDay.asPrimitive();
                int count = timeOfDay.get(Calendar.HOUR_OF_DAY);
                for (short i = 1; i <= count; i++)
                {
                    collection.getEntities().add(createAllPrimEntity(i, "UARTCollETAllPrimParam int16 value: " + i, null));
                }
            }
            return collection;
        }
        throw new DataProviderException("Action " + name + " is not yet implemented.");
    }

    @SuppressWarnings("unchecked")
    private static Entity createETKeyNavEntity(final Short number)
    {
        return new Entity()
                .addProperty(DataCreator.createPrimitive("PropertyInt16", number))
                .addProperty(DataCreator.createPrimitive("PropertyString", "UARTCollETKeyNavParam int16 value: " + number))
                .addProperty(DataCreator.createComplex("PropertyCompNav",
                                                       DataCreator.createPrimitive("PropertyInt16", (short) 0)))
                .addProperty(createKeyNavAllPrimComplexValue("PropertyCompAllPrim"))
                .addProperty(DataCreator.createComplex("PropertyCompTwoPrim",
                                                       DataCreator.createPrimitive("PropertyInt16", (short) 0),
                                                       DataCreator.createPrimitive("PropertyString", ""))).addProperty(
                DataCreator.createPrimitiveCollection("CollPropertyString"))
                .addProperty(DataCreator.createPrimitiveCollection("CollPropertyInt16"))
                .addProperty(DataCreator.createComplexCollection("CollPropertyComp"))
                .addProperty(DataCreator.createComplex("PropertyCompCompNav",
                                                       DataCreator.createPrimitive("PropertyString", ""),
                                                       DataCreator.createComplex("PropertyCompNav",
                                                                                 DataCreator.createPrimitive("PropertyInt16", (short) 0))));
    }

    protected static Property createKeyNavAllPrimComplexValue(final String name)
    {
        return DataCreator.createComplex(name,
                                         DataCreator.createPrimitive("PropertyString", ""),
                                         DataCreator.createPrimitive("PropertyBinary", new byte[]
                                                             {
                                 }),
                                         DataCreator.createPrimitive("PropertyBoolean", false),
                                         DataCreator.createPrimitive("PropertyByte", (short) 0),
                                         DataCreator.createPrimitive("PropertyDate", null),
                                         DataCreator.createPrimitive("PropertyDateTimeOffset", null),
                                         DataCreator.createPrimitive("PropertyDecimal", BigDecimal.valueOf(0)),
                                         DataCreator.createPrimitive("PropertySingle", (float) 0),
                                         DataCreator.createPrimitive("PropertyDouble", 0D),
                                         DataCreator.createPrimitive("PropertyDuration", BigDecimal.valueOf(0)),
                                         DataCreator.createPrimitive("PropertyGuid", null),
                                         DataCreator.createPrimitive("PropertyInt16", null),
                                         DataCreator.createPrimitive("PropertyInt32", null),
                                         DataCreator.createPrimitive("PropertyInt64", null),
                                         DataCreator.createPrimitive("PropertySByte", null),
                                         DataCreator.createPrimitive("PropertyTimeOfDay", null));
    }
}
