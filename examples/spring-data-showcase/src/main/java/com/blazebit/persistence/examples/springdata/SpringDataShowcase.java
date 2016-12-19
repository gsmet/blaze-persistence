/*
 * Copyright 2014 - 2016 Blazebit.
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

package com.blazebit.persistence.examples.springdata;

import com.blazebit.persistence.examples.spi.AbstractShowcase;
import com.blazebit.persistence.examples.springdata.data.TestDataGenerator;
import com.blazebit.persistence.examples.springdata.repository.CatRepository;

import javax.inject.Inject;

/**
 * @author Moritz Becker (moritz.becker@gmx.at)
 * @since 1.2
 */
public class SpringDataShowcase extends AbstractShowcase {

    @Inject
    private TestDataGenerator testDataGenerator;

    @Inject
    private CatRepository catRepository;

    @Override
    public void run() {
        testDataGenerator.generateTestData();
        print(catRepository.findAll());
    }
}