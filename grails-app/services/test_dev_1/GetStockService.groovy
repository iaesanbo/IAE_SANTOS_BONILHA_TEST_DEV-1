package test_dev_1

import grails.gorm.transactions.Transactional

import test_dev_1.Company
import test_dev_1.Stock
import grails.gorm.transactions.Transactional
import groovy.time.TimeCategory

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.concurrent.TimeUnit

@Transactional
class GetStocksService {

    List<Stock>  getStocks(String name, int hours) {

        long initialTime = System.currentTimeMillis()
        Company company = Company.findByName(name)
        Date date = new Date()
        Date targetDate = new Date(date.getTime() - TimeUnit.HOURS.toMillis(hours)) //targetDate e setada para hours horas antes de hoje
        List<Stock>stocks = Stock.all.findAll{ //buscando todas as acoes da compania company com data mais recente que targetDate
            it.company == company && it.priceDate > targetDate
        }
        DateFormat formatter =new SimpleDateFormat("dd MMM yyyy HH:mm")
        for(stock in stocks)
            System.out.print("Company: "+stock.company.name+" Price: "+stock.price+" Date: "+ formatter.format(stock.priceDate)+"\n")
        System.out.println("This service took "+(System.currentTimeMillis()-initialTime)+" ms to be executed."+"\n")
        System.out.println("Quantity of stocks retrieved: "+stocks.size()+"\n")
        stocks
    }
}
