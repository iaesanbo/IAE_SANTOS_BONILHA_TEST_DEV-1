package test_dev_1

class Company {

    String name
    String segment
    Set quotes = []
    static hasMany = [quotes : Stock]

    static constraints = {
        name nullable : false, blank : false, maxSize : 255
        segment nullable : false, blank : false

    }

    public Company(String name, String segment)
    {
        this.name = name
        this.segment = segment
    }
}
