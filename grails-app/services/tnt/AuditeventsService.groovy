package tnt

import grails.gorm.services.Service

@Service(Auditevents)
interface AuditeventsService {

    Auditevents get(Serializable id)

    List<Auditevents> list(Map args)

    Long count()

    void delete(Serializable id)

    Auditevents save(Auditevents auditevents)

}