package org.openregistry.core.web.propertyeditors;

import org.openregistry.core.domain.jpa.JpaCountryImpl;
import org.openregistry.core.domain.Country;
import org.openregistry.core.repository.ReferenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.PropertyEditorSupport;
import java.util.List;
import java.util.Iterator;

/**
 * Created by IntelliJ IDEA.
 * User: Nancy Mond
 * Date: Jan 30, 2009
 * Time: 10:34:12 AM
 * To change this template use File | Settings | File Templates.
 */
public class CountryEditor extends PropertyEditorSupport {

    private String format;

     protected final Logger logger = LoggerFactory.getLogger(getClass());
    ReferenceRepository referenceRepository;

    public CountryEditor(ReferenceRepository referenceRepository){
        this.referenceRepository = referenceRepository;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getAsText(){
        Country country = (Country) getValue();
        String name = " ";
        if (country != null)
            name = country.getName();
        return (name);
    }

    
    public void setAsText(String text) {
        setValue(null);
        if (text != null && StringUtils.hasText(text)){
            List countryList =  referenceRepository.getCountries();
            Iterator<Country> iterator = countryList.iterator();
	        while ( iterator.hasNext() ){
	            Country country = iterator.next();
                if (country.getCode().equals(text)){
                    setValue(country);
                    break;
                }
	        }
        }
    }
}
