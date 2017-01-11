import com.stachanov.angular.workshop.backend.Post
import com.stachanov.angular.workshop.backend.User

class BootStrap {

    def init = { servletContext ->

        def user = new User(firstName: 'Matthijs', lastName: 'Bierman', email: 'matthijs@geckotech.nl').save(failOnError: true, flush: true)
        new Post(author: user, title: 'Hello World', message: 'This is the first post. Will it work?').save(failOnError: true, flush: true)

    }
    def destroy = {
    }
}
