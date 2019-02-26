package tnt
/**
 * The Customers entity.
 *
 * @author    
 *
 *
 */
class Customers {
    static mapping = {
         table 'customers'
         // version is set to false, because this isn't available by default for legacy databases
         version false
         // In case a sequence is needed, changed the identity generator for the following code:
       id generator:'sequence', column:'id', params:[sequence:'customers_sequence']
//         id generator:'identity', column:'id'
       //     lastLogin defaultValue: new Date()
    }
    Long id
    String userid
    String lastName
    String firstName
    Date lastLogin

    static constraints = {
        id(max: 9999999999L)
        userid(size: 1..20, blank: false)
        lastName(size: 1..100, blank: false)
        firstName(size: 1..100, blank: false)
        lastLogin(max: 9999999999L)
    }
    String toString() {
        return "${id}" 
    }
}
