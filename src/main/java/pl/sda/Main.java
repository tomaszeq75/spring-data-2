package pl.sda;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Repository;
import pl.sda.config.ApplicationConfiguration;
import pl.sda.model.AccountEntity;
import pl.sda.model.PersonEntity;
import pl.sda.repository.AccountRepository;
import pl.sda.repository.PersonRepository;
import pl.sda.service.MoneyTransferExecutor;

import java.math.BigDecimal;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
        Arrays.asList(context.getBeanDefinitionNames()).forEach(System.out::println);

        PersonRepository personRepository = context.getBean(PersonRepository.class);
        AccountRepository accountRepository = context.getBean(AccountRepository.class);


//        personRepository.save(PersonEntity.builder().firstName("Jarek").lastName("Nowak").pesel("121").build());
//        personRepository.save(PersonEntity.builder().firstName("Marek").lastName("Kwak").pesel("122").build());
//        personRepository.save(PersonEntity.builder().firstName("Arek").lastName("Zswak").pesel("124").build());

        savePersons(personRepository);

        personRepository.findAll().forEach(System.out::println);

        System.out.println("-------------");
        System.out.println(personRepository.findByFirstName("Jarek"));
        System.out.println(personRepository.findByLastName("Nowak"));
        System.out.println(personRepository.findByFirstNameAndLastName("Jarek", "Kwak"));

        System.out.println("-------------");
        MoneyTransferExecutor moneyTransferExecutor = context.getBean(MoneyTransferExecutor.class);
        moneyTransferExecutor.send("1000", "2000", BigDecimal.valueOf(100));
        personRepository.findAll().forEach(System.out::println);


    }

    private static void savePersons(PersonRepository personRepository) {
        personRepository.deleteAll();


        AccountEntity account1 = AccountEntity.builder().accountNumber("1000").state(BigDecimal.valueOf(2000)).build();
        AccountEntity account2 = AccountEntity.builder().accountNumber("2000").state(BigDecimal.valueOf(3000)).build();

        PersonEntity personEntity1 = PersonEntity.builder().firstName("Marcin").lastName("Nowak").pesel("123").account(account1).build();
        PersonEntity personEntity2 = PersonEntity.builder().firstName("Jarek").lastName("Kwak").pesel("124").account(account2).build();

        personRepository.save(personEntity1);
        personRepository.save(personEntity2);
    }
}
