/*
 * SPDX-License-Identifier: Apache-2.0
 * Copyright Blazebit
 */

package com.blazebit.persistence.view.testsuite.builder.model;

import com.blazebit.persistence.testsuite.entity.Person;
import com.blazebit.persistence.view.EntityView;
import com.blazebit.persistence.view.testsuite.basic.model.IdHolderView;

/**
 *
 * @author Christian Beikov
 * @since 1.5.0
 */
@EntityView(Person.class)
public interface PersonView extends IdHolderView<Long> {

    public String getName();
}
