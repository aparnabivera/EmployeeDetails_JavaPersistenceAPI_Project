package io.phoenix;

import org.hibernate.annotations.Columns;
import org.hibernate.annotations.Generated;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity   //JPA interphase
@Table(name = "EMPLOYEE_DATA")
@NamedQuery(query = "select e from Employee e order by e.name", name="emp name asc")
@NamedQuery(query = "select e from Employee e where e.age > :age order by e.name", name="emp age greater")
public class Employee {

    @Id
    @GeneratedValue //    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    //@Basic
    @Column(name="Emaployee_name",  length = 100)
    private String name;

    @Column(unique = true,length = 10, nullable = false)// says value is unique
    private String ssn;

    @Temporal(TemporalType.DATE)
    private Date date;

    @Enumerated(EnumType.STRING)
    private EmployeeType type;

    @Transient   // to tell jpa that this should not be saved in db
    private String debugString;

    @OneToOne(fetch = FetchType.LAZY)   // it links with the primary key of AccessCard ; makes this field a foriegn key(card_id)
    private AccessCard card;

    @OneToMany(mappedBy = "employee" ,cascade = CascadeType.REMOVE)  //allows you to set what the cascading nature of the operations should be, //whenever the employee is removed delete the paystub)
    private List<PayStub> payStub = new ArrayList<PayStub>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "EMAIL_GROUP_SUBSCRIPTIONs",        //To customize name of the table created during ManyToMany relation
    joinColumns = @JoinColumn(name="EMPLOYEE_ID"),       //To customize the name of column of JoinTable
    inverseJoinColumns = @JoinColumn(name = "SUBSCRIPTION_EMAIL_ID"))     // to customize name of column of the other member of JoinTable
    private List<EmailGroup> emailGroups = new ArrayList<EmailGroup>();
    public AccessCard getCard() {
        return card;
    }

    public void setCard(AccessCard card) {
        this.card = card;
    }
/*private int accessCardId;

    public int getAccessCardId() {
        return accessCardId;
    }

    public void setAccessCardId(int accessCardId) {
        this.accessCardId = accessCardId;
    }*/

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public EmployeeType getType() {
        return type;
    }

    public void setType(EmployeeType type) {
        this.type = type;
    }

    public List<PayStub> getPayStub() {
        return payStub;
    }

    public void setPayStub(List<PayStub> payStub) {
        this.payStub = payStub;
    }

    public List<EmailGroup> getEmailGroups() {
        return emailGroups;
    }

    public void setEmailGroups(List<EmailGroup> emailGroups) {
        this.emailGroups = emailGroups;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", ssn='" + ssn + '\'' +
                ", date=" + date +
                ", type=" + type +
                ", debugString='" + debugString + '\'' +
                ", card=" + card +
                '}';
    }

    public void addPayStub(PayStub payStub)
    {
        this.payStub.add(payStub);
    }

    public void addEmailSubscription(EmailGroup group)
    {
        this.emailGroups.add(group);
    }
}
