package com.stachanov.angular.workshop.backend

import grails.rest.*

@Resource(uri='/posts', superClass = RestController )
class Post {

    User author
    String title
    String message
    Date dateCreated

    static constraints = {
        dateCreated nullable:true
    }

}