/**
 * Copyright (C) 2009 - 2010 by OpenGamma Inc.
 *
 * Please see distribution for license.
 */
package com.opengamma.financial.timeseries.util;

import java.util.List;

import org.apache.commons.lang.Validate;

import com.opengamma.util.timeseries.DoubleTimeSeries;

/**
 * Utility class for test that are performed often on time series.
 */
public class TimeSeriesDataTestUtils {

  /**
   * Tests that the time series is not null or empty
   * @param ts The time series
   * @throws IllegalArgumentException If the time series is null or empty
   */
  public static void testNotNullOrEmpty(final DoubleTimeSeries<?> ts) {
    Validate.notNull(ts, "time series");
    Validate.isTrue(!ts.isEmpty(), "time series");
  }

  /**
   * Tests that the time series has a minimum amount of data
   * @param ts The time series
   * @param minLength The minimum size
   * @throws IllegalArgumentException If the time series is null, empty, or contains fewer elements than the minimum size, if the minimum size is less than zero
   */
  public static void testTimeSeriesSize(final DoubleTimeSeries<?> ts, final int minLength) {
    testNotNullOrEmpty(ts);
    Validate.isTrue(minLength >= 0);
    Validate.isTrue(ts.size() >= minLength, "time series must contain at least " + minLength + " values");
  }

  /**
   * Tests that the two time series contain the same dates
   * @param ts1 The first time series
   * @param ts2 The second time series
   * @throws IllegalArgumentException If either time series is: null; empty; contains fewer than two data points; are not the same length; do not contain the same dates 
   */
  public static void testTimeSeriesDates(final DoubleTimeSeries<?> ts1, final DoubleTimeSeries<?> ts2) {
    testNotNullOrEmpty(ts1);
    testNotNullOrEmpty(ts2);
    if (ts1.size() != ts2.size()) {
      throw new IllegalArgumentException("Time series were not the same length; have " + ts1.size() + " and " + ts2.size());
    }
    final List<?> times1 = ts1.times();
    final List<?> times2 = ts2.times();
    for (final Object t : times1) {
      if (!times2.contains(t)) {
        throw new IllegalArgumentException("Time series did not contain the same dates");
      }
    }
  }
}
