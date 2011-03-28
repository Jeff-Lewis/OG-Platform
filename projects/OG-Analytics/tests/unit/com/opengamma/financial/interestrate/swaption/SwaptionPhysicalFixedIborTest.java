package com.opengamma.financial.interestrate.swaption;

import static org.testng.AssertJUnit.assertEquals;

import javax.time.calendar.LocalDate;
import javax.time.calendar.Period;
import javax.time.calendar.ZonedDateTime;

import org.testng.annotations.Test;

import com.opengamma.financial.convention.businessday.BusinessDayConvention;
import com.opengamma.financial.convention.businessday.BusinessDayConventionFactory;
import com.opengamma.financial.convention.calendar.Calendar;
import com.opengamma.financial.convention.calendar.MondayToFridayCalendar;
import com.opengamma.financial.convention.daycount.DayCount;
import com.opengamma.financial.convention.daycount.DayCountFactory;
import com.opengamma.financial.instrument.annuity.AnnuityCouponFixedDefinition;
import com.opengamma.financial.instrument.annuity.AnnuityCouponIborDefinition;
import com.opengamma.financial.instrument.index.IborIndex;
import com.opengamma.financial.instrument.swap.ZZZSwapFixedIborDefinition;
import com.opengamma.financial.instrument.swaption.SwaptionPhysicalFixedIborDefinition;
import com.opengamma.financial.interestrate.ParRateCalculator;
import com.opengamma.financial.interestrate.PresentValueCalculator;
import com.opengamma.financial.interestrate.YieldCurveBundle;
import com.opengamma.financial.interestrate.payments.Payment;
import com.opengamma.financial.interestrate.swap.SwapFixedIborAnnuityCalculator;
import com.opengamma.financial.interestrate.swap.definition.FixedCouponSwap;
import com.opengamma.financial.model.interestrate.curve.YieldAndDiscountCurve;
import com.opengamma.financial.model.interestrate.curve.YieldCurve;
import com.opengamma.financial.model.option.definition.SABRInterestRateDataBundle;
import com.opengamma.financial.model.option.definition.SABRInterestRateParameter;
import com.opengamma.financial.model.option.pricing.analytic.formula.BlackFunctionData;
import com.opengamma.financial.model.option.pricing.analytic.formula.BlackPriceFunction;
import com.opengamma.financial.model.volatility.smile.function.SABRFormulaData;
import com.opengamma.financial.model.volatility.smile.function.SABRHaganVolatilityFunction;
import com.opengamma.financial.model.volatility.surface.VolatilitySurface;
import com.opengamma.math.curve.ConstantDoublesCurve;
import com.opengamma.math.function.Function1D;
import com.opengamma.math.interpolation.GridInterpolator2D;
import com.opengamma.math.interpolation.LinearInterpolator1D;
import com.opengamma.math.surface.InterpolatedDoublesSurface;
import com.opengamma.util.money.Currency;
import com.opengamma.util.time.DateUtil;

