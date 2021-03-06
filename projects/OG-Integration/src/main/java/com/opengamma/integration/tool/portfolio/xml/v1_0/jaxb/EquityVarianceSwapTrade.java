/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.integration.tool.portfolio.xml.v1_0.jaxb;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.joda.beans.BeanDefinition;
import org.joda.beans.PropertyDefinition;
import org.threeten.bp.LocalDate;

import com.opengamma.integration.tool.portfolio.xml.v1_0.conversion.EquityVarianceSwapTradeSecurityExtractor;
import com.opengamma.integration.tool.portfolio.xml.v1_0.conversion.TradeSecurityExtractor;
import com.opengamma.util.money.Currency;
import java.util.Map;
import org.joda.beans.BeanBuilder;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.Property;
import org.joda.beans.impl.direct.DirectBeanBuilder;
import org.joda.beans.impl.direct.DirectMetaProperty;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@BeanDefinition
public class EquityVarianceSwapTrade extends Trade {

  @XmlElement(name = "buySell", required = true)
  @PropertyDefinition
  private BuySell _buySell;

  @XmlElement(name = "currency", required = true)
  @PropertyDefinition
  private Currency _currency;

  @XmlElement(name = "strike", required = true)
  @PropertyDefinition
  private BigDecimal _strike;

  @XmlElement(name = "underlying", required = true)
  @PropertyDefinition
  private IdWrapper _underlying;

  @XmlElement(name = "vegaAmount", required = true)
  @PropertyDefinition
  private BigDecimal _vegaAmount;

  @XmlElement(name = "observationStartDate", required = true)
  @PropertyDefinition
  private LocalDate _observationStartDate;

  @XmlElement(name = "observationEndDate", required = true)
  @PropertyDefinition
  private LocalDate _observationEndDate;

  @XmlElement(name = "observationfrequency")
  @PropertyDefinition
  private String _observationfrequency;

  @XmlElement(name = "annualizationFactor")
  @PropertyDefinition
  private double _annualizationFactor;

  @Override
  public boolean canBePositionAggregated() {
    return false;
  }

  @Override
  public TradeSecurityExtractor getSecurityExtractor() {
    return new EquityVarianceSwapTradeSecurityExtractor(this);
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code EquityVarianceSwapTrade}.
   * @return the meta-bean, not null
   */
  public static EquityVarianceSwapTrade.Meta meta() {
    return EquityVarianceSwapTrade.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(EquityVarianceSwapTrade.Meta.INSTANCE);
  }

  @Override
  public EquityVarianceSwapTrade.Meta metaBean() {
    return EquityVarianceSwapTrade.Meta.INSTANCE;
  }

  @Override
  protected Object propertyGet(String propertyName, boolean quiet) {
    switch (propertyName.hashCode()) {
      case 244977400:  // buySell
        return getBuySell();
      case 575402001:  // currency
        return getCurrency();
      case -891985998:  // strike
        return getStrike();
      case -1770633379:  // underlying
        return getUnderlying();
      case 1691266433:  // vegaAmount
        return getVegaAmount();
      case 2022170724:  // observationStartDate
        return getObservationStartDate();
      case -311788195:  // observationEndDate
        return getObservationEndDate();
      case 2077957808:  // observationfrequency
        return getObservationfrequency();
      case 663363412:  // annualizationFactor
        return getAnnualizationFactor();
    }
    return super.propertyGet(propertyName, quiet);
  }

