package edu.usf.PersonRegistry

class IdentifierService {
    static transactional = true
    def grailsApplication

    def addIdentifierType(String name,String validator="") {
        if(!!name) {
            def identifierType = [ 
                name: name.trim(),
                validator: (!!validator)?validator.trim():""
            ] as IdentifierType
            if(!identifierType.save(failOnError:false, flush: true, insert: true, validate: true)) {
                return [ error : "'${identifierType.errors.fieldError.field}' value '${identifierType.errors.fieldError.rejectedValue}' rejected" ]
            } else {
                return [ identifierType: identifierType ]
            }
        }
        return [ error: "You must supply a name" ]
    }
    
    def getIdentifierType(String name) {
        if(!!name) {
            def identifierType = IdentifierType.findByName(name.trim())
            if(!!identifierType) {
                return [ identifierType: identifierType ]
            }
            return [ error : "IdentifierType named ${name} not found!"]
        }
        return [ error : "You must supply a name for the target IdentifierType"]        
    }
}