public class SwaptionPhysicalFixedIborTest {
  // Swaption description
  private static final ZonedDateTime EXPIRY_DATE = DateUtil.getUTCDate(2011, 3, 28);
  private static final boolean IS_LONG = true;
  // Swap 2Y description
  private static final Currency CUR = Currency.USD;
  private static final Calendar CALENDAR = new MondayToFridayCalendar("A");
  private static final BusinessDayConvention BUSINESS_DAY = BusinessDayConventionFactory.INSTANCE.getBusinessDayConvention("Modified Following");
  private static final boolean IS_EOM = true;
  private static final Period ANNUITY_TENOR = Period.ofYears(2);
  private static final ZonedDateTime SETTLEMENT_DATE = DateUtil.getUTCDate(2011, 3, 30);
  private static final double NOTIONAL = 100000000; //100m
  //  Fixed leg: Semi-annual bond
  private static final Period FIXED_PAYMENT_PERIOD = Period.ofMonths(6);
  private static final DayCount FIXED_DAY_COUNT = DayCountFactory.INSTANCE.getDayCount("30/360");
  private static final double RATE = 0.0325;
  private static final boolean FIXED_IS_PAYER = true;
  private static final AnnuityCouponFixedDefinition FIXED_ANNUITY_PAYER = AnnuityCouponFixedDefinition.from(CUR, SETTLEMENT_DATE, ANNUITY_TENOR, FIXED_PAYMENT_PERIOD, CALENDAR, FIXED_DAY_COUNT,
      BUSINESS_DAY, IS_EOM, NOTIONAL, RATE, FIXED_IS_PAYER);
  private static final AnnuityCouponFixedDefinition FIXED_ANNUITY_RECEIVER = AnnuityCouponFixedDefinition.from(CUR, SETTLEMENT_DATE, ANNUITY_TENOR, FIXED_PAYMENT_PERIOD, CALENDAR, FIXED_DAY_COUNT,
      BUSINESS_DAY, IS_EOM, NOTIONAL, RATE, !FIXED_IS_PAYER);
  //  Ibor leg: quarterly money
  private static final Period INDEX_TENOR = Period.ofMonths(3);
  private static final int SETTLEMENT_DAYS = 2;
  private static final DayCount DAY_COUNT = DayCountFactory.INSTANCE.getDayCount("Actual/360");
  private static final IborIndex INDEX = new IborIndex(CUR, INDEX_TENOR, SETTLEMENT_DAYS, CALENDAR, DAY_COUNT, BUSINESS_DAY, IS_EOM);
  private static final AnnuityCouponIborDefinition IBOR_ANNUITY_RECEIVER = AnnuityCouponIborDefinition.from(SETTLEMENT_DATE, ANNUITY_TENOR, NOTIONAL, INDEX, !FIXED_IS_PAYER);
  private static final AnnuityCouponIborDefinition IBOR_ANNUITY_PAYER = AnnuityCouponIborDefinition.from(SETTLEMENT_DATE, ANNUITY_TENOR, NOTIONAL, INDEX, FIXED_IS_PAYER);
  // Swaption construction: All combinations
  private static final ZZZSwapFixedIborDefinition SWAP_DEFINITION_PAYER = new ZZZSwapFixedIborDefinition(FIXED_ANNUITY_PAYER, IBOR_ANNUITY_RECEIVER);
  private static final ZZZSwapFixedIborDefinition SWAP_DEFINITION_RECEIVER = new ZZZSwapFixedIborDefinition(FIXED_ANNUITY_RECEIVER, IBOR_ANNUITY_PAYER);
  private static final SwaptionPhysicalFixedIborDefinition SWAPTION_DEFINITION_LONG_PAYER = SwaptionPhysicalFixedIborDefinition.from(EXPIRY_DATE, SWAP_DEFINITION_PAYER, IS_LONG);
  private static final SwaptionPhysicalFixedIborDefinition SWAPTION_DEFINITION_LONG_RECEIVER = SwaptionPhysicalFixedIborDefinition.from(EXPIRY_DATE, SWAP_DEFINITION_RECEIVER, IS_LONG);
  private static final SwaptionPhysicalFixedIborDefinition SWAPTION_DEFINITION_SHORT_PAYER = SwaptionPhysicalFixedIborDefinition.from(EXPIRY_DATE, SWAP_DEFINITION_PAYER, !IS_LONG);
  private static final SwaptionPhysicalFixedIborDefinition SWAPTION_DEFINITION_SHORT_RECEIVER = SwaptionPhysicalFixedIborDefinition.from(EXPIRY_DATE, SWAP_DEFINITION_RECEIVER, !IS_LONG);
  // to derivatives
  private static final LocalDate REFERENCE_DATE = LocalDate.of(2010, 8, 18);
  private static final String FUNDING_CURVE_NAME = " Funding";
  private static final String FORWARD_CURVE_NAME = " Forward";
  private static final String[] CURVES_NAME = {FUNDING_CURVE_NAME, FORWARD_CURVE_NAME};
  private static final FixedCouponSwap<Payment> SWAP_PAYER = SWAP_DEFINITION_PAYER.toDerivative(REFERENCE_DATE, CURVES_NAME);
  private static final FixedCouponSwap<Payment> SWAP_RECEIVER = SWAP_DEFINITION_RECEIVER.toDerivative(REFERENCE_DATE, CURVES_NAME);
  private static final SwaptionPhysicalFixedIbor SWAPTION_LONG_PAYER = SWAPTION_DEFINITION_LONG_PAYER.toDerivative(REFERENCE_DATE, CURVES_NAME);
  private static final SwaptionPhysicalFixedIbor SWAPTION_LONG_RECEIVER = SWAPTION_DEFINITION_LONG_RECEIVER.toDerivative(REFERENCE_DATE, CURVES_NAME);
  private static final SwaptionPhysicalFixedIbor SWAPTION_SHORT_PAYER = SWAPTION_DEFINITION_SHORT_PAYER.toDerivative(REFERENCE_DATE, CURVES_NAME);
  private static final SwaptionPhysicalFixedIbor SWAPTION_SHORT_RECEIVER = SWAPTION_DEFINITION_SHORT_RECEIVER.toDerivative(REFERENCE_DATE, CURVES_NAME);
  // Yield curves
  private static final YieldAndDiscountCurve CURVE_5 = new YieldCurve(ConstantDoublesCurve.from(0.05));
  private static final YieldAndDiscountCurve CURVE_4 = new YieldCurve(ConstantDoublesCurve.from(0.04));
  // Calculators
  private static final ParRateCalculator PRC = ParRateCalculator.getInstance();
  private static final PresentValueCalculator PVC = PresentValueCalculator.getInstance();
  // Volatility and pricing functions
  private static final SABRHaganVolatilityFunction SABR_FUNCTION = new SABRHaganVolatilityFunction();
  private static final BlackPriceFunction BLACK_FUNCTION = new BlackPriceFunction();
  //Interpolation method
  private static final LinearInterpolator1D LINEAR = new LinearInterpolator1D();

