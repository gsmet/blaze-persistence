/*
 * Copyright 2014 - 2017 Blazebit.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.blazebit.persistence.view.impl.metamodel.attribute;

import com.blazebit.persistence.view.impl.metamodel.AbstractMethodSingularAttribute;
import com.blazebit.persistence.view.impl.metamodel.MetamodelBuildingContext;
import com.blazebit.persistence.view.metamodel.ManagedViewType;
import com.blazebit.persistence.view.metamodel.SubqueryAttribute;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 *
 * @author Christian Beikov
 * @since 1.2.0
 */
public class SubqueryMethodSingularAttribute<X, Y> extends AbstractMethodSingularAttribute<X, Y> implements SubqueryAttribute<X, Y> {

    public SubqueryMethodSingularAttribute(ManagedViewType<X> viewType, Method method, Annotation mapping, MetamodelBuildingContext context) {
        super(viewType, method, mapping, context);
    }

    @Override
    public boolean isCorrelated() {
        return false;
    }

    @Override
    public boolean isSubquery() {
        return true;
    }
}