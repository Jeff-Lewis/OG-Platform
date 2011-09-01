/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.financial.security.swap;

import java.util.Map;

import org.joda.beans.BeanBuilder;
import org.joda.beans.BeanDefinition;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.Property;
import org.joda.beans.PropertyDefinition;
import org.joda.beans.impl.direct.DirectBeanBuilder;
import org.joda.beans.impl.direct.DirectMetaProperty;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;

import com.opengamma.financial.convention.businessday.BusinessDayConvention;
import com.opengamma.financial.convention.daycount.DayCount;
import com.opengamma.financial.convention.frequency.Frequency;
import com.opengamma.id.ExternalId;

/**
 * The floating interest rate swap leg.
 */
@BeanDefinition
public class FloatingInterestRateLeg extends InterestRateLeg {

  /** Serialization version. */
  private static final long serialVersionUID = 1L;

  /**
   * The reference rate external identifier.
   */
  @PropertyDefinition(validate = "notNull")
  private ExternalId _floatingReferenceRateIdentifier;
  /**
   * The initial floating rate.
   */
  @PropertyDefinition
  private Double _initialFloatingRate;
  /**
   * The spread.
   */
  @PropertyDefinition
  private double _spread;
  /**
   * The IBOR flag.
   */
  @PropertyDefinition
  private boolean _ibor;

  /**
   * Creates an instance.
   */
  private FloatingInterestRateLeg() {
  }

  /**
   * Creates an instance.
   * 
   * @param dayCount  the day count, not null
   * @param frequency  the frequency, not null
   * @param regionIdentifier  the region, not null
   * @param businessDayConvention  the business day convention, not null
   * @param notional  the notional, not null
   * @param floatingReferenceRateIdentifier  the reference rate, not null
   * @param spread  the spread
   * @param ibor  whether this is IBOR
   */
  public FloatingInterestRateLeg(DayCount dayCount, Frequency frequency, ExternalId regionIdentifier, BusinessDayConvention businessDayConvention,
      Notional notional, ExternalId floatingReferenceRateIdentifier, double spread, boolean ibor) {
    super(dayCount, frequency, regionIdentifier, businessDayConvention, notional);
    setFloatingReferenceRateIdentifier(floatingReferenceRateIdentifier);
    setSpread(spread);
    setIbor(ibor);
  }

  /**
   * Creates an instance.
   * 
   * @param dayCount  the day count, not null
   * @param frequency  the frequency, not null
   * @param regionIdentifier  the region, not null
   * @param businessDayConvention  the business day convention, not null
   * @param notional  the notional, not null
   * @param floatingReferenceRateIdentifier  the reference rate, not null
   * @param initialFloatingRate  the initial floating rate, may be null
   * @param spread  the spread
   * @param isIBOR  whether this is IBOR
   */
  public FloatingInterestRateLeg(DayCount dayCount, Frequency frequency, ExternalId regionIdentifier, BusinessDayConvention businessDayConvention,
      Notional notional, ExternalId floatingReferenceRateIdentifier, Double initialFloatingRate, double spread, boolean isIBOR) {
    super(dayCount, frequency, regionIdentifier, businessDayConvention, notional);
    setFloatingReferenceRateIdentifier(floatingReferenceRateIdentifier);
    setInitialFloatingRate(initialFloatingRate);
    setSpread(spread);
    setIbor(isIBOR);
  }

  //-------------------------------------------------------------------------
  @Override
  public <T> T accept(SwapLegVisitor<T> visitor) {
    return visitor.visitFloatingInterestRateLeg(this);
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code FloatingInterestRateLeg}.
   * @return the meta-bean, not null
   */
  public static FloatingInterestRateLeg.Meta meta() {
    return FloatingInterestRateLeg.Meta.INSTANCE;
  }
  static {
    JodaBeanUtils.registerMetaBean(FloatingInterestRateLeg.Meta.INSTANCE);
  }

  @Override
  public FloatingInterestRateLeg.Meta metaBean() {
    return FloatingInterestRateLeg.Meta.INSTANCE;
  }

  @Override
  protected Object propertyGet(String propertyName, boolean quiet) {
    switch (propertyName.hashCode()) {
      case 1984582382:  // floatingReferenceRateIdentifier
        return getFloatingReferenceRateIdentifier();
      case -1216922774:  // initialFloatingRate
        return getInitialFloatingRate();
      case -895684237:  // spread
        return getSpread();
      case 3225788:  // ibor
        return isIbor();
    }
    return super.propertyGet(propertyName, quiet);
  }

  @Override
  protected void propertySet(String propertyName, Object newValue, boolean quiet) {
    switch (propertyName.hashCode()) {
      case 1984582382:  // floatingReferenceRateIdentifier
        setFloatingReferenceRateIdentifier((ExternalId) newValue);
        return;
      case -1216922774:  // initialFloatingRate
        setInitialFloatingRate((Double) newValue);
        return;
      case -895684237:  // spread
        setSpread((Double) newValue);
        return;
      case 3225788:  // ibor
        setIbor((Boolean) newValue);
        return;
    }
    super.propertySet(propertyName, newValue, quiet);
  }

