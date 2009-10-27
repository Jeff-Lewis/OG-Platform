/**
 * Copyright (C) 2009 - 2009 by OpenGamma Inc.
 *
 * Please see distribution for license.
 */
package com.opengamma.engine.analytics;

import java.util.Collection;

import com.opengamma.engine.position.Position;

/**
 * 
 *
 * @author kirk
 */
public interface AggregatePositionAnalyticFunctionDefinition
extends AnalyticFunctionDefinition {

  /**
   * Determine whether this function is applicable to the collection of 
   * positions provided.
   * 
   * @param positions The positions to check.
   * @return {@code true} iff this function is potentially applicable to
   *         the provided position.
   */
  boolean isApplicableTo(Collection<Position> positions);
  
}
