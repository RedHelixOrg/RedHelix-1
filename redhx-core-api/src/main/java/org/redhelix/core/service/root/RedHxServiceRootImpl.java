/*
 * Copyright 2015 JBlade LLC
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package org.redhelix.core.service.root;

import org.redhelix.core.account.service.RedHxAccountService;
import org.redhelix.core.chassis.RedHxChassisCollection;
import org.redhelix.core.computer.system.RedHxComputerSystemCollection;
import org.redhelix.core.event.service.RedHxEventService;
import org.redhelix.core.manager.collection.RedHxManagerCollection;
import org.redhelix.core.message.vignette.RedHxMessageVignette;
import org.redhelix.core.message.vignette.registry.file.collection.RedHxMessageVignetteRegistryFileCollection;
import org.redhelix.core.schema.file.collection.RedHxSchemaFileCollection;
import org.redhelix.core.session.collection.RedHxSessionCollection;
import org.redhelix.core.session.service.RedHxSessionService;
import org.redhelix.core.task.service.RedHxTaskService;
import org.redhelix.core.util.RedHxRedfishProtocolVersionEnum;
import org.redhelix.core.util.RedHxRedfishSchemaVersionEnum;

/**
 * 
 * Git SHA: $Id$
 * 
 * @since RedHelix Version 0.0.1
 * @author Hank Bruning
 */
public class RedHxServiceRootImpl implements RedHxServiceRoot
{
    private final RedHxAccountService accountService;
    private final RedHxChassisCollection chassisCollection;

    //
    // "Systems": {
    // "@odata.id": "/redfish/v1/Systems"
    // },
    // "Chassis": {
    // "@odata.id": "/redfish/v1/Chassis"
    // },
    // "Managers": {
    // "@odata.id": "/redfish/v1/Managers"
    // },
    // "Tasks": {
    // "@odata.id": "/redfish/v1/TaskService"
    // },
    // "SessionService": {
    // "@odata.id": "/redfish/v1/SessionService"
    // },
    // "AccountService": {
    // "@odata.id": "/redfish/v1/AccountService"
    // },
    // "EventService": {
    // "@odata.id": "/redfish/v1/EventService"
    // },
    // "Registries": {
    // "@odata.id": "/redfish/v1/Registries"
    // },
    // "JsonSchemas": {
    // "@odata.id": "/redfish/v1/JsonSchemas"
    // },
    // "Links": {
    // "Sessions": {
    // "@odata.id": "/redfish/v1/SessionService/Sessions"
    // }
    // },
    // "Oem": {}

    private final RedHxComputerSystemCollection computerSystemCollection;
    private final RedHxEventService eventService;
    private final RedHxManagerCollection managerCollection;
    private final RedHxMessageVignette messageVignette;
    private final RedHxMessageVignetteRegistryFileCollection messageVignetteRegistryFileCollection;
    private final RedHxRedfishProtocolVersionEnum protocolVersion;
    private final RedHxSchemaFileCollection schemaFileCollection;
    private final RedHxRedfishSchemaVersionEnum schemaVersion;
    private final RedHxSessionCollection sessionCollection;
    private final RedHxSessionService SessionService;
    private final RedHxTaskService taskService;

