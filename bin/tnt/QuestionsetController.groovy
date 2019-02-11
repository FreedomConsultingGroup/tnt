package tnt

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class QuestionsetController {

    QuestionsetService questionsetService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond questionsetService.list(params), model:[questionsetCount: questionsetService.count()]
    }

    def show(Long id) {
        respond questionsetService.get(id)
    }

    def create() {
        respond new Questionset(params)
    }

    def save(Questionset questionset) {
        if (questionset == null) {
            notFound()
            return
        }

        try {
            questionsetService.save(questionset)
        } catch (ValidationException e) {
            respond questionset.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'questionset.label', default: 'Questionset'), questionset.id])
                redirect questionset
            }
            '*' { respond questionset, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond questionsetService.get(id)
    }

    def update(Questionset questionset) {
        if (questionset == null) {
            notFound()
            return
        }

        try {
            questionsetService.save(questionset)
        } catch (ValidationException e) {
            respond questionset.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'questionset.label', default: 'Questionset'), questionset.id])
                redirect questionset
            }
            '*'{ respond questionset, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        questionsetService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'questionset.label', default: 'Questionset'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'questionset.label', default: 'Questionset'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
