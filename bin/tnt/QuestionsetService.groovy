package tnt

import grails.gorm.services.Service

@Service(Questionset)
interface QuestionsetService {

    Questionset get(Serializable id)

    List<Questionset> list(Map args)

    Long count()

    void delete(Serializable id)

    Questionset save(Questionset questionset)

}