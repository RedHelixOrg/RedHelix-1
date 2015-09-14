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
package org.redhelix.server.lib.reader.computer.system;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Set;
import org.redhelix.core.action.RedHxActionProperties;
import org.redhelix.core.annotations.Immutable;
import org.redhelix.core.computer.system.RedHxComputerSystem;
import org.redhelix.core.computer.system.RedHxComputerSystemBuilder;
import org.redhelix.core.service.root.RedHxServiceRootIdEum;
import org.redhelix.core.util.RedHxHttpResponseException;
import org.redhelix.core.util.RedHxOperatingStatus;
import org.redhelix.core.util.RedHxParseException;
import org.redhelix.core.util.RedHxUriPath;
import org.redhelix.server.lib.reader.util.AbstractRedfishJsonReader;
import org.redhelix.server.lib.reader.util.RedHxServerConnectionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * send HTTP get to a Redfish server and parse the JSON response into a RedHxChassis class.
 *
 * @since RedHelix Version 0.1
 * @author Hank Bruning
 *
 */
@Immutable
final class ComputerSystemReader extends AbstractRedfishJsonReader {

  private final static String JSON_KEY_ACTIONS = "Actions";
  private final static String JSON_KEY_ANOTATION_LOG_SERVICES = "LogServices";
  private final static String JSON_KEY_ASSET_TAG = "AssetTag";
  private final static String JSON_KEY_BIOS_VERSION = "BiosVersion";
  private final static String JSON_KEY_BOOT_SOURCE = "BootSource";
  private final static String JSON_KEY_BOOT_SOURCE_OVERRIDE_ENABLED = "BootSourceOverrideEnabled";
  private final static String JSON_KEY_BOOT_SOURCE_OVERRIDE_TARGET = "BootSourceOverrideTarget";
  private final static String JSON_KEY_COMPUTER_NAME = "Name";
  private final static String JSON_KEY_COMPUTER_STATUS = "Status";
  private final static String JSON_KEY_DESCRIPTION = "Description";
  private final static String JSON_KEY_ETHERNET_INTERFACES = "EthernetInterfaces";
  private final static String JSON_KEY_HOST_NAME = "HostName";
  private final static String JSON_KEY_ID = "Id";
  private final static String JSON_KEY_INDICATOR_LED = "IndicatorLED";
  private final static String JSON_KEY_LOG_SERVICES = "LogServices";
  private final static String JSON_KEY_MANUFACTURER = "Manufacturer";
  private final static String JSON_KEY_MEMORY_SUMMARY = "MemorySummary";
  private final static String JSON_KEY_MODEL = "Model";
  private final static String JSON_KEY_PART_NUMBER = "PartNumber";
  private final static String JSON_KEY_POWER_STATE = "PowerState";
  private final static String JSON_KEY_PROCESSORS = "Processors";
  private final static String JSON_KEY_PROCESSOR_SUMMARY = "ProcessorSummary";
  private final static String JSON_KEY_SERIAL_NUMBER = "SerialNumber";
  private final static String JSON_KEY_SIMPLE_STORAGE = "SimpleStorage";
  private final static String JSON_KEY_SKU = "SKU";
  private final static String JSON_KEY_SYSTEM_TYPE = "SystemType";
  private final static String JSON_KEY_UEFI_TARGET_BOOT_SOURCE_OVERRIDE =
      "UefiTargetBootSourceOverride";
  private final static String JSON_KEY_UUID = "UUID";
  private final static String JSON_LINK_KEY_CHASSIS = "Chassis"; // array of links
  private final static String JSON_LINK_KEY_COOLED_BY = "CooledBy"; // array of links
  private final static String JSON_LINK_KEY_MANAGED_BY = "ManagedBy"; // single link
  private final static String JSON_LINK_KEY_POWERED_BY = "PoweredBy"; // array of links

  //
  private final Logger logger;
  private final StringBuilder logMsgBuilder;

  ComputerSystemReader(RedHxServerConnectionContext ctx, RedHxUriPath pathToChassis)
      throws URISyntaxException, RedHxHttpResponseException {
    super(ctx, RedHxServiceRootIdEum.COMPUTER_SYSTEMS, pathToChassis);
    this.logger = LoggerFactory.getLogger(ComputerSystemReader.class);
    this.logMsgBuilder = new StringBuilder();
  }

