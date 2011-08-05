/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.core.security.impl;

import java.util.Collection;
import java.util.Map;

import com.opengamma.core.security.Security;
import com.opengamma.core.security.SecuritySource;
import com.opengamma.id.ExternalIdBundle;
import com.opengamma.id.UniqueId;
import com.opengamma.id.UniqueIdSchemeDelegator;
import com.opengamma.util.ArgumentChecker;

/**
 * A source of securities that uses the scheme of the unique identifier to determine which
 * underlying source should handle the request.
 * <p>
 * If no scheme-specific handler has been registered, a default is used.
 */
public class DelegatingSecuritySource extends UniqueIdSchemeDelegator<SecuritySource> implements SecuritySource {

  /**
   * Creates an instance specifying the default delegate.
   * 
   * @param defaultSource  the source to use when no scheme matches, not null
   */
  public DelegatingSecuritySource(SecuritySource defaultSource) {
    super(defaultSource);
  }

  /**
   * Creates an instance specifying the default delegate.
   * 
   * @param defaultSource  the source to use when no scheme matches, not null
   * @param schemePrefixToSourceMap  the map of sources by scheme to switch on, not null
   */
  public DelegatingSecuritySource(SecuritySource defaultSource, Map<String, SecuritySource> schemePrefixToSourceMap) {
    super(defaultSource, schemePrefixToSourceMap);
  }

  //-------------------------------------------------------------------------
  @Override
  public Security getSecurity(UniqueId uniqueId) {
    ArgumentChecker.notNull(uniqueId, "uniqueId");
    return chooseDelegate(uniqueId).getSecurity(uniqueId);
  }

  @Override
  public Collection<Security> getSecurities(ExternalIdBundle bundle) {
    ArgumentChecker.notNull(bundle, "bundle");
    // best implementation is to return first matching result
    for (SecuritySource delegateSource : getDelegates().values()) {
      Collection<Security> result = delegateSource.getSecurities(bundle);
      if (!result.isEmpty()) {
        return result;
      }
    }
    return getDefaultDelegate().getSecurities(bundle);
  }

  @Override
  public Security getSecurity(ExternalIdBundle bundle) {
    ArgumentChecker.notNull(bundle, "bundle");
    // best implementation is to return first matching result
    for (SecuritySource delegateSource : getDelegates().values()) {
      Security result = delegateSource.getSecurity(bundle);
      if (result != null) {
        return result;
      }
    }
    return getDefaultDelegate().getSecurity(bundle);
  }

}
