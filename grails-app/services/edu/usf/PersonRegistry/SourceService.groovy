package edu.usf.PersonRegistry

class SourceService {
    static transactional = true
    def grailsApplication

    def addSource(String name) {
        if(!!name) {
            def source = [ 
                name: name.trim()
            ] as Source
            if(!source.save(failOnError:false, flush: true, insert: true, validate: true)) {
                return [ error : "Name value '${source.errors.fieldError.rejectedValue}' rejected" ]
            } else {
                return [ source: source ]
            }
        }
        return [ error: "You must supply a name" ]
    }
    
    def getSource(String name) {
        if(!!name) {
            def source = Source.findByName(name.trim())
            if(!!source) {
                return [ source: source ]
            }
            return [ error : "Source named ${name} not found!"]
        }
        return [ error : "You must supply a name for the target Source"]        
    }
}
