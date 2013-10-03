package edu.usf.PersonRegistry

class IdentifierType {
    String name
    String validator = ""
    static hasMany = [identifiers:Identifier]
    static constraints = {
        name(   
                blank: false,
                nullable: false,
                size: 3..255,
                unique: true,
                //Custom constraint - only allow upper, lower, digits, dash and underscore
                validator: { val, obj -> val ==~ /[A-Za-z0-9_.-]+/ }
        )
        validator(blank:true,nullable:false)
    }
}
