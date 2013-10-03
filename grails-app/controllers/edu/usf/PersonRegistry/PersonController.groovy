package edu.usf.PersonRegistry

import grails.converters.*

class PersonController {
    def personService
    def addPerson() {
        withFormat {
            html {
                return personService.addPerson()
            }
            xml {
                render personService.addPerson() as XML
            }
            json {
                JSON.use("deep") { render personService.addPerson() as JSON }
            }
        }                    
    }

}
