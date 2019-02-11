package tnt
/**
 * The Auditlog entity.
 *
 * @author    
 *
 *
 */
class Auditlog implements Serializable {
    static mapping = {
         table 'auditlog'
         // version is set to false, because this isn't available by default for legacy databases
         version false
         // In case a sequence is needed, changed the identity generator for the following code:
//       id generator:'sequence', column:'customers_id', params:[sequence:'auditlog_sequence']
         id generator:'identity', column:'customers_id'
         // In case a sequence is needed, changed the identity generator for the following code:
//       id generator:'sequence', column:'auditevents_id', params:[sequence:'auditlog_sequence']
         id generator:'identity', column:'auditevents_id'
         id composite:['customersId','auditeventsId'], generator:'assigned'
    }
    Long customersId
    Long auditeventsId
    Date timeOfEvent
    String customersUserid

    static constraints = {
        customersId(max: 9999999999L)
        auditeventsId(max: 9999999999L)
        timeOfEvent()
        customersUserid(size: 1..20, blank: false)
    }
    String toString() {
        return "${customersId}" 
    }
}
