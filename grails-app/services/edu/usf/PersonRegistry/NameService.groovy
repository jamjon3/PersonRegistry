package edu.usf.PersonRegistry

class NameService {
    static transactional = true
    def grailsApplication
    def addName(def name,String personId,String sor) {
        if(!!personId) {
            name.person = Person.get(personId)
            if(!!name.person) {
                if(!!sor) {
                    name.source = Source.findByName(sor.trim())
                    if(!!name.source) {
                        def nameExists = Name.find(name as Name)
                        if(!!!nameExists) {
                            nameExists = name as Name
                            if(!nameExists.save(failOnError:false, flush: true, insert: true, validate: true)) {
                                return [ error : "'${nameExists.errors.fieldError.field}' value '${nameExists.errors.fieldError.rejectedValue}' rejected" ]
                            } else {
                                return [ 
                                    name: name.collectEntries {
                                        if(it.key in ['person','source']) {
                                            switch(it.key) {
                                                case "person":
                                                    return [ personId: it.value.id ]
                                                    break
                                                case "source":
                                                    return [ source: it.value.name ]
                                                    break
                                            }
                                        }
                                        return [ "${it.key}": it.value ]
                                    } 
                                ]
                            }                            
                        }
                        return [ error: "Name already exists for this person and source of record"]
                    }
                }
                return [ error: "You must supply the name of the source of record (SOR)"]
            }
            return [ error: "Person was not found!"]
        }
        return [ error: "You must supply the target identity ID" ]
    }
    
    def getNames(String personId,String sor) {
        if(!!personId) {
            def person = Person.get(personId)
            if(!!person) {
                return [ 
                    names: Name.createCriteria().list(sort: "lastUpdated") {
                        eq('person',person)
                        if(!!sor) {
                            def source = Source.findByName(sor.trim())
                            if(!!source) eq('source',source)
                        }
                    }.collect { name ->
                        return name.collectEntries {
                            if(it.key in ['person','source']) {
                                switch(it.key) {
                                    case "person":
                                        return [ personId: it.value.id ]
                                        break
                                    case "source":
                                        return [ source: it.value.name ]
                                        break
                                }
                            }
                            return [ "${it.key}": it.value ]
                        }
                    }
                ]
            }
            return [ error: "Person was not found!"]
        }
        return [ error: "You must supply the person ID" ]
    }
}
