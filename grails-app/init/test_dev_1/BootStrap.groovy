package test_dev_1

import java.text.SimpleDateFormat
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter;



class BootStrap {

    CompanyService companyService
    def init = { servletContext ->
        def companies
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm")
        Random random = new Random()
        companyService.save(new Company('Toyota', "Vehicles"))
        companyService.save(new Company('Honda', "Vehicles"))
        companyService.save(new Company('Hyundai', "Vehicles"))
        LocalDate time = LocalDate.now()
        companies = Company.list() //Lista de companias para ser iterada durante a insercao de acoes

        for (int day = 0; day < 30; day++) {//Para cada um dos 30 dias. Dias sao subtraidos da data pois estamos criando acoes para os dias anteriores a hoje.
            DayOfWeek daOfWeek = time.minusDays(day+1).getDayOfWeek()//checa o dia da semana
            if (daOfWeek != DayOfWeek.SATURDAY && daOfWeek != DayOfWeek.SUNDAY) {//Se nao for sabado ou domingo
                for (int hour = 10; hour < 18; hour++) {//para cada uma das horas do expediente
                    for (int minute = 0; minute < 60; minute++) {//para cada minuto de cada hora
                        for (company in companies) {
                            String priceDate = time.minusDays(day+1).atTime(hour,minute).format(formatter) //cria uma string contendo a data com o offset apropriado
                            float randomValue = 0.01 + (500.00 - 0.01) * random.nextFloat() //cria um valor aleatorio para o preco da acao
                            Stock newStock = new Stock()
                            newStock.price = randomValue
                            newStock.company = company
                            newStock.priceDate = new SimpleDateFormat("dd MMM yyyy HH:mm").parse(priceDate) //converte a string em uma Date para ser inserida no campo da acao
                            company.quotes.add(newStock)//adiciona a acao a lista de acoes da compania company
                        }
                    }
                }
            }
        }
        for (company in companies)
            companyService.save(company)//Salva o estado de cada uma das companies. Por as acoes possuerem uma relacao de belongTo com a compania, elas serao salvas em cascata.

    }
    def destroy = {
    }
}
