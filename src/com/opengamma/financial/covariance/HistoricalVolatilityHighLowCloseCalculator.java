/**
 * Copyright (C) 2009 - 2009 by OpenGamma Inc.
 * 
 * Please see distribution for license.
 */
package com.opengamma.financial.covariance;

import java.util.Iterator;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opengamma.financial.timeseries.returns.ContinuouslyCompoundedRelativeTimeSeriesReturnCalculator;
import com.opengamma.financial.timeseries.returns.RelativeTimeSeriesReturnCalculator;
import com.opengamma.financial.timeseries.returns.TimeSeriesReturnCalculator;
import com.opengamma.util.CalculationMode;
import com.opengamma.util.timeseries.DoubleTimeSeries;

/**
 * The historical high-low-close volatility of a price time series can be calculated using:
 * {@latex.ilb %preamble{\\usepackage{amsmath}} 
 * \\begin{eqnarray*}
 * \\sigma = \\frac{1}{n}\\sum\\limits_{i=1}^n \\frac{rr_i}{2} - \\frac{1}{n}\\sum\\limits_{i=1}^n (2\\ln{2} - 1) r_i^2
 * \\end{eqnarray*}}
 * where {@latex.inline $rr_i$} is the {@latex.inline %preamble{\\usepackage{amsmath}} $i^\\text{th}$} period <b>relative</b> return of the high and low prices,
 * {@latex.inline $rr_i$} is the {@latex.inline %preamble{\\usepackage{amsmath}} $i^\\text{th}$} period return of the close price
 * and {@latex.inline $n$} is the number of data points in the price series. 
 * <p> 
 * Although any relative return calculator can be used, to get correct results the calculator should be a {@link ContinuouslyCompoundedRelativeTimeSeriesReturnCalculator}.
 */
public class HistoricalVolatilityHighLowCloseCalculator extends HistoricalVolatilityCalculator {
  private static final Logger s_logger = LoggerFactory.getLogger(HistoricalVolatilityHighLowCloseCalculator.class);
  private final TimeSeriesReturnCalculator _returnCalculator;
  private final RelativeTimeSeriesReturnCalculator _relativeReturnCalculator;

  /**
   * Creates a calculator with the given return and relative return calculation method and default values for the calculation mode and allowable percentage of bad data points
   * @param returnCalculator The return calculator
   * @param relativeReturnCalculator The relative return calculator
   * @throws IllegalArgumentException If the return calculator is null
   */
  public HistoricalVolatilityHighLowCloseCalculator(final TimeSeriesReturnCalculator returnCalculator, final RelativeTimeSeriesReturnCalculator relativeReturnCalculator) {
    super();
    Validate.notNull(returnCalculator, "return calculator");
    Validate.notNull(relativeReturnCalculator, "relative return calculator");
    _returnCalculator = returnCalculator;
    _relativeReturnCalculator = relativeReturnCalculator;
  }

  /**
   * Creates a calculator with the given return and relative return calculation method and default values for the calculation mode and allowable percentage of bad data points
   * @param returnCalculator The return calculator
   * @param relativeReturnCalculator The relative return calculator
   * @param mode The calculation mode
   * @throws IllegalArgumentException If the return calculator is null
   */
  public HistoricalVolatilityHighLowCloseCalculator(final TimeSeriesReturnCalculator returnCalculator, final RelativeTimeSeriesReturnCalculator relativeReturnCalculator, final CalculationMode mode) {
    super(mode);
    Validate.notNull(returnCalculator, "return calculator");
    Validate.notNull(relativeReturnCalculator, "relative return calculator");
    _returnCalculator = returnCalculator;
    _relativeReturnCalculator = relativeReturnCalculator;
  }

  /**
   * Creates a calculator with the given return and relative return calculation method and default values for the calculation mode and allowable percentage of bad data points
   * @param returnCalculator The return calculator
   * @param relativeReturnCalculator The relative return calculator
   * @param mode The calculation mode
   * @param percentBadDataPoints The maximum allowable percentage of bad data points
   * @throws IllegalArgumentException If the return calculator is null
   */
  public HistoricalVolatilityHighLowCloseCalculator(final TimeSeriesReturnCalculator returnCalculator, final RelativeTimeSeriesReturnCalculator relativeReturnCalculator, final CalculationMode mode,
      final double percentBadDataPoints) {
    super(mode, percentBadDataPoints);
    Validate.notNull(returnCalculator, "return calculator");
    Validate.notNull(relativeReturnCalculator, "relative return calculator");
    _returnCalculator = returnCalculator;
    _relativeReturnCalculator = relativeReturnCalculator;
  }

  /**
   * The array of time series assumes that the first series is the high price series, the second series the low and the third the close.
   * @param x The array of price time series
   * @return The historical close volatility
   * @throws IllegalArgumentException If the array is null or empty; if the first element of the array is null; if the array does not contain three time series; 
   * if the high, low and close time series do not satisfy the requirements (see {@link HistoricalVolatilityCalculator#testHighLowClose}); if the price series does not contain at 
   * least two data points 
   */
  @Override
  public Double evaluate(final DoubleTimeSeries<?>... x) {
    testTimeSeries(x, 2);
    if (x.length < 3) {
      throw new IllegalArgumentException("Need high, low and close time series to calculate high-low-close volatility");
    }
    if (x.length > 3) {
      s_logger.info("Time series array contained more than three series; only using the first three");
    }
    final DoubleTimeSeries<?> high = x[0];
    final DoubleTimeSeries<?> low = x[1];
    final DoubleTimeSeries<?> close = x[2];
    testHighLowClose(high, low, close);
    final DoubleTimeSeries<?> closeReturns = _returnCalculator.evaluate(close);
    final DoubleTimeSeries<?> highLowReturns = _relativeReturnCalculator.evaluate(new DoubleTimeSeries<?>[] {high, low});
    final Iterator<Double> highLowIterator = highLowReturns.valuesIterator();
    final Iterator<Double> closeReturnIterator = closeReturns.valuesIterator();
    double value, highLowValue;
    double sumHL = 0;
    double sum = 0;
    highLowIterator.next();
    while (closeReturnIterator.hasNext()) {
      value = closeReturnIterator.next();
      highLowValue = highLowIterator.next();
      sum += value * value;
      sumHL += highLowValue * highLowValue;
    }
    final int n = closeReturns.size();
    return Math.sqrt((0.5 * sumHL - (2 * Math.log(2) - 1) * sum) / n);
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((_relativeReturnCalculator == null) ? 0 : _relativeReturnCalculator.hashCode());
    result = prime * result + ((_returnCalculator == null) ? 0 : _returnCalculator.hashCode());
    return result;
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (!super.equals(obj)) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final HistoricalVolatilityHighLowCloseCalculator other = (HistoricalVolatilityHighLowCloseCalculator) obj;
    if (!ObjectUtils.equals(_relativeReturnCalculator, other._relativeReturnCalculator)) {
      return false;
    }
    return ObjectUtils.equals(_returnCalculator, other._returnCalculator);
  }

}
