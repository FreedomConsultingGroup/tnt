package tnt
/**
 * The Questionset entity.
 *
 * @author    
 *
 *
 */
class Questionset {
    static mapping = {
         table 'questionset'
         // version is set to false, because this isn't available by default for legacy databases
         version false
         // In case a sequence is needed, changed the identity generator for the following code:
//       id generator:'sequence', column:'id', params:[sequence:'questionset_sequence']
         id generator:'identity', column:'id'
    }
    Long id
    String name
    String description

    static constraints = {
        id(max: 9999999999L)
        name(size: 1..100, blank: false)
        description(size: 1..4000, blank: false)
    }
    String toString() {
        return "${id}" 
    }
}
