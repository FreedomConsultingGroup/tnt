package tnt

import grails.gorm.services.Service

@Service(Auditlog)
interface AuditlogService {

    Auditlog get(Serializable id)

    List<Auditlog> list(Map args)

    Long count()

    void delete(Serializable id)

    Auditlog save(Auditlog auditlog)

}