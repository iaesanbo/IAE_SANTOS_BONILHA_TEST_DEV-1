package test_dev_1

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class StockSpec extends Specification implements DomainUnitTest<Stock> {

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
        expect:"fix me"
            true == false
    }
}
