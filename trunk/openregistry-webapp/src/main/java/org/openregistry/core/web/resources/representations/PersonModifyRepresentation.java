/**
 * Copyright (C) 2009 Jasig, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.openregistry.core.web.resources.representations;

import org.openregistry.core.domain.Name;

import javax.ws.rs.core.MultivaluedMap;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * A simple struct-like class encapsulating an incoming request for modification to the registry of
 * a person record from typical upstream systems of record.
 * This class should <b>only</b> be used to unmarshal serialized representation of a modify person record.
 * The unmarshaling of the person XML representation is done automatically by JAXB.
 * <p/>
 *
 * @author Dmitriy Kopylenko
 * @author Nancy Mond
 * @since 1.0
 */
@XmlRootElement(name = "open-registry-modify-person")
public class PersonModifyRepresentation {

    /* Required fields *************/

    @XmlElementWrapper(name = "names")
    @XmlElement(name = "name")
    public List<Name> names = new ArrayList<Name>();

    @XmlRootElement(name = "name")
    public static class Name {

        @XmlAttribute(name = "type")
        public String nameType;
        
        @XmlElement(name = "first-name")
        public String firstName;

        @XmlElement(name = "last-name")
        public String lastName;

        @XmlElement(name = "prefix")
        public String prefix;

        @XmlElement(name = "suffix")
        public String suffix;

        @XmlElement(name = "middle-name")
        public String middleName;
    }

    /* Optional fields *************/
    @XmlElement(name = "dob")
    public Date dateOfBirth;

    @XmlElement
    public String ssn;

    @XmlElement
    public String gender;

    @Override
    public String toString() {
        return "PersonModifyRepresentation{" +
                ", firstName='" + names.get(0).firstName + '\'' +
                ", lastName='" + names.get(0).lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", ssn='" + ssn + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }

    /**
     * Factory method for use only for mocking purposes in tests
     *
     * @return
     */

    public static PersonModifyRepresentation newRepresentationWithRequiredData() {
        //For test purposes, distinguish between 'reconciliation error' and 'validation error'
        // by setting the 'ssn' property
        final PersonModifyRepresentation rep = new PersonModifyRepresentation();
        Name name = new Name();
        name.nameType = "FORMAL";
        name.firstName = "test";
        name.lastName = "test";
        name.prefix = "test";
        name.suffix = "test";
        name.middleName = "test";
        rep.names.add(name);
        rep.ssn = "xxx-xx-xxxx";
        rep.gender="F";
        rep.dateOfBirth=new Date();
        return rep;
    }
}