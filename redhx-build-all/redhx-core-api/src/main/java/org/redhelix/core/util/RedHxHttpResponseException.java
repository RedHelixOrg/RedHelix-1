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



package org.redhelix.core.util;

import org.redhelix.core.service.root.RedHxServiceRootIdEum;

/**
 * Identify a problem receiving a HTTP response. This contains the HTTP error codes.
 *
 *
 *
 * @since RedHelix Version 0.1
 * @author Hank Bruning
 *
 */
public class RedHxHttpResponseException
        extends Exception
{
    private final int                   httpErrorCode;
    private final RedHxServiceRootIdEum serviceRootId;

    public RedHxHttpResponseException( final RedHxServiceRootIdEum serviceRootId,
                                       int                         httpErrorCode,
                                       String                      message )
    {
        super(message);
        this.serviceRootId = serviceRootId;
        this.httpErrorCode = httpErrorCode;
    }

    public int getHttpErrorCode( )
    {
        return httpErrorCode;
    }

    public RedHxServiceRootIdEum getServiceRootId( )
    {
        return serviceRootId;
    }
}
