package tnt

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class AuditlogController {

    AuditlogService auditlogService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond auditlogService.list(params), model:[auditlogCount: auditlogService.count()]
    }

    def show(Long id) {
        respond auditlogService.get(id)
    }

    def create() {
        respond new Auditlog(params)
    }

    def save(Auditlog auditlog) {
        if (auditlog == null) {
            notFound()
            return
        }

        try {
            auditlogService.save(auditlog)
        } catch (ValidationException e) {
            respond auditlog.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'auditlog.label', default: 'Auditlog'), auditlog.id])
                redirect auditlog
            }
            '*' { respond auditlog, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond auditlogService.get(id)
    }

    def update(Auditlog auditlog) {
        if (auditlog == null) {
            notFound()
            return
        }

        try {
            auditlogService.save(auditlog)
        } catch (ValidationException e) {
            respond auditlog.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'auditlog.label', default: 'Auditlog'), auditlog.id])
                redirect auditlog
            }
            '*'{ respond auditlog, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        auditlogService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'auditlog.label', default: 'Auditlog'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'auditlog.label', default: 'Auditlog'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
