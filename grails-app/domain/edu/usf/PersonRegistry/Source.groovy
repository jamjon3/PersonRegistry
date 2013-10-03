package edu.usf.PersonRegistry

class Source {
    String name
    static hasMany = [
        identifiers:Identifier,
        names: Name
    ]
    static constraints = {
        name(   
                blank: false,
                nullable: false,
                size: 3..255,
                unique: true,
                //Custom constraint - only allow upper, lower, digits, dash and underscore
                validator: { val, obj -> val ==~ /[A-Za-z0-9_.-]+/ }
        )
    }
    
}
