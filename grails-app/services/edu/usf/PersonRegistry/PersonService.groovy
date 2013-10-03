package edu.usf.PersonRegistry

class PersonService {
    static transactional = true
    def grailsApplication

    def addPerson() {
        def person = new Person()
        if(!person.save(failOnError:false, flush: true, insert: true, validate: true)) {
            return [ error : "'${person.errors.fieldError.field}' value '${person.errors.fieldError.rejectedValue}' rejected" ]
        } else {
            return [ personId: person.id ]
        }        
    }
}
