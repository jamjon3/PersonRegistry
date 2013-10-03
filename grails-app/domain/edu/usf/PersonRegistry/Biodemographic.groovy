package edu.usf.PersonRegistry

class Biodemographic {
    Date dateCreated
    Date lastUpdated
    Date birthDate
    Date deathDate
    String gender = "O"

    static hasOne = [ 
        person: Person,
        source: Source
    ]
    static mapping = {
        autoTimestamp true
    }
    static constraints = {
        birthDate(nullable:true)
        deathDate(nullable:true)
        gender(inList:['M','F','O'],nullable:false,blank:false)        
    }
}
