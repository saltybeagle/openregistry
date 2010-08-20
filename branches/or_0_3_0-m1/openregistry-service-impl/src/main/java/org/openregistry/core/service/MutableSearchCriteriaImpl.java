/**
 * Licensed to Jasig under one or more contributor license
 * agreements. See the NOTICE file distributed with this work
 * for additional information regarding copyright ownership.
 * Jasig licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a
 * copy of the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.openregistry.core.service;

import org.springframework.core.style.ToStringCreator;

import java.util.Date;

/**
 * @author Scott Battaglia
 * @version $Revision$ $Date$
 * @since 1.0.0
 */
public final class MutableSearchCriteriaImpl implements SearchCriteria {

    private String givenName;

    private String familyName;

    private Date dateOfBirth;

    private String identifierValue;

    private String name;

    public String getGivenName() {
        return this.givenName;
    }

    public String getFamilyName() {
        return this.familyName;
    }

    public Date getDateOfBirth() {
        return this.dateOfBirth;
    }

    public String getIdentifierValue() {
        return this.identifierValue;
    }

    public void setGivenName(final String givenName) {
        this.givenName = givenName;
    }

    public void setFamilyName(final String familyName) {
        this.familyName = familyName;
    }

    public void setDateOfBirth(final Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setIdentifierValue(final String identifierValue) {
        this.identifierValue = identifierValue;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.familyName = null;
        this.givenName = null;
        this.name = name.trim();

        if (!this.name.contains(" ") && !this.name.contains(",")) {
            return;
        }

        if (this.name.contains(",")) {
            final String[] split = this.name.split(",");
            this.givenName = split[1];
            this.familyName = split[0];
            return;
        }

        final String[] split = name.split(" ");
        this.givenName = split[0];
        this.familyName = split[1];
    }

    public String toString() {
        final ToStringCreator toStringCreator = new ToStringCreator(this);
        toStringCreator.append("identifierValue", this.identifierValue);
        toStringCreator.append("dateOfBirth", this.dateOfBirth);
        toStringCreator.append("familyName", this.familyName);
        toStringCreator.append("givenName", this.givenName);
        toStringCreator.append("name", this.name);
        return toStringCreator.toString();
    }
}