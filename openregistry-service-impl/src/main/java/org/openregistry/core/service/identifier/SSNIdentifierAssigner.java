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

package org.openregistry.core.service.identifier;

import org.openregistry.core.domain.*;
import org.openregistry.core.domain.sor.*;
import org.openregistry.core.repository.*;
import org.springframework.util.*;

import javax.inject.*;

/**
 * SSN identifier assigner.
 *
 * @version $Revision$ $Date$
 * @since 0.1
 */
public class SSNIdentifierAssigner extends AbstractIdentifierAssigner {

    private final String identifierType = "SSN";

	private final ReferenceRepository referenceRepository;

    @Inject
    public SSNIdentifierAssigner(final ReferenceRepository referenceRepository) {
        this.referenceRepository = referenceRepository;
    }

    public void addIdentifierTo(final SorPerson sorPerson, final Person person) {
		if (StringUtils.hasText(sorPerson.getSsn())) {
			final Identifier ssn = findPrimaryIdentifier(person, this.getIdentifierType());
			if (ssn == null) {
				final Identifier identifier = person.addIdentifier(referenceRepository.findIdentifierType(identifierType),sorPerson.getSsn());
				identifier.setDeleted(false);
				identifier.setPrimary(true);
			} else { // check that we aren't given a different SSN value
				if (! ssn.getValue().equals(sorPerson.getSsn())) {
					// TODO Throw error here!!!  or log
				}
			}
		}
    }

    public String getIdentifierType() {
        return identifierType;
    }
}
