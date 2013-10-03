package edu.usf.PersonRegistry

class BiodemographicService {
    static transactional = true
    def grailsApplication
    def addBiodemographic(def biodemographic,String personId,String sor) {
        if(!!personId) {
            biodemographic.person = Person.get(personId)
            if(!!biodemographic.person) {
                if(!!sor) {
                    biodemographic.source = Source.findByName(sor.trim())
                    if(!!biodemographic.source) {
                        def biodemographicExists = Biodemographic.find(biodemographic as Biodemographic)
                        if(!!!biodemographicExists) {
                            biodemographicExists = biodemographic as Biodemographic
                            if(!biodemographicExists.save(failOnError:false, flush: true, insert: true, validate: true)) {
                                return [ error : "'${biodemographicExists.errors.fieldError.field}' value '${biodemographicExists.errors.fieldError.rejectedValue}' rejected" ]
                            } else {
                                return [ 
                                    biodemographic: biodemographic.collectEntries {
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
                        return [ error: "Biodemographic already exists for this person and source of record"]
                    }
                }
                return [ error: "You must supply the name of the source of record (SOR)"]
            }
            return [ error: "Person was not found!"]
        }
        return [ error: "You must supply the target identity ID" ]
    }
    
    def getBiodemographics(String personId,String sor) {
        if(!!personId) {
            def person = Person.get(personId)
            if(!!person) {
                return [ 
                    biodemographics: Biodemographic.createCriteria().list(sort: "lastUpdated") {
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
