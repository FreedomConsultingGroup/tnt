package tnt

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class HelloController {

    HelloService helloService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond helloService.list(params), model:[helloCount: helloService.count()]
    }

    def show(Long id) {
        respond helloService.get(id)
    }

    def create() {
        respond new Hello(params)
    }

    def save(Hello hello) {
        if (hello == null) {
            notFound()
            return
        }

        try {
            helloService.save(hello)
        } catch (ValidationException e) {
            respond hello.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'hello.label', default: 'Hello'), hello.id])
                redirect hello
            }
            '*' { respond hello, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond helloService.get(id)
    }

    def update(Hello hello) {
        if (hello == null) {
            notFound()
            return
        }

        try {
            helloService.save(hello)
        } catch (ValidationException e) {
            respond hello.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'hello.label', default: 'Hello'), hello.id])
                redirect hello
            }
            '*'{ respond hello, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        helloService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'hello.label', default: 'Hello'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'hello.label', default: 'Hello'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
