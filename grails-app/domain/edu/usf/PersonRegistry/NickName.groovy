package edu.usf.PersonRegistry

class NickName {
    String name
    static constraints = {
        name(blank: false,nullable: false,size: 1..255,unique: true)
    }
}
