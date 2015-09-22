/*
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license
 * agreements. See the NOTICE file distributed with this work for additional information regarding
 * copyright ownership. The ASF licenses this file to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the License. You may obtain a
 * copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package org.redhelix.server.data; // org.apache.olingo.server.tecsvc.provider;

import org.apache.olingo.commons.api.edm.EdmPrimitiveTypeKind;
import org.apache.olingo.commons.api.edm.FullQualifiedName;
import org.apache.olingo.commons.api.edm.provider.CsdlNavigationProperty;
import org.apache.olingo.commons.api.edm.provider.CsdlProperty;

public class PropertyProvider {

  // Primitive Type Names
  public static final FullQualifiedName nameBinary =
      EdmPrimitiveTypeKind.Binary.getFullQualifiedName();
  public static final FullQualifiedName nameBoolean =
      EdmPrimitiveTypeKind.Boolean.getFullQualifiedName();
  public static final FullQualifiedName nameByte = EdmPrimitiveTypeKind.Byte.getFullQualifiedName();

  public static final FullQualifiedName nameDate = EdmPrimitiveTypeKind.Date.getFullQualifiedName();
  public static final FullQualifiedName nameDateTimeOffset =
      EdmPrimitiveTypeKind.DateTimeOffset.getFullQualifiedName();

  public static final FullQualifiedName nameDecimal =
      EdmPrimitiveTypeKind.Decimal.getFullQualifiedName();
  public static final FullQualifiedName nameDouble =
      EdmPrimitiveTypeKind.Double.getFullQualifiedName();
  public static final FullQualifiedName nameDuration =
      EdmPrimitiveTypeKind.Duration.getFullQualifiedName();

  public static final FullQualifiedName nameGuid = EdmPrimitiveTypeKind.Guid.getFullQualifiedName();
  public static final FullQualifiedName nameInt16 =
      EdmPrimitiveTypeKind.Int16.getFullQualifiedName();
  public static final FullQualifiedName nameInt32 =
      EdmPrimitiveTypeKind.Int32.getFullQualifiedName();
  public static final FullQualifiedName nameInt64 =
      EdmPrimitiveTypeKind.Int64.getFullQualifiedName();

  public static final FullQualifiedName nameSByte =
      EdmPrimitiveTypeKind.SByte.getFullQualifiedName();
  public static final FullQualifiedName nameSingle =
      EdmPrimitiveTypeKind.Single.getFullQualifiedName();

  public static final FullQualifiedName nameString =
      EdmPrimitiveTypeKind.String.getFullQualifiedName();
  public static final FullQualifiedName nameTimeOfDay =
      EdmPrimitiveTypeKind.TimeOfDay.getFullQualifiedName();

  // Primitive Properties
  // --------------------------------------------------------------------------------------------
  public static final CsdlProperty collPropertyBinary =
      new CsdlProperty().setName("CollPropertyBinary").setType(nameBinary).setCollection(true);

  public static final CsdlProperty collPropertyBinary_ExplicitNullable = new CsdlProperty()
      .setName("CollPropertyBinary").setType(nameBinary).setNullable(true).setCollection(true);

  public static final CsdlProperty collPropertyBinary_NotNullable = new CsdlProperty()
      .setName("CollPropertyBinary").setType(nameBinary).setNullable(false).setCollection(true);

  public static final CsdlProperty collPropertyBoolean =
      new CsdlProperty().setName("CollPropertyBoolean").setType(nameBoolean).setCollection(true);

  public static final CsdlProperty collPropertyBoolean_ExplicitNullable = new CsdlProperty()
      .setName("CollPropertyBoolean").setType(nameBoolean).setNullable(true).setCollection(true);

  public static final CsdlProperty collPropertyBoolean_NotNullable = new CsdlProperty()
      .setName("CollPropertyBoolean").setType(nameBoolean).setNullable(true).setCollection(true);

  public static final CsdlProperty collPropertyByte =
      new CsdlProperty().setName("CollPropertyByte").setType(nameByte).setCollection(true);

  public static final CsdlProperty collPropertyByte_ExplicitNullable = new CsdlProperty()
      .setName("CollPropertyByte").setType(nameByte).setNullable(true).setCollection(true);

  public static final CsdlProperty collPropertyByte_NotNullable = new CsdlProperty()
      .setName("CollPropertyByte").setType(nameByte).setNullable(false).setCollection(true);

  public static final CsdlProperty collPropertyDate =
      new CsdlProperty().setName("CollPropertyDate").setType(nameDate).setCollection(true);

  public static final CsdlProperty collPropertyDate_ExplicitNullable = new CsdlProperty()
      .setName("CollPropertyDate").setType(nameDate).setNullable(true).setCollection(true);

  public static final CsdlProperty collPropertyDate_NotNullable = new CsdlProperty()
      .setName("CollPropertyDate").setType(nameDate).setNullable(false).setCollection(true);

  public static final CsdlProperty collPropertyDateTimeOffset = new CsdlProperty()
      .setName("CollPropertyDateTimeOffset").setType(nameDateTimeOffset).setCollection(true);

  public static final CsdlProperty collPropertyDateTimeOffset_ExplicitNullable =
      new CsdlProperty().setName("CollPropertyDateTimeOffset").setType(nameDateTimeOffset)
          .setNullable(true).setCollection(true);

  public static final CsdlProperty collPropertyDateTimeOffset_NotNullable =
      new CsdlProperty().setName("CollPropertyDateTimeOffset").setType(nameDateTimeOffset)
          .setNullable(false).setCollection(true);

  public static final CsdlProperty collPropertyDecimal =
      new CsdlProperty().setName("CollPropertyDecimal").setType(nameDecimal).setCollection(true);

  public static final CsdlProperty collPropertyDecimal_ExplicitNullable = new CsdlProperty()
      .setName("CollPropertyDecimal").setType(nameDecimal).setNullable(true).setCollection(true);

  public static final CsdlProperty collPropertyDecimal_NotNullable = new CsdlProperty()
      .setName("CollPropertyDecimal").setType(nameDecimal).setNullable(false).setCollection(true);

  public static final CsdlProperty collPropertyDouble =
      new CsdlProperty().setName("CollPropertyDouble").setType(nameDouble).setCollection(true);

  public static final CsdlProperty collPropertyDouble_ExplicitNullable = new CsdlProperty()
      .setName("CollPropertyDouble").setType(nameDouble).setNullable(true).setCollection(true);

  public static final CsdlProperty collPropertyDouble_NotNullable = new CsdlProperty()
      .setName("CollPropertyDouble").setType(nameDouble).setNullable(false).setCollection(true);

  public static final CsdlProperty collPropertyDuration =
      new CsdlProperty().setName("CollPropertyDuration").setType(nameDuration).setCollection(true);

  public static final CsdlProperty collPropertyDuration_ExplicitNullable = new CsdlProperty()
      .setName("CollPropertyDuration").setType(nameDuration).setNullable(true).setCollection(true);

  public static final CsdlProperty collPropertyDuration_NotNullable = new CsdlProperty()
      .setName("CollPropertyDuration").setType(nameDuration).setNullable(false).setCollection(true);

  public static final CsdlProperty collPropertyGuid =
      new CsdlProperty().setName("CollPropertyGuid").setType(nameGuid).setCollection(true);

  public static final CsdlProperty collPropertyGuid_ExplicitNullable = new CsdlProperty()
      .setName("CollPropertyGuid").setType(nameGuid).setNullable(true).setCollection(true);

  public static final CsdlProperty collPropertyGuid_NotNullable = new CsdlProperty()
      .setName("CollPropertyGuid").setType(nameGuid).setNullable(false).setCollection(true);

  public static final CsdlProperty collPropertyInt16 =
      new CsdlProperty().setName("CollPropertyInt16").setType(nameInt16).setCollection(true);

  public static final CsdlProperty collPropertyInt16_ExplicitNullable = new CsdlProperty()
      .setName("CollPropertyInt16").setType(nameInt16).setNullable(true).setCollection(true);

  public static final CsdlProperty collPropertyInt16_NotNullable = new CsdlProperty()
      .setName("CollPropertyInt16").setType(nameInt16).setNullable(false).setCollection(true);

  public static final CsdlProperty collPropertyInt32 =
      new CsdlProperty().setName("CollPropertyInt32").setType(nameInt32).setCollection(true);

  public static final CsdlProperty collPropertyInt32_ExplicitNullable = new CsdlProperty()
      .setName("CollPropertyInt32").setType(nameInt32).setNullable(true).setCollection(true);

  public static final CsdlProperty collPropertyInt32_NotNullable = new CsdlProperty()
      .setName("CollPropertyInt32").setType(nameInt32).setNullable(false).setCollection(true);

  public static final CsdlProperty collPropertyInt64 =
      new CsdlProperty().setName("CollPropertyInt64").setType(nameInt64).setCollection(true);

  public static final CsdlProperty collPropertyInt64_ExplicitNullable = new CsdlProperty()
      .setName("CollPropertyInt64").setType(nameInt64).setNullable(true).setCollection(true);

  public static final CsdlProperty collPropertyInt64_NotNullable = new CsdlProperty()
      .setName("CollPropertyInt64").setType(nameInt64).setNullable(false).setCollection(true);

  public static final CsdlProperty collPropertySByte =
      new CsdlProperty().setName("CollPropertySByte").setType(nameSByte).setCollection(true);

  public static final CsdlProperty collPropertySByte_ExplicitNullable = new CsdlProperty()
      .setName("CollPropertySByte").setType(nameSByte).setNullable(true).setCollection(true);

  public static final CsdlProperty collPropertySByte_NotNullable = new CsdlProperty()
      .setName("CollPropertySByte").setType(nameSByte).setNullable(false).setCollection(true);

  public static final CsdlProperty collPropertySingle =
      new CsdlProperty().setName("CollPropertySingle").setType(nameSingle).setCollection(true);

  public static final CsdlProperty collPropertySingle_ExplicitNullable = new CsdlProperty()
      .setName("CollPropertySingle").setType(nameSingle).setNullable(true).setCollection(true);

  public static final CsdlProperty collPropertySingle_NotNullable = new CsdlProperty()
      .setName("CollPropertySingle").setType(nameSingle).setNullable(false).setCollection(true);

  public static final CsdlProperty collPropertyString =
      new CsdlProperty().setName("CollPropertyString").setType(nameString).setCollection(true);

  public static final CsdlProperty collPropertyString_ExplicitNullable = new CsdlProperty()
      .setName("CollPropertyString").setType(nameString).setNullable(true).setCollection(true);

  public static final CsdlProperty collPropertyString_NotNullable = new CsdlProperty()
      .setName("CollPropertyString").setType(nameString).setNullable(false).setCollection(true);

  public static final CsdlProperty collPropertyTimeOfDay = new CsdlProperty()
      .setName("CollPropertyTimeOfDay").setType(nameTimeOfDay).setCollection(true);

  public static final CsdlProperty collPropertyTimeOfDay_ExplicitNullable =
      new CsdlProperty().setName("CollPropertyTimeOfDay").setType(nameTimeOfDay).setNullable(true)
          .setCollection(true);

  public static final CsdlProperty collPropertyTimeOfDay_NotNullable =
      new CsdlProperty().setName("CollPropertyTimeOfDay").setType(nameTimeOfDay).setNullable(false)
          .setCollection(true);

  public static final CsdlProperty propertyBinary =
      new CsdlProperty().setName("PropertyBinary").setType(nameBinary);

  public static final CsdlProperty propertyBinary_NotNullable =
      new CsdlProperty().setName("PropertyBinary").setType(nameBinary).setNullable(false);

  public static final CsdlProperty propertyBinary_ExplicitNullable =
      new CsdlProperty().setName("PropertyBinary").setType(nameBinary).setNullable(true);

  public static final CsdlProperty propertyBoolean =
      new CsdlProperty().setName("PropertyBoolean").setType(nameBoolean);

  public static final CsdlProperty propertyBoolean_NotNullable =
      new CsdlProperty().setName("PropertyBoolean").setType(nameBoolean).setNullable(false);

  public static final CsdlProperty propertyBoolean_ExplicitNullable =
      new CsdlProperty().setName("PropertyBoolean").setType(nameBoolean).setNullable(true);

  public static final CsdlProperty propertyByte =
      new CsdlProperty().setName("PropertyByte").setType(nameByte);

  public static final CsdlProperty propertyByte_NotNullable =
      new CsdlProperty().setName("PropertyByte").setType(nameByte).setNullable(false);

  public static final CsdlProperty propertyByte_ExplicitNullable =
      new CsdlProperty().setName("PropertyByte").setType(nameByte).setNullable(true);

  public static final CsdlProperty propertyDate =
      new CsdlProperty().setName("PropertyDate").setType(nameDate);

  public static final CsdlProperty propertyDate_NotNullable =
      new CsdlProperty().setName("PropertyDate").setType(nameDate).setNullable(false);

  public static final CsdlProperty propertyDate_ExplicitNullable =
      new CsdlProperty().setName("PropertyDate").setType(nameDate).setNullable(true);

  public static final CsdlProperty propertyDateTimeOffset =
      new CsdlProperty().setName("PropertyDateTimeOffset").setType(nameDateTimeOffset);

  public static final CsdlProperty propertyDateTimeOffset_Precision = new CsdlProperty()
      .setName("PropertyDateTimeOffset").setPrecision(12).setType(nameDateTimeOffset);

  public static final CsdlProperty propertyDateTimeOffset_NotNullable = new CsdlProperty()
      .setName("PropertyDateTimeOffset").setType(nameDateTimeOffset).setNullable(false);

  public static final CsdlProperty propertyDateTimeOffset_ExplicitNullable = new CsdlProperty()
      .setName("PropertyDateTimeOffset").setType(nameDateTimeOffset).setNullable(true);

  public static final CsdlProperty propertyDecimal_Scale_Precision = new CsdlProperty()
      .setName("PropertyDecimal").setScale(5).setPrecision(11).setType(nameDecimal);

  public static final CsdlProperty propertyDecimal_Scale =
      new CsdlProperty().setName("PropertyDecimal").setScale(10).setType(nameDecimal);

  public static final CsdlProperty propertyDecimal_NotNullable =
      new CsdlProperty().setName("PropertyDecimal").setType(nameDecimal).setNullable(false);

  public static final CsdlProperty propertyDecimal_ExplicitNullable =
      new CsdlProperty().setName("PropertyDecimal").setType(nameDecimal).setNullable(true);

  public static final CsdlProperty propertyDouble =
      new CsdlProperty().setName("PropertyDouble").setType(nameDouble);

  public static final CsdlProperty propertyDouble_NotNullable =
      new CsdlProperty().setName("PropertyDouble").setType(nameDouble).setNullable(false);

  public static final CsdlProperty propertyDouble_ExplicitNullable =
      new CsdlProperty().setName("PropertyDouble").setType(nameDouble).setNullable(true);

  public static final CsdlProperty propertyDuration =
      new CsdlProperty().setName("PropertyDuration").setType(nameDuration);

  public static final CsdlProperty propertyDuration_NotNullable =
      new CsdlProperty().setName("PropertyDuration").setType(nameDuration).setNullable(false);

  public static final CsdlProperty propertyDuration_ExplicitNullable =
      new CsdlProperty().setName("PropertyDuration").setType(nameDuration).setNullable(true);

  public static final CsdlProperty propertyGuid =
      new CsdlProperty().setName("PropertyGuid").setType(nameGuid);

  public static final CsdlProperty propertyGuid_NotNullable =
      new CsdlProperty().setName("PropertyGuid").setType(nameGuid).setNullable(false);

  public static final CsdlProperty propertyGuid_ExplicitNullable =
      new CsdlProperty().setName("PropertyGuid").setType(nameGuid).setNullable(true);

  public static final CsdlProperty propertyInt16 =
      new CsdlProperty().setName("PropertyInt16").setType(nameInt16);

  public static final CsdlProperty propertyInt16_NotNullable =
      new CsdlProperty().setName("PropertyInt16").setType(nameInt16).setNullable(false);

  public static final CsdlProperty propertyInt16_ExplicitNullable =
      new CsdlProperty().setName("PropertyInt16").setType(nameInt16).setNullable(true);

  public static final CsdlProperty propertyInt32 =
      new CsdlProperty().setName("PropertyInt32").setType(nameInt32);

  public static final CsdlProperty propertyInt32_NotNullable =
      new CsdlProperty().setName("PropertyInt32").setType(nameInt32).setNullable(false);

  public static final CsdlProperty propertyInt32_ExplicitNullable =
      new CsdlProperty().setName("PropertyInt32").setType(nameInt32).setNullable(true);

  public static final CsdlProperty propertyInt64 =
      new CsdlProperty().setName("PropertyInt64").setType(nameInt64);

  public static final CsdlProperty propertyInt64_NotNullable =
      new CsdlProperty().setName("PropertyInt64").setType(nameInt64).setNullable(false);

  public static final CsdlProperty propertyInt64_ExplicitNullable =
      new CsdlProperty().setName("PropertyInt64").setType(nameInt64).setNullable(true);

  public static final CsdlProperty propertySByte =
      new CsdlProperty().setName("PropertySByte").setType(nameSByte);

  public static final CsdlProperty propertySByte_NotNullable =
      new CsdlProperty().setName("PropertySByte").setType(nameSByte).setNullable(false);

  public static final CsdlProperty propertySByte_ExplicitNullable =
      new CsdlProperty().setName("PropertySByte").setType(nameSByte).setNullable(true);

  public static final CsdlProperty propertySingle =
      new CsdlProperty().setName("PropertySingle").setType(nameSingle);

  public static final CsdlProperty propertySingle_NotNullable =
      new CsdlProperty().setName("PropertySingle").setType(nameSingle).setNullable(false);

  public static final CsdlProperty propertySingle_ExplicitNullable =
      new CsdlProperty().setName("PropertySingle").setType(nameSingle).setNullable(true);

  public static final CsdlProperty propertyString =
      new CsdlProperty().setName("PropertyString").setType(nameString);

  public static final CsdlProperty propertyString_NotNullable =
      new CsdlProperty().setName("PropertyString").setType(nameString).setNullable(false);

  public static final CsdlProperty propertyString_ExplicitNullable =
      new CsdlProperty().setName("PropertyString").setType(nameString).setNullable(true);

  public static final CsdlProperty propertyTimeOfDay =
      new CsdlProperty().setName("PropertyTimeOfDay").setType(nameTimeOfDay);

  public static final CsdlProperty propertyTimeOfDay_Precision =
      new CsdlProperty().setName("PropertyTimeOfDay").setPrecision(12).setType(nameTimeOfDay);

  public static final CsdlProperty propertyTimeOfDay_NotNullable =
      new CsdlProperty().setName("PropertyTimeOfDay").setType(nameTimeOfDay).setNullable(false);

  public static final CsdlProperty propertyTimeOfDay_ExplicitNullable =
      new CsdlProperty().setName("PropertyTimeOfDay").setType(nameTimeOfDay).setNullable(true);

  // Complex Properties
  // ----------------------------------------------------------------------------------------------
  public static final CsdlProperty collPropertyComp_CTPrimComp = new CsdlProperty()
      .setName("CollPropertyComp").setType(ComplexTypeProvider.nameCTPrimComp).setCollection(true);

  public static final CsdlProperty collPropertyComp_CTTwoPrim = new CsdlProperty()
      .setName("CollPropertyComp").setType(ComplexTypeProvider.nameCTTwoPrim).setCollection(true);

  public static final CsdlProperty propertyComp_CTAllPrim =
      new CsdlProperty().setName("PropertyComp").setType(ComplexTypeProvider.nameCTAllPrim);

  public static final CsdlProperty propertyComp_CTCollAllPrim =
      new CsdlProperty().setName("PropertyComp").setType(ComplexTypeProvider.nameCTCollAllPrim);

  public static final CsdlProperty propertyComp_CTCompCollComp =
      new CsdlProperty().setName("PropertyComp").setType(ComplexTypeProvider.nameCTCompCollComp);

  public static final CsdlProperty propertyComp_CTCompComp =
      new CsdlProperty().setName("PropertyComp").setType(ComplexTypeProvider.nameCTCompComp);

  public static final CsdlProperty propertyComp_CTNavFiveProp =
      new CsdlProperty().setName("PropertyComp").setType(ComplexTypeProvider.nameCTNavFiveProp);

  public static final CsdlProperty propertyCompNav_CTNavFiveProp =
      new CsdlProperty().setName("PropertyCompNav").setType(ComplexTypeProvider.nameCTNavFiveProp);

  public static final CsdlProperty propertyComp_CTPrimComp_NotNullable = new CsdlProperty()
      .setName("PropertyComp").setType(ComplexTypeProvider.nameCTPrimComp).setNullable(false);

  public static final CsdlProperty propertyComp_CTTwoPrim =
      new CsdlProperty().setName("PropertyComp").setType(ComplexTypeProvider.nameCTTwoPrim);

  public static final CsdlProperty propertyComp_CTTwoPrim_NotNullable = new CsdlProperty()
      .setName("PropertyComp").setType(ComplexTypeProvider.nameCTTwoPrim).setNullable(false);

  public static final CsdlProperty propertyCompNavCont =
      new CsdlProperty().setName("PropertyCompNavCont").setType(ComplexTypeProvider.nameCTNavCont);

  public static final CsdlProperty propertyCompAllPrim_CTAllPrim =
      new CsdlProperty().setName("PropertyCompAllPrim").setType(ComplexTypeProvider.nameCTAllPrim);

  public static final CsdlProperty propertyCompComp_CTCompComp =
      new CsdlProperty().setName("PropertyCompComp").setType(ComplexTypeProvider.nameCTCompComp);

  public static final CsdlProperty propertyCompComp_CTCompComp_NotNullable = new CsdlProperty()
      .setName("PropertyCompComp").setType(ComplexTypeProvider.nameCTCompComp).setNullable(false);

  public static final CsdlProperty propertyCompTwoPrim_CTTwoPrim =
      new CsdlProperty().setName("PropertyCompTwoPrim").setType(ComplexTypeProvider.nameCTTwoPrim);

  public static final CsdlProperty propertyMixedPrimCollComp_CTMixPrimCollComp = new CsdlProperty()
      .setName("PropertyMixedPrimCollComp").setType(ComplexTypeProvider.nameCTMixPrimCollComp);

  public static final CsdlProperty propertyComp_CTMixEnumTypeDefColl = new CsdlProperty()
      .setName("PropertyCompMixedEnumDef").setType(ComplexTypeProvider.nameCTMixEnumDef);

  public static final CsdlProperty propertyCompColl_CTMixEnumTypeDefColl =
      new CsdlProperty().setName("CollPropertyCompMixedEnumDef")
          .setType(ComplexTypeProvider.nameCTMixEnumDef).setCollection(true);

  // Navigation Properties
  // -------------------------------------------------------------------------------------------
  public static final CsdlNavigationProperty collectionNavPropertyETKeyNavMany_ETKeyNav =
      new CsdlNavigationProperty().setName("NavPropertyETKeyNavMany")
          .setType(EntityTypeProvider.nameETKeyNav).setCollection(true);

  public static final CsdlNavigationProperty collectionNavPropertyETMediaMany_ETMedia =
      new CsdlNavigationProperty().setName("NavPropertyETMediaMany")
          .setType(EntityTypeProvider.nameETMedia).setCollection(true);

  public static final CsdlNavigationProperty collectionNavPropertyETTwoKeyNavMany_ETTwoKeyNav_WithPartnerERKeyNavOne =
      new CsdlNavigationProperty().setName("NavPropertyETTwoKeyNavMany")
          .setType(EntityTypeProvider.nameETTwoKeyNav).setCollection(true)
          .setPartner("NavPropertyETKeyNavOne");

  public static final CsdlNavigationProperty collectionNavPropertyETTwoKeyNavMany_ETTwoKeyNav =
      new CsdlNavigationProperty().setName("NavPropertyETTwoKeyNavMany")
          .setType(EntityTypeProvider.nameETTwoKeyNav).setCollection(true);

  public static final CsdlNavigationProperty collectionNavPropertyETTwoKeyNavOne_ETTwoKeyNav =
      new CsdlNavigationProperty().setName("NavPropertyETTwoKeyNavOne")
          .setType(EntityTypeProvider.nameETTwoKeyNav);

  public static final CsdlNavigationProperty collectionNavPropertyETTwoPrimMany_ETTwoPrim =
      new CsdlNavigationProperty().setName("NavPropertyETTwoPrimMany")
          .setType(EntityTypeProvider.nameETTwoPrim).setCollection(true);

  public static final CsdlNavigationProperty collectionNavPropertyETAllPrimMany_ETAllPrim =
      new CsdlNavigationProperty().setName("NavPropertyETAllPrimMany")
          .setType(EntityTypeProvider.nameETAllPrim).setCollection(true);

  public static final CsdlNavigationProperty navPropertySINav = new CsdlNavigationProperty()
      .setName("NavPropertySINav").setCollection(false).setType(EntityTypeProvider.nameETTwoKeyNav);

  public static final CsdlNavigationProperty collectionNavPropertyETKeyNavContMany_CT_ETKeyNav =
      new CsdlNavigationProperty().setName("NavPropertyETKeyNavContMany").setCollection(true)
          .setContainsTarget(true).setType(EntityTypeProvider.nameETKeyNav);

  public static final CsdlNavigationProperty collectionNavPropertyETTwoKeyNavContMany_CT_ETKeyNav =
      new CsdlNavigationProperty().setName("NavPropertyETTwoKeyNavContMany").setCollection(true)
          .setContainsTarget(true).setType(EntityTypeProvider.nameETKeyNav);

  public static final CsdlNavigationProperty navPropertyETKeyNavOne_ETKeyNav =
      new CsdlNavigationProperty().setName("NavPropertyETKeyNavOne")
          .setType(EntityTypeProvider.nameETKeyNav);

  public static final CsdlNavigationProperty navPropertyETMediaOne_ETMedia =
      new CsdlNavigationProperty().setName("NavPropertyETMediaOne")
          .setType(EntityTypeProvider.nameETMedia);

  public static final CsdlNavigationProperty navPropertyETKeyPrimNavOne_ETKeyPrimNav =
      new CsdlNavigationProperty().setName("NavPropertyETKeyPrimNavOne")
          .setType(EntityTypeProvider.nameETKeyPrimNav);

  public static final CsdlNavigationProperty navPropertyETTwoKeyNavOne_ETTwoKeyNav_NotNullable =
      new CsdlNavigationProperty().setName("NavPropertyETTwoKeyNavOne")
          .setType(EntityTypeProvider.nameETTwoKeyNav).setNullable(false);

  public static final CsdlNavigationProperty navPropertyETTwoKeyNavOne_ETTwoKeyNav =
      new CsdlNavigationProperty().setName("NavPropertyETTwoKeyNavOne")
          .setType(EntityTypeProvider.nameETTwoKeyNav);

  public static final CsdlNavigationProperty navPropertyETTwoPrimOne_ETTwoPrim =
      new CsdlNavigationProperty().setName("NavPropertyETTwoPrimOne")
          .setType(EntityTypeProvider.nameETTwoPrim).setNullable(false);

  public static final CsdlNavigationProperty navPropertyETAllPrimOne_ETAllPrim =
      new CsdlNavigationProperty().setName("NavPropertyETAllPrimOne")
          .setType(EntityTypeProvider.nameETAllPrim);

  public static final CsdlNavigationProperty navPropertyETKeyNavContOne_CT_ETeyNav =
      new CsdlNavigationProperty().setName("NavPropertyETKeyNavContOne").setContainsTarget(true)
          .setType(EntityTypeProvider.nameETKeyNav);

  public static final CsdlNavigationProperty navPropertyETTwoKeyNavContOne_CT_ETKeyNav =
      new CsdlNavigationProperty().setName("NavPropertyETTwoKeyNavContOne").setContainsTarget(true)
          .setType(EntityTypeProvider.nameETKeyNav);

  public static final CsdlNavigationProperty navPropertyETTwoKeyNavContOneCT_ETTwoKeyNav =
      new CsdlNavigationProperty().setName("NavPropertyETTwoKeyNavContOne").setContainsTarget(true)
          .setType(EntityTypeProvider.nameETTwoKeyNav);

  public static final CsdlNavigationProperty collectionNavPropertyETTwoKeyNavContMany_CT_ETTwoKeyNav =
      new CsdlNavigationProperty().setName("NavPropertyETTwoKeyNavContMany").setContainsTarget(true)
          .setCollection(true).setType(EntityTypeProvider.nameETTwoKeyNav);

  // EnumProperties
  // --------------------------------------------------------------------------------------------------
  public static final CsdlProperty propertyEnumString_ENString = new CsdlProperty()
      .setName("PropertyEnumString").setType(EnumTypeProvider.nameENString).setNullable(false);

  public static final CsdlProperty propertyEnumString_ENString_Nullable =
      new CsdlProperty().setName("PropertyEnumString").setType(EnumTypeProvider.nameENString);

  public static final CsdlProperty collPropertyEnumString_ENString = new CsdlProperty()
      .setName("CollPropertyEnumString").setType(EnumTypeProvider.nameENString).setCollection(true);

  // TypeDefinition Properties
  // ---------------------------------------------------------------------------------------
  public static final CsdlProperty propertyTypeDefinition_TDString = new CsdlProperty()
      .setName("PropertyDefString").setType(TypeDefinitionProvider.nameTDString).setMaxLength(15);

  public static final CsdlProperty collPropertyTypeDefinition_TDString =
      new CsdlProperty().setName("CollPropertyDefString")
          .setType(TypeDefinitionProvider.nameTDString).setMaxLength(15).setCollection(true);
}
