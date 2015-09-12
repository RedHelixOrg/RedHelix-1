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
package org.redhelix.core.chassis;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import org.redhelix.core.action.RedHxActionGroup;
import org.redhelix.core.chassis.id.RedHxChassisAssetTag;
import org.redhelix.core.chassis.id.RedHxChassisDescription;
import org.redhelix.core.chassis.id.RedHxChassisId;
import org.redhelix.core.chassis.id.RedHxChassisManufacturerName;
import org.redhelix.core.chassis.id.RedHxChassisModelNumber;
import org.redhelix.core.chassis.id.RedHxChassisName;
import org.redhelix.core.chassis.id.RedHxChassisPartNumber;
import org.redhelix.core.chassis.id.RedHxChassisSKU;
import org.redhelix.core.chassis.id.RedHxChassisSerialNumber;
import org.redhelix.core.util.RedHxIndicatorLedStateEnum;
import org.redhelix.core.util.RedHxOperatingHealthEnum;
import org.redhelix.core.util.RedHxOperatingStateEnum;
import org.redhelix.core.util.RedHxUriPath;

/**
 *
 *
 *
 * @since RedHelix Version 0.1
 * @author Hank Bruning
 */
final class ChassisImpl
        implements RedHxChassis
{

    private RedHxActionGroup actionGroup;
    private RedHxChassisAssetTag assetTag;
    private RedHxChassisDescription chassisDescription;
    private RedHxChassisId chassisId;
    private RedHxChassisName chassisName;
    private RedHxChassisTypeEnum chassisType;
    private List<RedHxUriPath> computerSystemUriPathList;
    private RedHxUriPath containedByUriPath;
    private List<RedHxUriPath> containsList;
    private List<RedHxUriPath> cooledByUriPathList;
    private RedHxIndicatorLedStateEnum ledState;
    private RedHxUriPath logServicesUriPath;
    private RedHxChassisManufacturerName manufacturerName;
    private RedHxChassisModelNumber modelNumber;
    private RedHxOperatingHealthEnum operatingHealth;
    private RedHxOperatingStateEnum operatingState;
    private RedHxChassisPartNumber partNumber;
    private List<RedHxUriPath> poweredByList;
    private RedHxUriPath powerUriPath;
    private RedHxChassisSerialNumber serialNumber;
    private RedHxChassisSKU sku;
    private List<RedHxUriPath> systemManagerUriPathList;
    private RedHxUriPath thermalUriPath;

    /**
     * create a Chassis. The arguments are arranged in alpha order.
     *
     * @param actionGroup
     * @param assetTag
     * @param chassisDescription
     * @param chassisId
     * @param chassisName
     * @param chassisType
     * @param computerSystemUriPathList
     * @param containedByUriPath
     * @param containsList
     * @param cooledByUriPathList
     * @param ledState
     * @param logServicesUriPath
     * @param manufacturerName
     * @param modelNumber
     * @param operatingHealth
     * @param operatingState
     * @param partNumber
     * @param poweredByList
     * @param powerUriPath
     * @param serialNumber
     * @param sku
     * @param systemManagerUriPathList
     * @param thermalUriPath
     */
    ChassisImpl(RedHxActionGroup actionGroup,
                RedHxChassisAssetTag assetTag,
                RedHxChassisDescription chassisDescription,
                RedHxChassisId chassisId,
                RedHxChassisName chassisName,
                RedHxChassisTypeEnum chassisType,
                List<RedHxUriPath> computerSystemUriPathList,
                RedHxUriPath containedByUriPath,
                List<RedHxUriPath> containsList,
                List<RedHxUriPath> cooledByUriPathList,
                RedHxIndicatorLedStateEnum ledState,
                RedHxUriPath logServicesUriPath,
                RedHxChassisManufacturerName manufacturerName,
                RedHxChassisModelNumber modelNumber,
                RedHxOperatingHealthEnum operatingHealth,
                RedHxOperatingStateEnum operatingState,
                RedHxChassisPartNumber partNumber,
                List<RedHxUriPath> poweredByList,
                RedHxUriPath powerUriPath,
                RedHxChassisSerialNumber serialNumber,
                RedHxChassisSKU sku,
                List<RedHxUriPath> systemManagerUriPathList,
                RedHxUriPath thermalUriPath)
    {
        this.actionGroup = actionGroup;
        this.assetTag = assetTag;
        this.chassisDescription = chassisDescription;
        this.chassisId = chassisId;
        this.chassisName = chassisName;
        this.chassisType = chassisType;
        this.computerSystemUriPathList = Collections.unmodifiableList(computerSystemUriPathList);
        this.containedByUriPath = containedByUriPath;
        this.containsList = containsList;
        this.cooledByUriPathList = Collections.unmodifiableList(cooledByUriPathList);
        this.ledState = ledState;
        this.logServicesUriPath = logServicesUriPath;
        this.manufacturerName = manufacturerName;
        this.modelNumber = modelNumber;
        this.operatingHealth = operatingHealth;
        this.operatingState = operatingState;
        this.partNumber = partNumber;
        this.poweredByList = Collections.unmodifiableList(poweredByList);
        this.powerUriPath = powerUriPath;
        this.serialNumber = serialNumber;
        this.sku = sku;
        this.systemManagerUriPathList = Collections.unmodifiableList(systemManagerUriPathList);
        this.thermalUriPath = thermalUriPath;
    }

    private ChassisImpl()
    {
        this.assetTag = null;
        this.chassisDescription = null;
        this.chassisId = null;
        this.chassisName = null;
        this.chassisType = null;
        this.computerSystemUriPathList = null;
        this.containedByUriPath = null;
        this.containsList = null;
        this.cooledByUriPathList = null;
        this.ledState = null;
        this.logServicesUriPath = null;
        this.manufacturerName = null;
        this.modelNumber = null;
        this.operatingHealth = null;
        this.operatingState = null;
        this.partNumber = null;
        this.poweredByList = null;
        this.powerUriPath = null;
        this.serialNumber = null;
        this.sku = null;
        this.systemManagerUriPathList = null;
        this.thermalUriPath = null;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }

        if (obj == null)
        {
            return false;
        }

        if (getClass() != obj.getClass())
        {
            return false;
        }

        final ChassisImpl other = (ChassisImpl) obj;

        if (!Objects.equals(this.actionGroup, other.actionGroup))
        {
            return false;
        }

        if (!Objects.equals(this.assetTag, other.assetTag))
        {
            return false;
        }

        if (!Objects.equals(this.chassisDescription, other.chassisDescription))
        {
            return false;
        }

        if (!Objects.equals(this.chassisId, other.chassisId))
        {
            return false;
        }

        if (!Objects.equals(this.chassisName, other.chassisName))
        {
            return false;
        }

        if (this.chassisType != other.chassisType)
        {
            return false;
        }

        if (!Objects.equals(this.computerSystemUriPathList, other.computerSystemUriPathList))
        {
            return false;
        }

        if (!Objects.equals(this.containedByUriPath, other.containedByUriPath))
        {
            return false;
        }

        if (!Objects.equals(this.containsList, other.containsList))
        {
            return false;
        }

        if (!Objects.equals(this.cooledByUriPathList, other.cooledByUriPathList))
        {
            return false;
        }

        if (this.ledState != other.ledState)
        {
            return false;
        }

        if (!Objects.equals(this.logServicesUriPath, other.logServicesUriPath))
        {
            return false;
        }

        if (!Objects.equals(this.manufacturerName, other.manufacturerName))
        {
            return false;
        }

        if (!Objects.equals(this.modelNumber, other.modelNumber))
        {
            return false;
        }

        if (this.operatingHealth != other.operatingHealth)
        {
            return false;
        }

        if (this.operatingState != other.operatingState)
        {
            return false;
        }

        if (!Objects.equals(this.partNumber, other.partNumber))
        {
            return false;
        }

        if (!Objects.equals(this.poweredByList, other.poweredByList))
        {
            return false;
        }

        if (!Objects.equals(this.powerUriPath, other.powerUriPath))
        {
            return false;
        }

        if (!Objects.equals(this.serialNumber, other.serialNumber))
        {
            return false;
        }

        if (!Objects.equals(this.sku, other.sku))
        {
            return false;
        }

        if (!Objects.equals(this.systemManagerUriPathList, other.systemManagerUriPathList))
        {
            return false;
        }

        if (!Objects.equals(this.thermalUriPath, other.thermalUriPath))
        {
            return false;
        }

        return true;
    }

    @Override
    public RedHxActionGroup getActionGroup()
    {
        return actionGroup;
    }

    @Override
    public RedHxChassisAssetTag getAssetTag()
    {
        return assetTag;
    }

    @Override
    public RedHxChassisDescription getChassisDescription()
    {
        return chassisDescription;
    }

    @Override
    public RedHxChassisId getChassisId()
    {
        return chassisId;
    }

    @Override
    public RedHxChassisName getChassisName()
    {
        return chassisName;
    }

    @Override
    public RedHxChassisTypeEnum getChassisType()
    {
        return chassisType;
    }

    @Override
    public List<RedHxUriPath> getComputerSystemUriPathList()
    {
        return computerSystemUriPathList;
    }

    @Override
    public RedHxUriPath getContainedByUriPath()
    {
        return containedByUriPath;
    }

    @Override
    public List<RedHxUriPath> getContainsList()
    {
        return containsList;
    }

    @Override
    public List<RedHxUriPath> getCooledByUriPathList()
    {
        return cooledByUriPathList;
    }

    @Override
    public RedHxIndicatorLedStateEnum getIndicatorLedState()
    {
        return ledState;
    }

    @Override
    public RedHxUriPath getLogServicesUriPath()
    {
        return logServicesUriPath;
    }

    @Override
    public RedHxChassisManufacturerName getManufacturerName()
    {
        return manufacturerName;
    }

    @Override
    public RedHxChassisModelNumber getModelNumber()
    {
        return modelNumber;
    }

    @Override
    public RedHxOperatingHealthEnum getOperatingHealth()
    {
        return operatingHealth;
    }

    @Override
    public RedHxOperatingStateEnum getOperatingState()
    {
        return operatingState;
    }

    @Override
    public RedHxChassisPartNumber getPartNumber()
    {
        return partNumber;
    }

    @Override
    public List<RedHxUriPath> getPoweredByList()
    {
        return poweredByList;
    }

    @Override
    public RedHxUriPath getPowerUriPath()
    {
        return powerUriPath;
    }

    @Override
    public RedHxChassisSerialNumber getSerialNumber()
    {
        return serialNumber;
    }

    @Override
    public RedHxChassisSKU getSku()
    {
        return sku;
    }

    @Override
    public List<RedHxUriPath> getSystemManagerUriPathList()
    {
        return systemManagerUriPathList;
    }

    @Override
    public RedHxUriPath getThermalUriPath()
    {
        return thermalUriPath;
    }

    @Override
    public int hashCode()
    {
        int hash = 5;

        hash = 29 * hash + Objects.hashCode(this.assetTag);
        hash = 29 * hash + Objects.hashCode(this.chassisId);
        hash = 29 * hash + Objects.hashCode(this.serialNumber);

        return hash;
    }

    @Override
    public String toString()
    {
        StringBuilder toStringBuilder;

        toStringBuilder = new StringBuilder("{");

        if (chassisId != null)
        {
            toStringBuilder.append("chassisId=").append(this.chassisId.getValue());
        }

        if (chassisName != null)
        {
            toStringBuilder.append(", chassisName=").append(this.chassisName.getValue());
        }

        if (assetTag != null)
        {
            toStringBuilder.append(", assetTag=").append(this.assetTag.getValue());
        }

        if (actionGroup != null)
        {
            toStringBuilder.append(" actionGroup=").append(this.actionGroup);
        }

        if (chassisDescription != null)
        {
            toStringBuilder.append(", chassisDescription=").append(this.chassisDescription.getValue());
        }

        if (chassisType != null)
        {
            toStringBuilder.append(", chassisType=").append(this.chassisType);
        }

        if ((computerSystemUriPathList != null) && !computerSystemUriPathList.isEmpty())
        {
            toStringBuilder.append(", computerSystemUriPathList=");
            createPathString(toStringBuilder,
                             computerSystemUriPathList);
        }

        if (containedByUriPath != null)
        {
            toStringBuilder.append(", containedByUriPath=").append(this.containedByUriPath.getValue());
        }

        if ((containsList != null) && !containsList.isEmpty())
        {
            toStringBuilder.append(", containsList=");
            createPathString(toStringBuilder,
                             containsList);
        }

        if ((cooledByUriPathList != null) && !cooledByUriPathList.isEmpty())
        {
            toStringBuilder.append(", cooledByUriPathList=");
            createPathString(toStringBuilder,
                             cooledByUriPathList);
        }

        if (ledState != null)
        {
            toStringBuilder.append(", ledState=").append(this.ledState);
        }

        if (logServicesUriPath != null)
        {
            toStringBuilder.append(", logServicesUriPath=").append(this.logServicesUriPath);
        }

        if (manufacturerName != null)
        {
            toStringBuilder.append(", manufacturerName=").append(this.manufacturerName.getValue());
        }

        if (modelNumber != null)
        {
            toStringBuilder.append(", modelNumber=").append(this.modelNumber.getValue());
        }

        if (operatingHealth != null)
        {
            toStringBuilder.append(", operatingHealth=").append(this.operatingHealth);
        }

        if (operatingState != null)
        {
            toStringBuilder.append(", operatingState=").append(this.operatingState);
        }

        if (partNumber != null)
        {
            toStringBuilder.append(", partNumber=").append(this.partNumber.getValue());
        }

        if ((poweredByList != null) && !poweredByList.isEmpty())
        {
            toStringBuilder.append(", poweredByList=").append(this.poweredByList);
        }

        if (powerUriPath != null)
        {
            toStringBuilder.append(", powerUriPath=").append(this.powerUriPath.getValue());
        }

        if (serialNumber != null)
        {
            toStringBuilder.append(", serialNumber=").append(this.serialNumber.getValue());
        }

        if (sku != null)
        {
            toStringBuilder.append(", sku=").append(this.sku.getValue());
        }

        if ((systemManagerUriPathList != null) && !systemManagerUriPathList.isEmpty())
        {
            toStringBuilder.append(", systemManagerUriPathList=");
            createPathString(toStringBuilder,
                             systemManagerUriPathList);
        }

        if (thermalUriPath != null)
        {
            toStringBuilder.append(", thermalUriPath=").append(this.thermalUriPath.getValue());
        }

        toStringBuilder.append('}');

        return toStringBuilder.toString();
    }

    private static void createPathString(StringBuilder toStringBuilder,
                                         List<RedHxUriPath> pathList)
    {
        toStringBuilder.append("[");

        boolean isFirst = true;

        for (RedHxUriPath path : pathList)
        {
            if (!isFirst)
            {
                toStringBuilder.append(", ");
            }

            toStringBuilder.append(path.getValue());
            isFirst = false;
        }

        toStringBuilder.append("]");
    }

}
