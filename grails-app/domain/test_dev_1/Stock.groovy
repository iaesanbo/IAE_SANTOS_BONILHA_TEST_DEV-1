package test_dev_1

class Stock {

    float price
    Date priceDate
    Company company

    static belongsTo = [company: Company]
    @SuppressWarnings('GroovyAssignabilityCheck')
    static constraints = {
        price min: 0.0f
        company nullable : false
    }
}