  /**
   * parse the JSON response into a RedHxComputerSystem class. It create method sent the HTTP
   * request for the chassis message an validated that a JSON message had been received.
   *
   * @param ctx
   * @param chassisLink
   * @return
   * @throws RedHxHttpResponseException
   * @throws URISyntaxException
   */
  RedHxComputerSystem readComputerSystem()
      throws RedHxHttpResponseException, RedHxParseException, URISyntaxException {
    String tmpStr;
    RedHxComputerSystemBuilder builder = new RedHxComputerSystemBuilder(super.getUriPath());

    Set<RedHxActionProperties> actionSet = getActions(JSON_KEY_ACTIONS);

    /*
     * the sequence of reading the JSON file is organized in Alpha order by key name. The list of
     * static fields(above) is in alpha order.
     */
    if (actionSet != null) {
      builder.setActions(actionSet);
    }

    tmpStr = getAnnotation(JSON_KEY_ANOTATION_LOG_SERVICES);

    if (tmpStr != null) {
      builder.setPathToLogServices(tmpStr);
    }

    tmpStr = getOptionalProperty(JSON_KEY_ASSET_TAG);

    if (tmpStr != null) {
      builder.setAssetTag(tmpStr);
    }

    tmpStr = getOptionalProperty(JSON_KEY_BIOS_VERSION);

    if (tmpStr != null) {
      builder.setBiosVersion(tmpStr);
    }

    tmpStr = getOptionalProperty(JSON_KEY_BOOT_SOURCE);

    if (tmpStr != null) {
      builder.setBootSource(tmpStr);
    }

    tmpStr = getOptionalProperty(JSON_KEY_BOOT_SOURCE_OVERRIDE_ENABLED);

    if (tmpStr != null) {
      builder.setBootSourceOverride(tmpStr);
    }

    tmpStr = getOptionalProperty(JSON_KEY_BOOT_SOURCE_OVERRIDE_TARGET);

    if (tmpStr != null) {
      builder.setBootSource(tmpStr);
    }

    tmpStr = getOptionalProperty(JSON_KEY_COMPUTER_NAME);

    if (tmpStr != null) {
      builder.setComputerName(tmpStr);
    }

    /*
     * read computers operating status.
     */
    RedHxOperatingStatus computerOperatingStats = getOperatingStatus(JSON_KEY_COMPUTER_STATUS);

    if (computerOperatingStats != null) {
      builder.setComputerOperatingStatus(computerOperatingStats);
    }

    tmpStr = getOptionalProperty(JSON_KEY_DESCRIPTION);

    if (tmpStr != null) {
      builder.setDescription(tmpStr);
    }

    tmpStr = getOptionalProperty(JSON_KEY_ETHERNET_INTERFACES);

    if (tmpStr != null) {
      builder.setEthernetInterfaces(tmpStr);
    }

    tmpStr = getOptionalProperty(JSON_KEY_HOST_NAME);

    if (tmpStr != null) {
      builder.setHostname(tmpStr);
    }

    tmpStr = getOptionalProperty(JSON_KEY_ID);

    if (tmpStr != null) {
      builder.setComputerId(tmpStr);
    }

    tmpStr = getOptionalProperty(JSON_KEY_INDICATOR_LED);

    if (tmpStr != null) {
      builder.setIndicatorLed(tmpStr);
    }

    tmpStr = getLinkSingle(JSON_KEY_LOG_SERVICES);

    if (tmpStr != null) {
      builder.setPathToLogServices(tmpStr);
    }

    tmpStr = getOptionalProperty(JSON_KEY_MANUFACTURER);

    if (tmpStr != null) {
      builder.setManufacturerName(tmpStr);
    }

    RedHxOperatingStatus memoryOperatingStats = getOperatingStatus(JSON_KEY_MEMORY_SUMMARY);

    if (memoryOperatingStats != null) {
      builder.setMemoryOperatingStatus(memoryOperatingStats);
    }

    tmpStr = getOptionalProperty(JSON_KEY_MODEL);

    if (tmpStr != null) {
      builder.setModelNumber(tmpStr);
    }

    tmpStr = getOptionalProperty(JSON_KEY_PART_NUMBER);

    if (tmpStr != null) {
      builder.setPartNumber(tmpStr);
    }

    tmpStr = getOptionalProperty(JSON_KEY_POWER_STATE);

    if (tmpStr != null) {
      builder.setPowerState(tmpStr);
    }

    tmpStr = getOptionalProperty(JSON_KEY_PROCESSORS);

    if (tmpStr != null) {
      // todo throw new UnsupportedOperationException("Processors collection is not yet
      // implementd");
    }

    RedHxOperatingStatus processorOperatingStats = getOperatingStatus(JSON_KEY_PROCESSOR_SUMMARY);

    if (processorOperatingStats != null) {
      builder.setProcessorOperatingStatus(processorOperatingStats);
    }

    tmpStr = getOptionalProperty(JSON_KEY_SERIAL_NUMBER);

    if (tmpStr != null) {
      builder.setSerialNumber(tmpStr);
    }

    tmpStr = getOptionalProperty(JSON_KEY_SIMPLE_STORAGE);

    if (tmpStr != null) {
      // todo throw new UnsupportedOperationException("Simple storage collection is not yet
      // implementd");
    }

    tmpStr = getOptionalProperty(JSON_KEY_SKU);

    if (tmpStr != null) {
      builder.setComputerSku(tmpStr);
    }

    tmpStr = getOptionalProperty(JSON_KEY_SYSTEM_TYPE);

    if (tmpStr != null) {
      builder.setSystemType(tmpStr);
    }

    tmpStr = getOptionalProperty(JSON_KEY_UEFI_TARGET_BOOT_SOURCE_OVERRIDE);

    if (tmpStr != null) {
      builder.setBootUefiTarget(tmpStr);
    }

    tmpStr = getOptionalProperty(JSON_KEY_UUID);

    if (tmpStr != null) {
      builder.setUuid(tmpStr);
    }

    List<String> chassisList = getLinkArray(JSON_LINK_KEY_CHASSIS);

    if (chassisList != null) {
      builder.setChassisList(chassisList);
    }

    List<String> cooledByList = getLinkArray(JSON_LINK_KEY_COOLED_BY);

    if (cooledByList != null) {
      builder.setCooledByList(cooledByList);
    }

    List<String> systemManagerList = getLinkArray(JSON_LINK_KEY_MANAGED_BY);

    if (systemManagerList != null) {
      builder.setSystemManagerList(systemManagerList);
    }

    List<String> poweredByList = getLinkArray(JSON_LINK_KEY_POWERED_BY);

    if (poweredByList != null) {
      builder.setPoweredByList(poweredByList);
    }

    //
    final RedHxComputerSystem computer = builder.getInstance();

    return computer;
  }

  private void logDebug(String msg) {
    logMsgBuilder.append("Reading ");
    logMsgBuilder.append(super.getUriPath().getValue());
    logMsgBuilder.append(" : ");
    logMsgBuilder.append(msg);
    logger.debug(logMsgBuilder.toString());
    logMsgBuilder.setLength(0);
  }
}
