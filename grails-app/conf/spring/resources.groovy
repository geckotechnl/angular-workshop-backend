import com.stachanov.angular.workshop.backend.Post
import com.stachanov.angular.workshop.backend.User
import grails.rest.render.json.JsonRenderer

// Place your Spring DSL code here
beans = {

    userMarshaller(JsonRenderer, User) {
        excludes = ['class']
    }

    postMarshaller(JsonRenderer, Post) {
        excludes = ['class']
    }

}