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

import java.net.URISyntaxException;
import java.util.List;
import java.util.Set;
import org.redhelix.core.action.RedHxActionProperties;
import org.redhelix.core.chassis.RedHxChassis;
import org.redhelix.core.chassis.RedHxChassisBuilder;
import org.redhelix.core.chassis.RedHxChassisTypeEnum;
import org.redhelix.core.service.root.RedHxServiceRootIdEum;
import org.redhelix.core.util.RedHxHttpResponseException;
import org.redhelix.core.util.RedHxParseException;
import org.redhelix.core.util.RedHxUriPath;
import org.redhelix.server.lib.reader.util.AbstractRedfishJsonReader;
import org.redhelix.server.lib.reader.util.RedHxServerConnectionContext;

/**
 * send HTTP get to a Redfish server and parse the JSON response into a RedHxChassis class.
 *
 * @since RedHelix Version 0.1
 * @author Hank Bruning
 *
 */
final class ChassisReader extends AbstractRedfishJsonReader {

  private final static String JSON_ANOTATION_KEY_POWER = "Power";
  private final static String JSON_ANOTATION_KEY_THERMAL = "Thermal";
  private final static String JSON_ANOTATION_LOG_SERVICES = "LogServices";
  private final static String JSON_KEY_ACTIONS = "Actions";
  private final static String JSON_KEY_ASSET_TAG = "AssetTag";
  private final static String JSON_KEY_CHASSIS_TYPE = "ChassisType"; // required by the JSON schema
  private final static String JSON_KEY_DESCRIPTION = "Description";
  private final static String JSON_KEY_ID = "Id";
  private final static String JSON_KEY_INDICATOR_LED = "IndicatorLED";
  private final static String JSON_KEY_LOG_SERVICES = "LogServices";
  private final static String JSON_KEY_MANUFACTURER = "Manufacturer";
  private final static String JSON_KEY_MODEL = "Model";
  private final static String JSON_KEY_CHASSIS_NAME = "Name";
  private final static String JSON_KEY_PART_NUMBER = "PartNumber";
  private final static String JSON_KEY_SERIAL_NUMBER = "SerialNumber";
  private final static String JSON_KEY_SKU = "SKU";
  private final static String JSON_KEY_STATUS = "Status";
  private final static String JSON_LINK_KEY_COMPUTER_SYSTEM = "ComputerSystems"; // array of links
  private final static String JSON_LINK_KEY_CONTAINED_BY = "ContainedBy"; // single link
  private final static String JSON_LINK_KEY_CONTAINS = "Contains"; // array of links
  private final static String JSON_LINK_KEY_COOLED_BY = "CooledBy"; // array of links
  private final static String JSON_LINK_KEY_MANAGED_BY = "ManagedBy"; // single link
  private final static String JSON_LINK_KEY_POWERED_BY = "PoweredBy"; // array of links
  private final static String JSON_SUB_KEY_STATUS_HEALTH = "Health";
  private final static String JSON_SUB_KEY_STATUS_STATE = "State";

  ChassisReader(RedHxServerConnectionContext ctx, RedHxUriPath pathToChassis)
      throws URISyntaxException, RedHxHttpResponseException {
    super(ctx, RedHxServiceRootIdEum.CHASSIS, pathToChassis);
  }

