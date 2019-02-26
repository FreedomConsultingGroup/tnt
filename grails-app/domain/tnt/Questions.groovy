package tnt
/**
 * The Questions entity.
 *
 * @author    
 *
 *
 */
class Questions {
    static mapping = {
         table 'questions'
         // version is set to false, because this isn't available by default for legacy databases
         version false
         // In case a sequence is needed, changed the identity generator for the following code:
       id generator:'sequence', column:'id', params:[sequence:'questions_sequence']
 //        id generator:'identity', column:'id'
      //  dateCreated defaultValue: new Date()
      //  dateModified defaultValue: new Date()
    
    }
    Long id
    Long questionsetId
    String question
    String questionDescription
    Date dateCreated
    Date dateModified

    static constraints = {
        id(max: 9999999999L)
        questionsetId(max: 9999999999L)
        question(size: 1..100, blank: false)
        questionDescription(size: 1..4000, blank: false)
        dateCreated()
        dateModified()
    }
    String toString() {
        return "${id}" 
    }
}
