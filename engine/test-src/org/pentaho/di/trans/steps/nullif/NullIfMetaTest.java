/*! ******************************************************************************
 *
 * Pentaho Data Integration
 *
 * Copyright (C) 2002-2016 by Pentaho : http://www.pentaho.com
 *
 *******************************************************************************
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 ******************************************************************************/

package org.pentaho.di.trans.steps.nullif;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.trans.steps.loadsave.LoadSaveTester;
import org.pentaho.di.trans.steps.loadsave.validator.ArrayLoadSaveValidator;
import org.pentaho.di.trans.steps.loadsave.validator.FieldLoadSaveValidator;
import org.pentaho.di.trans.steps.loadsave.validator.StringLoadSaveValidator;

public class NullIfMetaTest {
  LoadSaveTester loadSaveTester;

  @Before
  public void setUp() throws Exception {

    List<String> attributes =
        Arrays.asList( "fieldName", "fieldValue" );

    Map<String, String> getterMap = new HashMap<String, String>() {
      {
        put( "fieldName", "getFieldName" );
        put( "fieldValue", "getFieldValue" );
      }
    };
    Map<String, String> setterMap = new HashMap<String, String>() {
      {
        put( "fieldName", "setFieldName" );
        put( "fieldValue", "setFieldValue" );
      }
    };
    FieldLoadSaveValidator<String[]> stringArrayLoadSaveValidator =
        new ArrayLoadSaveValidator<String>( new StringLoadSaveValidator(), 5 );
    Map<String, FieldLoadSaveValidator<?>> attrValidatorMap = new HashMap<String, FieldLoadSaveValidator<?>>();
    attrValidatorMap.put( "fieldName", stringArrayLoadSaveValidator );
    attrValidatorMap.put( "fieldValue", stringArrayLoadSaveValidator );

    Map<String, FieldLoadSaveValidator<?>> typeValidatorMap = new HashMap<String, FieldLoadSaveValidator<?>>();
    // typeValidatorMap.put( int[].class.getCanonicalName(), new PrimitiveIntArrayLoadSaveValidator( new IntLoadSaveValidator(), 1 ) );

    loadSaveTester =
        new LoadSaveTester( NullIfMeta.class, attributes, getterMap, setterMap, attrValidatorMap, typeValidatorMap );
  }

  @Test
  public void testSerialization() throws KettleException {
    loadSaveTester.testSerialization();
  }

}
