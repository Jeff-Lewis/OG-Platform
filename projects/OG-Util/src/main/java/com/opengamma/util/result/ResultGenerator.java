/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.util.result;

import java.util.Arrays;
import java.util.List;

import com.google.common.collect.Lists;

/**
 * Factory class for {@link Result} objects.
 *
 * <p/>
 * <h3>Typical usage pattern:</h3>
 * <pre>
 *
 * import static com.opengamma.util.result.ResultGenerator.failure;
 * import static com.opengamma.util.result.ResultGenerator.propagateFailure;
 * import static com.opengamma.util.result.ResultGenerator.success;
 * import static com.opengamma.util.result.FailureStatus.CALCULATION_FAILED;
 *
 * public class Calculations {
 *
 *   public Result<CalculatedValue> calculate() {
 *
 *     Result<InterimValue> interimResult = doInitialCalculation();
 *     if (interimResult.isValueAvailable()) {
 *       InterimValue value = interimResult.getValue();
 *       Calculator calculator = getCalculator()
 *
 *       if (calculator.isCalculationPossible(value)) {
 *         return calculator.doComplexCalculation(value);
 *       } else {
 *         return failure(CALCULATION_FAILED,
 *             "Cannot calculate as value {} is incompatible with calculator {}", value, calculator);
 *       }
 *     } else {
 *       return propagateFailure(interimResult);
 *     }
 *   }
 * }
 *
 * </pre>
 */
public class ResultGenerator {

  /**
   * Generate a result object indicating that a function completed successfully
   * with the supplied value as the result.
   *
   * @param <T> the type of the value to be returned
   * @param value  the value that the invoked function returned
   * @return a result object wrapping the actual function invocation result, not null
   */
  public static <T> Result<T> success(T value) {
    return new SuccessResult<>(value);
  }

  /**
   * Generate a result object indicating that a function did not complete
   * successfully.
   *
   * @param status  an indication of why the invocation failed, not null
   * @param message  a detailed parameterized message indicating why the function
   *  invocation failed, uses SLF4J placeholders for parameters, not null
   * @param messageArgs  the arguments to be used for the formatted message, not null
   * @param <T> the type of the value which would have been returned if successful
   * @return a result object wrapping the failure details, not null
   */
  public static <T> Result<T> failure(FailureStatus status, String message, Object... messageArgs) {
    return new FailureResult<>(status, message, messageArgs);
  }

  /**
   * Propagate a failure result, ensuring that its generic type signature
   * matches the one required.
   *
   * @param <T>  the required type of the new result object
   * @param result  the failure to be propagated, not null
   * @return the new function result object, not null
   */
  public static <T> Result<T> propagateFailure(Result<?> result) {
    // todo remove the cast
    FailureResult<?> failureFunctionResult = (FailureResult<?>) result;
    return new FailureResult<>(failureFunctionResult.getStatus(), failureFunctionResult.getErrorMessage());
  }

  /**
   * Transforms a result containing a type R into one containing a type T.
   * If the passed result is a failure then the original object will be passed through unaltered.
   *
   * @param <R>  the type of the result object to be transformed
   * @param <T>  the required type of the new result object
   * @param result  the result to be transformed, not null
   * @param mapper  the mapper object to transform the value with, not null
   * @return the new function result object, not null
   */
  public static <R, T> Result<T> map(Result<R> result, ResultMapper<R, T> mapper) {
    if (result.isValueAvailable()) {
      return mapper.map(result.getValue());
    } else {
      return propagateFailure(result);
    }
  }

  /**
   * Check a set of results to see if there are any failures.
   *
   * @param results  the set of results to be checked, not null
   * @return true if the set of results contains at least one failure
   */
  public static boolean anyFailures(Result<?>... results) {
    for (Result<?> result : results) {
      if (!result.isValueAvailable()) {
        return true;
      }
    }
    return false;
  }

  /**
   * Propagate a set of results as a failure such that multiple failure reasons
   * can be recorded. Any successes included in the set are ignored. If there
   * are no failures in the set then an exception will be thrown.
   *
   * @param <T>  the required type of the new result object
   * @param result1  result to be included in the combined result, not null
   * @param result2  result to be included in the combined result, not null
   * @param results  results to be included in the combined result, not null
   * @return the new function result object
   * @throws IllegalArgumentException if there are no failures in the results set
   */
  // results can include successes which are ignored
  public static <T> Result<T> propagateFailures(Result<?> result1,
                                                        Result<?> result2,
                                                        Result<?>... results) {

    // todo - what if one of the results was itself a MultipleFailureResult?
    List<Result<?>> resultList = Lists.newArrayListWithCapacity(results.length + 2);
    resultList.add(result1);
    resultList.add(result2);
    resultList.addAll(Arrays.asList(results));
    List<Result<?>> failures = Lists.newArrayList();
    for (Result<?> result : resultList) {
      if (result instanceof FailureResult) {
        failures.add((FailureResult<?>) result);
      }
    }
    if (failures.isEmpty()) {
      throw new IllegalArgumentException("No failures found in " + failures);
    }
    return new MultipleFailureResult<>(failures);
  }

  //-------------------------------------------------------------------------
  /**
   * Functional interface that can transform a result.
   * 
   * @param <R> the result type
   * @param <T> the type of the mapped result
   */
  public interface ResultMapper<R, T> {

    /**
     * Transforms the input.
     * 
     * @param result  the result to be transformed
     * @return the result
     */
    Result<T> map(R result);
  }

}
