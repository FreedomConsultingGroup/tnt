package tnt

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class AuditeventsController {

    AuditeventsService auditeventsService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond auditeventsService.list(params), model:[auditeventsCount: auditeventsService.count()]
    }

    def show(Long id) {
        respond auditeventsService.get(id)
    }

    def create() {
        respond new Auditevents(params)
    }

    def save(Auditevents auditevents) {
        if (auditevents == null) {
            notFound()
            return
        }

        try {
            auditeventsService.save(auditevents)
        } catch (ValidationException e) {
            respond auditevents.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'auditevents.label', default: 'Auditevents'), auditevents.id])
                redirect auditevents
            }
            '*' { respond auditevents, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond auditeventsService.get(id)
    }

    def update(Auditevents auditevents) {
        if (auditevents == null) {
            notFound()
            return
        }

        try {
            auditeventsService.save(auditevents)
        } catch (ValidationException e) {
            respond auditevents.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'auditevents.label', default: 'Auditevents'), auditevents.id])
                redirect auditevents
            }
            '*'{ respond auditevents, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        auditeventsService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'auditevents.label', default: 'Auditevents'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'auditevents.label', default: 'Auditevents'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
