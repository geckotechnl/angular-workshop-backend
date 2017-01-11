package com.stachanov.angular.workshop.backend

import grails.converters.JSON
import grails.rest.RestfulController
import org.codehaus.groovy.grails.commons.DefaultGrailsDomainClass
import org.codehaus.groovy.grails.commons.GrailsDomainClass

class RestController<T> extends RestfulController<T> {

    RestController(Class<T> domainClass) {
        this(domainClass, false)
    }

    RestController(Class<T> domainClass, boolean readOnly) {
        super(domainClass, readOnly)
    }

    def index(Integer max) {
        String query = params.query?.toLowerCase()
        if (!query) {
            params.max = Math.min(max ?: 10, 100)
            respond listAllResources(params), model: [("${resourceName}Count".toString()): countResources()]
        } else {
            GrailsDomainClass domainClass = new DefaultGrailsDomainClass(resource)
            List<String> allFields = domainClass.persistentProperties.grep {
                it.type == String
            }.name
            List<String> searchFields = params.fields?.split(',')
            if(searchFields && !allFields.containsAll(searchFields)) {
                throw new IllegalArgumentException("invalid field: ${searchFields - allFields}")
            }
            List<String> properties = searchFields ?: allFields
            respond resource.withCriteria {
                or {
                    properties.each {
                        ilike(it, "%${params.query}%")
                    }
                }
            }
        }
    }

    def handleIllegalArgumentException(IllegalArgumentException ex) {
        response.status = 500
        render ([error: ex.message] as JSON)
    }

}
