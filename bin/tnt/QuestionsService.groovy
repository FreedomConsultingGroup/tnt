package tnt

import grails.gorm.services.Service

@Service(Questions)
interface QuestionsService {

    Questions get(Serializable id)

    List<Questions> list(Map args)

    Long count()

    void delete(Serializable id)

    Questions save(Questions questions)

}