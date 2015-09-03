/*
 * Copyright 2015 JBlade LLC
 *
 * Licensed under the Apache License, Version 2.0 (the License); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an AS IS BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied. See the License for the specific language governing permissions and limitations under
 * the License
 */



package org.redhelix.core.computer.system.id;

/**
 *
 *
 *
 * @since RedHelix Version 0.1 It will be replaced when checked in to the master branch
 * @author Hank Bruning
 *
 */
public enum RedHxComputerBootSourceEnum
{
    BIOSSETUP("Boot to the BIOS Setup Utility"),
    CD("Boot from the CD/DVD disc"),
    DIAGS("Boot the manufacturer's Diagnostics program"),
    FLOPPY("Boot from the floppy disk drive"),
    HDD("Boot from a hard drive"),
    NONE("Boot from the normal boot device"),
    PXE("Boot from the Pre-Boot EXecution (PXE) environment"),
    UEFI_SHELL("Boot to the UEFI Shell"),
    UEFI_TARGET("Boot to the UEFI Device specified in the UefiTargetBootSourceOverride property"),
    USB("Boot from a USB device as specified by the system BIOS"),
    UTILITIES("Boot the manufacturer's Utilities program(s)");

    private final String desc;

    private RedHxComputerBootSourceEnum( String desc )
    {
        this.desc = desc;
    }

    public String getDescription( )
    {
        return desc;
    }
}
