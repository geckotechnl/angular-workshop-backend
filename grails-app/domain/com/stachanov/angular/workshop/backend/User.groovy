package com.stachanov.angular.workshop.backend

import grails.rest.*

@Resource(uri='/users', superClass = RestController)
class User {

    String firstName
    String lastName
    String email
    Boolean enabled = true
    Date dateCreated

    static constraints = {
        dateCreated nullable:true
    }
}