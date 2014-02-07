/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.core.user.impl;

import java.io.Serializable;
import java.util.Map;

import org.joda.beans.Bean;
import org.joda.beans.BeanBuilder;
import org.joda.beans.BeanDefinition;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.Property;
import org.joda.beans.PropertyDefinition;
import org.joda.beans.impl.direct.DirectBean;
import org.joda.beans.impl.direct.DirectBeanBuilder;
import org.joda.beans.impl.direct.DirectMetaBean;
import org.joda.beans.impl.direct.DirectMetaProperty;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;
import org.threeten.bp.ZoneId;

import com.opengamma.core.user.OGUser;
import com.opengamma.id.ExternalIdBundle;
import com.opengamma.id.MutableUniqueIdentifiable;
import com.opengamma.id.UniqueId;
import com.opengamma.util.OpenGammaClock;

/**
 * Simple implementation of {@code OGUser}.
 * <p>
 * This is the simplest possible implementation of the {@link OGUser} interface.
 * <p>
 * This class is mutable and not thread-safe.
 * It is intended to be used in the engine via the read-only {@code OGUser} interface.
 */
@BeanDefinition
public class SimpleOGUser extends DirectBean implements OGUser, MutableUniqueIdentifiable, Serializable {

  /** Serialization version. */
  private static final long serialVersionUID = 1L;

  /**
   * The unique identifier of the user.
   */
  @PropertyDefinition
  private UniqueId _uniqueId;
  /**
   * The bundle of external identifiers that define the user.
   */
  @PropertyDefinition(validate = "notNull")
  private ExternalIdBundle _externalIdBundle = ExternalIdBundle.EMPTY;
  /**
   * The user id that uniquely identifies the user
   * This is used with the password to authenticate.
   */
  @PropertyDefinition(validate = "notNull")
  private String _userId;
  /**
   * The hashed version of the user password, null if no password.
   */
  @PropertyDefinition
  private String _passwordHash;
  /**
   * The user password salt, null if no password.
   */
  @PropertyDefinition
  private String _salt;
  /**
   * The display user name, used to identify the user in a GUI.
   */
  @PropertyDefinition
  private String _name;
  /**
   * The time-zone used to display local times.
   */
  @PropertyDefinition(validate = "notNull")
  private ZoneId _timeZone = OpenGammaClock.getZone();
  /**
   * The primary email address associated with the account.
   */
  @PropertyDefinition
  private String _emailAddress;

  /**
   * Creates a user.
   */
  protected SimpleOGUser() {
  }

  /**
   * Creates a user.
   * 
   * @param userId  the user id, not null
   */
  public SimpleOGUser(String userId) {
    setUserId(userId);
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code SimpleOGUser}.
   * @return the meta-bean, not null
   */
  public static SimpleOGUser.Meta meta() {
    return SimpleOGUser.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(SimpleOGUser.Meta.INSTANCE);
  }

