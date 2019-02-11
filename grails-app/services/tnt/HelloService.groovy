package tnt

import grails.gorm.services.Service

@Service(Hello)
interface HelloService {

    Hello get(Serializable id)

    List<Hello> list(Map args)

    Long count()

    void delete(Serializable id)

    Hello save(Hello hello)

}