  @Override
  protected void validate() {
    JodaBeanUtils.notNull(_floatingReferenceRateIdentifier, "floatingReferenceRateIdentifier");
    super.validate();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      FloatingInterestRateLeg other = (FloatingInterestRateLeg) obj;
      return JodaBeanUtils.equal(getFloatingReferenceRateIdentifier(), other.getFloatingReferenceRateIdentifier()) &&
          JodaBeanUtils.equal(getInitialFloatingRate(), other.getInitialFloatingRate()) &&
          JodaBeanUtils.equal(getSpread(), other.getSpread()) &&
          JodaBeanUtils.equal(isIbor(), other.isIbor()) &&
          super.equals(obj);
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash += hash * 31 + JodaBeanUtils.hashCode(getFloatingReferenceRateIdentifier());
    hash += hash * 31 + JodaBeanUtils.hashCode(getInitialFloatingRate());
    hash += hash * 31 + JodaBeanUtils.hashCode(getSpread());
    hash += hash * 31 + JodaBeanUtils.hashCode(isIbor());
    return hash ^ super.hashCode();
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the reference rate external identifier.
   * @return the value of the property, not null
   */
  public ExternalId getFloatingReferenceRateIdentifier() {
    return _floatingReferenceRateIdentifier;
  }

  /**
   * Sets the reference rate external identifier.
   * @param floatingReferenceRateIdentifier  the new value of the property, not null
   */
  public void setFloatingReferenceRateIdentifier(ExternalId floatingReferenceRateIdentifier) {
    JodaBeanUtils.notNull(floatingReferenceRateIdentifier, "floatingReferenceRateIdentifier");
    this._floatingReferenceRateIdentifier = floatingReferenceRateIdentifier;
  }

  /**
   * Gets the the {@code floatingReferenceRateIdentifier} property.
   * @return the property, not null
   */
  public final Property<ExternalId> floatingReferenceRateIdentifier() {
    return metaBean().floatingReferenceRateIdentifier().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the initial floating rate.
   * @return the value of the property
   */
  public Double getInitialFloatingRate() {
    return _initialFloatingRate;
  }

  /**
   * Sets the initial floating rate.
   * @param initialFloatingRate  the new value of the property
   */
  public void setInitialFloatingRate(Double initialFloatingRate) {
    this._initialFloatingRate = initialFloatingRate;
  }

  /**
   * Gets the the {@code initialFloatingRate} property.
   * @return the property, not null
   */
  public final Property<Double> initialFloatingRate() {
    return metaBean().initialFloatingRate().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the spread.
   * @return the value of the property
   */
  public double getSpread() {
    return _spread;
  }

  /**
   * Sets the spread.
   * @param spread  the new value of the property
   */
  public void setSpread(double spread) {
    this._spread = spread;
  }

  /**
   * Gets the the {@code spread} property.
   * @return the property, not null
   */
  public final Property<Double> spread() {
    return metaBean().spread().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the IBOR flag.
   * @return the value of the property
   */
  public boolean isIbor() {
    return _ibor;
  }

  /**
   * Sets the IBOR flag.
   * @param ibor  the new value of the property
   */
  public void setIbor(boolean ibor) {
    this._ibor = ibor;
  }

  /**
   * Gets the the {@code ibor} property.
   * @return the property, not null
   */
  public final Property<Boolean> ibor() {
    return metaBean().ibor().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code FloatingInterestRateLeg}.
   */
  public static class Meta extends InterestRateLeg.Meta {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code floatingReferenceRateIdentifier} property.
     */
    private final MetaProperty<ExternalId> _floatingReferenceRateIdentifier = DirectMetaProperty.ofReadWrite(
        this, "floatingReferenceRateIdentifier", FloatingInterestRateLeg.class, ExternalId.class);
    /**
     * The meta-property for the {@code initialFloatingRate} property.
     */
    private final MetaProperty<Double> _initialFloatingRate = DirectMetaProperty.ofReadWrite(
        this, "initialFloatingRate", FloatingInterestRateLeg.class, Double.class);
    /**
     * The meta-property for the {@code spread} property.
     */
    private final MetaProperty<Double> _spread = DirectMetaProperty.ofReadWrite(
        this, "spread", FloatingInterestRateLeg.class, Double.TYPE);
    /**
     * The meta-property for the {@code ibor} property.
     */
    private final MetaProperty<Boolean> _ibor = DirectMetaProperty.ofReadWrite(
        this, "ibor", FloatingInterestRateLeg.class, Boolean.TYPE);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<Object>> _map = new DirectMetaPropertyMap(
      this, (DirectMetaPropertyMap) super.metaPropertyMap(),
        "floatingReferenceRateIdentifier",
        "initialFloatingRate",
        "spread",
        "ibor");

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case 1984582382:  // floatingReferenceRateIdentifier
          return _floatingReferenceRateIdentifier;
        case -1216922774:  // initialFloatingRate
          return _initialFloatingRate;
        case -895684237:  // spread
          return _spread;
        case 3225788:  // ibor
          return _ibor;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends FloatingInterestRateLeg> builder() {
      return new DirectBeanBuilder<FloatingInterestRateLeg>(new FloatingInterestRateLeg());
    }

    @Override
    public Class<? extends FloatingInterestRateLeg> beanType() {
      return FloatingInterestRateLeg.class;
    }

    @Override
    public Map<String, MetaProperty<Object>> metaPropertyMap() {
      return _map;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code floatingReferenceRateIdentifier} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<ExternalId> floatingReferenceRateIdentifier() {
      return _floatingReferenceRateIdentifier;
    }

    /**
     * The meta-property for the {@code initialFloatingRate} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Double> initialFloatingRate() {
      return _initialFloatingRate;
    }

    /**
     * The meta-property for the {@code spread} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Double> spread() {
      return _spread;
    }

    /**
     * The meta-property for the {@code ibor} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Boolean> ibor() {
      return _ibor;
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