  @Override
  protected void propertySet(String propertyName, Object newValue, boolean quiet) {
    switch (propertyName.hashCode()) {
      case 244977400:  // buySell
        setBuySell((BuySell) newValue);
        return;
      case 575402001:  // currency
        setCurrency((Currency) newValue);
        return;
      case -891985998:  // strike
        setStrike((BigDecimal) newValue);
        return;
      case -1770633379:  // underlying
        setUnderlying((IdWrapper) newValue);
        return;
      case 1691266433:  // vegaAmount
        setVegaAmount((BigDecimal) newValue);
        return;
      case 2022170724:  // observationStartDate
        setObservationStartDate((LocalDate) newValue);
        return;
      case -311788195:  // observationEndDate
        setObservationEndDate((LocalDate) newValue);
        return;
      case 2077957808:  // observationfrequency
        setObservationfrequency((String) newValue);
        return;
      case 663363412:  // annualizationFactor
        setAnnualizationFactor((Double) newValue);
        return;
    }
    super.propertySet(propertyName, newValue, quiet);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      EquityVarianceSwapTrade other = (EquityVarianceSwapTrade) obj;
      return JodaBeanUtils.equal(getBuySell(), other.getBuySell()) &&
          JodaBeanUtils.equal(getCurrency(), other.getCurrency()) &&
          JodaBeanUtils.equal(getStrike(), other.getStrike()) &&
          JodaBeanUtils.equal(getUnderlying(), other.getUnderlying()) &&
          JodaBeanUtils.equal(getVegaAmount(), other.getVegaAmount()) &&
          JodaBeanUtils.equal(getObservationStartDate(), other.getObservationStartDate()) &&
          JodaBeanUtils.equal(getObservationEndDate(), other.getObservationEndDate()) &&
          JodaBeanUtils.equal(getObservationfrequency(), other.getObservationfrequency()) &&
          JodaBeanUtils.equal(getAnnualizationFactor(), other.getAnnualizationFactor()) &&
          super.equals(obj);
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash += hash * 31 + JodaBeanUtils.hashCode(getBuySell());
    hash += hash * 31 + JodaBeanUtils.hashCode(getCurrency());
    hash += hash * 31 + JodaBeanUtils.hashCode(getStrike());
    hash += hash * 31 + JodaBeanUtils.hashCode(getUnderlying());
    hash += hash * 31 + JodaBeanUtils.hashCode(getVegaAmount());
    hash += hash * 31 + JodaBeanUtils.hashCode(getObservationStartDate());
    hash += hash * 31 + JodaBeanUtils.hashCode(getObservationEndDate());
    hash += hash * 31 + JodaBeanUtils.hashCode(getObservationfrequency());
    hash += hash * 31 + JodaBeanUtils.hashCode(getAnnualizationFactor());
    return hash ^ super.hashCode();
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the buySell.
   * @return the value of the property
   */
  public BuySell getBuySell() {
    return _buySell;
  }

  /**
   * Sets the buySell.
   * @param buySell  the new value of the property
   */
  public void setBuySell(BuySell buySell) {
    this._buySell = buySell;
  }

  /**
   * Gets the the {@code buySell} property.
   * @return the property, not null
   */
  public final Property<BuySell> buySell() {
    return metaBean().buySell().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the currency.
   * @return the value of the property
   */
  public Currency getCurrency() {
    return _currency;
  }

  /**
   * Sets the currency.
   * @param currency  the new value of the property
   */
  public void setCurrency(Currency currency) {
    this._currency = currency;
  }

  /**
   * Gets the the {@code currency} property.
   * @return the property, not null
   */
  public final Property<Currency> currency() {
    return metaBean().currency().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the strike.
   * @return the value of the property
   */
  public BigDecimal getStrike() {
    return _strike;
  }

  /**
   * Sets the strike.
   * @param strike  the new value of the property
   */
  public void setStrike(BigDecimal strike) {
    this._strike = strike;
  }

  /**
   * Gets the the {@code strike} property.
   * @return the property, not null
   */
  public final Property<BigDecimal> strike() {
    return metaBean().strike().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the underlying.
   * @return the value of the property
   */
  public IdWrapper getUnderlying() {
    return _underlying;
  }

  /**
   * Sets the underlying.
   * @param underlying  the new value of the property
   */
  public void setUnderlying(IdWrapper underlying) {
    this._underlying = underlying;
  }

  /**
   * Gets the the {@code underlying} property.
   * @return the property, not null
   */
  public final Property<IdWrapper> underlying() {
    return metaBean().underlying().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the vegaAmount.
   * @return the value of the property
   */
  public BigDecimal getVegaAmount() {
    return _vegaAmount;
  }

  /**
   * Sets the vegaAmount.
   * @param vegaAmount  the new value of the property
   */
  public void setVegaAmount(BigDecimal vegaAmount) {
    this._vegaAmount = vegaAmount;
  }

  /**
   * Gets the the {@code vegaAmount} property.
   * @return the property, not null
   */
  public final Property<BigDecimal> vegaAmount() {
    return metaBean().vegaAmount().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the observationStartDate.
   * @return the value of the property
   */
  public LocalDate getObservationStartDate() {
    return _observationStartDate;
  }

  /**
   * Sets the observationStartDate.
   * @param observationStartDate  the new value of the property
   */
  public void setObservationStartDate(LocalDate observationStartDate) {
    this._observationStartDate = observationStartDate;
  }

  /**
   * Gets the the {@code observationStartDate} property.
   * @return the property, not null
   */
  public final Property<LocalDate> observationStartDate() {
    return metaBean().observationStartDate().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the observationEndDate.
   * @return the value of the property
   */
  public LocalDate getObservationEndDate() {
    return _observationEndDate;
  }

  /**
   * Sets the observationEndDate.
   * @param observationEndDate  the new value of the property
   */
  public void setObservationEndDate(LocalDate observationEndDate) {
    this._observationEndDate = observationEndDate;
  }

  /**
   * Gets the the {@code observationEndDate} property.
   * @return the property, not null
   */
  public final Property<LocalDate> observationEndDate() {
    return metaBean().observationEndDate().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the observationfrequency.
   * @return the value of the property
   */
  public String getObservationfrequency() {
    return _observationfrequency;
  }

  /**
   * Sets the observationfrequency.
   * @param observationfrequency  the new value of the property
   */
  public void setObservationfrequency(String observationfrequency) {
    this._observationfrequency = observationfrequency;
  }

  /**
   * Gets the the {@code observationfrequency} property.
   * @return the property, not null
   */
  public final Property<String> observationfrequency() {
    return metaBean().observationfrequency().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the annualizationFactor.
   * @return the value of the property
   */
  public double getAnnualizationFactor() {
    return _annualizationFactor;
  }

  /**
   * Sets the annualizationFactor.
   * @param annualizationFactor  the new value of the property
   */
  public void setAnnualizationFactor(double annualizationFactor) {
    this._annualizationFactor = annualizationFactor;
  }

  /**
   * Gets the the {@code annualizationFactor} property.
   * @return the property, not null
   */
  public final Property<Double> annualizationFactor() {
    return metaBean().annualizationFactor().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code EquityVarianceSwapTrade}.
   */
  public static class Meta extends Trade.Meta {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code buySell} property.
     */
    private final MetaProperty<BuySell> _buySell = DirectMetaProperty.ofReadWrite(
        this, "buySell", EquityVarianceSwapTrade.class, BuySell.class);
    /**
     * The meta-property for the {@code currency} property.
     */
    private final MetaProperty<Currency> _currency = DirectMetaProperty.ofReadWrite(
        this, "currency", EquityVarianceSwapTrade.class, Currency.class);
    /**
     * The meta-property for the {@code strike} property.
     */
    private final MetaProperty<BigDecimal> _strike = DirectMetaProperty.ofReadWrite(
        this, "strike", EquityVarianceSwapTrade.class, BigDecimal.class);
    /**
     * The meta-property for the {@code underlying} property.
     */
    private final MetaProperty<IdWrapper> _underlying = DirectMetaProperty.ofReadWrite(
        this, "underlying", EquityVarianceSwapTrade.class, IdWrapper.class);
    /**
     * The meta-property for the {@code vegaAmount} property.
     */
    private final MetaProperty<BigDecimal> _vegaAmount = DirectMetaProperty.ofReadWrite(
        this, "vegaAmount", EquityVarianceSwapTrade.class, BigDecimal.class);
    /**
     * The meta-property for the {@code observationStartDate} property.
     */
    private final MetaProperty<LocalDate> _observationStartDate = DirectMetaProperty.ofReadWrite(
        this, "observationStartDate", EquityVarianceSwapTrade.class, LocalDate.class);
    /**
     * The meta-property for the {@code observationEndDate} property.
     */
    private final MetaProperty<LocalDate> _observationEndDate = DirectMetaProperty.ofReadWrite(
        this, "observationEndDate", EquityVarianceSwapTrade.class, LocalDate.class);
    /**
     * The meta-property for the {@code observationfrequency} property.
     */
    private final MetaProperty<String> _observationfrequency = DirectMetaProperty.ofReadWrite(
        this, "observationfrequency", EquityVarianceSwapTrade.class, String.class);
    /**
     * The meta-property for the {@code annualizationFactor} property.
     */
    private final MetaProperty<Double> _annualizationFactor = DirectMetaProperty.ofReadWrite(
        this, "annualizationFactor", EquityVarianceSwapTrade.class, Double.TYPE);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, (DirectMetaPropertyMap) super.metaPropertyMap(),
        "buySell",
        "currency",
        "strike",
        "underlying",
        "vegaAmount",
        "observationStartDate",
        "observationEndDate",
        "observationfrequency",
        "annualizationFactor");

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case 244977400:  // buySell
          return _buySell;
        case 575402001:  // currency
          return _currency;
        case -891985998:  // strike
          return _strike;
        case -1770633379:  // underlying
          return _underlying;
        case 1691266433:  // vegaAmount
          return _vegaAmount;
        case 2022170724:  // observationStartDate
          return _observationStartDate;
        case -311788195:  // observationEndDate
          return _observationEndDate;
        case 2077957808:  // observationfrequency
          return _observationfrequency;
        case 663363412:  // annualizationFactor
          return _annualizationFactor;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends EquityVarianceSwapTrade> builder() {
      return new DirectBeanBuilder<EquityVarianceSwapTrade>(new EquityVarianceSwapTrade());
    }

    @Override
    public Class<? extends EquityVarianceSwapTrade> beanType() {
      return EquityVarianceSwapTrade.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code buySell} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<BuySell> buySell() {
      return _buySell;
    }

    /**
     * The meta-property for the {@code currency} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Currency> currency() {
      return _currency;
    }

    /**
     * The meta-property for the {@code strike} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<BigDecimal> strike() {
      return _strike;
    }

    /**
     * The meta-property for the {@code underlying} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<IdWrapper> underlying() {
      return _underlying;
    }

    /**
     * The meta-property for the {@code vegaAmount} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<BigDecimal> vegaAmount() {
      return _vegaAmount;
    }

    /**
     * The meta-property for the {@code observationStartDate} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<LocalDate> observationStartDate() {
      return _observationStartDate;
    }

    /**
     * The meta-property for the {@code observationEndDate} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<LocalDate> observationEndDate() {
      return _observationEndDate;
    }

    /**
     * The meta-property for the {@code observationfrequency} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<String> observationfrequency() {
      return _observationfrequency;
    }

    /**
     * The meta-property for the {@code annualizationFactor} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Double> annualizationFactor() {
      return _annualizationFactor;
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
