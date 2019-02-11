package tnt

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class AuditlogServiceSpec extends Specification {

    AuditlogService auditlogService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Auditlog(...).save(flush: true, failOnError: true)
        //new Auditlog(...).save(flush: true, failOnError: true)
        //Auditlog auditlog = new Auditlog(...).save(flush: true, failOnError: true)
        //new Auditlog(...).save(flush: true, failOnError: true)
        //new Auditlog(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //auditlog.id
    }

    void "test get"() {
        setupData()

        expect:
        auditlogService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Auditlog> auditlogList = auditlogService.list(max: 2, offset: 2)

        then:
        auditlogList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        auditlogService.count() == 5
    }

    void "test delete"() {
        Long auditlogId = setupData()

        expect:
        auditlogService.count() == 5

        when:
        auditlogService.delete(auditlogId)
        sessionFactory.currentSession.flush()

        then:
        auditlogService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Auditlog auditlog = new Auditlog()
        auditlogService.save(auditlog)

        then:
        auditlog.id != null
    }
}
