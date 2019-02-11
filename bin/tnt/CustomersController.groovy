package tnt

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class CustomersController {

    CustomersService customersService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond customersService.list(params), model:[customersCount: customersService.count()]
    }

    def show(Long id) {
        respond customersService.get(id)
    }

    def create() {
        respond new Customers(params)
    }

    def save(Customers customers) {
        if (customers == null) {
            notFound()
            return
        }

        try {
            customersService.save(customers)
        } catch (ValidationException e) {
            respond customers.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'customers.label', default: 'Customers'), customers.id])
                redirect customers
            }
            '*' { respond customers, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond customersService.get(id)
    }

    def update(Customers customers) {
        if (customers == null) {
            notFound()
            return
        }

        try {
            customersService.save(customers)
        } catch (ValidationException e) {
            respond customers.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'customers.label', default: 'Customers'), customers.id])
                redirect customers
            }
            '*'{ respond customers, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        customersService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'customers.label', default: 'Customers'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'customers.label', default: 'Customers'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
