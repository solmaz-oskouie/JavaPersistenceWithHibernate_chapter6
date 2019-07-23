package home.solmaz.JavaPersistenceWithHibernate;

import home.solmaz.JavaPersistenceWithHibernate.config.DaoFactory;
import home.solmaz.JavaPersistenceWithHibernate.entity.BankAccount;
import home.solmaz.JavaPersistenceWithHibernate.entity.BillingDetails;
import home.solmaz.JavaPersistenceWithHibernate.entity.CreditCard;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );

        SessionFactory sessionFactory= DaoFactory.getFactory();
        Session session=sessionFactory.openSession();
        if(session.isConnected() && session.isOpen()){
            BankAccount bankAccount=new BankAccount();
            bankAccount.setAccount("FB346");
            bankAccount.setBankName("Dozd bank");
            bankAccount.setSwift("98Fb346");
            bankAccount.setOwner("Solmaz");

            CreditCard creditCard=new CreditCard();
            creditCard.setCardNumber("6104-3379-2345-0987");
            creditCard.setExpMonth("09");
            creditCard.setExpYear("2020");
            creditCard.setOwner("solmaz");

            BillingDetails billingDetails=new BillingDetails();
            billingDetails.setOwner("Solmaz");

            Transaction transaction= session.beginTransaction();
            session.save(bankAccount);
            session.save(creditCard);
            session.save(billingDetails);
            transaction.commit();
            Optional<List<BillingDetails>> billingDetailsList= Optional.empty();
            List<BankAccount> bankAccountList=new ArrayList<>();
            List<CreditCard> creditCardList=new ArrayList<>();
            try{
                billingDetailsList=Optional.of(
                        session.createQuery("select bd from BillingDetails bd").getResultList());

            }catch (NoResultException ex){
                System.out.println("hghghg");

            }finally {
                session.close();
            }
            billingDetailsList.ifPresent(
                  myBillingDetails -> {
                      for (BillingDetails item:myBillingDetails) {
                          if(item instanceof CreditCard){
                              creditCardList.add((CreditCard) item);
                          }else if(item instanceof BankAccount){
                              bankAccountList.add((BankAccount) item);
                          }

                      }
                  }
            );




        }
    }


}