  @Override
  public SimpleOGUser.Meta metaBean() {
    return SimpleOGUser.Meta.INSTANCE;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the unique identifier of the user.
   * @return the value of the property
   */
  public UniqueId getUniqueId() {
    return _uniqueId;
  }

  /**
   * Sets the unique identifier of the user.
   * @param uniqueId  the new value of the property
   */
  public void setUniqueId(UniqueId uniqueId) {
    this._uniqueId = uniqueId;
  }

  /**
   * Gets the the {@code uniqueId} property.
   * @return the property, not null
   */
  public final Property<UniqueId> uniqueId() {
    return metaBean().uniqueId().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the bundle of external identifiers that define the user.
   * @return the value of the property, not null
   */
  public ExternalIdBundle getExternalIdBundle() {
    return _externalIdBundle;
  }

  /**
   * Sets the bundle of external identifiers that define the user.
   * @param externalIdBundle  the new value of the property, not null
   */
  public void setExternalIdBundle(ExternalIdBundle externalIdBundle) {
    JodaBeanUtils.notNull(externalIdBundle, "externalIdBundle");
    this._externalIdBundle = externalIdBundle;
  }

  /**
   * Gets the the {@code externalIdBundle} property.
   * @return the property, not null
   */
  public final Property<ExternalIdBundle> externalIdBundle() {
    return metaBean().externalIdBundle().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the user id that uniquely identifies the user
   * This is used with the password to authenticate.
   * @return the value of the property, not null
   */
  public String getUserId() {
    return _userId;
  }

  /**
   * Sets the user id that uniquely identifies the user
   * This is used with the password to authenticate.
   * @param userId  the new value of the property, not null
   */
  public void setUserId(String userId) {
    JodaBeanUtils.notNull(userId, "userId");
    this._userId = userId;
  }

  /**
   * Gets the the {@code userId} property.
   * This is used with the password to authenticate.
   * @return the property, not null
   */
  public final Property<String> userId() {
    return metaBean().userId().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the hashed version of the user password, null if no password.
   * @return the value of the property
   */
  public String getPasswordHash() {
    return _passwordHash;
  }

  /**
   * Sets the hashed version of the user password, null if no password.
   * @param passwordHash  the new value of the property
   */
  public void setPasswordHash(String passwordHash) {
    this._passwordHash = passwordHash;
  }

  /**
   * Gets the the {@code passwordHash} property.
   * @return the property, not null
   */
  public final Property<String> passwordHash() {
    return metaBean().passwordHash().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the user password salt, null if no password.
   * @return the value of the property
   */
  public String getSalt() {
    return _salt;
  }

  /**
   * Sets the user password salt, null if no password.
   * @param salt  the new value of the property
   */
  public void setSalt(String salt) {
    this._salt = salt;
  }

  /**
   * Gets the the {@code salt} property.
   * @return the property, not null
   */
  public final Property<String> salt() {
    return metaBean().salt().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the display user name, used to identify the user in a GUI.
   * @return the value of the property
   */
  public String getName() {
    return _name;
  }

  /**
   * Sets the display user name, used to identify the user in a GUI.
   * @param name  the new value of the property
   */
  public void setName(String name) {
    this._name = name;
  }

  /**
   * Gets the the {@code name} property.
   * @return the property, not null
   */
  public final Property<String> name() {
    return metaBean().name().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the time-zone used to display local times.
   * @return the value of the property, not null
   */
  public ZoneId getTimeZone() {
    return _timeZone;
  }

  /**
   * Sets the time-zone used to display local times.
   * @param timeZone  the new value of the property, not null
   */
  public void setTimeZone(ZoneId timeZone) {
    JodaBeanUtils.notNull(timeZone, "timeZone");
    this._timeZone = timeZone;
  }

  /**
   * Gets the the {@code timeZone} property.
   * @return the property, not null
   */
  public final Property<ZoneId> timeZone() {
    return metaBean().timeZone().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the primary email address associated with the account.
   * @return the value of the property
   */
  public String getEmailAddress() {
    return _emailAddress;
  }

  /**
   * Sets the primary email address associated with the account.
   * @param emailAddress  the new value of the property
   */
  public void setEmailAddress(String emailAddress) {
    this._emailAddress = emailAddress;
  }

  /**
   * Gets the the {@code emailAddress} property.
   * @return the property, not null
   */
  public final Property<String> emailAddress() {
    return metaBean().emailAddress().createProperty(this);
  }

  //-----------------------------------------------------------------------
  @Override
  public SimpleOGUser clone() {
    BeanBuilder<? extends SimpleOGUser> builder = metaBean().builder();
    for (MetaProperty<?> mp : metaBean().metaPropertyIterable()) {
      if (mp.style().isBuildable()) {
        Object value = mp.get(this);
        if (value instanceof Bean) {
          value = ((Bean) value).clone();
        }
        builder.set(mp.name(), value);
      }
    }
    return builder.build();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      SimpleOGUser other = (SimpleOGUser) obj;
      return JodaBeanUtils.equal(getUniqueId(), other.getUniqueId()) &&
          JodaBeanUtils.equal(getExternalIdBundle(), other.getExternalIdBundle()) &&
          JodaBeanUtils.equal(getUserId(), other.getUserId()) &&
          JodaBeanUtils.equal(getPasswordHash(), other.getPasswordHash()) &&
          JodaBeanUtils.equal(getSalt(), other.getSalt()) &&
          JodaBeanUtils.equal(getName(), other.getName()) &&
          JodaBeanUtils.equal(getTimeZone(), other.getTimeZone()) &&
          JodaBeanUtils.equal(getEmailAddress(), other.getEmailAddress());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = getClass().hashCode();
    hash += hash * 31 + JodaBeanUtils.hashCode(getUniqueId());
    hash += hash * 31 + JodaBeanUtils.hashCode(getExternalIdBundle());
    hash += hash * 31 + JodaBeanUtils.hashCode(getUserId());
    hash += hash * 31 + JodaBeanUtils.hashCode(getPasswordHash());
    hash += hash * 31 + JodaBeanUtils.hashCode(getSalt());
    hash += hash * 31 + JodaBeanUtils.hashCode(getName());
    hash += hash * 31 + JodaBeanUtils.hashCode(getTimeZone());
    hash += hash * 31 + JodaBeanUtils.hashCode(getEmailAddress());
    return hash;
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(288);
    buf.append("SimpleOGUser{");
    int len = buf.length();
    toString(buf);
    if (buf.length() > len) {
      buf.setLength(buf.length() - 2);
    }
    buf.append('}');
    return buf.toString();
  }

  protected void toString(StringBuilder buf) {
    buf.append("uniqueId").append('=').append(JodaBeanUtils.toString(getUniqueId())).append(',').append(' ');
    buf.append("externalIdBundle").append('=').append(JodaBeanUtils.toString(getExternalIdBundle())).append(',').append(' ');
    buf.append("userId").append('=').append(JodaBeanUtils.toString(getUserId())).append(',').append(' ');
    buf.append("passwordHash").append('=').append(JodaBeanUtils.toString(getPasswordHash())).append(',').append(' ');
    buf.append("salt").append('=').append(JodaBeanUtils.toString(getSalt())).append(',').append(' ');
    buf.append("name").append('=').append(JodaBeanUtils.toString(getName())).append(',').append(' ');
    buf.append("timeZone").append('=').append(JodaBeanUtils.toString(getTimeZone())).append(',').append(' ');
    buf.append("emailAddress").append('=').append(JodaBeanUtils.toString(getEmailAddress())).append(',').append(' ');
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code SimpleOGUser}.
   */
  public static class Meta extends DirectMetaBean {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code uniqueId} property.
     */
    private final MetaProperty<UniqueId> _uniqueId = DirectMetaProperty.ofReadWrite(
        this, "uniqueId", SimpleOGUser.class, UniqueId.class);
    /**
     * The meta-property for the {@code externalIdBundle} property.
     */
    private final MetaProperty<ExternalIdBundle> _externalIdBundle = DirectMetaProperty.ofReadWrite(
        this, "externalIdBundle", SimpleOGUser.class, ExternalIdBundle.class);
    /**
     * The meta-property for the {@code userId} property.
     */
    private final MetaProperty<String> _userId = DirectMetaProperty.ofReadWrite(
        this, "userId", SimpleOGUser.class, String.class);
    /**
     * The meta-property for the {@code passwordHash} property.
     */
    private final MetaProperty<String> _passwordHash = DirectMetaProperty.ofReadWrite(
        this, "passwordHash", SimpleOGUser.class, String.class);
    /**
     * The meta-property for the {@code salt} property.
     */
    private final MetaProperty<String> _salt = DirectMetaProperty.ofReadWrite(
        this, "salt", SimpleOGUser.class, String.class);
    /**
     * The meta-property for the {@code name} property.
     */
    private final MetaProperty<String> _name = DirectMetaProperty.ofReadWrite(
        this, "name", SimpleOGUser.class, String.class);
    /**
     * The meta-property for the {@code timeZone} property.
     */
    private final MetaProperty<ZoneId> _timeZone = DirectMetaProperty.ofReadWrite(
        this, "timeZone", SimpleOGUser.class, ZoneId.class);
    /**
     * The meta-property for the {@code emailAddress} property.
     */
    private final MetaProperty<String> _emailAddress = DirectMetaProperty.ofReadWrite(
        this, "emailAddress", SimpleOGUser.class, String.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, null,
        "uniqueId",
        "externalIdBundle",
        "userId",
        "passwordHash",
        "salt",
        "name",
        "timeZone",
        "emailAddress");

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case -294460212:  // uniqueId
          return _uniqueId;
        case -736922008:  // externalIdBundle
          return _externalIdBundle;
        case -836030906:  // userId
          return _userId;
        case 566700617:  // passwordHash
          return _passwordHash;
        case 3522646:  // salt
          return _salt;
        case 3373707:  // name
          return _name;
        case -2077180903:  // timeZone
          return _timeZone;
        case -1070931784:  // emailAddress
          return _emailAddress;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends SimpleOGUser> builder() {
      return new DirectBeanBuilder<SimpleOGUser>(new SimpleOGUser());
    }

    @Override
    public Class<? extends SimpleOGUser> beanType() {
      return SimpleOGUser.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code uniqueId} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<UniqueId> uniqueId() {
      return _uniqueId;
    }

    /**
     * The meta-property for the {@code externalIdBundle} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<ExternalIdBundle> externalIdBundle() {
      return _externalIdBundle;
    }

    /**
     * The meta-property for the {@code userId} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<String> userId() {
      return _userId;
    }

    /**
     * The meta-property for the {@code passwordHash} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<String> passwordHash() {
      return _passwordHash;
    }

    /**
     * The meta-property for the {@code salt} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<String> salt() {
      return _salt;
    }

    /**
     * The meta-property for the {@code name} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<String> name() {
      return _name;
    }

    /**
     * The meta-property for the {@code timeZone} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<ZoneId> timeZone() {
      return _timeZone;
    }

    /**
     * The meta-property for the {@code emailAddress} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<String> emailAddress() {
      return _emailAddress;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case -294460212:  // uniqueId
          return ((SimpleOGUser) bean).getUniqueId();
        case -736922008:  // externalIdBundle
          return ((SimpleOGUser) bean).getExternalIdBundle();
        case -836030906:  // userId
          return ((SimpleOGUser) bean).getUserId();
        case 566700617:  // passwordHash
          return ((SimpleOGUser) bean).getPasswordHash();
        case 3522646:  // salt
          return ((SimpleOGUser) bean).getSalt();
        case 3373707:  // name
          return ((SimpleOGUser) bean).getName();
        case -2077180903:  // timeZone
          return ((SimpleOGUser) bean).getTimeZone();
        case -1070931784:  // emailAddress
          return ((SimpleOGUser) bean).getEmailAddress();
      }
      return super.propertyGet(bean, propertyName, quiet);
    }

    @Override
    protected void propertySet(Bean bean, String propertyName, Object newValue, boolean quiet) {
      switch (propertyName.hashCode()) {
        case -294460212:  // uniqueId
          ((SimpleOGUser) bean).setUniqueId((UniqueId) newValue);
          return;
        case -736922008:  // externalIdBundle
          ((SimpleOGUser) bean).setExternalIdBundle((ExternalIdBundle) newValue);
          return;
        case -836030906:  // userId
          ((SimpleOGUser) bean).setUserId((String) newValue);
          return;
        case 566700617:  // passwordHash
          ((SimpleOGUser) bean).setPasswordHash((String) newValue);
          return;
        case 3522646:  // salt
          ((SimpleOGUser) bean).setSalt((String) newValue);
          return;
        case 3373707:  // name
          ((SimpleOGUser) bean).setName((String) newValue);
          return;
        case -2077180903:  // timeZone
          ((SimpleOGUser) bean).setTimeZone((ZoneId) newValue);
          return;
        case -1070931784:  // emailAddress
          ((SimpleOGUser) bean).setEmailAddress((String) newValue);
          return;
      }
      super.propertySet(bean, propertyName, newValue, quiet);
    }

    @Override
    protected void validate(Bean bean) {
      JodaBeanUtils.notNull(((SimpleOGUser) bean)._externalIdBundle, "externalIdBundle");
      JodaBeanUtils.notNull(((SimpleOGUser) bean)._userId, "userId");
      JodaBeanUtils.notNull(((SimpleOGUser) bean)._timeZone, "timeZone");
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