  @Test
  public void testPriceBlack() {
    // Black price with given volatility
    YieldCurveBundle CURVES = new YieldCurveBundle();
    CURVES.setCurve(FUNDING_CURVE_NAME, CURVE_5);
    CURVES.setCurve(FORWARD_CURVE_NAME, CURVE_4);
    double sigmaBlack = 0.20;
    double forward = PRC.visit(SWAP_PAYER, CURVES);
    double pvbp = SwapFixedIborAnnuityCalculator.getAnnuityPhysical(SWAP_PAYER, CURVE_5);
    BlackFunctionData data = new BlackFunctionData(forward, pvbp, sigmaBlack);

    Function1D<BlackFunctionData, Double> funcLongPayer = BLACK_FUNCTION.getPriceFunction(SWAPTION_LONG_PAYER);
    double priceLongPayer = funcLongPayer.evaluate(data) * (SWAPTION_LONG_PAYER.isLong() ? 1.0 : -1.0);
    Function1D<BlackFunctionData, Double> funcLongReceiver = BLACK_FUNCTION.getPriceFunction(SWAPTION_LONG_RECEIVER);
    double priceLongReceiver = funcLongReceiver.evaluate(data) * (SWAPTION_LONG_RECEIVER.isLong() ? 1.0 : -1.0);
    Function1D<BlackFunctionData, Double> funcShortPayer = BLACK_FUNCTION.getPriceFunction(SWAPTION_SHORT_PAYER);
    double priceShortPayer = funcShortPayer.evaluate(data) * (SWAPTION_SHORT_PAYER.isLong() ? 1.0 : -1.0);
    Function1D<BlackFunctionData, Double> funcShortReceiver = BLACK_FUNCTION.getPriceFunction(SWAPTION_SHORT_RECEIVER);
    double priceShortReceiver = funcShortReceiver.evaluate(data) * (SWAPTION_SHORT_RECEIVER.isLong() ? 1.0 : -1.0);
    // Long/Short parity
    assertEquals(priceLongPayer, -priceShortPayer, 1E-2);
    // Payer/Receiver parity
    double priceSwapPayer = PVC.visit(SWAP_PAYER, CURVES);
    double priceSwapReceiver = PVC.visit(SWAP_RECEIVER, CURVES);
    assertEquals(priceSwapPayer, priceLongPayer + priceShortReceiver, 1E-2);
    assertEquals(priceSwapReceiver, priceLongReceiver + priceShortPayer, 1E-2);
  }

