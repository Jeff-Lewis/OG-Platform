/**
 * Copyright (C) 2011 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.master.marketdatasnapshot;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.joda.beans.BeanBuilder;
import org.joda.beans.BeanDefinition;
import org.joda.beans.MetaProperty;
import org.joda.beans.impl.direct.DirectBeanBuilder;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;

import com.opengamma.OpenGammaRuntimeException;
import com.opengamma.core.marketdatasnapshot.impl.ManageableMarketDataSnapshot;
import com.opengamma.master.AbstractHistoryResult;
import com.opengamma.util.PublicSPI;
import org.joda.beans.JodaBeanUtils;

/**
 * Result providing the history of a market data snapshot.
 * <p>
 * The returned documents may be a mixture of versions and corrections.
 * The document instant fields are used to identify which are which.
 * See {@link MarketDataSnapshotHistoryRequest} for more details.
 */
@PublicSPI
@BeanDefinition
public class MarketDataSnapshotHistoryResult extends AbstractHistoryResult<MarketDataSnapshotDocument> {

  /**
   * Creates an instance.
   */
  public MarketDataSnapshotHistoryResult() {
  }

  /**
   * Creates an instance.
   * 
   * @param coll  the collection of documents to add, not null
   */
  public MarketDataSnapshotHistoryResult(Collection<MarketDataSnapshotDocument> coll) {
    super(coll);
  }

  //-------------------------------------------------------------------------
  /**
   * Gets the returned snapshots from within the documents.
   * 
   * @return the snapshots, not null
   */
  public List<ManageableMarketDataSnapshot> getSnapshot() {
    List<ManageableMarketDataSnapshot> result = new ArrayList<ManageableMarketDataSnapshot>();
    if (getDocuments() != null) {
      for (MarketDataSnapshotDocument doc : getDocuments()) {
        result.add(doc.getSnapshot());
      }
    }
    return result;
  }

  /**
   * Gets the first snapshot, or null if no documents.
   * 
   * @return the first snapshot, null if none
   */
  public ManageableMarketDataSnapshot getFirstSnapshot() {
    return getDocuments().size() > 0 ? getDocuments().get(0).getSnapshot() : null;
  }

  /**
   * Gets the single result expected from a query.
   * <p>
   * This throws an exception if more than 1 result is actually available.
   * Thus, this method implies an assumption about uniqueness of the queried snapshot.
   * 
   * @return the matching snapshot, not null
   * @throws IllegalStateException if no holiday was found
   */
  public ManageableMarketDataSnapshot getSingleSnapshot() {
    if (getDocuments().size() != 1) {
      throw new OpenGammaRuntimeException("Expecting zero or single resulting match, and was " + getDocuments().size());
    } else {
      return getDocuments().get(0).getSnapshot();
    }
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code MarketDataSnapshotHistoryResult}.
   * @return the meta-bean, not null
   */
  @SuppressWarnings("unchecked")
  public static MarketDataSnapshotHistoryResult.Meta meta() {
    return MarketDataSnapshotHistoryResult.Meta.INSTANCE;
  }
  static {
    JodaBeanUtils.registerMetaBean(MarketDataSnapshotHistoryResult.Meta.INSTANCE);
  }

  @Override
  public MarketDataSnapshotHistoryResult.Meta metaBean() {
    return MarketDataSnapshotHistoryResult.Meta.INSTANCE;
  }

  @Override
  protected Object propertyGet(String propertyName, boolean quiet) {
    return super.propertyGet(propertyName, quiet);
  }

  @Override
  protected void propertySet(String propertyName, Object newValue, boolean quiet) {
    super.propertySet(propertyName, newValue, quiet);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      return super.equals(obj);
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    return hash ^ super.hashCode();
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code MarketDataSnapshotHistoryResult}.
   */
  public static class Meta extends AbstractHistoryResult.Meta<MarketDataSnapshotDocument> {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<Object>> _map = new DirectMetaPropertyMap(
      this, (DirectMetaPropertyMap) super.metaPropertyMap());

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    public BeanBuilder<? extends MarketDataSnapshotHistoryResult> builder() {
      return new DirectBeanBuilder<MarketDataSnapshotHistoryResult>(new MarketDataSnapshotHistoryResult());
    }

    @Override
    public Class<? extends MarketDataSnapshotHistoryResult> beanType() {
      return MarketDataSnapshotHistoryResult.class;
    }

    @Override
    public Map<String, MetaProperty<Object>> metaPropertyMap() {
      return _map;
    }

    //-----------------------------------------------------------------------
  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
