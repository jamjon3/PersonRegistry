package edu.usf.PersonRegistry

class Person {
    String id
    static hasMany = [
        identifiers:Identifier,
        names:Name,
        nickNames:NickName,
        biodemographics:Biodemographic
    ]
    static mapping = {
        id generator: 'uuid'
    }
    static constraints = {
    }
}
