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
package org.redhelix.core.computer.system.power;

import org.redhelix.core.annotations.Immutable;
import org.redhelix.core.sensor.RedHxAbstractSensor;
import org.redhelix.core.sensor.RedHxSensorNameImpl;
import org.redhelix.core.sensor.RedHxSensorNumberImpl;

/**
 *
 * @since RedHelix Version 0.1
 * @author Hank Bruning
 */
@Immutable
public class RedHxPowerVoltageSensorImpl extends RedHxAbstractSensor
    implements RedHxPowerVoltageSensor {

  private final RedHxPowerVoltageReading voltage;

  /**
   * @param sensorName
   * @param sensorNumber
   * @param voltage
   */
  public RedHxPowerVoltageSensorImpl(RedHxSensorNameImpl sensorName,
      RedHxSensorNumberImpl sensorNumber, final RedHxPowerVoltageReading voltage) {
    super(sensorName, sensorNumber);
    this.voltage = voltage;
  }

  /**
   * @return the voltage
   */
  @Override
  public RedHxPowerVoltageReading getVoltage() {
    return voltage;
  }
}
