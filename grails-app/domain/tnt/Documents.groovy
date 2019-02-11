package tnt
/**
 * The Documents entity.
 *
 * @author    
 *
 *
 */
class Documents {
    static mapping = {
         table 'documents'
         // version is set to false, because this isn't available by default for legacy databases
         version false
         id column:'id'
    }
    Long id
    Long customersId
    String name
    String description
    String hash
    Date dateUploaded
    Date dateModified
    String comments

    static constraints = {
        id(max: 9999999999L)
        customersId(max: 9999999999L)
        name(size: 0..100)
        description(size: 0..4000)
        hash(size: 0..4000)
        dateUploaded(nullable: true)
        dateModified(nullable: true)
        comments()
    }
    String toString() {
        return "${id}" 
    }
}