  @Test
  public void testPriceSABR() {
    YieldCurveBundle CURVES = new YieldCurveBundle();
    CURVES.setCurve(FUNDING_CURVE_NAME, CURVE_5);
    CURVES.setCurve(FORWARD_CURVE_NAME, CURVE_4);

    double alpha = 0.05;
    double beta = 0.5;
    double nu = 0.50;
    double rho = -0.25;

    double forward = PRC.visit(SWAP_PAYER, CURVES);
    double pvbp = SwapFixedIborAnnuityCalculator.getAnnuityPhysical(SWAP_PAYER, CURVE_5);

    SABRFormulaData data = new SABRFormulaData(forward, alpha, beta, nu, rho);

    Function1D<SABRFormulaData, Double> funcSabrLongPayer = SABR_FUNCTION.getVolatilityFunction(SWAPTION_LONG_PAYER);
    double volatilityLongPayer = funcSabrLongPayer.evaluate(data);
    BlackFunctionData dataBlackLP = new BlackFunctionData(forward, pvbp, volatilityLongPayer);
    Function1D<BlackFunctionData, Double> funcBlackLongPayer = BLACK_FUNCTION.getPriceFunction(SWAPTION_LONG_PAYER);
    double priceLongPayer = funcBlackLongPayer.evaluate(dataBlackLP) * (SWAPTION_LONG_PAYER.isLong() ? 1.0 : -1.0);

    Function1D<SABRFormulaData, Double> funcSabrShortPayer = SABR_FUNCTION.getVolatilityFunction(SWAPTION_SHORT_PAYER);
    double volatilityShortPayer = funcSabrShortPayer.evaluate(data);
    BlackFunctionData dataBlackSP = new BlackFunctionData(forward, pvbp, volatilityShortPayer);
    Function1D<BlackFunctionData, Double> funcBlackShortPayer = BLACK_FUNCTION.getPriceFunction(SWAPTION_SHORT_PAYER);
    double priceShortPayer = funcBlackShortPayer.evaluate(dataBlackSP) * (SWAPTION_SHORT_PAYER.isLong() ? 1.0 : -1.0);

    Function1D<SABRFormulaData, Double> funcSabrLongReceiver = SABR_FUNCTION.getVolatilityFunction(SWAPTION_LONG_RECEIVER);
    double volatilityLongReceiver = funcSabrLongReceiver.evaluate(data);
    BlackFunctionData dataBlackLR = new BlackFunctionData(forward, pvbp, volatilityLongReceiver);
    Function1D<BlackFunctionData, Double> funcBlackLongReceiver = BLACK_FUNCTION.getPriceFunction(SWAPTION_LONG_RECEIVER);
    double priceLongReceiver = funcBlackLongReceiver.evaluate(dataBlackLR) * (SWAPTION_LONG_RECEIVER.isLong() ? 1.0 : -1.0);

    Function1D<SABRFormulaData, Double> funcSabrShortReceiver = SABR_FUNCTION.getVolatilityFunction(SWAPTION_SHORT_RECEIVER);
    double volatilityShortReceiver = funcSabrShortReceiver.evaluate(data);
    BlackFunctionData dataBlackSR = new BlackFunctionData(forward, pvbp, volatilityShortReceiver);
    Function1D<BlackFunctionData, Double> funcBlackShortReceiver = BLACK_FUNCTION.getPriceFunction(SWAPTION_SHORT_RECEIVER);
    double priceShortReceiver = funcBlackShortReceiver.evaluate(dataBlackSR) * (SWAPTION_SHORT_RECEIVER.isLong() ? 1.0 : -1.0);
    // Long/Short parity
    assertEquals(priceLongPayer, -priceShortPayer, 1E-2);
    // Payer/Receiver parity
    double priceSwapPayer = PVC.visit(SWAP_PAYER, CURVES);
    double priceSwapReceiver = PVC.visit(SWAP_RECEIVER, CURVES);
    assertEquals(priceSwapPayer, priceLongPayer + priceShortReceiver, 1E-2);
    assertEquals(priceSwapReceiver, priceLongReceiver + priceShortPayer, 1E-2);
  }

