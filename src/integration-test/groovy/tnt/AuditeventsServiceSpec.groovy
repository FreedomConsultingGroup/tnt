package tnt

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class AuditeventsServiceSpec extends Specification {

    AuditeventsService auditeventsService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Auditevents(...).save(flush: true, failOnError: true)
        //new Auditevents(...).save(flush: true, failOnError: true)
        //Auditevents auditevents = new Auditevents(...).save(flush: true, failOnError: true)
        //new Auditevents(...).save(flush: true, failOnError: true)
        //new Auditevents(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //auditevents.id
    }

    void "test get"() {
        setupData()

        expect:
        auditeventsService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Auditevents> auditeventsList = auditeventsService.list(max: 2, offset: 2)

        then:
        auditeventsList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        auditeventsService.count() == 5
    }

    void "test delete"() {
        Long auditeventsId = setupData()

        expect:
        auditeventsService.count() == 5

        when:
        auditeventsService.delete(auditeventsId)
        sessionFactory.currentSession.flush()

        then:
        auditeventsService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Auditevents auditevents = new Auditevents()
        auditeventsService.save(auditevents)

        then:
        auditevents.id != null
    }
}
