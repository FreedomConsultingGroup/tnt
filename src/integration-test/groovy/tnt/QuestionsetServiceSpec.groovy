package tnt

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class QuestionsetServiceSpec extends Specification {

    QuestionsetService questionsetService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Questionset(...).save(flush: true, failOnError: true)
        //new Questionset(...).save(flush: true, failOnError: true)
        //Questionset questionset = new Questionset(...).save(flush: true, failOnError: true)
        //new Questionset(...).save(flush: true, failOnError: true)
        //new Questionset(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //questionset.id
    }

    void "test get"() {
        setupData()

        expect:
        questionsetService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Questionset> questionsetList = questionsetService.list(max: 2, offset: 2)

        then:
        questionsetList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        questionsetService.count() == 5
    }

    void "test delete"() {
        Long questionsetId = setupData()

        expect:
        questionsetService.count() == 5

        when:
        questionsetService.delete(questionsetId)
        sessionFactory.currentSession.flush()

        then:
        questionsetService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Questionset questionset = new Questionset()
        questionsetService.save(questionset)

        then:
        questionset.id != null
    }
}