  /**
   * parse the JSON response into a RedHxChassis class. It create method sent the HTTP request for
   * the chassis message an validated that a JSON message had been received.
   *
   * @param ctx
   * @param chassisLink
   * @return
   * @throws RedHxHttpResponseException
   * @throws URISyntaxException
   */
  RedHxChassis readChassis()
      throws RedHxHttpResponseException, RedHxParseException, URISyntaxException {
    String tmpStr;

    tmpStr = getOptionalProperty(JSON_KEY_CHASSIS_TYPE);

    RedHxChassisTypeEnum chassisType = RedHxChassisBuilder.convertChassisType(tmpStr);

    if (chassisType == null) {
      throw new RedHxParseException(RedHxServiceRootIdEum.CHASSIS,
          "The path to " + super.getUriPath().getValue()
              + " for the chassis does not contain the required field \"" + JSON_KEY_CHASSIS_TYPE
              + "\" in the JSON message.");
    }

    RedHxChassisBuilder builder = new RedHxChassisBuilder(chassisType);

    /*
     * read anotations
     */
    tmpStr = getAnnotation(JSON_ANOTATION_KEY_POWER);

    if (tmpStr != null) {
      builder.setPathToPower(tmpStr);
    }

    tmpStr = getAnnotation(JSON_ANOTATION_KEY_THERMAL);

    if (tmpStr != null) {
      builder.setPathToThermal(tmpStr);
    }

    tmpStr = getAnnotation(JSON_ANOTATION_LOG_SERVICES);

    if (tmpStr != null) {
      builder.setPathToLogServices(tmpStr);
    }

    /**
     * read actions
     */
    Set<RedHxActionProperties> actionSet = getActions(JSON_KEY_ACTIONS);

    if (tmpStr != null) {
      builder.setActions(actionSet);
    }

    /*
     * read values with two keys
     */
    tmpStr = getComplexValue(JSON_KEY_STATUS, JSON_SUB_KEY_STATUS_HEALTH);

    if (tmpStr != null) {
      builder.setStatusHealth(tmpStr);
    }

    tmpStr = getComplexValue(JSON_KEY_STATUS, JSON_SUB_KEY_STATUS_STATE);

    if (tmpStr != null) {
      builder.setOperatingState(tmpStr);
    }

    /*
     * read properties
     */
    tmpStr = getOptionalProperty(JSON_KEY_ASSET_TAG);

    if (tmpStr != null) {
      builder.setAssetTag(tmpStr);
    }

    tmpStr = getOptionalProperty(JSON_KEY_DESCRIPTION);

    if (tmpStr != null) {
      builder.setChassisDescription(tmpStr);
    }

    tmpStr = getOptionalProperty(JSON_KEY_ID);

    if (tmpStr != null) {
      builder.setChassisId(tmpStr);
    }

    tmpStr = getOptionalProperty(JSON_KEY_INDICATOR_LED);

    if (tmpStr != null) {
      builder.setIndicatorLed(tmpStr);
    }

    tmpStr = getOptionalProperty(JSON_KEY_MANUFACTURER);

    if (tmpStr != null) {
      builder.setManufacturerName(tmpStr);
    }

    tmpStr = getOptionalProperty(JSON_KEY_LOG_SERVICES);

    if (tmpStr != null) {
      builder.setPathToLogServices(tmpStr);
    }

    tmpStr = getOptionalProperty(JSON_KEY_MODEL);

    if (tmpStr != null) {
      builder.setModelNumber(tmpStr);
    }

    tmpStr = getOptionalProperty(JSON_KEY_CHASSIS_NAME);

    if (tmpStr != null) {
      builder.setChassisName(tmpStr);
    }

    tmpStr = getOptionalProperty(JSON_KEY_PART_NUMBER);

    if (tmpStr != null) {
      builder.setPartNumber(tmpStr);
    }

    tmpStr = getOptionalProperty(JSON_KEY_SERIAL_NUMBER);

    if (tmpStr != null) {
      builder.setSerialNumber(tmpStr);
    }

    tmpStr = getOptionalProperty(JSON_KEY_SKU);

    if (tmpStr != null) {
      builder.setSku(tmpStr);
    }

    /*
     * read single links
     */
    tmpStr = getLinkSingle(JSON_LINK_KEY_CONTAINED_BY);

    if (tmpStr != null) {
      builder.setContainedByLink(tmpStr);
    }

    /*
     * read lists of links.
     */
    List<String> computerSystemList = getLinkArray(JSON_LINK_KEY_COMPUTER_SYSTEM);

    if (computerSystemList != null) {
      builder.setComputerSystemList(computerSystemList);
    }

    List<String> systemManagerList = getLinkArray(JSON_LINK_KEY_MANAGED_BY);

    if (systemManagerList != null) {
      builder.setSystemManagerList(systemManagerList);
    }

    List<String> containsList = getLinkArray(JSON_LINK_KEY_CONTAINS);

    if (containsList != null) {
      builder.setContainsList(containsList);
    }

    List<String> cooledByList = getLinkArray(JSON_LINK_KEY_COOLED_BY);

    if (cooledByList != null) {
      builder.setCooledByList(cooledByList);
    }

    List<String> poweredByList = getLinkArray(JSON_LINK_KEY_POWERED_BY);

    if (poweredByList != null) {
      builder.setPoweredByList(poweredByList);
    }

    //
    final RedHxChassis chassis = builder.getInstance();

    return chassis;
  }
}
