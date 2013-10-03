package edu.usf.PersonRegistry

class Name {
    Date dateCreated
    Date lastUpdated
    String prefix = ""
    String first = ""
    String middle = ""
    String last = ""
    String suffix = ""
        
    static hasOne = [ 
        person: Person,
        source: Source
    ]
    static mapping = {
        autoTimestamp true
    }
    static constraints = {
        prefix(nullable:false,blank:true)
        first(nullable:false,blank:true)
        middle(nullable:false,blank:true)
        last(nullable:false,blank:true)
        suffix(nullable:false,blank:true)        
    }
    def beforeInsert() {
        if(!!!prefix) prefix = ""
        if(!!!first) first = ""
        if(!!!middle) middle = ""
        if(!!!last) last = ""
        if(!!!suffix) suffix = ""
    }
    def beforeUpdate() {
        if(!!!prefix) prefix = ""
        if(!!!first) first = ""
        if(!!!middle) middle = ""
        if(!!!last) last = ""
        if(!!!suffix) suffix = ""
    }    

}
