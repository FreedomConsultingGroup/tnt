package tnt
/**
 * The Auditevents entity.
 *
 * @author    
 *
 *
 */
class Auditevents implements Serializable {
    static mapping = {
         table 'auditevents'
         // version is set to false, because this isn't available by default for legacy databases
         version false
         // In case a sequence is needed, changed the identity generator for the following code:
//       id generator:'sequence', column:'id', params:[sequence:'auditevents_sequence']
         id generator:'identity', column:'id'
    }
    Long id
    String description
    String code

    static constraints = {
        id(max: 9999999999L)
        description(size: 1..4000, blank: false)
        code(size: 1..10, blank: false)
    }
    String toString() {
        return "${id}" 
    }
}
