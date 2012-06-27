/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.masterdb.security;

import java.net.URI;

import javax.time.Instant;

import com.opengamma.core.change.ChangeManager;
import com.opengamma.master.security.impl.RemoteSecurityMaster;
import com.opengamma.masterdb.TimeOverrideRequest;

/**
 * Provides remote access to a {@link DbSecurityMaster}.
 */
public class RemoteDbSecurityMaster extends RemoteSecurityMaster {

  /**
   * Creates an instance.
   * 
   * @param baseUri  the base target URI for all RESTful web services, not null
   */
  public RemoteDbSecurityMaster(final URI baseUri) {
    super(baseUri);
  }

  /**
   * Creates an instance.
   * 
   * @param baseUri  the base target URI for all RESTful web services, not null
   * @param changeManager  the change manager, not null
   */
  public RemoteDbSecurityMaster(final URI baseUri, ChangeManager changeManager) {
    super(baseUri, changeManager);
  }
  
  //-------------------------------------------------------------------------
  /**
   * Overrides the current time seen by the remote security master to a fixed instant.
   * 
   * @param instant  the instant, or null to remove an existing override.
   */
  public void setTimeOverride(final Instant instant) {
    URI uri = DataDbSecurityMasterResource.uriTimeOverride(getBaseUri());
    TimeOverrideRequest request = new TimeOverrideRequest();
    request.setTimeOverride(instant);
    accessRemote(uri).put(request);
  }
  
}