  @Test
  public void testPriceSABRSurface() {
    // Yield curves
    YieldCurveBundle CURVES = new YieldCurveBundle();
    CURVES.setCurve(FUNDING_CURVE_NAME, CURVE_5);
    CURVES.setCurve(FORWARD_CURVE_NAME, CURVE_4);
    // Parameter surfaces are expiry - maturity - parameter
    InterpolatedDoublesSurface alphaSurface = InterpolatedDoublesSurface.from(new double[] {0.5, 1, 2, 0.5, 1, 2}, new double[] {1, 5, 1, 5, 1, 5,}, new double[] {0.05, 0.05, 0.05, 0.05, 0.05, 0.05},
        new GridInterpolator2D(LINEAR, LINEAR));
    VolatilitySurface alphaVolatility = new VolatilitySurface(alphaSurface);
    InterpolatedDoublesSurface betaSurface = InterpolatedDoublesSurface.from(new double[] {0.5, 1, 2, 0.5, 1, 2}, new double[] {1, 5, 1, 5, 1, 5,}, new double[] {0.5, 0.5, 0.5, 0.5, 0.5, 0.5},
        new GridInterpolator2D(LINEAR, LINEAR));
    VolatilitySurface betaVolatility = new VolatilitySurface(betaSurface);
    InterpolatedDoublesSurface rhoSurface = InterpolatedDoublesSurface.from(new double[] {0.5, 1, 2, 0.5, 1, 2}, new double[] {1, 5, 1, 5, 1, 5,}, new double[] {0.50, 0.40, 0.30, 0.30, 0.30, 0.30},
        new GridInterpolator2D(LINEAR, LINEAR));
    VolatilitySurface rhoVolatility = new VolatilitySurface(rhoSurface);
    InterpolatedDoublesSurface nuSurface = InterpolatedDoublesSurface.from(new double[] {0.5, 1, 2, 0.5, 1, 2}, new double[] {1, 5, 1, 5, 1, 5,},
        new double[] {-0.25, -0.20, -0.10, 0.00, -0.10, 0.00}, new GridInterpolator2D(LINEAR, LINEAR));
    VolatilitySurface nuVolatility = new VolatilitySurface(nuSurface);
    SABRInterestRateParameter sabrParameter = new SABRInterestRateParameter(alphaVolatility, betaVolatility, rhoVolatility, nuVolatility);
    SABRInterestRateDataBundle sabrBundle = new SABRInterestRateDataBundle(sabrParameter, CURVES);
    // Swaption pricing.
    double priceLongPayer = PVC.visit(SWAPTION_LONG_PAYER, sabrBundle);
    double priceShortPayer = PVC.visit(SWAPTION_SHORT_PAYER, sabrBundle);
    double priceLongReceiver = PVC.visit(SWAPTION_LONG_RECEIVER, sabrBundle);
    double priceShortReceiver = PVC.visit(SWAPTION_SHORT_RECEIVER, sabrBundle);
    // Long/Short parity
    assertEquals(priceLongPayer, -priceShortPayer, 1E-2);
    assertEquals(priceLongReceiver, -priceShortReceiver, 1E-2);
    // Payer/Receiver parity
    double priceSwapPayer = PVC.visit(SWAP_PAYER, CURVES);
    double priceSwapReceiver = PVC.visit(SWAP_RECEIVER, CURVES);
    assertEquals(priceSwapPayer, priceLongPayer + priceShortReceiver, 1E-2);
    assertEquals(priceSwapReceiver, priceLongReceiver + priceShortPayer, 1E-2);
  }
}