    /**
     * @param protocolVersion
     * @param schemaVersion
     * @param accountService
     * @param chassisCollection
     * @param computerSystemCollection
     * @param eventService
     * @param managerCollection
     * @param messageVignette
     * @param messageVignetteRegistryFileCollection
     * @param schemaFileCollection
     * @param sessionCollection
     * @param sessionService
     * @param taskService
     */
    public RedHxServiceRootImpl(
            RedHxRedfishProtocolVersionEnum protocolVersion,
            RedHxRedfishSchemaVersionEnum schemaVersion,
            RedHxAccountService accountService,
            RedHxChassisCollection chassisCollection,
            RedHxComputerSystemCollection computerSystemCollection,
            RedHxEventService eventService,
            RedHxManagerCollection managerCollection,
            RedHxMessageVignette messageVignette,
            RedHxMessageVignetteRegistryFileCollection messageVignetteRegistryFileCollection,
            RedHxSchemaFileCollection schemaFileCollection,
            RedHxSessionCollection sessionCollection,
            RedHxSessionService sessionService,
            RedHxTaskService taskService)
    {
        super();
        this.protocolVersion = protocolVersion;
        this.schemaVersion = schemaVersion;
        this.accountService = accountService;
        this.chassisCollection = chassisCollection;
        this.computerSystemCollection = computerSystemCollection;
        this.eventService = eventService;
        this.managerCollection = managerCollection;
        this.messageVignette = messageVignette;
        this.messageVignetteRegistryFileCollection = messageVignetteRegistryFileCollection;
        this.schemaFileCollection = schemaFileCollection;
        this.sessionCollection = sessionCollection;
        SessionService = sessionService;
        this.taskService = taskService;
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
        final RedHxServiceRootImpl other = (RedHxServiceRootImpl) obj;
        if (SessionService == null)
        {
            if (other.SessionService != null)
            {
                return false;
            }
        }
        else if (!SessionService.equals(other.SessionService))
        {
            return false;
        }
        if (accountService == null)
        {
            if (other.accountService != null)
            {
                return false;
            }
        }
        else if (!accountService.equals(other.accountService))
        {
            return false;
        }
        if (chassisCollection == null)
        {
            if (other.chassisCollection != null)
            {
                return false;
            }
        }
        else if (!chassisCollection.equals(other.chassisCollection))
        {
            return false;
        }
        if (computerSystemCollection == null)
        {
            if (other.computerSystemCollection != null)
            {
                return false;
            }
        }
        else if (!computerSystemCollection.equals(other.computerSystemCollection))
        {
            return false;
        }
        if (eventService == null)
        {
            if (other.eventService != null)
            {
                return false;
            }
        }
        else if (!eventService.equals(other.eventService))
        {
            return false;
        }
        if (managerCollection == null)
        {
            if (other.managerCollection != null)
            {
                return false;
            }
        }
        else if (!managerCollection.equals(other.managerCollection))
        {
            return false;
        }
        if (messageVignette == null)
        {
            if (other.messageVignette != null)
            {
                return false;
            }
        }
        else if (!messageVignette.equals(other.messageVignette))
        {
            return false;
        }
        if (messageVignetteRegistryFileCollection == null)
        {
            if (other.messageVignetteRegistryFileCollection != null)
            {
                return false;
            }
        }
        else if (!messageVignetteRegistryFileCollection.equals(other.messageVignetteRegistryFileCollection))
        {
            return false;
        }
        if (protocolVersion != other.protocolVersion)
        {
            return false;
        }
        if (schemaFileCollection == null)
        {
            if (other.schemaFileCollection != null)
            {
                return false;
            }
        }
        else if (!schemaFileCollection.equals(other.schemaFileCollection))
        {
            return false;
        }
        if (schemaVersion != other.schemaVersion)
        {
            return false;
        }
        if (sessionCollection == null)
        {
            if (other.sessionCollection != null)
            {
                return false;
            }
        }
        else if (!sessionCollection.equals(other.sessionCollection))
        {
            return false;
        }
        if (taskService == null)
        {
            if (other.taskService != null)
            {
                return false;
            }
        }
        else if (!taskService.equals(other.taskService))
        {
            return false;
        }
        return true;
    }

    public RedHxAccountService getAccountService()
    {
        return accountService;
    }

    public RedHxChassisCollection getChassisCollection()
    {
        return chassisCollection;
    }

    public RedHxComputerSystemCollection getComputerSystemCollection()
    {
        return computerSystemCollection;
    }

    public RedHxEventService getEventService()
    {
        return eventService;
    }

    public RedHxManagerCollection getManagerCollection()
    {
        return managerCollection;
    }

    public RedHxMessageVignette getMessageVignette()
    {
        return messageVignette;
    }

    public RedHxMessageVignetteRegistryFileCollection getMessageVignetteRegistryFileCollection()
    {
        return messageVignetteRegistryFileCollection;
    }

    public RedHxRedfishProtocolVersionEnum getProtocolVersion()
    {
        return protocolVersion;
    }

    public RedHxSchemaFileCollection getSchemaFileCollection()
    {
        return schemaFileCollection;
    }

    public RedHxRedfishSchemaVersionEnum getSchemaVersion()
    {
        return schemaVersion;
    }

    public RedHxSessionCollection getSessionCollection()
    {
        return sessionCollection;
    }

    public RedHxSessionService getSessionService()
    {
        return SessionService;
    }

    public RedHxTaskService getTaskService()
    {
        return taskService;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((SessionService == null) ? 0 : SessionService.hashCode());
        result = prime * result + ((accountService == null) ? 0 : accountService.hashCode());
        result = prime * result + ((chassisCollection == null) ? 0 : chassisCollection.hashCode());
        result = prime * result + ((computerSystemCollection == null) ? 0 : computerSystemCollection.hashCode());
        result = prime * result + ((eventService == null) ? 0 : eventService.hashCode());
        result = prime * result + ((managerCollection == null) ? 0 : managerCollection.hashCode());
        result = prime * result + ((messageVignette == null) ? 0 : messageVignette.hashCode());
        result = prime * result + ((messageVignetteRegistryFileCollection == null) ? 0 : messageVignetteRegistryFileCollection.hashCode());
        result = prime * result + ((protocolVersion == null) ? 0 : protocolVersion.hashCode());
        result = prime * result + ((schemaFileCollection == null) ? 0 : schemaFileCollection.hashCode());
        result = prime * result + ((schemaVersion == null) ? 0 : schemaVersion.hashCode());
        result = prime * result + ((sessionCollection == null) ? 0 : sessionCollection.hashCode());
        result = prime * result + ((taskService == null) ? 0 : taskService.hashCode());
        return result;
    }

}
