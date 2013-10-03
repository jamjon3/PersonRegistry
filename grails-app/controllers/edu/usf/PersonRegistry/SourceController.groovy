package edu.usf.PersonRegistry

import grails.converters.*

class SourceController {
    def sourceService
    def addSource() {
        withFormat {
            html {
                return sourceService.addSource(params.name)
            }
            xml {
                render sourceService.addSource(params.name) as XML
            }
            json {
                JSON.use("deep") { render sourceService.addSource(params.name) as JSON }
            }
        }                    
    }
    def getSource() {
        withFormat {
            html {
                return sourceService.getSource(params.name)
            }
            xml {
                render sourceService.getSource(params.name) as XML
            }
            json {
                JSON.use("deep") { render sourceService.getSource(params.name) as JSON }
            }
        }                    
    }
}
